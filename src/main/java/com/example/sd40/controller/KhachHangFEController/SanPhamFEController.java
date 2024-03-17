package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.service.SanPham.CTSPService;
import com.example.sd40.service.SanPham.LoaiGiayService;
import com.example.sd40.service.SanPham.SanPhamService;
import com.example.sd40.service.SanPham.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SanPhamFEController {
    @Autowired
    ThuongHieuService thuongHieuService;
    @Autowired
    LoaiGiayService loaiGiayService;
    @Autowired
    SanPhamService sanPhamService;
    @Autowired
    CTSPService ctspService;

    @GetMapping("/sanphamcus")
    public String sanPhamCus(Model model){
        model.addAttribute("listTH",thuongHieuService.getAll());
        model.addAttribute("listTL",loaiGiayService.findAll());
        model.addAttribute("listSP",ctspService.getAllSPCus());
        model.addAttribute("tongSLSP",ctspService.tongSLSP());

        model.addAttribute("view", "/product/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/sanphamcus/theloaicus/{idtl}")
    public String sanPhamCusByTL(Model model,
                                 @PathVariable("idtl")Long idTL
                                 ){
        model.addAttribute("listTH",thuongHieuService.getAll());
        model.addAttribute("listTL",loaiGiayService.findAll());
        model.addAttribute("listSP",ctspService.getAllSPCusByTL(idTL));
        model.addAttribute("tongSLSP",ctspService.tongSLSPByTL(idTL));
        model.addAttribute("TL",loaiGiayService.detail(idTL));

        model.addAttribute("view", "/product/theloai.jsp");
        return "/customerFE/index";
    }
    @GetMapping("/sanphamcus/thuonghieucus/{idth}")
    public String sanPhamCusByTH(Model model,
                                 @PathVariable("idth")Long idTh
    ){
        model.addAttribute("listTH",thuongHieuService.getAll());
        model.addAttribute("listTL",loaiGiayService.findAll());
        model.addAttribute("listSP",ctspService.getAllSPCusByTH(idTh));
        model.addAttribute("tongSLSP",ctspService.tongSLSPByTH(idTh));
        model.addAttribute("TH",thuongHieuService.detail(idTh));

        model.addAttribute("view", "/product/thuonghieu.jsp");
        return "/customerFE/index";
    }
}
