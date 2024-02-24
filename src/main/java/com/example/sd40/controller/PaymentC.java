package com.example.sd40.controller;

import com.example.sd40.entity.*;
import com.example.sd40.entity.ViewModels.CartDetailView;
import com.example.sd40.service.serviceimpl.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/bill")
public class PaymentC {

    @Autowired
    HttpSession session;
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    CartdetailServiceImpl cartDetailService;
    @Autowired
    ProductServiceimpl productService;
    @Autowired
    BillStatusServiceImpl billStatusService;
    @Autowired
    BillServiceImpl billService;
    @Autowired
    BillDetailServiceImpl billDetailService;
    @Autowired
    ProductDetailServiceimpl productDetailServiceimpl;
    @Autowired
    PaymentServiceImpl paymentService;
    @Autowired
    VoucherServiceImpl voucherService;
    @Autowired
    UsedVoucherServiceImpl usedVoucherService;

    @GetMapping("/payment")
    public String checkout(Model model) {
        if (session.getAttribute("CustomerName") != null) {
            String username = (String) session.getAttribute("CustomerName");
            Customer customer = customerService.getCustomerByName(username);
            var cart = cartService.getOne(customer.getId());
            var lstCartDetailView = cartDetailService.getCartDetailByCustomerId(customer.getId());
            model.addAttribute("customer", customer);
            model.addAttribute("cart", cart);
            model.addAttribute("cartDetail", lstCartDetailView);
        } else {
            Customer customer = new Customer();
            customer.setFullname("");
            customer.setPhone("");
            customer.setAddress("");
            Cart cartSession = (Cart) session.getAttribute("Cart");
            ArrayList<CartDetailView> cartDetailSession = (ArrayList<CartDetailView>) session.getAttribute("CartDetail");

            model.addAttribute("customer", customer);
            model.addAttribute("cart", cartSession);
            model.addAttribute("cartDetail", cartDetailSession);
        }
        model.addAttribute("view", "/pay/pay.jsp");
        return "/customerFE/index";
    }

