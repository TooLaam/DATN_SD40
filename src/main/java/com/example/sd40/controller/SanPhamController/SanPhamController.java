package com.example.sd40.controller.SanPhamController;


import com.example.sd40.entity.NhanVien.NhanVien;
import com.example.sd40.entity.San_pham.SanPham;
import com.example.sd40.service.SanPham.GiamGiaService;
import com.example.sd40.service.SanPham.LoaiGiayService;
import com.example.sd40.service.SanPham.SanPhamService;
import com.example.sd40.service.SanPham.ThuongHieuService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/sanpham")
public class SanPhamController {
    @Autowired
    SanPhamService sanPhamService;
    @Autowired
    ThuongHieuService thuongHieuService;
    @Autowired
    LoaiGiayService loaiGiayService;

    @Autowired
    GiamGiaService giamGiaService;

    @GetMapping("/index")
    public String hienThi(Model model, HttpSession session){
        NhanVien nhanVien = (NhanVien) session.getAttribute("nhanVien");
        if (nhanVien == null){
            return "redirect:/admin/login";
        }else {
            model.addAttribute("nhanvien",nhanVien);
            model.addAttribute("listTong",sanPhamService.getAllTongSL());
            model.addAttribute("listSPChuaSL",sanPhamService.getAllSPChuaSL());
            model.addAttribute("view","/SanPham/SanPham/index.jsp");
            return "index";
        }

    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id")Long id,HttpSession session){
        NhanVien nhanVien = (NhanVien) session.getAttribute("nhanVien");
        if (nhanVien == null){
            return "redirect:/admin/login";
        }else {
            model.addAttribute("nhanvien",nhanVien);
            model.addAttribute("sp",sanPhamService.detail(id));
            model.addAttribute("listTH",thuongHieuService.listTHDetail(sanPhamService.detail(id).getThuongHieu().getId()));
            model.addAttribute("listTL",loaiGiayService.listTLDetail(sanPhamService.detail(id).getTheLoai().getId()));
            model.addAttribute("listGG",giamGiaService.listGGDetail(sanPhamService.detail(id).getGiamGIa().getId()));
            model.addAttribute("GGDetail",sanPhamService.detail(id).getGiamGIa());
            model.addAttribute("view","/SanPham/SanPham/detail.jsp");
            return "index";
        }
    }
    Date currentDate = new Date(System.currentTimeMillis());

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id") Long id,
                                    @RequestParam("ten") String ten,
                                    @RequestParam("moTa") String moTa,
                                    @RequestParam("trangThai") int trangThai,
                                    @RequestParam("image") MultipartFile image,
                                    @RequestParam("thuongHieu") Long thuongHieu,
                                    @RequestParam("theLoai") Long theLoai,
                                    @RequestParam("giamGia") Long giamGia
                         ) throws IOException {

        Date currentDate = new Date(System.currentTimeMillis());
        List<SanPham> sanPhams = sanPhamService.findByName(ten,id);
        if(!sanPhams.isEmpty()){
            return ResponseEntity.ok("errTrungTen");
        }
        if (image != null && !image.isEmpty()) {
            String originalFileName = image.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(originalFileName);
            ClassPathResource resource = new ClassPathResource("static/assets/img/product/");
            String uploadDir = resource.getFile().getAbsolutePath();
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }
            File img = new File(uploadPath, fileName);
            image.transferTo(img);

            sanPhamService.update(ten,moTa,trangThai,fileName,thuongHieu,theLoai,id,currentDate,giamGia);
        } else {
            // Xử lý khi không có ảnh mới được tải lên
            sanPhamService.update(ten,moTa,trangThai,"",thuongHieu,theLoai,id,currentDate,giamGia);
        }

        return ResponseEntity.ok("ok");
    }
}
