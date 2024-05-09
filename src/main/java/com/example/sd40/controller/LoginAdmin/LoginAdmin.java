package com.example.sd40.controller.LoginAdmin;

import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.NhanVien.NhanVien;
import com.example.sd40.service.NhanVien.NhanVienService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class LoginAdmin {

    @Autowired
    NhanVienService nhanVienService;

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("nhanVien");
        return "/login";
    }

    @GetMapping("/index")
    public String index(HttpSession session,Model model){
        NhanVien nhanVien = (NhanVien) session.getAttribute("nhanVien");
        if (nhanVien == null){
            return "redirect:/admin/login";
        }else {
            model.addAttribute("nhanvien",nhanVien);
            return "/index";

        }

    }

    @PostMapping("/loginOK")
    public ResponseEntity<?> loginOK(
            @RequestParam("taiKhoan")String taiKhoan,
            @RequestParam("matKhau")String matKhau, HttpSession session
            ) {
        try {
            NhanVien nhanVien = nhanVienService.login(taiKhoan,matKhau);
            if (nhanVien == null){
                return ResponseEntity.ok("ko");
            }else {
                session.setAttribute("nhanVien",nhanVien);
                return ResponseEntity.ok("ok");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi");
        }
    }
}
