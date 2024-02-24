package com.example.sd40.controller;

import com.example.sd40.entity.Voucher;
import com.example.sd40.service.serviceimpl.VoucherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

@Controller
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherServiceImpl voucherServiceimpl;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("listVoucher", voucherServiceimpl.getAll());
        model.addAttribute("view", "/Voucher/index.jsp");
        return "index";
    }

    @PostMapping("/add")
    public String add(Model model,
                      @RequestParam(value = "code") String code,
                      @RequestParam(value = "value") BigDecimal value,
                      @RequestParam(value = "timeStart") java.sql.Date timeStart,
                      @RequestParam(value = "timeEnd") java.sql.Date timeEnd,
                      @RequestParam(value = "status") Integer status
    ) throws IOException {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

        Voucher voucher = new Voucher();
        voucher.setId(UUID.randomUUID());
        voucher.setCode(code);
        voucher.setValue(value);
        voucher.setTimeStart(timeStart);
        voucher.setTimeEnd(timeEnd);
        voucher.setCreatedDate(currentDate);
        voucher.setStatus(status);

        voucherServiceimpl.add(voucher);
        return "redirect:/voucher/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model,
                         @PathVariable("id") String id
    ) {
        voucherServiceimpl.delete(UUID.fromString(id));
        return "redirect:/voucher/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") UUID id) {
        model.addAttribute("listVoucher", voucherServiceimpl.getAll());
        model.addAttribute("voucher", voucherServiceimpl.getOne(id));
        model.addAttribute("view", "/Voucher/index.jsp");
        return "index";
    }

    @PostMapping("/update/{id}")
    public String update(Model model,
                         @PathVariable("id") UUID id,
                         @RequestParam(value = "code") String code,
                         @RequestParam(value = "value") BigDecimal value,
                         @RequestParam(value = "timeStart") java.sql.Date timeStart,
                         @RequestParam(value = "timeEnd") java.sql.Date timeEnd,
                         @RequestParam(value = "status") Integer status)
    throws IOException {
        Voucher v = voucherServiceimpl.getOne(id);

        Voucher voucher = new Voucher();
        voucher.setId(v.getId());
        voucher.setCode(code);
        voucher.setValue(value);
        voucher.setTimeStart(timeStart);
        voucher.setTimeEnd(timeEnd);
        voucher.setCreatedDate(v.getCreatedDate());
        voucher.setStatus(status);

        voucherServiceimpl.update(id, voucher);
        return "redirect:/voucher/index";
    }
}
