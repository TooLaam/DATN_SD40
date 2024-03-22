//package com.example.sd40.controller.NhanVienController;
//
//import com.example.sd40.repuest.NhanVienRequyest;
//import com.example.sd40.service.NhanVien.NhanVienService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// * @author quynh
// */
//@Controller
//@RequestMapping("/employee")
//public class NhanVienController {
//
//    @Autowired
//    private NhanVienService nhanVienServicel;
//
//
//    @GetMapping("/index")
//    public String hienThiNhanVien(Model md){
//        md.addAttribute("nhanViens",nhanVienServicel.findByNhanVien());
//        md.addAttribute("view","/TaiKhoan/NhanVien/ViewNhanVien.jsp");
//        return "index";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String updateTrangThaiNhanVien(@PathVariable("id") Long id){
//        nhanVienServicel.delete(id);
//        return "redirect:/employee/index";
//    }
//
//    @GetMapping("/view-create")
//    public String hienThiCreateNhanVien(Model md){
//        md.addAttribute("view","/TaiKhoan/NhanVien/create.jsp");
//        return "index";
//    }
//
//    @GetMapping("/view-update/{id}")
//    public String hienThiUpdateNhanVien(@PathVariable("id") Long id, Model md){
//        md.addAttribute("nhanVien",nhanVienServicel.detail(id));
//        md.addAttribute("view","/TaiKhoan/NhanVien/update.jsp");
//        return "index";
//    }
//
//    @PostMapping("/update")
//    public String updateNhanVien(NhanVienRequyest requyest){
//        nhanVienServicel.update(requyest);
//        return "redirect:/employee/index";
//    }
//
//    @PostMapping("/create")
//    public String createNhanVien(NhanVienRequyest requyest){
//        nhanVienServicel.create(requyest);
//        return "redirect:/employee/index";
//    }
//
//}
