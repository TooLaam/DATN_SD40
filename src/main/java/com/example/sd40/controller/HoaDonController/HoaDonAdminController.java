package com.example.sd40.controller.HoaDonController;

import com.example.sd40.service.HoaDon.HoaDonDetalService;
import com.example.sd40.service.HoaDon.HoaDonService;
import com.example.sd40.service.HoaDon.LichSuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author quynh
 */
@Controller
@RequestMapping("/bill")
public class HoaDonAdminController {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private LichSuService lichSuService;

    @Autowired
    private HoaDonDetalService hoaDonDetalService;



    @GetMapping("/index")
    private String findAllByStatus( Model model){
        model.addAttribute("choXacNhans", hoaDonService.findAllByStatus(0));
        model.addAttribute("xacNhans", hoaDonService.findAllByStatus(1));
        model.addAttribute("choVanChuyens", hoaDonService.findAllByStatus(2));
        model.addAttribute("VanChuyens", hoaDonService.findAllByStatus(3));
        model.addAttribute("hoanThanhs", hoaDonService.findAllByStatus(4));
        model.addAttribute("huys", hoaDonService.findAllByStatus(5));
        model.addAttribute("view","/HoaDon/hoaDon.jsp");
        return "index";
    }

    @GetMapping("/detail/{id}")
    private String detail(@PathVariable Long id, Model model){
        model.addAttribute("hoaDon", hoaDonService.findByid(id));
        model.addAttribute("sanPhams", hoaDonDetalService.findAllByHoaDon(id));
        model.addAttribute("lichSus", lichSuService.findAllByHoaDon(id));
        model.addAttribute("view","/HoaDon/detail.jsp");
        return "index";
    }

}

