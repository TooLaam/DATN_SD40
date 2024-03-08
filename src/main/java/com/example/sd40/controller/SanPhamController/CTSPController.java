package com.example.sd40.controller.SanPhamController;

import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.San_pham.ChiTietSanPhamMauSacHinhAnh;
import com.example.sd40.entity.San_pham.SanPham;
import com.example.sd40.service.SanPham.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Controller
@RequestMapping("/ctsp")
public class CTSPController {

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    CTSPService ctspService;

    @Autowired
    CTSPMSHAService ctspmshaService;

    @Autowired
    MauSacService mauSacService;

    @Autowired
    KichCoService kichCoService;

    @Autowired
    ThuongHieuService thuongHieuService;
    @Autowired
    LoaiGiayService loaiGiayService;

    @GetMapping("/hienthi/{id}")
    public String hienThi(Model model,
                          @PathVariable("id") Long id
                          ){
        model.addAttribute("sp",sanPhamService.detail(id));
//        model.addAttribute("listKC",ctspService.findKCTheoSP(id));
        model.addAttribute("listMS",ctspmshaService.findMSTheoSP(id));
        model.addAttribute("listHA", ctspmshaService.getAllHinhAnhbyIDSP(id));
        model.addAttribute("view","/SanPham/SanPham/CTSP.jsp");
        return "index";
    }

    @GetMapping("/hienthiKC/{id}/{ms}")
    public String hienThiKC(Model model,
                          @PathVariable("id") Long id,
                            @PathVariable("ms") Long ms
    ){
        model.addAttribute("sp",sanPhamService.detail(id));
        model.addAttribute("listKC",ctspService.findKCTheoSP(id,ms));
        model.addAttribute("listMS",ctspmshaService.findMSTheoSP(id));
        model.addAttribute("listHA",ctspmshaService.getHAbySPandMS(id,ms));
        model.addAttribute("ms",mauSacService.detail(ms));
        model.addAttribute("view","/SanPham/SanPham/CTSPKC.jsp");
        return "index";
    }

    @GetMapping("/themmausac/{id}")
    public String themMauSac(Model model,
                            @PathVariable("id") Long id

    ){
        model.addAttribute("sp",sanPhamService.detail(id));
        model.addAttribute("listMSDangCo",ctspmshaService.findMSTheoSP(id));
        model.addAttribute("listMSChuaCo",ctspmshaService.getMSNotInCTSPMSByIdsp(id));
        model.addAttribute("view","/SanPham/SanPham/themMS.jsp");
        return "index";
    }

    @GetMapping("/hienthithemkc/{idsp}/{idms}")
    public String hienthithemkc(Model model,
                             @PathVariable("idsp") Long idsp,
                             @PathVariable("idms") Long idms

    ){
        ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh = ctspmshaService.getHAbySPandMS(idsp,idms);
        model.addAttribute("sp",sanPhamService.detail(idsp));
        model.addAttribute("ms",mauSacService.detail(idms));
        model.addAttribute("listKCChuaCo",ctspService.findKCNotInCTSP(chiTietSanPhamMauSacHinhAnh.getId()));
        model.addAttribute("listKCDangCo",ctspService.findCTSP2(chiTietSanPhamMauSacHinhAnh.getId()));

        model.addAttribute("view","/SanPham/SanPham/themKC.jsp");
        return "index";
    }

    @GetMapping("/hienthiupdateKC/{idsp}/{idms}/{idkc}")
    public String hienthiupdatekc(Model model,
                                @PathVariable("idsp") Long idsp,
                                @PathVariable("idkc") Long idkc,
                                @PathVariable("idms") Long idms

    ){
        ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh = ctspmshaService.getHAbySPandMS(idsp,idms);
        model.addAttribute("sp",sanPhamService.detail(idsp));
        model.addAttribute("ms",mauSacService.detail(idms));
        model.addAttribute("kc",kichCoService.detail(idkc));
        model.addAttribute("ctsp",ctspService.findCTSP(chiTietSanPhamMauSacHinhAnh.getId(),idkc));
        model.addAttribute("listKCChuaCo",ctspService.findKCNotInCTSP(chiTietSanPhamMauSacHinhAnh.getId()));
        model.addAttribute("listKCDangCo",ctspService.findCTSP2(chiTietSanPhamMauSacHinhAnh.getId()));

        model.addAttribute("view","/SanPham/SanPham/themKC.jsp");
        return "index";
    }

