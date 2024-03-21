package com.example.sd40.controller.TaiKhoan;

import com.example.sd40.repuest.KhachHangRequest;
import com.example.sd40.repuest.NhanVienRequyest;
import com.example.sd40.service.TaiKhoan.KhachHangService;
import com.example.sd40.service.TaiKhoan.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author quynh
 */
@Controller
@RequestMapping("/customer")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;


    @GetMapping("/index")
    public String hienThiNhanVien(Model md){
        md.addAttribute("khachHangs",khachHangService.findByKhachHang());
        md.addAttribute("view","/TaiKhoan/KhachHang/ViewKhachHang.jsp");
        return "index";
    }


    @GetMapping("/view-create")
    public String hienThiCreateKhachHang(Model md){
        md.addAttribute("view","/TaiKhoan/KhachHang/create.jsp");
        return "index";
    }

    @GetMapping("/view-update/{id}")
    public String hienThiUpdateKhachHang(@PathVariable("id") Long id, Model md){
        md.addAttribute("nhanVien",khachHangService.detail(id));
        md.addAttribute("view","/TaiKhoan/KhachHang/update.jsp");
        return "index";
    }

    @PostMapping("/update")
    public String updateKhachHang(KhachHangRequest requyest){
        khachHangService.update(requyest);
        return "redirect:/customer/index";
    }

    @PostMapping("/create")
    public String createKhachHang(KhachHangRequest requyest){
        khachHangService.create(requyest);
        return "redirect:/customer/index";
    }

}

