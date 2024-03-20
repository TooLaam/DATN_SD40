package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.entity.Gio_hang.GioHangDetail;
import com.example.sd40.repuest.HoaDonKhachHangResquest;
import com.example.sd40.service.GioHang.GioHangDetailSerVice;
import com.example.sd40.service.HoaDon.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bill")
public class HoaDonController {


    @Autowired
    private GioHangDetailSerVice gioHangDetailSerVice;

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/payment")
    private String viewthanhToan(Model model){
        Double total = Double.parseDouble("0");
        for (GioHangDetail product : gioHangDetailSerVice.gioHangDetails(Long.parseLong("1"))) {
            total = total + product.getQuantity() * product.getPrice();
        }
        model.addAttribute("listCartDetail", gioHangDetailSerVice.gioHangDetails(Long.parseLong("1")));
        model.addAttribute("totalMoney",total);
        model.addAttribute("view", "/pay/pay.jsp");
        return "/customerFE/index";

    }

    @PostMapping("/placeorder")
    private String thanhToan(HoaDonKhachHangResquest resquest, Model model){
        try {
            resquest.setIdUser("1");
            System.out.println(hoaDonService.createHoaDonKhachHang(resquest));;

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "redirect:/home";

    }
}
