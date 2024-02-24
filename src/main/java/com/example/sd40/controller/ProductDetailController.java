package com.example.sd40.controller;


import com.example.sd40.entity.*;
import com.example.sd40.repository.ProductImageResponsitory;
import com.example.sd40.service.serviceimpl.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product_detail")
public class ProductDetailController {
    @Autowired
    private ProductServiceimpl productServiceimpl;
    @Autowired
    private ColorServiceimpl colorServiceimpl;
    @Autowired
    private ProductDetailServiceimpl productDetailServiceimpl;
    @Autowired
    private ProductImageServiceimpl productImageServiceimpl;
    @Autowired
    private BrandServiceimpl brandServiceimpl;
    @Autowired
    private CategoryServiceimpl categoryServiceimpl;
    @Autowired
    private ProductImageResponsitory productImageResponsitory;
    private final Path root = Paths.get("src/main/resources/static/assets/img/product");

    @GetMapping("/index")
    public String hienThi(Model model) {
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listColor", colorServiceimpl.getAll());
        model.addAttribute("listProductImage", productImageServiceimpl.getAll());
        model.addAttribute("listProductDetail", productDetailServiceimpl.getAll());
        model.addAttribute("ProductDetail", new ProductDetail());
        model.addAttribute("view", "/ProductDetail/index.jsp");
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listColor", colorServiceimpl.getAll());
        model.addAttribute("listProductImage", productImageServiceimpl.getAll());
        model.addAttribute("listProductDetail", productDetailServiceimpl.getAll());
        model.addAttribute("ProductDetail", new ProductDetail());
        model.addAttribute("view", "/ProductDetail/createProductDetail.jsp");
        return "index";
    }

