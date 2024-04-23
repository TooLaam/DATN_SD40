package com.example.sd40.controller.SanPhamController;

import com.example.sd40.entity.San_pham.KichCo;
import com.example.sd40.entity.San_pham.TheLoai;

import com.example.sd40.service.SanPham.LoaiGiayService;
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
    public ResponseEntity<TheLoai> detail(Model model,
                                         @PathVariable("id")Long id){
        TheLoai theLoai = loaiGiayService.detail(id);
        if (theLoai != null) {
            return new ResponseEntity<>(theLoai, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    Date currentDate = new Date(System.currentTimeMillis());

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id")String id,
                                    @RequestParam("ten")String ten,
                                    @RequestParam("trangThai")Integer status
    ) throws ParseException {
        List<TheLoai> theLoais = loaiGiayService.findByNameUpdate(ten,Long.valueOf(id));
        if (!theLoais.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        } else {
            TheLoai theLoai = loaiGiayService.detail(Long.valueOf(id));

            theLoai.setTen(ten);
            theLoai.setTrangThai(status);
            theLoai.setNgaySua(currentDate);
            loaiGiayService.update(theLoai);
            return ResponseEntity.ok("ok");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestParam("ten")String ten,
            @RequestParam("trangThai")Integer status
    ) throws ParseException {
        List<TheLoai> theLoais = loaiGiayService.findByName(ten);
        if (!theLoais.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        } else {
            TheLoai theLoai = new TheLoai();
            theLoai.setTen(ten);
            theLoai.setTrangThai(status);
            theLoai.setNgayTao(currentDate);
            loaiGiayService.add(theLoai);
            return ResponseEntity.ok("ok");
        }
    }
}
