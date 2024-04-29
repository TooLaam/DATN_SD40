package com.example.sd40.controller.SanPhamController;

import com.example.sd40.entity.San_pham.GiamGIa;
import com.example.sd40.entity.San_pham.KichCo;
import com.example.sd40.entity.San_pham.ThuongHieu;
import com.example.sd40.service.SanPham.KichCoService;
import com.example.sd40.service.SanPham.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
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

    Date currentDate = new Date(System.currentTimeMillis());

    @GetMapping("/detail/{id}")
    public ResponseEntity<KichCo> detail(Model model,
                                          @PathVariable("id")Long id){
        KichCo kichCo = kichCoService.detail(id);
        if (kichCo != null) {
            return new ResponseEntity<>(kichCo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id")String id,
                                    @RequestParam("ten")String ten,
                                    @RequestParam("trangThai")Integer status
    ) throws ParseException {
        List<KichCo> kichCos = kichCoService.findByNameUpdate(ten,Long.valueOf(id));
        if (!kichCos.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        } else {
            KichCo kichCo = kichCoService.detail(Long.valueOf(id));

            kichCo.setTen(ten);
            kichCo.setTrangThai(status);
            kichCo.setNgaySua(currentDate);
            kichCoService.update(kichCo);
            return ResponseEntity.ok("ok");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestParam("ten")String ten,
            @RequestParam("trangThai")Integer status
    ) throws ParseException {
        List<KichCo> kichCos = kichCoService.findByName(ten);
        if (!kichCos.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        } else {

            KichCo kichCo = new KichCo();
            kichCo.setTen(ten);
            kichCo.setTrangThai(status);
            kichCo.setNgayTao(currentDate);
            kichCoService.add(kichCo);
            return ResponseEntity.ok("ok");
        }
    }
}