    @PostMapping("/updateMS")
    public String updateMS(@RequestParam("id") Long id,
                           @RequestParam("mauSac") Long mauSac,
                           @RequestParam("image")MultipartFile image
                           ) throws IOException {
        ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh = new ChiTietSanPhamMauSacHinhAnh();
        Date currentDate = new Date(System.currentTimeMillis());

        if (image != null && !image.isEmpty()) {
            String originalFileName = image.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(originalFileName);

            // Lưu tệp hình ảnh vào thư mục resources
            ClassPathResource resource = new ClassPathResource("static/assets/img/product/");
            String uploadDir = resource.getFile().getAbsolutePath();
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            File img = new File(uploadPath, fileName);
            image.transferTo(img);
            chiTietSanPhamMauSacHinhAnh.setHinhAnh(fileName);
            chiTietSanPhamMauSacHinhAnh.setMau_sac(mauSacService.detail(mauSac));
            chiTietSanPhamMauSacHinhAnh.setSanPham(sanPhamService.detail(id));
            chiTietSanPhamMauSacHinhAnh.setNgayTao(currentDate);
            chiTietSanPhamMauSacHinhAnh.setTrangThai(0);
            chiTietSanPhamMauSacHinhAnh.setNgaySua(currentDate);
            ctspmshaService.add(chiTietSanPhamMauSacHinhAnh);
        } else {
            // Xử lý khi không có ảnh mới được tải lên
            chiTietSanPhamMauSacHinhAnh.setMau_sac(mauSacService.detail(mauSac));
            chiTietSanPhamMauSacHinhAnh.setSanPham(sanPhamService.detail(id));
            chiTietSanPhamMauSacHinhAnh.setNgayTao(currentDate);
            chiTietSanPhamMauSacHinhAnh.setTrangThai(0);
            chiTietSanPhamMauSacHinhAnh.setNgaySua(currentDate);
            ctspmshaService.add(chiTietSanPhamMauSacHinhAnh);
        }


        return "redirect:/ctsp/hienthithemkc/" + id+"/"+mauSac;
    }

    @PostMapping("/updateHA/{idsp}/{idms}")
    public String updateSP(@PathVariable("idsp")Long idsp,
                           @PathVariable("idms")Long idms,
                           @RequestParam("image")MultipartFile image
                           ) throws IOException {
        Date currentDate = new Date(System.currentTimeMillis());

        if (image != null && !image.isEmpty()) {
            String originalFileName = image.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(originalFileName);

            // Lưu tệp hình ảnh vào thư mục resources
            ClassPathResource resource = new ClassPathResource("static/assets/img/product/");
            String uploadDir = resource.getFile().getAbsolutePath();
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            File img = new File(uploadPath, fileName);
            image.transferTo(img);
            ctspmshaService.updateHA(fileName,idsp,idms,currentDate);
        } else {
            // Xử lý khi không có ảnh mới được tải lên

        }

        return "redirect:/ctsp/hienthiKC/"+idsp+"/"+idms;
    }


