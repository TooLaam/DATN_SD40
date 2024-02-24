package com.example.sd40.controller;

import com.example.sd40.entity.ProductImage;
import com.example.sd40.repository.ProductDetailResponsitory;
import com.example.sd40.repository.ProductResponsitory;
import com.example.sd40.service.ProductImageService;
import com.example.sd40.service.serviceimpl.ProductDetailServiceimpl;
import com.example.sd40.service.serviceimpl.ProductServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product_image")
public class ProductImageController {
    @Autowired
    private ProductServiceimpl productServiceimpl;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private ProductDetailServiceimpl productDetailServiceimpl;
    @Autowired
    private ProductResponsitory productResponsitory;
    @Autowired
    private ProductDetailResponsitory productDetailResponsitory;
    private final Path root = Paths.get("src/main/resources/static/assets/img/product");

    @GetMapping("/index")
    public String hienThi(Model model) {
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listProductDetail", productDetailServiceimpl.getAll());
        model.addAttribute("listProductImage", productImageService.getAll());
        model.addAttribute("ProductImage", new ProductImage());
        model.addAttribute("view", "/ProductImage/index.jsp");
        return "index";
    }

//    @GetMapping("/indexcus" )
//    public String show_data_product_cus(Model model){
//
//        model.addAttribute("listProduct",productServiceimpl.getAll());
//        model.addAttribute("Product",new Product());
//        model.addAttribute("listCategory",categoryServiceimpl.getAll());
//        model.addAttribute("listColor",colorServiceimpl.getAll());
//        model.addAttribute("view", "/product/index.jsp");
//        return "/customerFE/index";
//    }
//
//    @PostMapping("/add")
//    public String add(Model model,
//                      @RequestParam("name") String name,
//                      @RequestParam("staTus") Integer staTus,
//                      @RequestParam("productDetail")String productDetail
//    ){
//
//        ProductDetail productDetail1 = productDetailResponsitory.findById(UUID.fromString(productDetail)).orElse(null);
//        ProductImage productImage = new ProductImage(name,staTus,productDetail1);
//        productImageService.add(productImage);
//
//        return "redirect:/product_image/index";

    @PostMapping(value = "add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(Model model,
                         @RequestParam("files") MultipartFile[] files,
                         @RequestParam("staTus") Integer staTus,
                         @RequestParam("productDetail") UUID id) {

        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file -> {
                ProductImage a = new ProductImage();
                File uploadRootDir = new File(String.valueOf(root));
                if (!uploadRootDir.exists()) {
                    uploadRootDir.mkdirs();
                }
                try {
                    String filename = file.getOriginalFilename();
                    File serverFile = new File(uploadRootDir.getAbsoluteFile() + File.separator + filename);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(file.getBytes());
                    a.setName(filename);
                    a.setStaTus(staTus);
                    a.setProductDetail(productDetailServiceimpl.getOne(id));
                    productImageService.add(a);
                    stream.close();
                } catch (Exception e) {

                }
            });

            message = "Uploaded the files successfully: " + fileNames;
        } catch (Exception e) {
            message = "Fail to upload files!";
        }
        return "redirect:/product_image/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") UUID id) {
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listProductDetail", productDetailServiceimpl.getAll());
        model.addAttribute("listProductImage", productImageService.getAll());
        model.addAttribute("imageSP", productImageService.getOne(id));
        model.addAttribute("view", "/ProductImage/index.jsp");
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model,
                         @PathVariable("id") UUID id) {
        productImageService.delete(id);
        return "redirect:/product_image/index";
    }

    //    @PostMapping("/update/{id}")
//    public String update(Model model,
//                         @PathVariable("id") UUID id,
//                         @ModelAttribute("productImage") ProductImage productImage){
//
//        productImageService.update(id, productImage);
//        return "redirect:/product_image/index";
//    }
    @PostMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String update(Model model,
                         @PathVariable("id") UUID id,
                         @RequestParam("files") List<MultipartFile> files,
                         @RequestParam("staTus") Integer staTus,
                         @RequestParam("productDetail") UUID idSP) {

        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();

            for (MultipartFile file : files) {
                ProductImage a = new ProductImage();
                File uploadRootDir = new File(String.valueOf(root));
                if (!uploadRootDir.exists()) {
                    uploadRootDir.mkdirs();
                }
                try {
                    String filename = file.getOriginalFilename();
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + filename);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(file.getBytes());
                    a.setName(filename);
                    a.setStaTus(staTus);
                    a.setProductDetail(productDetailServiceimpl.getOne(idSP));
                    productImageService.update(id, a);
                    stream.close();
                    fileNames.add(filename);
                } catch (Exception e) {
                    // Xử lý lỗi tải lên ảnh
                    // Ví dụ: ghi log hoặc thông báo lỗi
                    e.printStackTrace();
                }
            }

            message = "Uploaded the files successfully: " + fileNames;
        } catch (Exception e) {
            // Xử lý lỗi chung
            // Ví dụ: ghi log hoặc thông báo lỗi
            e.printStackTrace();
            message = "Fail to upload files!";
        }
        return "redirect:/product_image/index";
    }

}
