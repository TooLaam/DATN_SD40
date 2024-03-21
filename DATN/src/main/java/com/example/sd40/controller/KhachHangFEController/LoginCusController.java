package com.example.sd40.controller.KhachHangFEController;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginCusController {


    @GetMapping("/login")
    public String loginCus(Model model){
        model.addAttribute("view", "/login/index.jsp");
        return "/customerFE/login/index";
    }

//    @PostMapping("/loginOK")
//    public String loginOK(@RequestParam("username") String username,
//                          @RequestParam("password") String password,
//                          HttpSession session,
//                          Model model) {
//        if (username == "" || password == "") {
//            model.addAttribute("erTrongCustomer", "Please enter complete information !!!");
//            return "/customerFE/login/index";
//        } else {
//            Customer checkLogin = customerService.login(username, password);
//            if (!(checkLogin == null)) {
//                session.setAttribute("CustomerName", username);
//                session.setAttribute("CustomerPass", password);
//                return "redirect:/home";
//            } else {
//                model.addAttribute("erCheckCustomer", "Username and password are incorrect");
//                return "/customerFE/login/index";
//            }
//        }
//    }
}