    @PostMapping("/updateKC/{idsp}/{idms}/{idkc}/{idctsp}")
    public String updateKC(@PathVariable("idsp")Long idsp,
                           @PathVariable("idms")Long idms,
                           @PathVariable("idkc")Long idkc,
                           @RequestParam("soLuong") int soLuong,
                           @RequestParam("giaHienHanh") BigDecimal giaHienHanh,
                           @RequestParam("trangThai") int trangThai,
                           @PathVariable("idctsp")Long idCTSP
                           ){
        Date currentDate = new Date(System.currentTimeMillis());

        ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh= ctspmshaService.getHAbySPandMS(idsp,idms);
        ChiTietSanPham chiTietSanPham = ctspService.findCTSP(chiTietSanPhamMauSacHinhAnh.getId(),idkc);
        chiTietSanPham.setNgaySua(currentDate);
        chiTietSanPham.setGiaHienHanh(giaHienHanh);
        chiTietSanPham.setSoLuong(soLuong);
        chiTietSanPham.setTrangThai(trangThai);
        ctspService.updateCTSP(chiTietSanPham);
        return "redirect:/ctsp/hienthithemkc/"+idsp+"/"+idms;
    }

    @PostMapping("/addKC/{idsp}/{idms}")
    public String addKC(@PathVariable("idsp")Long idsp,
                           @PathVariable("idms")Long idms,
                           @RequestParam("kichCo")Long idKC,
                           @RequestParam("soLuong") int soLuong,
                           @RequestParam("giaHienHanh") BigDecimal giaHienHanh,
                           @RequestParam("trangThai") int trangThai

    ){
        Date currentDate = new Date(System.currentTimeMillis());

        ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh= ctspmshaService.getHAbySPandMS(idsp,idms);
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
        chiTietSanPham.setNgaySua(currentDate);
        chiTietSanPham.setChiTietSanPhamMauSacHinhAnh(chiTietSanPhamMauSacHinhAnh);
        chiTietSanPham.setNgayTao(currentDate);
        chiTietSanPham.setKichCo(kichCoService.detail(idKC));
        chiTietSanPham.setGiaHienHanh(giaHienHanh);
        chiTietSanPham.setSoLuong(soLuong);
        chiTietSanPham.setTrangThai(trangThai);
        ctspService.updateCTSP(chiTietSanPham);
        return "redirect:/ctsp/hienthithemkc/"+idsp+"/"+idms;
    }

    @GetMapping("/hienThiaddSP")
    public String hienThiaddSP(Model model){
        model.addAttribute("listTL",loaiGiayService.findAll());
        model.addAttribute("listTH",thuongHieuService.getAll());
        model.addAttribute("view","/SanPham/SanPham/addSP.jsp");
        return "index";
    }
    @PostMapping("/addSP")
    public String addSP(@RequestParam("ten")String ten,
                        @RequestParam("moTa")String moTa,
                        @RequestParam("thuongHieu")Long thuongHieu,
                        @RequestParam("theLoai")Long theLoai,
                        @RequestParam("image") MultipartFile image
                        ) throws IOException {
        SanPham sanPham = sanPhamService.findSPCoTonTaiKhong(ten, thuongHieu, theLoai);
        Date currentDate = new Date(System.currentTimeMillis());

        if (sanPham == null){
            String originalFileName = image.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(originalFileName);

            // Lưu tệp hình ảnh vào thư mục resources
            ClassPathResource resource = new ClassPathResource("static/assets/img/product/");
            String uploadDir = resource.getFile().getAbsolutePath();
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            File img = new File(uploadPath, fileName);
            image.transferTo(img);


            SanPham sanPham1 = new SanPham();
//            sanPham1.setTen(ten);
//            sanPham1.setTheLoai(loaiGiayService.detail(theLoai));
//            sanPham1.setThuongHieu(thuongHieuService.detail(thuongHieu));
//            sanPham1.setMoTa(moTa);
//            sanPham1.setNgaySua(currentDate);
//            sanPham1.setNgayTao(currentDate);
//            sanPham1.setTrangThai(0);
//            sanPham1.setHinhAnhDaiDien(fileName);
            sanPhamService.save(ten,moTa,fileName,thuongHieu,theLoai);
            SanPham sanPham2 = sanPhamService.timSPCuoi();
            return "redirect:/ctsp/themmausac/"+sanPham2.getId();
        }
        else {
            return "redirect:/ctsp/themmausac/"+sanPham.getId();
        }


    }
}
