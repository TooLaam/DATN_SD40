package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.repuest.HoaDonKhachHangResquest;
import com.example.sd40.service.HoaDon.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/bill")
public class HoaDonController {


    @Autowired
    private HoaDonService hoaDonService;

}
