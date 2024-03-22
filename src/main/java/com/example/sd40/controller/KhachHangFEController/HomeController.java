package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.service.SanPham.SanPhamService;
import com.example.sd40.service.SanPham.ThuongHieuService;
import com.example.sd40.service.TaiKhoan.KhachHangCusService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    ThuongHieuService thuongHieuService;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    KhachHangCusService khachHangCusService;

    @GetMapping("/home" )
    public String home(Model model, HttpSession session){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null){
            model.addAttribute("listTH",thuongHieuService.listTLHome());
            model.addAttribute("listSPNew",sanPhamService.findAllByOrderByNgayTaoDesc());
            model.addAttribute("listSPBest",sanPhamService.findAllByOrderSLDaBanDesc());
            model.addAttribute("view", "/home/index.jsp");
            return "/customerFE/index";
        }
        model.addAttribute("listTH",thuongHieuService.listTLHome());
        model.addAttribute("listSPNew",sanPhamService.findAllByOrderByNgayTaoDesc());
        model.addAttribute("listSPBest",sanPhamService.findAllByOrderSLDaBanDesc());
        model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
        model.addAttribute("view", "/home/index.jsp");
        return "/customerFE/index";
    }
    @GetMapping("/blog")
    public String blog(Model model,HttpSession session){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("view", "/blog/index.jsp");
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("view", "/blog/index.jsp");
            return "/customerFE/index";
        }

    }

    @GetMapping("/blogPage1" )
    public String blogPage1(Model model,HttpSession session){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("view", "/blogPage1/index.jsp");
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("view", "/blogPage1/index.jsp");
            return "/customerFE/index";
        }
    }

    @GetMapping("/blogPage2" )
    public String blogPage2(Model model,HttpSession session){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("view", "/blogPage2/index.jsp");
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("view", "/blogPage2/index.jsp");
            return "/customerFE/index";
        }
    }

    @GetMapping("/blogPage3" )
    public String blogPage3(Model model,HttpSession session){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("view", "/blogPage3/index.jsp");
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("view", "/blogPage3/index.jsp");
            return "/customerFE/index";
        }
    }

    @GetMapping("/blogPage4" )
    public String blogPage4(Model model,HttpSession session){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("view", "/blogPage4/index.jsp");
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("view", "/blogPage4/index.jsp");
            return "/customerFE/index";
        }
    }

    @GetMapping("/blogPage5" )
    public String blogPage5(Model model, HttpSession session){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("view", "/blogPage5/index.jsp");
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("view", "/blogPage5/index.jsp");
            return "/customerFE/index";
        }
    }

    @GetMapping("/aboutus" )
    public String aboutUs(Model model,HttpSession session){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("view", "/aboutus/index.jsp");
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("view", "/aboutus/index.jsp");
            return "/customerFE/index";
        }
    }

}
