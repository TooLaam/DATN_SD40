package com.example.sd40.controller.HoaDonController;

import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.repuest.HoaDonDetailRequest;
import com.example.sd40.service.HoaDon.HoaDonDetalService;
import com.example.sd40.service.HoaDon.HoaDonService;
import com.example.sd40.service.HoaDon.LichSuService;
import com.example.sd40.service.SanPham.CTSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private CTSPService ctspService;

    @GetMapping("/index")
    private String findAllByStatus( Model model){
        hoaDonService.create();
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


    @GetMapping("/delete/{id}")
    private String viewHuyHoaDon(@PathVariable Long id, Model model){
        model.addAttribute("id",id);
        model.addAttribute("view","/HoaDon/HuyHoaDon.jsp");
        return "index";
    }

    @GetMapping("/change/{id}")
    private String viewChageStatus(@PathVariable Long id, Model model){
        model.addAttribute("id",id);
        model.addAttribute("view","/HoaDon/ChangeHoaDon.jsp");
        return "index";
    }

    @PostMapping("/delete/{id}")
    private String huyHoaDon(@PathVariable Long id, @RequestParam("note") String note, Model model){
        hoaDonService.huyHoaDon(id,note);
        return "redirect:/bill/detail/" + id;
    }

    @PostMapping("/change/{id}")
    private String huyChageStatus(@PathVariable Long id, @RequestParam("note") String note, Model model){
        hoaDonService.changeStatus(id,note);
        return "redirect:/bill/detail/" + id;
    }

    @GetMapping("/update/{id}")
    private String viewUpdateBilldetail(@PathVariable Long id,@RequestParam("quantity") String quantity, Model model){
        model.addAttribute("id",id);
        model.addAttribute("quantity",quantity);
        model.addAttribute("view","/HoaDon/updateSanPham.jsp");
        return "index";
    }


    @GetMapping("/delete-bill-detail/{id}")
    private String viewXoaBilldetail(@PathVariable Long id, Model model){
        model.addAttribute("id",id);
        model.addAttribute("view","/HoaDon/XoaSanPham.jsp");
        return "index";
    }

    @GetMapping("/create-bill-detail/{id}")
    private String viewCreateBilldetail(@PathVariable Long id, Model model){
        model.addAttribute("id",id);
        model.addAttribute("sanPhams",ctspService.findAll());
        model.addAttribute("view","/HoaDon/themSanPham.jsp");
        return "index";
    }


    @PostMapping("/create-bill-detail/{id}")
    private String createBilldetail(@PathVariable Long id,HoaDonDetailRequest request, Model model){
        hoaDonDetalService.save(request);
        return "redirect:/bill/detail/" + id;
    }
    @PostMapping("/delete-bill-detail/{id}")
    private String xoaBilldetail(@PathVariable Long id,@RequestParam("note") String note, Model model){
        HoaDonChiTiet hoaDonChiTiet = hoaDonDetalService.delete(id,note);
        return "redirect:/bill/detail/" + hoaDonChiTiet.getHoaDon().getId();
    }

    @PostMapping("/update/{id}")
    private String updateBilldetail(@PathVariable Long id,@RequestParam("quantity") int quantity,@RequestParam("note") String note, Model model){
        HoaDonChiTiet hoaDonChiTiet = hoaDonDetalService.update(id,quantity,note);
        return "redirect:/bill/detail/" + hoaDonChiTiet.getHoaDon().getId();
    }
}

