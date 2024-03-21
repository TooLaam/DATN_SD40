package com.example.sd40.controller.BanHangController;

import com.example.sd40.service.SanPham.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product_detail")
public class DetailSanPhamController {
    @Autowired
    SanPhamService spSer;

    @GetMapping("/chiTiet/{id}")
    public String sanPhamDetail(Model md ,@PathVariable("id") Long id){
        md.addAttribute("view","./detailSanPham/index.jsp");
        md.addAttribute("productDetail",spSer.findTheoId(id));
        System.out.println("hello");
        return "/TrangBanHang/index";
    }
}
