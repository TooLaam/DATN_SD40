package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.San_pham.ChiTietSanPhamMauSacHinhAnh;
import com.example.sd40.entity.San_pham.SanPham;
import com.example.sd40.service.GioHang.GioHangChiTietService;
import com.example.sd40.service.SanPham.*;
import com.example.sd40.service.KhachHang.KhachHangCusService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    MauSacService mauSacService;
    @Autowired
    KichCoService kichCoService;
    @Autowired
    CTSPMSHAService ctspmshaService;

    @Autowired
    KhachHangCusService khachHangCusService;



    @GetMapping("/sanphamcus")
    public String sanPhamCus(Model model, HttpSession session){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("listTH",thuongHieuService.getAll());
            model.addAttribute("listTL",loaiGiayService.findAll());
            model.addAttribute("listSP",ctspService.getAllSPCus());
            model.addAttribute("tongSLSP",ctspService.getAllSPCus().size());
            model.addAttribute("view", "/product/index.jsp");
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("idkh",idKH );
            model.addAttribute("listTH",thuongHieuService.getAll());
            model.addAttribute("listTL",loaiGiayService.findAll());
            model.addAttribute("listSP",ctspService.getAllSPCus());
            model.addAttribute("tongSLSP",ctspService.getAllSPCus().size());
            model.addAttribute("view", "/product/index.jsp");
            return "/customerFE/index";
        }
    }

    @GetMapping("/sanphamcusgiamgia")
    public String sanPhamCusGiamGia(Model model, HttpSession session){

        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("listTH",thuongHieuService.getAll());
            model.addAttribute("listTL",loaiGiayService.findAll());
            model.addAttribute("listSP",ctspService.getAllSPCusByGiamGia());
            model.addAttribute("tongSLSP",ctspService.getAllSPCusByGiamGia().size());
            model.addAttribute("view", "/product/giamGia.jsp");
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("idkh",idKH );
            model.addAttribute("listTH",thuongHieuService.getAll());
            model.addAttribute("listTL",loaiGiayService.findAll());
            model.addAttribute("listSP",ctspService.getAllSPCusByGiamGia());
            model.addAttribute("tongSLSP",ctspService.getAllSPCusByGiamGia().size());
            model.addAttribute("view", "/product/giamGia.jsp");
            return "/customerFE/index";
        }
    }

    @GetMapping("/sanphamcus/theloaicus/{idtl}")
    public String sanPhamCusByTL(Model model,
                                 @PathVariable("idtl")Long idTL, HttpSession session
    ){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("listTH",thuongHieuService.getAll());
            model.addAttribute("listTL",loaiGiayService.findAll());
            model.addAttribute("listSP",ctspService.getAllSPCusByTL(idTL));
            model.addAttribute("tongSLSP",ctspService.getAllSPCusByTL(idTL).size());
            model.addAttribute("TL",loaiGiayService.detail(idTL));
            model.addAttribute("view", "/product/theloai.jsp");
            return "/customerFE/index";

        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("idkh",idKH );
            model.addAttribute("listTH",thuongHieuService.getAll());
            model.addAttribute("listTL",loaiGiayService.findAll());
            model.addAttribute("listSP",ctspService.getAllSPCusByTL(idTL));
            model.addAttribute("tongSLSP",ctspService.getAllSPCusByTL(idTL).size());
            model.addAttribute("TL",loaiGiayService.detail(idTL));
            model.addAttribute("view", "/product/theloai.jsp");
            return "/customerFE/index";

        }
    }
    @GetMapping("/sanphamcus/thuonghieucus/{idth}")
    public String sanPhamCusByTH(Model model,
                                 @PathVariable("idth")Long idTh, HttpSession session
    ){



        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("listTH",thuongHieuService.getAll());
            model.addAttribute("listTL",loaiGiayService.findAll());
            model.addAttribute("listSP",ctspService.getAllSPCusByTH(idTh));
            model.addAttribute("tongSLSP",ctspService.getAllSPCusByTH(idTh).size());
            model.addAttribute("TH",thuongHieuService.detail(idTh));
            model.addAttribute("view", "/product/thuonghieu.jsp");
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("idkh",idKH );
            model.addAttribute("listTH",thuongHieuService.getAll());
            model.addAttribute("listTL",loaiGiayService.findAll());
            model.addAttribute("listSP",ctspService.getAllSPCusByTH(idTh));
            model.addAttribute("tongSLSP",ctspService.getAllSPCusByTH(idTh).size());
            model.addAttribute("TH",thuongHieuService.detail(idTh));
            model.addAttribute("view", "/product/thuonghieu.jsp");
            return "/customerFE/index";
        }
    }

    @GetMapping("/sanphamcus/mucGia/{giaMin}/{giaMax}")
    public String sanPhamCusByGia(Model model,
                                  @PathVariable("giaMin") BigDecimal giaMin,
                                  @PathVariable("giaMax")BigDecimal giaMax, HttpSession session
    ){



        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("listTH",thuongHieuService.getAll());
            model.addAttribute("listTL",loaiGiayService.findAll());
            model.addAttribute("listSP",ctspService.getAllSPCusByGia(giaMin,giaMax));
            model.addAttribute("tongSLSP",ctspService.getAllSPCusByGia(giaMin,giaMax).size());
            model.addAttribute("giaMin",giaMin);
            model.addAttribute("giaMax",giaMax);
            model.addAttribute("view", "/product/filterByGia.jsp");
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("idkh",idKH );
            model.addAttribute("listTH",thuongHieuService.getAll());
            model.addAttribute("listTL",loaiGiayService.findAll());
            model.addAttribute("listSP",ctspService.getAllSPCusByGia(giaMin,giaMax));
            model.addAttribute("tongSLSP",ctspService.getAllSPCusByGia(giaMin,giaMax).size());
            model.addAttribute("giaMin",giaMin);
            model.addAttribute("giaMax",giaMax);

            model.addAttribute("view", "/product/filterByGia.jsp");
            return "/customerFE/index";
        }
    }

    @GetMapping("/detailsanphamcus/{idsp}")
    public String detailSanPhamCus(Model model,@PathVariable("idsp") Long idsp, HttpSession session){


        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("sp",ctspService.detailSanPhamCus(idsp));
            model.addAttribute("listMS",ctspmshaService.findMSTheoSPDangDung(idsp));
            model.addAttribute("listHA",ctspmshaService.getAllHinhAnhbyIDSP(idsp));
            model.addAttribute("SP1", sanPhamService.detail(idsp));
            model.addAttribute("view", "/detail/index.jsp");
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("idkh",idKH );
            model.addAttribute("sp",ctspService.detailSanPhamCus(idsp));
            model.addAttribute("listMS",ctspmshaService.findMSTheoSPDangDung(idsp));
            model.addAttribute("listHA",ctspmshaService.getAllHinhAnhbyIDSP(idsp));
            model.addAttribute("SP1", sanPhamService.detail(idsp));
            model.addAttribute("view", "/detail/index.jsp");
            return "/customerFE/index";
        }
    }

    @GetMapping("/hienthiKCCus/{idsp}/{idms}")
    public ResponseEntity<List<Object[]>> hienthiKCCus(Model model,
                                                       @PathVariable("idsp") Long idsp,
                                                       @PathVariable("idms") Long idms, HttpSession session
    ){

        List<Object[]> kichCos = ctspService.findKCTheoSP(idsp, idms);
        if (kichCos != null && !kichCos.isEmpty()) {
            return new ResponseEntity<>(kichCos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/timKiemSanPham/{key}")
    public ResponseEntity<?> timKiemSanPham(@PathVariable("key") String key) {
        try {
            List<Object[]> sanPhams = sanPhamService.searchByTenOrThuongHieuOrTheLoai(key);
            return new ResponseEntity<>(sanPhams, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi");
        }
    }

    @GetMapping("/timKiemSanPhamAll")
    public String timKiemSanPhamAll(Model model,HttpSession session,@RequestParam("key")String key){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("listTH",thuongHieuService.getAll());
            model.addAttribute("listTL",loaiGiayService.findAll());
            model.addAttribute("listSP",sanPhamService.getAllSPBySearch(key));
            model.addAttribute("tongSLSP",ctspService.getAllSPCus().size());
            model.addAttribute("view", "/product/timKiem.jsp");
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("idkh",idKH );
            model.addAttribute("listTH",thuongHieuService.getAll());
            model.addAttribute("listTL",loaiGiayService.findAll());
            model.addAttribute("listSP",sanPhamService.getAllSPBySearch(key));
            model.addAttribute("tongSLSP",sanPhamService.getAllSPBySearch(key).size());
            model.addAttribute("view", "/product/timKiem.jsp");
            return "/customerFE/index";
        }
    }


}
