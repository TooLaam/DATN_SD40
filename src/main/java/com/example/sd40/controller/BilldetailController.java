package com.example.sd40.controller;

import com.example.sd40.entity.BillDetail;
import com.example.sd40.service.serviceimpl.BillDetailServiceImpl;
import com.example.sd40.service.serviceimpl.BillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/bill_detail")
public class BilldetailController {


    @Autowired
    BillDetailServiceImpl billDetailService;
    @Autowired
    BillServiceImpl billService;

    @GetMapping("/index/{billId}")
    public String show_data_bill(Model model,@PathVariable("billId")UUID billId) {
        model.addAttribute("listBill", billService.get_all_bill());
        List<BillDetail> billDetailList = billDetailService.get_all_by_billId(billId);
        double total = 0; // Khởi tạo biến tổng giá trị hóa đơn
        for (BillDetail billDetail : billDetailList) {
            int quantity = billDetail.getQuantity();
            BigDecimal price = billDetail.getProductDetail().getPrice();
            BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(quantity));
            total += totalPrice.doubleValue();
//            System.out.println("price" + price);
            System.out.println("quantity" + quantity);
        }

        System.out.println(total);
        model.addAttribute("billDetailD", billDetailList);
        model.addAttribute("total", total);
        model.addAttribute("view", "/BillDetail/index.jsp");
        return "index";
    }

    @PostMapping("/create_bill_detail")
    public String create_bill(Model model,
//                              @RequestParam("price") BigDecimal price,
                              @RequestParam("quantity") Integer quantity,
                              @RequestParam("billId") UUID billId,
                              @RequestParam("productId") UUID productId
    ) {
        BillDetail billDetail = new BillDetail();
//        billDetail.setPrice(price);
        billDetail.setQuantity(quantity);
        billDetail.setBill(billService.get_one_bill(billId));
//        billDetail.setProduct(productId);
        billDetailService.create_new_billdetail(billDetail);
        return "redirect:";
    }


    @GetMapping("delete_bill_detail")
    public String delete_bill(Model model, @RequestParam("billDetailId") UUID billDetailId) {
        billDetailService.delete_detail(billDetailId);
        return "";
    }
}
