package com.example.sd40.controller.SanPhamController;


import com.example.sd40.entity.San_pham.Mau_sac;
import com.example.sd40.service.SanPham.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/mausac")
public class MauSacController {
    @Autowired
    MauSacService mauSacService;

    @GetMapping("/index")
    public String hienThi(Model model){
        model.addAttribute("listMS",mauSacService.findAll());
        model.addAttribute("view","/SanPham/MauSac/index.jsp");
        return "index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") Long id) {
        model.addAttribute("mau", mauSacService.detail(id));
        model.addAttribute("listMS",mauSacService.findAll());

        model.addAttribute("view", "/SanPham/MauSac/index.jsp");
        return "index";
    }

    @PostMapping("/update")
    public String update(Model model,
                         @RequestParam("id") String id,
                         @RequestParam(value = "ten") String ten,
                         @RequestParam(value = "image") MultipartFile image,
                         @RequestParam(value = "status") Integer status) throws IOException {
        if (id.isBlank()){
            model.addAttribute("err","Bạn phải chọn đối tượng màu sắc ở danh sách");
            model.addAttribute("listMS",mauSacService.findAll());
            model.addAttribute("view","/SanPham/MauSac/index.jsp");
            return "index";
        }
        else {
            List<Mau_sac> mau_sacs = mauSacService.findByNameUpdate(ten,Long.valueOf(id));
            if (!mau_sacs.isEmpty()){
                model.addAttribute("errUpdate","Trùng tên. Vui lòng đổi tên !!!");
                model.addAttribute("mau", mauSacService.detail(Long.valueOf(id)));
                model.addAttribute("listMS",mauSacService.findAll());
                model.addAttribute("view","/SanPham/MauSac/index.jsp");
                return "index";
            }
            Mau_sac color = mauSacService.detail(Long.valueOf(id));
            Date currentDate = new Date(System.currentTimeMillis());

            if (image != null && !image.isEmpty()) {
                String originalFileName = image.getOriginalFilename();
                String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(originalFileName);

                // Lưu tệp hình ảnh vào thư mục resources
                ClassPathResource resource = new ClassPathResource("static/assets/img/color/");
                String uploadDir = resource.getFile().getAbsolutePath();
                File uploadPath = new File(uploadDir);

                if (!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }

                File img = new File(uploadPath, fileName);
                image.transferTo(img);

                color.setHinhAnh(fileName); // Cập nhật tên ảnh mới

                // Cập nhật các trường thông tin khác
                color.setTen(ten);
                color.setTrangThai(status);
                color.setNgaySua(currentDate);

                mauSacService.update(color);
            } else {
                // Xử lý khi không có ảnh mới được tải lên
                color.setTen(ten);
                color.setTrangThai(status);
                color.setNgaySua(currentDate);

                mauSacService.update(color);
            }

            return "redirect:/mausac/index";
        }

    }

    @PostMapping("/add")
    public String add(Model model,
                         @RequestParam(value = "ten") String ten,
                         @RequestParam(value = "image") MultipartFile image,
                         @RequestParam(value = "status") Integer status) throws IOException {
        Mau_sac color = new Mau_sac();
        List<Mau_sac> mau_sacs = mauSacService.findByName(ten);
        Date currentDate = new Date(System.currentTimeMillis());
        if (!mau_sacs.isEmpty()){
            model.addAttribute("errName","Tên thêm mới trùng với trong danh sách. Vui lòng chọn tên mới !!!");
            model.addAttribute("active","nav-link active");
            model.addAttribute("listMS",mauSacService.findAll());
            model.addAttribute("view","/SanPham/MauSac/index.jsp");
            return "index";
        }

        if (image != null && !image.isEmpty()) {
            String originalFileName = image.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(originalFileName);

            // Lưu tệp hình ảnh vào thư mục resources
            ClassPathResource resource = new ClassPathResource("static/assets/img/color/");
            String uploadDir = resource.getFile().getAbsolutePath();
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            File img = new File(uploadPath, fileName);
            image.transferTo(img);

            color.setHinhAnh(fileName); // Cập nhật tên ảnh mới

            // Cập nhật các trường thông tin khác
            color.setTen(ten);
            color.setTrangThai(status);
            color.setNgayTao(currentDate);

            mauSacService.add(color);
        } else {
            // Xử lý khi không có ảnh mới được tải lên
            color.setTen(ten);
            color.setTrangThai(status);
            color.setNgayTao(currentDate);

            mauSacService.add(color);
        }

        return "redirect:/mausac/index";
    }
}