    @PostMapping("/placeorder")
    public String confirm(Model model,
                          @RequestParam("receiverName") String receiverName,
                          @RequestParam("customerPhone") String customerPhone,
                          @RequestParam("PaymentId") UUID PaymentId,
                          @RequestParam("addressDelivery") String addressDelivery,
                          @RequestParam("code") String code,
                          RedirectAttributes redirectAttributes
    ) {
        boolean hasError = false; // Biến kiểm tra có lỗi nào xảy ra

        if (receiverName.isEmpty() || !receiverName.matches("^[a-zA-Z\\p{IsAlphabetic} ]+$")) {
            redirectAttributes.addFlashAttribute("receiverNameError", "Invalid Receiver Name (must not be blank and must only contain letters and spaces)");
            hasError = true; // Đặt biến lỗi thành true
        } else {
            redirectAttributes.addAttribute("inputReceiverName", receiverName); // Giữ nguyên giá trị người dùng
        }

        if (customerPhone.isEmpty() || !customerPhone.matches("^\\d+$")) {
            redirectAttributes.addFlashAttribute("customerPhoneError", "Invalid Customer Phone (must not be blank and must be numeric)");
            hasError = true; // Đặt biến lỗi thành true
        }else {
            redirectAttributes.addAttribute("inputCustomerPhone", customerPhone); // Giữ nguyên giá trị người dùng
        }

        if (addressDelivery.isEmpty()) {
            redirectAttributes.addFlashAttribute("addressDeliveryError", "Address Delivery is required");
            hasError = true; // Đặt biến lỗi thành true
        }else {
            redirectAttributes.addAttribute("inputAddressDelivery", addressDelivery); // Giữ nguyên giá trị người dùng
        }

        if (hasError) {
            return "redirect:/bill/payment"; // Chuyển hướng đến trang thanh toán
        }

        Date currentDate = new Date(System.currentTimeMillis());

        if (!code.isEmpty()) {
            if (voucherService.getByCode(code) == null){
                redirectAttributes.addFlashAttribute("codeError", "Enter a valid discount code or gift card");
                return "redirect:/bill/payment"; // Chuyển hướng đến trang thanh toán
            }
            else if(voucherService.getByCode(code).getTimeStart().compareTo(currentDate) > 0){
                redirectAttributes.addFlashAttribute("codeError", "Not eligible to use Discount Code");
                return "redirect:/bill/payment"; // Chuyển hướng đến trang thanh toán
            }
            else if(voucherService.getByCode(code).getTimeEnd().compareTo(currentDate) < 0){
                redirectAttributes.addFlashAttribute("codeError", "Discount code has expired");
                return "redirect:/bill/payment"; // Chuyển hướng đến trang thanh toán
            }
        }

        if (session.getAttribute("CustomerName") != null) {
            String username = (String) session.getAttribute("CustomerName");
            Customer customer = customerService.getCustomerByName(username);
            Cart cart = cartService.getOne(customer.getId());
            ArrayList<CartDetailView> lstCartDetailView = cartDetailService.getCartDetailByCustomerId(customer.getId());
            Bill bill = new Bill();

            bill.setId(UUID.randomUUID());
            bill.setReceiverName(receiverName);
            bill.setCustomerPhone(customerPhone);
            bill.setAddressDelivery(addressDelivery);
            bill.setCreatedDate(currentDate);
            bill.setCustomer(customer);
            bill.setPayment(paymentService.getOne(PaymentId));
            bill.setBillStatus(billStatusService.findById(UUID.fromString("259b8bc3-5489-47c0-a115-b94a0cf6286f")));

            Bill b;

            if (code.isEmpty()){
                bill.setTotalMoney(cart.getTotalMoney());

                b = billService.create_new_bill(bill);
            }
            else{
                bill.setTotalMoney(cart.getTotalMoney().subtract(voucherService.getByCode(code).getValue()));

                b = billService.create_new_bill(bill);

                UsedVoucher usedVoucher = new UsedVoucher();
                usedVoucher.setId(UUID.randomUUID());
                usedVoucher.setSubTotal(cart.getTotalMoney());
                usedVoucher.setBill(b);
                usedVoucher.setVoucher(voucherService.getByCode(code));
                usedVoucherService.add(usedVoucher);
            }

            // Tạo danh sách Chi Tiết Hóa Đơn
            for (var lstItem : lstCartDetailView) {
                Product product = productService.getOne(lstItem.getProductId());
                product.setAvailableQuantity(product.getAvailableQuantity() - lstItem.getQuantity());
                product.setSold(product.getSold() + lstItem.getQuantity());

                BillDetail billDetail = new BillDetail();
                billDetail.setId(UUID.randomUUID());
                billDetail.setQuantity(lstItem.getQuantity());
                billDetail.setPrice(lstItem.getPrice());
                billDetail.setBill(b);
                billDetail.setProductDetail(productDetailServiceimpl.getOne(lstItem.getProductDetailId()));

                // Lưu Chi Tiết Hóa Đơn vào cơ sở dữ liệu
                billDetailService.create_new_billdetail(billDetail);
                productService.update(product.getId(), product);
                cartDetailService.delete(lstItem.getId());
            }

            cart.setQuantity(0);
            cart.setTotalMoney(BigDecimal.ZERO);
            cart.setStatus(1);
            cartService.update(cart.getId(), cart);
            // Truyền tham số qua RedirectAttributes
            redirectAttributes.addAttribute("billId", b.getId());
        } else {
            Cart cartSession = (Cart) session.getAttribute("Cart");
            ArrayList<CartDetailView> cartDetailViewSession = (ArrayList<CartDetailView>) session.getAttribute("CartDetail");
            Customer customer = new Customer();
            Bill bill = new Bill();

            customer.setFullname(receiverName);
            customer.setPhone(customerPhone);
            customer.setAddress(addressDelivery);

            bill.setId(UUID.randomUUID());
            bill.setReceiverName(receiverName);
            bill.setCustomerPhone(customerPhone);
            bill.setAddressDelivery(addressDelivery);
            bill.setCreatedDate(currentDate);
            bill.setPayment(paymentService.getOne(PaymentId));
            bill.setBillStatus(billStatusService.findById(UUID.fromString("259b8bc3-5489-47c0-a115-b94a0cf6286f")));

            Bill b;

            if (code.isEmpty()){
                bill.setTotalMoney(cartSession.getTotalMoney());

                b = billService.create_new_bill(bill);
            }
            else{
                bill.setTotalMoney(cartSession.getTotalMoney().subtract(voucherService.getByCode(code).getValue()));

                b = billService.create_new_bill(bill);

                UsedVoucher usedVoucher = new UsedVoucher();
                usedVoucher.setId(UUID.randomUUID());
                usedVoucher.setSubTotal(cartSession.getTotalMoney());
                usedVoucher.setBill(b);
                usedVoucher.setVoucher(voucherService.getByCode(code));
                usedVoucherService.add(usedVoucher);
            }

            // Tạo danh sách Chi Tiết Hóa Đơn
            for (var lstItem : cartDetailViewSession) {
                Product product = productService.getOne(lstItem.getProductId());
                product.setAvailableQuantity(product.getAvailableQuantity() - lstItem.getQuantity());
                product.setSold(product.getSold() + lstItem.getQuantity());

                BillDetail billDetail = new BillDetail();
                billDetail.setId(UUID.randomUUID());
                billDetail.setQuantity(lstItem.getQuantity());
                billDetail.setPrice(lstItem.getPrice());
                billDetail.setBill(b);
                billDetail.setProductDetail(productDetailServiceimpl.getOne(lstItem.getProductDetailId()));

                // Lưu Chi Tiết Hóa Đơn vào cơ sở dữ liệu
                billDetailService.create_new_billdetail(billDetail);
                productService.update(product.getId(), product);
                CartDetailView cartDetailViewOld = lstItem;
                cartDetailViewSession.remove(cartDetailViewOld);
                if (cartDetailViewSession.size() == 0){
                    break;
                }
            }
            session.invalidate();
            // Truyền tham số qua RedirectAttributes
            redirectAttributes.addAttribute("billId", b.getId());
        }
        return "redirect:/bill/orderComplete/{billId}";
    }

    @GetMapping("/orderComplete/{billId}")
    public String orderComplete(@PathVariable("billId") UUID billId, Model model) {
        model.addAttribute("bill", billService.get_one_bill(billId));
        model.addAttribute("billDetail", billDetailService.getByBillId(billId));
        if(usedVoucherService.getUsedVoucherByBillId(billId) != null){
            model.addAttribute("usedVoucher", usedVoucherService.getUsedVoucherByBillId(billId));
            model.addAttribute("voucher", voucherService.getOne(usedVoucherService.getUsedVoucherByBillId(billId).getVoucher().getId()));
        }
        model.addAttribute("view", "/checkout/index.jsp");
        return "/customerFE/index";
    }
}
