package com.example.sd40.controller;

import com.example.sd40.entity.Bill;
import com.example.sd40.service.serviceimpl.BillDetailServiceImpl;
import com.example.sd40.service.serviceimpl.BillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class StatisticsController {
    @Autowired
    private BillServiceImpl billService;
    @Autowired
    private BillDetailServiceImpl billDetailService;

    @GetMapping("/statisticsResult")
    public String getTotalRevenueByDateAll(
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String endDate,
            Model model) {

        if (startDate == null || endDate == null) {
            // Nếu không có ngày bắt đầu và kết thúc được truyền, sử dụng khoảng mặc định (1 tháng trước đến hiện tại)
            LocalDate defaultEndDate = LocalDate.now();
            LocalDate defaultStartDate = defaultEndDate.minusMonths(1);
            startDate = String.valueOf(Date.valueOf(defaultStartDate));
            endDate = String.valueOf(Date.valueOf(defaultEndDate));
        }

        Date startDate1 = Date.valueOf(startDate);
        Date endDate1 = Date.valueOf(endDate);
        List<Bill> allByDate = billService.get_all_by_Date(startDate1, endDate1);
        BigDecimal total = BigDecimal.ZERO;
        for (Bill billTotal : allByDate
        ) {
            total = total.add(billTotal.getTotalMoney());
        }
        List<Object[]> allByDateProDuct = billDetailService.get_all_product_sale_by_Date(startDate1, endDate1);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("startDateProduct", startDate);
        model.addAttribute("endDateProduct", endDate);
        model.addAttribute("statisticsResult", allByDate);
        model.addAttribute("statisticsResultProduct", allByDateProDuct);
        model.addAttribute("total", total);
        model.addAttribute("view", "/Statistics/index.jsp");

        return "index";
    }

    @GetMapping("/statisticsResultBill")
    public String getTotalRevenueByDateRange(
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String endDate,
            @RequestParam(name = "startDateProduct", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDateProduct,
            @RequestParam(name = "endDateProduct", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDateProduct,

            Model model) {

        if (startDate == null || endDate == null) {
            // Nếu không có ngày bắt đầu và kết thúc được truyền, sử dụng khoảng mặc định (1 tháng trước đến hiện tại)
            LocalDate defaultEndDate = LocalDate.now();
            LocalDate defaultStartDate = defaultEndDate.minusMonths(1);
            startDate = String.valueOf(Date.valueOf(defaultStartDate));
            endDate = String.valueOf(Date.valueOf(defaultEndDate));
        }

        Date startDate1 = Date.valueOf(startDate);
        Date endDate1 = Date.valueOf(endDate);
        List<Bill> allByDate = billService.get_all_by_Date(startDate1, endDate1);
        List<Object[]> allByDatePro = billDetailService.get_all_product_sale_by_Date(startDateProduct, endDateProduct);

        BigDecimal total = BigDecimal.ZERO;
        for (Bill billTotal : allByDate
        ) {
            total = total.add(billTotal.getTotalMoney());
        }
        model.addAttribute("startDateProduct", startDateProduct);
        model.addAttribute("endDateProduct", endDateProduct);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("startDateProduct", startDateProduct);
        model.addAttribute("endDateProduct", endDateProduct);
        model.addAttribute("statisticsResultProduct", allByDatePro);
        model.addAttribute("statisticsResult", allByDate);
        model.addAttribute("total", total);
        model.addAttribute("view", "/Statistics/index.jsp");

        return "index";
    }


    @GetMapping("/statisticsResultProduct")
    public String getProductSalesData(@RequestParam(name = "startDateProduct", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String startDate,
                                      @RequestParam(name = "endDateProduct", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String endDate,
                                      Model model) {
        if (startDate == null || endDate == null) {
            // Nếu không có ngày bắt đầu và kết thúc được truyền, sử dụng khoảng mặc định (1 tháng trước đến hiện tại)
            LocalDate defaultEndDate = LocalDate.now();
            LocalDate defaultStartDate = defaultEndDate.minusMonths(1);
            startDate = String.valueOf(Date.valueOf(defaultStartDate));
            endDate = String.valueOf(Date.valueOf(defaultEndDate));
        }

        Date startDate1 = Date.valueOf(startDate);
        Date endDate1 = Date.valueOf(endDate);
        List<Object[]> allByDate = billDetailService.get_all_product_sale_by_Date(startDate1, endDate1);
        BigDecimal total = BigDecimal.ZERO;

        model.addAttribute("startDateProduct", startDate1);
        model.addAttribute("endDateProduct", endDate1);
        model.addAttribute("statisticsResultProduct", allByDate);
        model.addAttribute("total", total);
        model.addAttribute("view", "/Statistics/index.jsp");

        return "index";
    }
}



