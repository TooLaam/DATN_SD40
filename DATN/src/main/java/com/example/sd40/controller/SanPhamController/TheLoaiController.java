package com.example.sd40.controller.SanPhamController;

import com.example.sd40.entity.San_pham.KichCo;
import com.example.sd40.entity.San_pham.TheLoai;

import com.example.sd40.service.SanPham.LoaiGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/theloai")
public class TheLoaiController {
    @Autowired
    LoaiGiayService loaiGiayService;

    @GetMapping("/index")
    public String getAll(Model model){
        model.addAttribute("listTL",loaiGiayService.findAll());
        model.addAttribute("view","/SanPham/TheLoai/index.jsp");
        return "index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id")Long id){
        model.addAttribute("tl",loaiGiayService.detail(id));
        model.addAttribute("listTL",loaiGiayService.findAll());
        model.addAttribute("view","/SanPham/TheLoai/index.jsp");
        return "index";
    }
    Date currentDate = new Date(System.currentTimeMillis());

    @PostMapping("/update")
    public String update(@RequestParam("id")String id,Model model,
                         @RequestParam("ten")String ten,
                         @RequestParam("status")Integer status
    ){

        if (id.isBlank()){
            model.addAttribute("err","Bạn phải chọn đối tượng thể loại ở danh sách");
            model.addAttribute("listTL",loaiGiayService.findAll());
            model.addAttribute("view","/SanPham/TheLoai/index.jsp");
            return "index";
        }
        else {
            List<TheLoai> theLoais = loaiGiayService.findByNameUpdate(ten,Long.valueOf(id));
            if (!theLoais.isEmpty()){
                model.addAttribute("errUpdate","Trùng tên. Vui lòng đổi tên !!!");
                model.addAttribute("tl", loaiGiayService.detail(Long.valueOf(id)));
                model.addAttribute("listTL",loaiGiayService.findAll());
                model.addAttribute("view","/SanPham/TheLoai/index.jsp");
                return "index";
            }
            TheLoai theLoai = loaiGiayService.detail(Long.valueOf(id));

            theLoai.setTen(ten);
            theLoai.setTrangThai(status);
            theLoai.setNgaySua(currentDate);
            loaiGiayService.update(theLoai);

            return ("redirect:/theloai/index");
        }


    }

    @PostMapping("/add")
    public String add(@RequestParam("ten")String ten,Model model,
                      @RequestParam("status")Integer status){
        List<TheLoai> theLoais = loaiGiayService.findByName(ten);
        if (!theLoais.isEmpty()){
            model.addAttribute("errName","Tên thêm mới trùng với trong danh sách. Vui lòng chọn tên mới !!!");
            model.addAttribute("active","nav-link active");
            model.addAttribute("listTL",loaiGiayService.findAll());
            model.addAttribute("view","/SanPham/TheLoai/index.jsp");
            return "index";
        }

        TheLoai theLoai = new TheLoai();
        theLoai.setTen(ten);
        theLoai.setTrangThai(status);
        theLoai.setNgayTao(currentDate);
        loaiGiayService.add(theLoai);

        return ("redirect:/theloai/index");

    }
}
