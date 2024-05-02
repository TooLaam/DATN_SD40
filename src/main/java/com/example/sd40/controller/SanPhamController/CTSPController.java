package com.example.sd40.controller.SanPhamController;

import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.San_pham.ChiTietSanPhamMauSacHinhAnh;
import com.example.sd40.entity.San_pham.SanPham;
import com.example.sd40.service.SanPham.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
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
    @Autowired
    GiamGiaService giamGiaService;

    @GetMapping("/hienthi/{id}")
    public String hienThi(Model model,
                          @PathVariable("id") Long id
                          ){
        model.addAttribute("sp",sanPhamService.detail(id));
        model.addAttribute("listMS",ctspmshaService.findMSTheoSPDangDung(id));
        model.addAttribute("listHA", ctspmshaService.getAllHinhAnhbyIDSP(id));
        model.addAttribute("view","/SanPham/SanPham/CTSP.jsp");
        return "index";
    }

    @GetMapping("/hienthiKC/{id}/{ms}")
    public ResponseEntity<?> hienThiKC(
                                       @PathVariable("id") Long id,
                                       @PathVariable("ms") Long ms
    ){
        List<Object[]> objects = ctspService.findKCTheoSPDangDung(id,ms);
        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    @GetMapping("/hienthiGiaDaGiam/{id}/{ms}")
    public ResponseEntity<?> hienthiGiaDaGiam(
                                       @PathVariable("id") Long id,
                                       @PathVariable("ms") Long ms
    ){
        Object objects = giamGiaService.hienThiTienDaGiam(id,ms);
        return new ResponseEntity<>(objects, HttpStatus.OK);
    }


    @GetMapping("/hienThiChinhSuaMauSac/{id}")
    public String themMauSac(Model model,
                            @PathVariable("id") Long id

    ){
        model.addAttribute("sp",sanPhamService.detail(id));
        model.addAttribute("listMSDangCo",ctspmshaService.findMSTheoSP(id));
        model.addAttribute("listMSChuaCo",ctspmshaService.getMSNotInCTSPMSByIdsp(id));
        model.addAttribute("view","/SanPham/SanPham/themMS.jsp");
        return "index";
    }



    @GetMapping("/detailMS/{idsp}/{idms}")
    public ResponseEntity<?> updateMS(
                             @PathVariable("idsp") Long idsp,
                             @PathVariable("idms") Long idms

    ){
        ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh = ctspmshaService.getHAbySPandMS(idsp,idms);
        return new ResponseEntity<>(chiTietSanPhamMauSacHinhAnh, HttpStatus.OK);
    }

    @PostMapping("/updateMS")
    public ResponseEntity<?> updateSP(@RequestParam("idsp")Long idsp,
                           @RequestParam("idms")String idms,
                           @RequestParam("image")MultipartFile image,
                           @RequestParam("giaHienHanh")BigDecimal giaHienHanh,
                           @RequestParam("trangThai")Integer trangThai
    ) throws IOException {
        Date currentDate = new Date(System.currentTimeMillis());
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
            ctspmshaService.updateHA(fileName,idsp,Long.valueOf(idms),currentDate,giaHienHanh,trangThai);


        return ResponseEntity.ok("ok");
    }

    @PostMapping("/addMS")
    public ResponseEntity<?> addMS(@RequestParam("idsp") Long id,
                           @RequestParam("idms") Long mauSac,
                           @RequestParam("image")MultipartFile image,
                           @RequestParam("giaHienHanh") BigDecimal giaHienHanh
    ) throws IOException {
        ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh = new ChiTietSanPhamMauSacHinhAnh();
        Date currentDate = new Date(System.currentTimeMillis());

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
            chiTietSanPhamMauSacHinhAnh.setGiaHienHanh(giaHienHanh);
            ctspmshaService.add(chiTietSanPhamMauSacHinhAnh);
        return ResponseEntity.ok("ok");
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

    @GetMapping("/detailKC/{idsp}/{idms}/{idkc}")
    public ResponseEntity<?> detaiKC(
                                @PathVariable("idsp") Long idsp,
                                @PathVariable("idkc") Long idkc,
                                @PathVariable("idms") Long idms

    ){
        ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh = ctspmshaService.getHAbySPandMS(idsp,idms);
        ChiTietSanPham chiTietSanPham = ctspService.findCTSP(chiTietSanPhamMauSacHinhAnh.getId(),idkc);
        return new ResponseEntity<>(chiTietSanPham, HttpStatus.OK);
    }






    @PostMapping("/updateKC")
    public ResponseEntity<?> updateKC(@RequestParam("idctsp")Long idctsp,
                           @RequestParam("soLuong") int soLuong,

                           @RequestParam("trangThai") int trangThai
                           ){
        Date currentDate = new Date(System.currentTimeMillis());

        ChiTietSanPham chiTietSanPham = ctspService.detail(idctsp);
        chiTietSanPham.setNgaySua(currentDate);
        chiTietSanPham.setSoLuong(soLuong);
        chiTietSanPham.setTrangThai(trangThai);
        ctspService.updateCTSP(chiTietSanPham);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/addKC")
    public ResponseEntity<?> addKC(@RequestParam("idsp")Long idsp,
                           @RequestParam("idms")Long idms,
                           @RequestParam("idkc")Long idKC,
                           @RequestParam("soLuong") int soLuong,
                           @RequestParam("trangThai") int trangThai

    ){
        Date currentDate = new Date(System.currentTimeMillis());

        ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh= ctspmshaService.getHAbySPandMS(idsp,idms);
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
        chiTietSanPham.setNgaySua(currentDate);
        chiTietSanPham.setChiTietSanPhamMauSacHinhAnh(chiTietSanPhamMauSacHinhAnh);
        chiTietSanPham.setNgayTao(currentDate);
        chiTietSanPham.setKichCo(kichCoService.detail(idKC));
        chiTietSanPham.setSoLuong(soLuong);
        chiTietSanPham.setTrangThai(trangThai);
        ctspService.updateCTSP(chiTietSanPham);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/hienThiaddSP")
    public String hienThiaddSP(Model model){
        model.addAttribute("listTL",loaiGiayService.listTLConDung());
        model.addAttribute("listTH",thuongHieuService.listTLConDung());
        model.addAttribute("listGG",giamGiaService.listGGConDung());
        model.addAttribute("view","/SanPham/SanPham/addSP.jsp");
        return "index";
    }
    @PostMapping("/addSP")
    public ResponseEntity<?> addSP(@RequestParam("ten")String ten,
                        @RequestParam("moTa")String moTa,
                        @RequestParam("thuongHieu")Long thuongHieu,
                        @RequestParam("theLoai")Long theLoai,
                        @RequestParam("giamGia")Long giamGia,
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

            sanPhamService.save(ten,moTa,fileName,thuongHieu,theLoai,giamGia);
            SanPham sanPham2 = sanPhamService.timSPCuoi();
            return new ResponseEntity<>(sanPham2, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(sanPham, HttpStatus.OK);
        }


    }

    @GetMapping("/giamGia/{id}")
    public String giamGia(Model model,
                          @PathVariable("id") Long id

    ){
        SanPham sanPham = sanPhamService.detail(id);

        if (sanPham.getGiamGIa() == null){
            model.addAttribute("sp",sanPhamService.detail(id));
            model.addAttribute("listGG",giamGiaService.getAll());
            model.addAttribute("view","/SanPham/SanPham/giamGia.jsp");
            return "index";
        }
        model.addAttribute("sp",sanPhamService.detail(id));
        model.addAttribute("listGG",giamGiaService.findByIDSP(id));
        model.addAttribute("view","/SanPham/SanPham/giamGia.jsp");
        return "index";
    }
    @PostMapping("/updategiamGia/{id}")
    public String UpdateGiamGia(Model model,
                                @PathVariable("id") Long id,
                                @RequestParam("giamGia") Long giamGia

    ){
        giamGiaService.updateGG(giamGia,id);
        return "redirect:/ctsp/giamGia/" + id;
    }
}
