package com.example.sd40.controller.SanPhamController;

import com.example.sd40.entity.San_pham.TheLoai;
import com.example.sd40.entity.San_pham.ThuongHieu;
import com.example.sd40.service.SanPham.ThuongHieuService;
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
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/thuonghieu")
public class ThuongHieuController {
    @Autowired
    ThuongHieuService thuongHieuService;

    @GetMapping("/index")
    public String getAll(Model model){
        model.addAttribute("listTH",thuongHieuService.getAll());
        model.addAttribute("view","/SanPham/ThuongHieu/index.jsp");
        return "index";
    }

    Date currentDate = new Date(System.currentTimeMillis());

    @GetMapping("/detail/{id}")
    public ResponseEntity<ThuongHieu> detail(Model model,
                                          @PathVariable("id")Long id){
        ThuongHieu thuongHieu = thuongHieuService.detail(id);
        if (thuongHieu != null) {
            return new ResponseEntity<>(thuongHieu, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id")String id,
                                    @RequestParam("ten")String ten,
                                    @RequestParam("trangThai")Integer status,
                                    @RequestParam("image") MultipartFile image
    ) throws ParseException, IOException {
        List<ThuongHieu> thuongHieus = thuongHieuService.findByNameUpdate(ten,Long.valueOf(id));
        if (!thuongHieus.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        } else {
            ThuongHieu thuongHieu = thuongHieuService.detail(Long.valueOf(id));
            if (image != null && !image.isEmpty()) {

                String originalFileName = image.getOriginalFilename();
                String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(originalFileName);

                // Lưu tệp hình ảnh vào thư mục resources
                ClassPathResource resource = new ClassPathResource("static/assets/img/brand/");
                String uploadDir = resource.getFile().getAbsolutePath();
                File uploadPath = new File(uploadDir);

                if (!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }

                File img = new File(uploadPath, fileName);
                image.transferTo(img);

                thuongHieu.setHinhAnh(fileName); // Cập nhật tên ảnh mới
                // Cập nhật các trường thông tin khác
                thuongHieu.setTen(ten);
                thuongHieu.setTrangThai(status);
                thuongHieu.setNgaySua(currentDate);
                thuongHieuService.update(thuongHieu);
            }


            thuongHieu.setTen(ten);
            thuongHieu.setTrangThai(status);
            thuongHieu.setNgaySua(currentDate);
            thuongHieuService.update(thuongHieu);
            return ResponseEntity.ok("ok");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestParam("ten")String ten,
            @RequestParam("trangThai")Integer status,Model model,
            @RequestParam("image") MultipartFile image
    ) throws ParseException, IOException {
        List<ThuongHieu> thuongHieus = thuongHieuService.findByName(ten);
        ThuongHieu thuongHieu = new ThuongHieu();
        if (!thuongHieus.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        } else {
            if (image != null && !image.isEmpty()) {
                String originalFileName = image.getOriginalFilename();
                String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(originalFileName);

                // Lưu tệp hình ảnh vào thư mục resources
                ClassPathResource resource = new ClassPathResource("static/assets/img/brand/");
                String uploadDir = resource.getFile().getAbsolutePath();
                File uploadPath = new File(uploadDir);

                if (!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }

                File img = new File(uploadPath, fileName);
                image.transferTo(img);

                thuongHieu.setHinhAnh(fileName); // Cập nhật tên ảnh mới
                // Cập nhật các trường thông tin khác
                thuongHieu.setTen(ten);
                thuongHieu.setTrangThai(status);
                thuongHieu.setNgaySua(currentDate);
                thuongHieuService.update(thuongHieu);
            }
            thuongHieu.setTen(ten);
            thuongHieu.setTrangThai(status);
            thuongHieu.setNgaySua(currentDate);
            thuongHieuService.update(thuongHieu);
            return ResponseEntity.ok("ok");
        }
    }
}
