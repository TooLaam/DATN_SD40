package com.example.sd40.controller.SanPhamController;

import com.example.sd40.entity.San_pham.Mau_sac;
import com.example.sd40.entity.San_pham.SanPham;
import com.example.sd40.service.SanPham.LoaiGiayService;
import com.example.sd40.service.SanPham.SanPhamService;
import com.example.sd40.service.SanPham.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
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

    @GetMapping("/index")
    public String hienThi(Model model){
        model.addAttribute("listTong",sanPhamService.getAllTongSL());
        model.addAttribute("listSPChuaSL",sanPhamService.getAllSPChuaSL());
        model.addAttribute("view","/SanPham/SanPham/index.jsp");


        return "index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id")Long id){
        model.addAttribute("sp",sanPhamService.detail(id));
        model.addAttribute("listTH",thuongHieuService.getAll());
        model.addAttribute("listTL",loaiGiayService.findAll());
        model.addAttribute("view","/SanPham/SanPham/detail.jsp");

        return "index";

    }
    Date currentDate = new Date(System.currentTimeMillis());

    @PostMapping("/update")
    public String update(@RequestParam("id") Long id,
                         @RequestParam("ten") String ten,
                         @RequestParam("moTa") String moTa,
                         @RequestParam("trangThai") int trangThai,
                         @RequestParam("image") MultipartFile image,
                         @RequestParam("thuongHieu") Long thuongHieu,
                         @RequestParam("theLoai") Long theLoai
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

//            sp.setHinhAnhDaiDien(fileName); // Cập nhật tên ảnh mới

            // Cập nhật các trường thông tin khác


            sanPhamService.update(ten,moTa,trangThai,fileName,thuongHieu,theLoai,id,currentDate);
        } else {
            // Xử lý khi không có ảnh mới được tải lên
            sanPhamService.update(ten,moTa,trangThai,"",thuongHieu,theLoai,id,currentDate);
        }

        return "redirect:/sanpham/index";
    }
}
