package com.example.sd40.controller;

import com.example.sd40.entity.Brand;
import com.example.sd40.entity.ViewModels.ProductView;
import com.example.sd40.service.serviceimpl.BrandServiceimpl;
import com.example.sd40.service.serviceimpl.ColorServiceimpl;
import com.example.sd40.service.serviceimpl.ProductServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class homeController {
    @Autowired
    ColorServiceimpl colorServiceimpl;
    @Autowired
    BrandServiceimpl brandServiceimpl;
    @Autowired
    ProductServiceimpl productServiceimpl;
    @GetMapping("/home" )
    public String home(Model model){

        ArrayList<ProductView> productSortBySold = productServiceimpl.getAllProduct();
        Collections.sort(productSortBySold, (p1, p2) -> Integer.compare(p2.getSold(), p1.getSold()));

        ArrayList<ProductView> productSortByCreatedDate = productServiceimpl.getAllProduct();
        Collections.sort(productSortByCreatedDate, (p1, p2) -> p1.getCreatedDate().compareTo(p2.getCreatedDate()));

        ArrayList<ProductView> listProduct = productServiceimpl.getAllProduct();

        Map<Brand, ProductView> firstProductInEachCategory = new LinkedHashMap<>();

        listProduct.forEach(product -> firstProductInEachCategory
                .putIfAbsent(product.getBrand(), product)
        );

        ArrayList<ProductView> uniqueQuantityProducts = new ArrayList<>(firstProductInEachCategory.values());

        model.addAttribute("productSortBySold", productSortBySold.subList(0, 4));
        model.addAttribute("productSortByCreatedDate", productSortByCreatedDate.subList(0, 4));
        model.addAttribute("uniqueQuantityProducts", uniqueQuantityProducts.subList(0, 4));
        model.addAttribute("view", "/home/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/blog" )
    public String blog(Model model){
        model.addAttribute("view", "/blog/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/blogPage1" )
    public String blogPage1(Model model){
        model.addAttribute("view", "/blogPage1/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/blogPage2" )
    public String blogPage2(Model model){
        model.addAttribute("view", "/blogPage2/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/blogPage3" )
    public String blogPage3(Model model){
        model.addAttribute("view", "/blogPage3/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/blogPage4" )
    public String blogPage4(Model model){
        model.addAttribute("view", "/blogPage4/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/blogPage5" )
    public String blogPage5(Model model){
        model.addAttribute("view", "/blogPage5/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/aboutus" )
    public String aboutUs(Model model){
        model.addAttribute("view", "/aboutus/index.jsp");
        return "/customerFE/index";
    }
}
