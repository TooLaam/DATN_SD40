package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.service.SanPham.*;
import com.example.sd40.service.KhachHang.KhachHangCusService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

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
            if (ctspService.getAllSPCusByTL(idTL).isEmpty()){
                model.addAttribute("listTH",thuongHieuService.getAll());
                model.addAttribute("listTL",loaiGiayService.findAll());
                model.addAttribute("err","Hiện không có sản phẩm nào thỏa mãn điều kiện của bạn. Vui lòng chọn filter khác !");
                model.addAttribute("view", "/product/theloai.jsp");
                return "/customerFE/index";
            }
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
            if (ctspService.getAllSPCusByTL(idTL).isEmpty()){
                model.addAttribute("listTH",thuongHieuService.getAll());
                model.addAttribute("listTL",loaiGiayService.findAll());
                model.addAttribute("err","Hiện không có sản phẩm nào thỏa mãn điều kiện của bạn. Vui lòng chọn filter khác !");
                model.addAttribute("view", "/product/theloai.jsp");
                return "/customerFE/index";
            }
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
            if (ctspService.getAllSPCusByTH(idTh).isEmpty()){
                model.addAttribute("listTH",thuongHieuService.getAll());
                model.addAttribute("listTL",loaiGiayService.findAll());
                model.addAttribute("err","Hiện không có sản phẩm nào thỏa mãn điều kiện của bạn. Vui lòng chọn filter khác !");
                model.addAttribute("view", "/product/thuonghieu.jsp");
                return "/customerFE/index";
            }
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
            if (ctspService.getAllSPCusByTH(idTh).isEmpty()){
                model.addAttribute("listTH",thuongHieuService.getAll());
                model.addAttribute("listTL",loaiGiayService.findAll());
                model.addAttribute("err","Hiện không có sản phẩm nào thỏa mãn điều kiện của bạn. Vui lòng chọn filter khác !");
                model.addAttribute("view", "/product/thuonghieu.jsp");
                return "/customerFE/index";
            }
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
            if (ctspService.getAllSPCusByGia(giaMin,giaMax).isEmpty()){
                model.addAttribute("listTH",thuongHieuService.getAll());
                model.addAttribute("listTL",loaiGiayService.findAll());
                model.addAttribute("err","Hiện không có sản phẩm nào thỏa mãn điều kiện của bạn. Vui lòng chọn filter khác !");
                model.addAttribute("view", "/product/filterByGia.jsp");
                return "/customerFE/index";
            }
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
            if (ctspService.getAllSPCusByGia(giaMin,giaMax).isEmpty()){
                model.addAttribute("listTH",thuongHieuService.getAll());
                model.addAttribute("listTL",loaiGiayService.findAll());
                model.addAttribute("err","Hiện không có sản phẩm nào thỏa mãn điều kiện của bạn. Vui lòng chọn filter khác !");
                model.addAttribute("view", "/product/filterByGia.jsp");
                return "/customerFE/index";
            }
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
            model.addAttribute("listMS",ctspmshaService.findMSTheoSP(idsp));
            model.addAttribute("listHA",ctspmshaService.getAllHinhAnhbyIDSP(idsp));
            model.addAttribute("SP1", sanPhamService.detail(idsp));

            model.addAttribute("view", "/detail/index.jsp");
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("sp",ctspService.detailSanPhamCus(idsp));
            model.addAttribute("listMS",ctspmshaService.findMSTheoSP(idsp));
            model.addAttribute("listHA",ctspmshaService.getAllHinhAnhbyIDSP(idsp));
            model.addAttribute("SP1", sanPhamService.detail(idsp));

            model.addAttribute("view", "/detail/index.jsp");
            return "/customerFE/index";
        }
    }

    @GetMapping("/hienthiKCCus/{idsp}/{idms}")
    public String hienthiKCCus(Model model,
                               @PathVariable("idsp") Long idsp,
                               @PathVariable("idms") Long idms, HttpSession session
    ){

        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null) {
            model.addAttribute("sp",ctspService.detailSanPhamCus(idsp));
            model.addAttribute("listMS",ctspmshaService.findMSTheoSP(idsp));
            model.addAttribute("listHA",ctspmshaService.getAllHinhAnhbyIDSP(idsp));
            model.addAttribute("SP1", sanPhamService.detail(idsp));
            model.addAttribute("listKC",ctspService.findKCTheoSP(idsp,idms));
            model.addAttribute("ms",mauSacService.detail(idms));

            model.addAttribute("view", "/detail/index.jsp");
            return "/customerFE/index";
        }
        else {
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("sp",ctspService.detailSanPhamCus(idsp));
            model.addAttribute("listMS",ctspmshaService.findMSTheoSP(idsp));
            model.addAttribute("listHA",ctspmshaService.getAllHinhAnhbyIDSP(idsp));
            model.addAttribute("SP1", sanPhamService.detail(idsp));
            model.addAttribute("listKC",ctspService.findKCTheoSP(idsp,idms));
            model.addAttribute("ms",mauSacService.detail(idms));

            model.addAttribute("view", "/detail/index.jsp");
            return "/customerFE/index";
        }
    }

    @GetMapping("/hienthiAddHD/{idsp}")
    public String hienthiAddHD(Model model,
                               @PathVariable("idsp") Long idsp,
                               @RequestParam("mauSac") Long idms,
                               @RequestParam("soLuong") int soLuong,
                               @RequestParam("kichCo") Long kichCo
    ){


        model.addAttribute("view", "/cart/index.jsp");
        return "/customerFE/index";
    }

}
