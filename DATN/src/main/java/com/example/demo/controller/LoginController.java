package com.example.demo.controller;



import com.example.demo.configuation.PrincipalCustom;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.entity.VaiTro;
import com.example.demo.repository.TaiKhoanRepository;
import com.example.demo.service.TaiKhoanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
public class LoginController {

    @Autowired
    TaiKhoanService service;

    @Autowired
    TaiKhoanRepository taiKhoanRepository;



    String emailKiemTra;

    @Autowired
    PasswordEncoder passwordEncoder;

    TaiKhoan userInfoStorage = new TaiKhoan();

    String ranDomMa;

    private PrincipalCustom principalCustom = new PrincipalCustom();

    @GetMapping("/login")
    public String formLogin() {
        if (principalCustom.getCurrentUserNameAdmin() != null) {
            return "redirect:/ban-hang-tai-quay/hoa-don";
        } else if (principalCustom.getCurrentUserNameCustomer() != null) {
            return "redirect:/home";
        } else {
            return "dang-nhap";
        }
    }

    @GetMapping("/login-error")
    public String loginErorr(
            Model model
    ) {
        model.addAttribute("message", "Tài khoản chưa kích hoạt hoặc sai thông tin tài khoản");
        return "dang-nhap";
    }

}
