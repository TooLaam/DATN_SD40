package com.example.sd40.controller.KhachHang;

import com.example.sd40.service.SanPham.CTSPMSHAService;
import com.example.sd40.service.SanPham.CTSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author thangdt
 */
@Controller
@RequestMapping("/client")
public class Home {

    @Autowired
    private CTSPService ctspService;

    @GetMapping
    public String home(Model md){
        md.addAttribute("sanPhams",ctspService.findAll());
        md.addAttribute("view","/home/viewHome.jsp");
        return "/khachHang/index";
    }

    @GetMapping("/detail-product/{id}")
    public String detailProduct(@PathVariable("id") Long id,  Model md){
        md.addAttribute("sanPhams",ctspService.findById(id));
        md.addAttribute("view","/home/viewHome.jsp");
        return "/khachHang/index";
    }

    @GetMapping("/detail-product/{id}")
    public String addProductToCard(@PathVariable("id") Long id,  Model md){
        md.addAttribute("sanPhams",ctspService.findById(id));
        md.addAttribute("view","/home/viewHome.jsp");
        return "/khachHang/index";
    }
}
