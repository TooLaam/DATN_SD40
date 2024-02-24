package com.example.sd40.controller;
import com.example.sd40.entity.CartDetail;
import com.example.sd40.service.serviceimpl.CartServiceImpl;
import com.example.sd40.service.serviceimpl.CartdetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@Controller
@RequestMapping("/cart-detail")
public class CartdetailController {
    @Autowired
    CartdetailServiceImpl cartdetailService;
    @Autowired
    CartServiceImpl cartService;

    @GetMapping("/index")
    public String show_data_cart(Model model) {
        model.addAttribute("listCartDetail", cartdetailService.getAll());
        model.addAttribute("listCart", cartService.getAll());
        model.addAttribute("listProduct", "");
        model.addAttribute("listQuantity", cartdetailService.getAll());
        model.addAttribute("view", "/CartDetail/index.jsp");
        return "redirect:/cart/index";
    }
    @PostMapping("/create_cart_detail")
    public String create_cart(Model model,
                              @PathVariable("Cartdetail") UUID cartdetailid,
                              @RequestParam("ProductDetailId") UUID productdetailid,
                              @RequestParam("Quantity") Integer quantity,
                              @RequestParam("Price") BigDecimal price
    ) {
        CartDetail cartDetail = new CartDetail();
        cartdetailService.update(cartdetailid,cartDetail);
        return "redirect:/cart/index";
    }


    @GetMapping("delete_cart_detail")
    public String delete_cart(Model model, @RequestParam("CartDetailId") UUID cartdetailid) {
        cartdetailService.delete(cartdetailid);
        return "redirect:/cart/index";
    }
}
