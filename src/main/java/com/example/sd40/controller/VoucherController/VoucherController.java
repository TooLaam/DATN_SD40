package com.example.sd40.controller.VoucherController;


import com.example.sd40.entity.Voucher.Voucher;
import com.example.sd40.service.Voucher.VoucherService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Voucher> detail(Model model,
                         @PathVariable("id")Long id){
        Voucher vouchers = voucherService.detail(id);
        if (vouchers != null) {
            return new ResponseEntity<>(vouchers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    Date currentDate = new Date(System.currentTimeMillis());

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id")String id,
                                    @RequestParam("ten")String  ten,
                                    @RequestParam("ngayBatDau") Date ngayBatDau,
                                    @RequestParam("ngayKetThuc")Date ngayKetThuc,
                                    @RequestParam("soLuong")Integer soLuong,
                                    @RequestParam("phanTramGiam")Integer phanTramGiam,
                                    @RequestParam("giamToiDa") BigDecimal giamToiDa,
                                    @RequestParam("giaTriDonToiThieu")BigDecimal giaTriDonToiThieu,
                                    @RequestParam("trangThai")Integer trangThai,
                                    Model model
    ) throws ParseException {
        List<Voucher> vouchers = voucherService.findByNameUpdate(ten, Long.valueOf(id));
        if (!vouchers.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        } else {

            Voucher voucher = voucherService.detail(Long.valueOf(id));

            voucher.setTenVoucher(ten);
            voucher.setTrangThai(trangThai);
            voucher.setNgaySua(currentDate);
            voucher.setGiamToiDa(giamToiDa);
            voucher.setGiaTriDonToiThieu(giaTriDonToiThieu);
            voucher.setSoLuong(soLuong);
            voucher.setPhanTramGiam(phanTramGiam);
            voucher.setNgayBatDau(ngayBatDau);
            voucher.setNgayKetThuc(ngayKetThuc);
            voucherService.addVoucher(voucher);

            return ResponseEntity.ok("ok");
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(
                                    @RequestParam("ten")String  ten,
                                    @RequestParam("ngayBatDau") Date ngayBatDau,
                                    @RequestParam("ngayKetThuc")Date ngayKetThuc,
                                    @RequestParam("soLuong")Integer soLuong,
                                    @RequestParam("phanTramGiam")Integer phanTramGiam,
                                    @RequestParam("giamToiDa") BigDecimal giamToiDa,
                                    @RequestParam("giaTriDonToiThieu")BigDecimal giaTriDonToiThieu,
                                    @RequestParam("trangThai")Integer trangThai,
                                    Model model
    ) throws ParseException {
        List<Voucher> vouchers = voucherService.findByName(ten);
        if (!vouchers.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        } else {

            Voucher voucher = new Voucher();
            voucher.setTenVoucher(ten);
            voucher.setTrangThai(trangThai);
            voucher.setNgaySua(currentDate);
            voucher.setMaVoucher("Voucher"+(voucherService.getAll().size()+1));
            voucher.setGiamToiDa(giamToiDa);
            voucher.setGiaTriDonToiThieu(giaTriDonToiThieu);
            voucher.setSoLuong(soLuong);
            voucher.setPhanTramGiam(phanTramGiam);
            voucher.setNgayBatDau(ngayBatDau);
            voucher.setNgayKetThuc(ngayKetThuc);
            voucherService.addVoucher(voucher);

            return ResponseEntity.ok("ok");
        }
    }
}
