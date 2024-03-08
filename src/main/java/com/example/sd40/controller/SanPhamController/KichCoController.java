package com.example.sd40.controller.SanPhamController;

import com.example.sd40.entity.San_pham.KichCo;
import com.example.sd40.entity.San_pham.ThuongHieu;
import com.example.sd40.service.SanPham.KichCoService;
import com.example.sd40.service.SanPham.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/kichco")
public class KichCoController {
    @Autowired
    KichCoService kichCoService;

    @GetMapping("/index")
    public String getAll(Model model){
        model.addAttribute("listKC",kichCoService.findAll());
        model.addAttribute("view","/SanPham/KichCo/index.jsp");
        return "index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id")Long id){
        model.addAttribute("kc",kichCoService.detail(id));
        model.addAttribute("listKC",kichCoService.findAll());
        model.addAttribute("view","/SanPham/KichCo/index.jsp");
        return "index";
    }
    Date currentDate = new Date(System.currentTimeMillis());

    @PostMapping("/update")
    public String update(@RequestParam("id")String id,
                         @RequestParam("ten")String ten,
                         @RequestParam("status")Integer status,
                         Model model
    ){
        if (id.isBlank()){
            model.addAttribute("err","Bạn phải chọn đối tượng kích cỡ ở danh sách");
            model.addAttribute("listKC",kichCoService.findAll());
            model.addAttribute("view","/SanPham/KichCo/index.jsp");
            return "index";
        }
        else {
            List<KichCo> kichCos = kichCoService.findByNameUpdate(ten,Long.valueOf(id));
            if (!kichCos.isEmpty()){
                model.addAttribute("errUpdate","Trùng tên. Vui lòng chọn tên khác !!!");
                model.addAttribute("kc",kichCoService.detail(Long.valueOf(id)));
                model.addAttribute("listKC",kichCoService.findAll());
                model.addAttribute("view","/SanPham/KichCo/index.jsp");
                return "index";
            }
            KichCo kichCo = kichCoService.detail(Long.valueOf(id));

            kichCo.setTen(ten);
            kichCo.setTrangThai(status);
            kichCo.setNgaySua(currentDate);
            kichCoService.update(kichCo);

            return ("redirect:/kichco/index");
        }


    }

    @PostMapping("/add")
    public String add(@RequestParam("ten")String ten,Model model,
                      @RequestParam("status")Integer status) {
        List<KichCo> kichCos = kichCoService.findByName(ten);
        if (!kichCos.isEmpty()) {
            model.addAttribute("errName", "Tên thêm mới trùng với trong danh sách. Vui lòng chọn tên mới !!!");
            model.addAttribute("active", "nav-link active");
            model.addAttribute("listKC", kichCoService.findAll());
            model.addAttribute("view", "/SanPham/KichCo/index.jsp");
            return "index";
        } else {
            KichCo kichCo = new KichCo();
            kichCo.setTen(ten);
            kichCo.setTrangThai(status);
            kichCo.setNgayTao(currentDate);
            kichCoService.add(kichCo);

            return ("redirect:/kichco/index");

        }
    }
}
