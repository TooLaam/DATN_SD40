package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.entity.Gio_hang.GioHangDetail;
import com.example.sd40.service.GioHang.GioHangDetailSerVice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private GioHangDetailSerVice gioHangDetailSerVice;


    @GetMapping
    private String getAll(Model model){
        BigDecimal total = BigDecimal.ZERO;
        for (GioHangDetail product : gioHangDetailSerVice.gioHangDetails(Long.parseLong("3"))) {
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())));

        }
        model.addAttribute("listCartDetail", gioHangDetailSerVice.gioHangDetails(Long.parseLong("3")));
        model.addAttribute("totalMoney",total);
        model.addAttribute("view", "/cart/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/delete/{id}")
    private String delete(@PathVariable Long id, Model model){
       gioHangDetailSerVice.delete(id);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    private String update(@RequestParam("id") Long id, @RequestParam("quantity") int quantity, Model model){
        gioHangDetailSerVice.updateSanPham(id,quantity );
        return "redirect:/cart";
    }

}
