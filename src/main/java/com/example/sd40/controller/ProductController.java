package com.example.sd40.controller;


import com.example.sd40.entity.*;
import com.example.sd40.entity.ViewModels.ProductView;
import com.example.sd40.repository.*;
import com.example.sd40.service.EmployeeService;
import com.example.sd40.service.serviceimpl.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductServiceimpl productServiceimpl;
    @Autowired
    private BrandServiceimpl brandServiceimpl;
    @Autowired
    private CategoryServiceimpl categoryServiceimpl;
    @Autowired
    private ProductDetailServiceimpl productDetailServiceimpl;
    @Autowired
    private ProductImageServiceimpl productImageServiceimpl;
    @Autowired

    private EmployeeService employeeService;
    @Autowired
    private ProductResponsitory productResponsitory;

    private ColorServiceimpl colorServiceimpl;

    @Autowired
    private CategoryDetailResponsitory categoryDetailResponsitory;

    @GetMapping("/index")

    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("Name") != null){
            //Nếu đã đăng nhập vào trang index
            String username = (String) session.getAttribute("username");
            String password = (String) session.getAttribute("password");
            Employee checkLogin = employeeService.login(username,password);
            model.addAttribute("empLogin",checkLogin);
            //////
            model.addAttribute("listProduct", productServiceimpl.getAll());
            model.addAttribute("listBrand", brandServiceimpl.getAll());
            model.addAttribute("listCategory", categoryServiceimpl.getAll());
            model.addAttribute("Product", new Product());
            model.addAttribute("view", "/Product/index.jsp");
            return "index";
        }
        //Nếu chưa đăng nhập thì return về trang logina
        return "login";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("listCategory", categoryServiceimpl.getAll());
        model.addAttribute("Product", new Product());
        model.addAttribute("view", "/Product/createProduct.jsp");
        return "index";
    }

    @GetMapping("/indexcus")
    public String show_data_product_cus(Model model,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ProductView> entitiesPage = productServiceimpl.getAllProductWithPagination(pageable);
        model.addAttribute("entitiesPage", entitiesPage);
        model.addAttribute("listProduct", entitiesPage.getContent());
        model.addAttribute("quantityProduct", entitiesPage.getContent().size());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("view", "/product/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/indexcus/{name}")
    public String show_data_product_cus_search(Model model,
                                               @PathVariable("name") String name,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ProductView> entitiesPage = productServiceimpl.getAllProductByName(name, pageable);
        model.addAttribute("entitiesPage", entitiesPage);
        model.addAttribute("listProduct", entitiesPage.getContent());
        model.addAttribute("quantityProduct", entitiesPage.getContent().size());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("view", "/product/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/indexcus/brand/{brandId}")
    public String show_data_product_cus_brand(Model model,
                                              @PathVariable("brandId") UUID brandId,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ProductView> entitiesPage = productServiceimpl.getAllProductByBrand(brandId, pageable);
        model.addAttribute("entitiesPage", entitiesPage);
        model.addAttribute("listProduct", entitiesPage.getContent());
        model.addAttribute("quantityProduct", entitiesPage.getContent().size());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("view", "/product/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/indexcus/price/{min}/{max}")
    public String show_data_product_cus_price(Model model,
                                              @PathVariable("min") BigDecimal min,
                                              @PathVariable("max") BigDecimal max,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ProductView> entitiesPage = productServiceimpl.getAllProductByPrice(min, max, pageable);
        model.addAttribute("entitiesPage", entitiesPage);
        model.addAttribute("listProduct", entitiesPage.getContent());
        model.addAttribute("quantityProduct", entitiesPage.getContent().size());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("view", "/product/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam String name, RedirectAttributes attributes) {
        attributes.addAttribute("name", name);
        return "redirect:/product/indexcus/{name}";
    }

    //    @PostMapping("/add")
//    public String add(Model model,
//                      @RequestParam("name") String name,
//                      @RequestParam("availableQuantity") Integer availableQuantity,
//                      @RequestParam("sold") Integer sold,
//                      @RequestParam("likes") Integer likes,
//                      @RequestParam("status") Integer status,
//                      @RequestParam("descripTion") String descripTion,
//                      @RequestParam("brand") String brandId,
//                      @RequestParam("list") List<String> list) {
//        Brand brand = brandServiceimpl.getOne(UUID.fromString(brandId));
//
//        List<CategoryDetail> categoryDetails = new ArrayList<>();
//        for (String categoryId : list) {
//            CategoryDetail categoryDetail = categoryDetailServiceimpl.getOne(UUID.fromString(categoryId));
//            if (categoryDetail != null) {
//                categoryDetails.add(categoryDetail);
//
//            }
//
//            Product product = new Product(name, availableQuantity, sold, likes, status, descripTion, brand, (List<CategoryDetail>) categoryDetail);
//            product.setList(categoryDetails); // Set the one-to-many relationship
//            productServiceimpl.add(product);
//
//
//        }
//        return "redirect:/product/index";
//    }
    @PostMapping("/add")
    public String add(Model model,
                      @RequestParam("name") String name,
                      @RequestParam("availableQuantity") Integer availableQuantity,
                      @RequestParam("sold") Integer sold,
                      @RequestParam("likes") Integer likes,
                      @RequestParam("status") Integer status,
                      @RequestParam("descripTion") String descripTion,
                      @RequestParam("brand") UUID brandId,
                      @RequestParam("category") UUID categoryId
    ) {
        // Tạo một đối tượng Product
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setAvailableQuantity(availableQuantity);
        newProduct.setSold(sold);
        newProduct.setLikes(likes);
        newProduct.setStatus(status);
        newProduct.setDescripTion(descripTion);
        newProduct.setCreatedDate(java.sql.Date.valueOf(LocalDate.now()));
        // Tạo và lưu đối tượng Brand
        newProduct.setBrand(Brand.builder().Id(brandId).build());
        newProduct = productServiceimpl.add(newProduct);

        // Lặp qua danh sách CategoryValue từ ProductRepuest và tạo các CategoryDetail
        // Tạo đối tượng Category từ categoryValue và gán cho CategoryDetail
//            System.out.println("Cai nay o dau ta" + categoryServiceimpl.getOne(idCategory));
//            System.out.println("id"+idCategory);
        Category category = categoryServiceimpl.getOne(categoryId);
        if (category == null) {
            System.out.println(category.getId() + "Quân");
        } else {
            CategoryDetail categoryDetail = new CategoryDetail();
            categoryDetail.setCategory(category);
            categoryDetail.setQuanTity(0);
            categoryDetail.setProduct(newProduct);
            categoryDetailResponsitory.save(categoryDetail);
        }
        // Sau khi đã tạo sản phẩm và liên kết với danh mục, bạn có thể thực hiện bất kỳ hành động nào khác cần thiết, chẳng hạn chuyển hướng hoặc hiển thị thông báo thành công.
        return "redirect:/product/index"; // Chuyển hướng đến trang danh sách sản phẩm hoặc trang khác tùy theo nhu cầu của bạn.
    }


    @GetMapping("/detail/{id}")
    public String detailSPCT(Model model,
                         @PathVariable("id") UUID id) {
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listCategory", categoryServiceimpl.getAll());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("sp", productServiceimpl.getOne(id));
        model.addAttribute("view", "/Product/editProduct.jsp");
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model,
                         @PathVariable("id") UUID id) {
        productServiceimpl.delete(id);
        return "redirect:/product/index";
    }
//    @GetMapping("/detailSPCT/{id}")
//    public String detailSPCT(Model model,
//                         @PathVariable("id") UUID id
//    ) {
//        ProductDetail productDetail = productDetailServiceimpl.getOne(id);
//        model.addAttribute("productDetail", productDetail);
//        model.addAttribute("listProduct", productServiceimpl.getAll());
//        model.addAttribute("listColor", colorServiceimpl.getAll());
//        model.addAttribute("listProductImage", productImageServiceimpl.getAll());
//        model.addAttribute("listProductDetail", productDetailServiceimpl.getAll());
////        model.addAttribute("sp", productDetailServiceimpl.getOne(id));
//        model.addAttribute("view", "/ProductDetail/editProductDetail.jsp");
//        return "index";
//    }
    @PostMapping("/update/{id}")
    public String update(Model model,
                         @PathVariable("id") UUID id,
                         @ModelAttribute("product") Product product) {
        productServiceimpl.update(id, product);
        return "redirect:/product/index";
    }


}
