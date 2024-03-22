package com.example.sd40.controller.BanHangController;

import com.example.sd40.service.SanPham.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrangChuController {

@Autowired
SanPhamService sanPhamService;

    @GetMapping("/trangChu")
    public String hienThiTrangChu(Model md){
   //     md.addAttribute("view","./TrangChu/a.jsp");
        md.addAttribute("view","./TrangChu/index.jsp");
        return "/TrangBanHang/index";
    }

    @GetMapping("/product/indexcus")
    public String hienThiSP(Model md){
        md.addAttribute("view","./product/index.jsp");
        md.addAttribute("listGiay",sanPhamService.getList());
        System.out.println(sanPhamService.getList().get(1).getMa());
        return "/TrangBanHang/index";
    }
}
