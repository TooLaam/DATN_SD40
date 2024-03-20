package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.entity.Gio_hang.GioHangDetail;
import com.example.sd40.service.GioHang.GioHangDetailSerVice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private GioHangDetailSerVice gioHangDetailSerVice;


    @GetMapping
    private String getAll(Model model){
        Double total = Double.parseDouble("0");
        for (GioHangDetail product : gioHangDetailSerVice.gioHangDetails(Long.parseLong("1"))) {
            total = total + product.getQuantity() * product.getPrice();
        }
        model.addAttribute("listCartDetail", gioHangDetailSerVice.gioHangDetails(Long.parseLong("1")));
        model.addAttribute("totalMoney",total);
        model.addAttribute("view", "/cart/index.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/delete/{id}")
    private String delete(@PathVariable Long id, Model model){
       gioHangDetailSerVice.delete(id);
        return "redirect:/cart";
    }

}
