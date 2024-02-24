package com.example.sd40.controller;


import com.example.sd40.entity.*;
import com.example.sd40.service.serviceimpl.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillServiceImpl billService;
    @Autowired
    BillDetailServiceImpl billDetailService;
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    EmployeeServiceImpl employeeService;

    @Autowired
    BillStatusServiceImpl billStatusService;


    @PostMapping("/create_bill")
    public String create_bill(Model model,
                              @RequestParam("price") BigDecimal price,
//                              @RequestParam("receiverName") String receiverrame,
//                              @RequestParam("totalMoney") BigDecimal totalmoney,
//                              @RequestParam("customerPhone") String customerPhone,
//                              @RequestParam("addressDelivery") String addressDelivery,
                              @RequestParam("customerId") UUID Customer,
                              @RequestParam("employeeId") UUID Employee

    ) {
        Date currentDate = new Date(System.currentTimeMillis());
        Bill bill = new Bill();
        bill.setReceiverName(customerService.detail(Customer).getFullname());
        bill.setTotalMoney(BigDecimal.valueOf(3333.0));
        bill.setCustomerPhone(customerService.detail(Customer).getPhone());
        bill.setAddressDelivery(customerService.detail(Customer).getAddress());
//        bill.setReceiverName(receiverrame);
        bill.setCreatedDate(currentDate);
        bill.setBillStatus(billStatusService.findById(UUID.fromString("159b8bc3-5489-47c0-a115-b94a0cf6286f")));
        bill.setCustomer(customerService.detail(Customer));
        bill.setEmployee(employeeService.detail(Employee));
        billService.create_new_bill(bill);

        System.out.println("Ngày hiện tại: " + currentDate);
        return "redirect:/bill/index";
    }

    @PostMapping("/update-bill/{billId}")
    public String update_bill(Model model,
                              @RequestParam("price") BigDecimal price,
                              @RequestParam("receiverName") String receiverrame,
//                              @RequestParam("totalMoney") BigDecimal totalmoney,
//                              @RequestParam("customerPhone") String customerPhone,
//                              @RequestParam("addressDelivery") String addressDelivery,
                              @RequestParam("customerId") UUID Customer,
                              @RequestParam("employeeId") UUID Employee,
                              @PathVariable("billId") UUID billId

    ) {
        Date currentDate = new Date(System.currentTimeMillis());
        Bill bill = billService.get_one_bill(billId);
        bill.setReceiverName(customerService.detail(Customer).getFullname());
        bill.setTotalMoney(BigDecimal.valueOf(90000));
        bill.setCustomerPhone(customerService.detail(Customer).getPhone());
        bill.setAddressDelivery(customerService.detail(Customer).getAddress());
        bill.setReceiverName(receiverrame);
        bill.setCreatedDate(currentDate);
        bill.setBillStatus(billStatusService.findById(UUID.fromString("159b8bc3-5489-47c0-a115-b94a0cf6286f")));
        bill.setCustomer(customerService.detail(Customer));
        bill.setEmployee(employeeService.detail(Employee));
        billService.create_new_bill(bill);
        billService.create_new_bill(bill);

        System.out.println("Ngày hiện tại: " + currentDate);
        return "redirect:/bill/index";
    }

    @GetMapping("/index")
    public String show_data_bill(Model model,HttpSession session) {
        if (session.getAttribute("Name") != null){
            //Nếu đã đăng nhập vào trang index
            String username = (String) session.getAttribute("username");
            String password = (String) session.getAttribute("password");
            Employee checkLogin = employeeService.login(username,password);
            model.addAttribute("empLogin",checkLogin);
            //////
            model.addAttribute("listBill", billService.get_all_bill());
            model.addAttribute("listCustomer", customerService.findAll());
            model.addAttribute("listEmployee", employeeService.findAll());
            model.addAttribute("listBillStatus", billStatusService.get_all_bill_status());
            model.addAttribute("view", "/Bill/index.jsp");
            for (Bill bill : billService.get_all_bill()
            ) {
                System.out.println(bill.getReceiverName());
//            System.out.println(bill.getEmployee().getFullName());
//            System.out.println(bill.getCustomer().getFullname());
                System.out.println(bill.getCustomerPhone());
                System.out.println(bill.getAddressDelivery());
                System.out.println(bill.getTotalMoney());
                System.out.println(bill.getCreatedDate());
                System.out.println(bill.getBillStatus());
            }
            return "index";
        }
        //Nếu chưa đăng nhập thì return về trang logina
        return "login";

    }

    @GetMapping("/indexcus")
    public String show_data_bill_cus(Model model, HttpSession session/*,@PathVariable("idCus")UUID idCus*/) {

        if (session.getAttribute("CustomerName") != null)
        {
            String username = (String) session.getAttribute("CustomerName");
            Customer customer = customerService.getCustomerByName(username);
            model.addAttribute("listCustomer", customer);
            model.addAttribute("listBill", billService.get_all_byCusId(customer.getId()));
            model.addAttribute("listEmployee", employeeService.findAll());
            model.addAttribute("listBillStatus", billStatusService.get_all_bill_status());
        }
        model.addAttribute("view", "/bill/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/index/{billId}")
    public String show_data_bill(Model model, @PathVariable("billId") UUID billId) {
        model.addAttribute("listBill", billService.get_all_bill());
        model.addAttribute("listCustomer", customerService.findAll());
        model.addAttribute("listEmployee", employeeService.findAll());
        model.addAttribute("listBillStatus", billStatusService.get_all_bill_status());
        model.addAttribute("billD", billService.get_one_bill(billId));
        List<BillDetail> billDetailList = billDetailService.get_all_by_billId(billId);
        double allPrice = 0; // Khởi tạo biến tổng giá trị hóa đơn

        System.out.println(allPrice);
        model.addAttribute("billDetailD", billDetailList);
        model.addAttribute("allPrice", allPrice);


        model.addAttribute("view", "/Bill/index.jsp");
        return "index";
    }

    @GetMapping("delete_bill/{billId}")
    public String delete_bill(Model model, @PathVariable("billId") UUID billId) {
        billService.delete_bill(billId);
        return "redirect:/bill/index";
    }

    @PostMapping("change_bill_status/{billId}")
    public String change_bill_status(Model model, @PathVariable("billId") UUID billId) {
        billService.change_bill_status(billId);
        return "redirect:/bill/index";
    }
}