    @GetMapping("/indexcus/{productDetailId}")
    public String show_data_product_cus(@PathVariable("productDetailId") UUID productDetailId, Model model) {

        model.addAttribute("listProduct", productServiceimpl.getAllProduct().subList(0, 4));
        model.addAttribute("productDetail", productDetailServiceimpl.getProductDetailById(productDetailId));
        model.addAttribute("image", productImageServiceimpl.getByProductDetailId(productDetailId).get(0));
        model.addAttribute("listImage", productImageServiceimpl.getByProductDetailId(productDetailId));
        model.addAttribute("listColor", colorServiceimpl.getAllByProductDetailId(productDetailId));
        model.addAttribute("view", "/detail/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/increase/{productDetailId}")
    public String IncreaseProductDetail(@PathVariable("productDetailId") UUID productDetailId) {
        ProductDetail productDetail = productDetailServiceimpl.getOne(productDetailId);

        if (productDetail == null) {
            // Xử lý trường hợp sản phẩm không tồn tại, ví dụ: thông báo lỗi.
            return "redirect:/error";
        }

        productDetail.setQuantity(productDetail.getQuantity() + 1);
        productDetailServiceimpl.update(productDetailId, productDetail);
        return "redirect:/product_detail/indexcus/{productDetailId}";
    }

    @GetMapping("/reduce/{productDetailId}")
    public String ReduceProductDetail(@PathVariable("productDetailId") UUID productDetailId) {
        ProductDetail productDetail = productDetailServiceimpl.getOne(productDetailId);

        if (productDetail == null) {
            // Xử lý trường hợp sản phẩm không tồn tại, ví dụ: thông báo lỗi.
            return "redirect:/error";
        }

        if(productDetail.getQuantity() > 1){
            productDetail.setQuantity(productDetail.getQuantity() - 1);
            productDetailServiceimpl.update(productDetailId, productDetail);
        }
        return "redirect:/product_detail/indexcus/{productDetailId}";
    }

    @PostMapping(value = "add")
    public String add(Model model,
                         @ModelAttribute("ProductRepuest") ProductRepuest productRepuest,
                         @RequestParam("files") List<MultipartFile> files,
                         RedirectAttributes redirectAttributes
                         ) throws IOException {

        ProductDetail a = new ProductDetail();
            a.setImportPrice(productRepuest.getImportPrice());
            a.setPrice(productRepuest.getPrice());
            a.setQuantity(productRepuest.getQuantity());
            a.setStatus(0);
            a.setDescripTion(productRepuest.getDescripTion());
        a.setCreatedDate(java.sql.Date.valueOf(LocalDate.now()));
        a.setProduct(Product.builder().id(productRepuest.getProduct()).build());
        a.setColor(Color.builder().Id(productRepuest.getColor()).build());
        productDetailServiceimpl.add(a);
        for (MultipartFile file : files) {
            ProductImage spa = new ProductImage();
            ClassPathResource resource = new ClassPathResource("static/assets/img/product/");
            String uploadDir = resource.getFile().getAbsolutePath();
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }
            spa.setName(file.getOriginalFilename());
            spa.setStaTus(0);
            spa.setProductDetail(a);
            productImageServiceimpl.add(spa);
        }

        return "redirect:/product_detail/index";
    }
    @PostMapping(value = "update/{id}")
    public String update(Model model,
                         @ModelAttribute("ProductRepuest") ProductRepuest productRepuest,
                         @RequestParam("files") List<MultipartFile> files,
                         @PathVariable("id") UUID id) throws IOException {
        System.out.println(id+"??");
        ProductDetail existingProductDetail = productDetailServiceimpl.getOne(id);
//        if (existingProductDetail != null) {
            existingProductDetail.setImportPrice(productRepuest.getImportPrice());
            existingProductDetail.setPrice(productRepuest.getPrice());
            existingProductDetail.setQuantity(productRepuest.getQuantity());
            existingProductDetail.setStatus(productRepuest.getStatus());
            existingProductDetail.setDescripTion(productRepuest.getDescripTion());
            existingProductDetail.setProduct(Product.builder().id(productRepuest.getProduct()).build());
            existingProductDetail.setColor(Color.builder().Id(productRepuest.getColor()).build());
            existingProductDetail.setCreatedDate(java.sql.Date.valueOf(LocalDate.now()));
            productDetailServiceimpl.update(id, existingProductDetail);
//            if (files != null && !files.isEmpty()) {
            System.out.println(existingProductDetail.getId()+"heheh");
                for (MultipartFile file : files) {
                    ProductImage spa = new ProductImage();
                    ClassPathResource resource = new ClassPathResource("static/assets/img/product/");
                    String uploadDir = resource.getFile().getAbsolutePath();
                    File uploadPath = new File(uploadDir);
                    if (!uploadPath.exists()) {
                        uploadPath.mkdirs();
                    }
                    spa.setName(file.getOriginalFilename());
                    spa.setStaTus(0);
                    System.out.println(existingProductDetail.getId()+"heheh");
                    spa.setProductDetail(existingProductDetail);
                    productImageServiceimpl.add(spa);
//                }
            }
//        }
        return "redirect:/product_detail/index";
    }


    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") UUID id
    ) {
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listColor", colorServiceimpl.getAll());
        model.addAttribute("listProductImage", productImageServiceimpl.getAll());
        model.addAttribute("listProductDetail", productDetailServiceimpl.getAll());
        model.addAttribute("listCategory", categoryServiceimpl.getAll());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("detailSP", productDetailServiceimpl.getOne(id));
        model.addAttribute("view", "/ProductDetail/editProductDetail.jsp");
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model,
                         @PathVariable("id") UUID id) {
        productDetailServiceimpl.delete(id);
        return "redirect:/product_detail/index";
    }
    @GetMapping("/deleteImage/{id}")
    public String deleteImg(Model model, HttpServletRequest request,
                            @PathVariable("id") UUID id) {
        productImageServiceimpl.delete(id);
        String referer = request.getHeader("Referer");
        // Chuyển hướng trở lại trang gọi đến (referer)
        return "redirect:" + referer;

    }

//    @PostMapping("/update/{id}")
//    public String update(Model model,
//                         @PathVariable("id") UUID id,
//                         @ModelAttribute("productDetail") ProductDetail productDetail) {
//        productDetailServiceimpl.update(id, productDetail);
//        return "redirect:/product_detail/index";
//    }
}
