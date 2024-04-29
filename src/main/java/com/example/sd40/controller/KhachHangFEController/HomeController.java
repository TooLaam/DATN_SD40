package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.service.KhachHang.HangThanhVienService;
import com.example.sd40.service.SanPham.SanPhamService;
import com.example.sd40.service.SanPham.ThuongHieuService;
import com.example.sd40.service.KhachHang.KhachHangCusService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ThuongHieuService thuongHieuService;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    KhachHangCusService khachHangCusService;

    @Autowired
    HangThanhVienService hangThanhVienService;


    @GetMapping("/home" )
    public String home(Model model, HttpSession session){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null){
            model.addAttribute("listTH",thuongHieuService.listTLHome());
            model.addAttribute("listSPNew",sanPhamService.findAllByOrderByNgayTaoDesc());
            model.addAttribute("listSPBest",sanPhamService.findAllByOrderSLDaBanDesc());
            model.addAttribute("idkh",idKH );
            model.addAttribute("view", "/home/index.jsp");
            return "/customerFE/index";
        }
        model.addAttribute("listTH",thuongHieuService.listTLHome());
        model.addAttribute("listSPNew",sanPhamService.findAllByOrderByNgayTaoDesc());
        model.addAttribute("listSPBest",sanPhamService.findAllByOrderSLDaBanDesc());
        model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
        model.addAttribute("idkh",idKH );
        model.addAttribute("view", "/home/index.jsp");
        return "/customerFE/index";
    }
    @GetMapping("/blog")
    public String blog(Model model,HttpSession session){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("view", "/blog/index.jsp");
            model.addAttribute("idkh",idKH );
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("view", "/blog/index.jsp");
            model.addAttribute("idkh",idKH );
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
            model.addAttribute("idkh",idKH );
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
            model.addAttribute("idkh",idKH );
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
            model.addAttribute("idkh",idKH );
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
            model.addAttribute("idkh",idKH );
            model.addAttribute("view", "/blogPage4/index.jsp");
            return "/customerFE/index";
        }
    }

    @GetMapping("/blogPage5" )
    public String blogPage5(Model model, HttpSession session){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("view", "/blogPage5/index.jsp");
            model.addAttribute("idkh",idKH );
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("idkh",idKH );
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
            model.addAttribute("idkh",idKH );
            model.addAttribute("view", "/aboutus/index.jsp");
            return "/customerFE/index";
        }
    }
    @GetMapping("/infoKhachHang")
    public String infoKhachHang(Model model,HttpSession session){
        Long idKH = Long.valueOf(1);
        model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
        model.addAttribute("idkh",idKH );
        model.addAttribute("kh",khachHangCusService.detailKhachHang(idKH) );

        model.addAttribute("view", "/changeAccountInfo/index.jsp");
        return "/customerFE/index";
    }
    @PostMapping("/doiMatKhau")
    public ResponseEntity<?> updateMacDinh(@RequestParam("taiKhoan") String taiKhoan,@RequestParam("matKhau") String matKhau, HttpSession session) {
        try {
//            Long idKH = (Long) session.getAttribute("idKhachHang");
            Long idKH = Long.valueOf(1);
            List<KhachHang> khachHangs = khachHangCusService.checkTaiKhoan(idKH,taiKhoan);
            if (khachHangs.isEmpty()){
                khachHangCusService.doiMatKhau(taiKhoan,matKhau,idKH);
                return ResponseEntity.ok("ok");
            }else {
                return ResponseEntity.ok("ko");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi");
        }
    }

    @GetMapping("/hienThiUpdateKhachHang")
    public String hienThiUpdateKhachHang(Model model,HttpSession session){
        Long idKH = Long.valueOf(1);
        model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
        model.addAttribute("idkh",idKH );
        model.addAttribute("kh",khachHangCusService.detailKhachHang(idKH) );

        model.addAttribute("view", "/changeAccountInfo/update.jsp");
        return "/customerFE/index";
    }
    @PostMapping("/updateKhachHang")
    public String updateKhacHang(HttpSession session,
                                 @RequestParam("ten")String ten,
                                 @RequestParam("ngaySinh") String ngaySinh,
                                 @RequestParam("sdt")String sdt,
                                 @RequestParam("gioiTinh")Integer gioiTinhn,
                                 @RequestParam("email")String email
    ) throws ParseException {
        Long idKH = Long.valueOf(1);
        KhachHang khachHang = khachHangCusService.detailKhachHang(idKH);
        khachHang.setNgaySinh(new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinh));
        khachHang.setHoTen(ten);
        khachHang.setEmail(email);
        khachHang.setSdt(sdt);
        khachHang.setGioiTinh(gioiTinhn);
        khachHangCusService.updateKhachHang(khachHang);
        return "redirect:/infoKhachHang";
    }
}
