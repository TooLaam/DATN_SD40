package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.service.KhachHang.KhachHangCusService;
import jakarta.servlet.http.HttpSession;
//import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginCusController {
    @Autowired
    KhachHangCusService khachHangCusService;


    @GetMapping("/login")
    public String loginCus(Model model,@RequestParam(defaultValue = "0",value = "idsp")Long idsp,@RequestParam(defaultValue = "0",value = "idms")Long idms,HttpSession session){
        session.setAttribute("idsp",idsp);
        session.setAttribute("idms",idms);
        model.addAttribute("view", "/login/index.jsp");
        return "/customerFE/login/index";
    }
    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("view", "/signup/index.jsp");
        return "/customerFE/signup/index";
    }


    @GetMapping("/logout")
    public String logout(Model model, HttpSession session){
//        model.addAttribute("view", "/signup/index.jsp");
        session.removeAttribute("khachHangCus");
        session.removeAttribute("idKhachHang");
        session.removeAttribute("idsp");
        session.removeAttribute("idms");
        session.removeAttribute("gioHangChiTiets");
        session.removeAttribute("listLong");
        return "redirect:/login";
    }


    @PostMapping("/loginOK")
    public String loginOK(@RequestParam("taiKhoan") String username,
                          @RequestParam("matKhau") String password,
                          HttpSession session,
                          Model model) {

            KhachHang checkLogin = khachHangCusService.login(username, password);
            if (checkLogin != null) {
                Long idsp = (Long) session.getAttribute("idsp");
                Long idms = (Long) session.getAttribute("idms");
                if (idms !=0 && idsp !=0){
                    session.setAttribute("khachHangCus", checkLogin);
                    session.setAttribute("idKhachHang", checkLogin.getId());
                    return "redirect:/hienthiKCCus/"+idsp+"/"+idms;
                }
                if (idsp != 0 && idms == 0) {
                    session.setAttribute("khachHangCus", checkLogin);
                    session.setAttribute("idKhachHang", checkLogin.getId());
                    return "redirect:/detailsanphamcus/"+idsp;
                }
                session.setAttribute("khachHangCus", checkLogin);
                session.setAttribute("idKhachHang", checkLogin.getId());
                Long id = (Long) session.getAttribute("idKhachHang");
                System.out.println(id);
                return "redirect:/home";
            } else {
                model.addAttribute("erCheckCustomer", "Tài khoản hoặc mật khẩu chưa chính xác !!!");
                model.addAttribute("view", "/login/index.jsp");
                return "/customerFE/login/index";
            }
        }



}
