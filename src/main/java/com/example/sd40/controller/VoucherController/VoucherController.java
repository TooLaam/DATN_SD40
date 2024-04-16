package com.example.sd40.controller.VoucherController;


import com.example.sd40.entity.Voucher.Voucher;
import com.example.sd40.service.Voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    VoucherService voucherService;

    @GetMapping("/index")
    public String getAll(Model model){
        model.addAttribute("listVC",voucherService.getAll());
        model.addAttribute("view","/Voucher/index.jsp");
        return "index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id")Long id){
        model.addAttribute("vc",voucherService.detail(id));
        model.addAttribute("listVC",voucherService.getAll());
        model.addAttribute("view","/Voucher/index.jsp");
        return "index";
    }
    Date currentDate = new Date(System.currentTimeMillis());

    @PostMapping("/update")
    public String update(@RequestParam("id")String id,
                         @RequestParam("ten")String  ten,
                         @RequestParam("ngayBatDau") String ngayBatDau,
                         @RequestParam("ngayKetThuc")String ngayKetThuc,
                         @RequestParam("soLuong")Integer soLuong,
                         @RequestParam("phanTramGiam")Integer phanTramGiam,
                         @RequestParam("giamToiDa") BigDecimal giamToiDa,
                         @RequestParam("giaTriDonToiThieu")BigDecimal giaTriDonToiThieu,
                         @RequestParam("trangThai")Integer trangThai,
                         Model model
    ) throws ParseException {
        Date ngayBD = Date.valueOf(ngayBatDau);
        Date ngayKT = Date.valueOf(ngayKetThuc);
        if (id.isBlank()){
            model.addAttribute("err","Bạn phải chọn voucher ở danh sách");
            model.addAttribute("listVC",voucherService.getAll());
            model.addAttribute("view","/Voucher/index.jsp");
            return "index";
        }
        else {
            List<Voucher> vouchers = voucherService.findByNameUpdate(ten,Long.valueOf(id));
            if (!vouchers.isEmpty()){
                model.addAttribute("errUpdate","Trùng tên. Vui lòng chọn tên khác !!!");
                model.addAttribute("vc",voucherService.detail(Long.valueOf(id)));
                model.addAttribute("listVC",voucherService.getAll());
                model.addAttribute("view","/Voucher/index.jsp");
                return "index";
            }

            Voucher voucher = voucherService.detail(Long.valueOf(id));

            voucher.setTenVoucher(ten);
            voucher.setTrangThai(trangThai);
            voucher.setNgaySua(currentDate);
            voucher.setGiamToiDa(giamToiDa);
            voucher.setGiaTriDonToiThieu(giaTriDonToiThieu);
            voucher.setSoLuong(soLuong);
            voucher.setPhanTramGiam(phanTramGiam);
            voucher.setNgayBatDau(ngayBD);
            voucher.setNgayKetThuc(ngayKT);
            voucherService.addVoucher(voucher);

            return ("redirect:/voucher/index");
        }


    }

    @PostMapping("/add")
    public String add(@RequestParam("ten")String  ten,
                      @RequestParam("ngayBatDau") String ngayBatDau,
                      @RequestParam("ngayKetThuc")String ngayKetThuc,
                      @RequestParam("soLuong")Integer soLuong,
                      @RequestParam("phanTramGiam")Integer phanTramGiam,
                      @RequestParam("giamToiDa") BigDecimal giamToiDa,
                      @RequestParam("giaTriDonToiThieu")BigDecimal giaTriDonToiThieu,
                      @RequestParam("trangThai")Integer trangThai,Model model) throws ParseException {
        List<Voucher> vouchers = voucherService.findByName(ten);
        Date ngayBD = Date.valueOf(ngayBatDau);
        Date ngayKT = Date.valueOf(ngayKetThuc);
        if (!vouchers.isEmpty()) {
            model.addAttribute("errName", "Tên thêm mới trùng với trong danh sách. Vui lòng chọn tên mới !!!");
            model.addAttribute("active", "nav-link active");
            model.addAttribute("listKC", voucherService.getAll());
            model.addAttribute("view","/Voucher/index.jsp");
            return "index";
        }
        else {

            Voucher voucher = new Voucher();
            voucher.setTenVoucher(ten);
            voucher.setTrangThai(trangThai);
            voucher.setNgaySua(currentDate);
            voucher.setMaVoucher("Voucher"+(voucherService.getAll().size()+1));
            voucher.setGiamToiDa(giamToiDa);
            voucher.setGiaTriDonToiThieu(giaTriDonToiThieu);
            voucher.setSoLuong(soLuong);
            voucher.setPhanTramGiam(phanTramGiam);
            voucher.setNgayBatDau(ngayBD);
            voucher.setNgayKetThuc(ngayKT);
            voucherService.addVoucher(voucher);

            return ("redirect:/voucher/index");

        }
    }
}
