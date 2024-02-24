package com.example.sd40.controller;


import com.example.sd40.entity.Category;
import com.example.sd40.entity.CategoryDetail;
import com.example.sd40.entity.Product;
import com.example.sd40.repository.CategoryResponsitory;
import com.example.sd40.repository.ProductResponsitory;
import com.example.sd40.service.serviceimpl.CategoryDetailServiceimpl;
import com.example.sd40.service.serviceimpl.CategoryServiceimpl;
import com.example.sd40.service.serviceimpl.ProductServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/category_detail")
public class CategoryDetailController {

    @Autowired
    private ProductServiceimpl productServiceimpl;
    @Autowired
    private CategoryServiceimpl categoryServiceimpl;
    @Autowired
    private CategoryDetailServiceimpl categoryDetailServiceimpl ;
    @Autowired
    private ProductResponsitory productResponsitory;
    @Autowired
    private CategoryResponsitory categoryResponsitory;
    @GetMapping("/index")
    public String hienThi(Model model){
        model.addAttribute("listProduct",productServiceimpl.getAll());
        model.addAttribute("listCategory",categoryServiceimpl.getAll());
        model.addAttribute("listCategoryDetail",categoryDetailServiceimpl.getAll());
        model.addAttribute("CategoryDetail",new CategoryDetail());
        model.addAttribute("view", "/CategoryDetail/index.jsp");
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

    @PostMapping("/add")
    public String add(Model model,
                      @RequestParam("quanTity") Integer quanTity,
                      @RequestParam("product") String product,
                      @RequestParam("category")String category
    ){
        Product product1 = productResponsitory.findById(UUID.fromString(product)).orElse(null);
        Category category1 = categoryResponsitory.findById(UUID.fromString(category)).orElse(null);
        CategoryDetail detail = new CategoryDetail(quanTity,product1,category1);
        categoryDetailServiceimpl.add(detail);
        return "redirect:/category_detail/index";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") UUID id){
        model.addAttribute("listProduct",productServiceimpl.getAll());
        model.addAttribute("listCategory",categoryServiceimpl.getAll());
        model.addAttribute("listCategoryDetail",categoryDetailServiceimpl.getAll());
        model.addAttribute("detail",categoryDetailServiceimpl.getOne(id));
        model.addAttribute("view", "/CategoryDetail/index.jsp");
        return "index";
    }
    @GetMapping("/delete/{id}")
    public String delete(Model model,
                         @PathVariable("id") UUID id){
        categoryDetailServiceimpl.delete(id);
        return "redirect:/category_detail/index";
    }
    @PostMapping("/update/{id}")
    public String update(Model model,
                         @PathVariable("id") UUID id,
                         @ModelAttribute("categoryDetail") CategoryDetail categoryDetail){
        categoryDetailServiceimpl.update(id,categoryDetail);
        return "redirect:/category_detail/index";
    }
}
