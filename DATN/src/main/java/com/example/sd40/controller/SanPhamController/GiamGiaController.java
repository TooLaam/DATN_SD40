package com.example.sd40.controller.SanPhamController;

import com.example.sd40.entity.San_pham.GiamGIa;
import com.example.sd40.entity.San_pham.KichCo;
import com.example.sd40.service.SanPham.GiamGiaService;
import com.example.sd40.service.SanPham.KichCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/giamgia")
public class GiamGiaController {
    @Autowired
    GiamGiaService giamGiaService;

    @GetMapping("/index")
    public String getAll(Model model){
        model.addAttribute("listGG",giamGiaService.getAll());
        model.addAttribute("view","/SanPham/GiamGia/index.jsp");
        return "index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id")Long id){
        model.addAttribute("gg",giamGiaService.detail(id));
        model.addAttribute("listGG",giamGiaService.getAll());
        model.addAttribute("view","/SanPham/GiamGia/index.jsp");
        return "index";
    }
    Date currentDate = new Date(System.currentTimeMillis());

    @PostMapping("/update")
    public String update(@RequestParam("id")String id,
                         @RequestParam("ten")String ten,
                         @RequestParam("status")Integer status,
                         @RequestParam("mucGiam")Integer mucGiam,
                         Model model
    ){
        if (id.isBlank()){
            model.addAttribute("err","Bạn phải chọn đối tượng chương trình giảm giá ở danh sách");
            model.addAttribute("listGG",giamGiaService.getAll());
            model.addAttribute("view","/SanPham/GiamGia/index.jsp");
            return "index";
        }
        else {
            List<GiamGIa> giamGIas = giamGiaService.findByNameUpdate(ten,Long.valueOf(id));
            if (!giamGIas.isEmpty()){
                model.addAttribute("errUpdate","Trùng tên. Vui lòng chọn tên khác !!!");
                model.addAttribute("gg",giamGiaService.detail(Long.valueOf(id)));
                model.addAttribute("listGG",giamGiaService.getAll());
                model.addAttribute("view","/SanPham/GiamGia/index.jsp");
                return "index";
            }
            GiamGIa giamGIa = giamGiaService.detail(Long.valueOf(id));

            giamGIa.setTen(ten);
            giamGIa.setTrangThai(status);
            giamGIa.setNgaySua(currentDate);
            giamGIa.setMucGiam(mucGiam);
            giamGiaService.update(giamGIa);

            return ("redirect:/giamgia/index");
        }


    }

    @PostMapping("/add")
    public String add(@RequestParam("ten")String ten,Model model,
                      @RequestParam("status")Integer status,
                      @RequestParam("mucGiam")Integer mucGiam
                      ) {
        List<GiamGIa> giamGIas = giamGiaService.findByName(ten);
        if (!giamGIas.isEmpty()) {
            model.addAttribute("errName", "Tên thêm mới trùng với trong danh sách. Vui lòng chọn tên mới !!!");
            model.addAttribute("active", "nav-link active");
            model.addAttribute("listKC", giamGiaService.getAll());
            model.addAttribute("view", "/SanPham/GiamGia/index.jsp");
            return "index";
        } else {
            GiamGIa giamGIa = new GiamGIa();
            giamGIa.setTen(ten);
            giamGIa.setTrangThai(status);
            giamGIa.setNgayTao(currentDate);
            giamGIa.setMucGiam(mucGiam);
            giamGiaService.add(giamGIa);

            return ("redirect:/giamgia/index");

        }
    }
}
