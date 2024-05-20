package com.example.sd40.controller.SanPhamController;


import com.example.sd40.entity.NhanVien.NhanVien;
import com.example.sd40.entity.San_pham.Mau_sac;
import com.example.sd40.service.SanPham.MauSacService;
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
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/mausac")
public class MauSacController {
    @Autowired
    MauSacService mauSacService;

    @GetMapping("/index")
    public String hienThi(Model model, HttpSession session){
        NhanVien nhanVien = (NhanVien) session.getAttribute("nhanVien");
        if (nhanVien == null){
            return "redirect:/admin/login";
        }else{
            model.addAttribute("nhanvien",nhanVien);
            model.addAttribute("listMS",mauSacService.listMSNew());
            model.addAttribute("view","/SanPham/MauSac/index.jsp");
            return "index";
        }

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Mau_sac> detail(Model model,
                                          @PathVariable("id")Long id){
        Mau_sac mau_sac = mauSacService.detail(id);
        if (mau_sac != null) {
            return new ResponseEntity<>(mau_sac, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/update")
    public ResponseEntity<?> update( @RequestParam("id") String id,
                                     @RequestParam(value = "ten") String ten,
                                     @RequestParam(value = "image") MultipartFile image,
                                     @RequestParam(value = "trangThai") Integer status
    ) throws ParseException, IOException {
        List<Mau_sac> mau_sacs = mauSacService.findByNameUpdate(ten,Long.valueOf(id));
        if (!mau_sacs.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        } else {
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
            return ResponseEntity.ok("ok");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestParam(value = "ten") String ten,
            @RequestParam(value = "image") MultipartFile image,
            @RequestParam(value = "trangThai") Integer status
    ) throws ParseException, IOException {
        List<Mau_sac> mau_sacs = mauSacService.findByName(ten);
        if (!mau_sacs.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        } else {
            Mau_sac color = new Mau_sac();
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
                color.setNgayTao(currentDate);

                mauSacService.add(color);
            } else {
                // Xử lý khi không có ảnh mới được tải lên
                color.setTen(ten);
                color.setTrangThai(status);
                color.setNgayTao(currentDate);

                mauSacService.add(color);
            }

            return ResponseEntity.ok("ok");
        }
    }
    @GetMapping("/listMSNew")
    public ResponseEntity<List<Mau_sac>> listMSNew(){
        List<Mau_sac> mau_sacs = mauSacService.listMSNew();
        if (mau_sacs != null) {
            return new ResponseEntity<>(mau_sacs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
