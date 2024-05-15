package com.example.sd40.controller.KhachHangController;


import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.NhanVien.NhanVien;
import com.example.sd40.service.KhachHang.KhachHangCusService;
import com.example.sd40.service.KhachHang.KhachHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

/**
 * @author quynh
 */
@Controller
@RequestMapping("/khachhang")
public class KhachHangController {
    @Autowired
    KhachHangService khachHangService;
    @Autowired
    KhachHangCusService khachHangCusService;

    @GetMapping("/index")
    public String getAll(Model model, HttpSession session){
        NhanVien nhanVien = (NhanVien) session.getAttribute("nhanVien");
        if (nhanVien == null){
            return "redirect:/admin/login";
        }else {
            model.addAttribute("nhanvien",nhanVien);
            model.addAttribute("listKH",khachHangService.getAll());
            model.addAttribute("view","/KhachHang/index.jsp");
            return "index";
        }

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<KhachHang> detail(@PathVariable("id")Long id){
        KhachHang khachHang = khachHangService.detail(id);
        if (khachHang != null) {
            return new ResponseEntity<>(khachHang, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    Date currentDate = new Date(System.currentTimeMillis());

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id")Long id,
                                    @RequestParam("hoTen")String ten,
                                    @RequestParam("ngaySinh")Date ngaySinh,
                                    @RequestParam("sdt")String sdt,
                                    @RequestParam("gioiTinh")Integer gioiTinh,
                                    @RequestParam("email")String email,
                                    @RequestParam("taiKhoan")String taiKhoan,
                                    @RequestParam("matKhau")String matKhau
    ) throws ParseException {
        List<KhachHang> khachHangs = khachHangService.findByUserNameUpdate(taiKhoan,id);
        List<KhachHang> khachHangs1 = khachHangService.findByEmailUpdate(email,id);
        if (!khachHangs.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        }else if (!khachHangs1.isEmpty()){
            return ResponseEntity.ok("errTrungEmail");
        }
        else {
            KhachHang khachHang = khachHangService.detail(id);

            khachHang.setHoTen(ten);
            khachHang.setNgaySinh(ngaySinh);
            khachHang.setSdt(sdt);
            khachHang.setGioiTinh(gioiTinh);
            khachHang.setEmail(email);
            khachHang.setTaiKhoan(taiKhoan);
            khachHang.setMatKhau(matKhau);
            khachHang.setTrangThai(0);

            khachHangService.save(khachHang);
            return ResponseEntity.ok("ok");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestParam("hoTen")String ten,
            @RequestParam("ngaySinh")Date ngaySinh,
            @RequestParam("sdt")String sdt,
            @RequestParam("gioiTinh")Integer gioiTinh,
            @RequestParam("email")String email,
            @RequestParam("taiKhoan")String taiKhoan,
            @RequestParam("matKhau")String matKhau
    ) throws ParseException {
        Long IDKHCuoi = khachHangService.IdKHCuoi();
        String maKHMoi = "KH00"+(IDKHCuoi+1);

        List<KhachHang> khachHangs = khachHangService.findByUserName(taiKhoan);
        List<KhachHang> khachHangs1 = khachHangService.findByEmal(email);
        if (!khachHangs.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        }else if (!khachHangs1.isEmpty()){
            return ResponseEntity.ok("errTrungEmail");
        } else {
            KhachHang khachHang = new KhachHang();

            khachHang.setMa(maKHMoi);
            khachHang.setHoTen(ten);
            khachHang.setNgaySinh(ngaySinh);
            khachHang.setSdt(sdt);
            khachHang.setGioiTinh(gioiTinh);
            khachHang.setEmail(email);
            khachHang.setTaiKhoan(taiKhoan);
            khachHang.setMatKhau(matKhau);
            khachHang.setTrangThai(0);

            khachHangService.save(khachHang);

            return ResponseEntity.ok("ok");
        }
    }


}

