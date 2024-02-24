package com.example.sd40.controller;


import com.example.sd40.entity.Brand;
import com.example.sd40.entity.Color;
import com.example.sd40.entity.Employee;
import com.example.sd40.repository.BrandResponsitory;
import com.example.sd40.service.EmployeeService;
import com.example.sd40.service.serviceimpl.BrandServiceimpl;
import com.example.sd40.service.serviceimpl.ColorServiceimpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
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
import java.util.UUID;

@Controller
@RequestMapping("/color")
public class ColorController {

    @Autowired
    private ColorServiceimpl colorServiceimpl;
    @Autowired
    private BrandServiceimpl brandServiceimpl;
    @Autowired
    private BrandResponsitory brandResponsitory;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/index")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("Name") != null){
            //Nếu đã đăng nhập vào trang index
            String username = (String) session.getAttribute("username");
            String password = (String) session.getAttribute("password");
            Employee checkLogin = employeeService.login(username,password);
            model.addAttribute("empLogin",checkLogin);
            //////
            model.addAttribute("listColor", colorServiceimpl.getAll());
            model.addAttribute("listBrand", brandServiceimpl.getAll());
            model.addAttribute("view", "/Color/index.jsp");
            return "index";
        }
        //Nếu chưa đăng nhập thì return về trang logina
        return "login";

    }

    @PostMapping("/add")
    public String add(Model model,
                      @RequestParam(value = "name") String name,
                      @RequestParam(value = "price") BigDecimal price,
                      @RequestParam(value = "image") MultipartFile image,
                      @RequestParam(value = "status") Integer status,
                      @RequestParam(value = "brand") String brand
    ) throws IOException {
        if (image != null && !image.isEmpty()) {
            String originalFileName = image.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(originalFileName);

            // Lưu tệp hình ảnh vào thư mục resources
            ClassPathResource resource = new ClassPathResource("static/assets/img/color/");
            String uploadDir = resource.getFile().getAbsolutePath();
            System.out.println("Cái này nha" + uploadDir);
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            File img = new File(uploadPath, fileName);
            image.transferTo(img);
            Brand brand1 = brandResponsitory.findById(UUID.fromString(brand)).orElse(null);
            colorServiceimpl.save(new Color(name, price, fileName, status, brand1));
        }
        return "redirect:/color/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model,
                         @PathVariable("id") String id
    ) {
        colorServiceimpl.delete(UUID.fromString(id));
        return "redirect:/color/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") UUID id) {
        model.addAttribute("listColor", colorServiceimpl.getAll());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("mau", colorServiceimpl.getOne(id));
        model.addAttribute("view", "/Color/index.jsp");
        return "index";
    }

    @PostMapping("/update/{id}")
    public String update(Model model,
                         @PathVariable("id") UUID id,
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "price") BigDecimal price,
                         @RequestParam(value = "image") MultipartFile image,
                         @RequestParam(value = "status") Integer status,
                         @RequestParam(value = "brand") String brand)
            throws IOException {
        Color color = colorServiceimpl.getOne(id);

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

            color.setImage(fileName); // Cập nhật tên ảnh mới

            // Cập nhật các trường thông tin khác
            Brand brand1 = brandResponsitory.findById(UUID.fromString(brand)).orElse(null);
            color.setBrand(brand1);
            color.setPrice(price);
            color.setStatus(status);
            color.setName(name);

            colorServiceimpl.update(id, color);
        } else {
            // Xử lý khi không có ảnh mới được tải lên
            Brand brand1 = brandResponsitory.findById(UUID.fromString(brand)).orElse(null);
            color.setBrand(brand1);
            color.setPrice(price);
            color.setStatus(status);
            color.setName(name);

            colorServiceimpl.update(id, color);
        }

        return "redirect:/color/index";
    }

}
