package com.example.sd40.controller.SanPhamController;

import com.example.sd40.entity.San_pham.GiamGIa;
import com.example.sd40.entity.San_pham.KichCo;
import com.example.sd40.entity.Voucher.Voucher;
import com.example.sd40.service.SanPham.GiamGiaService;
import com.example.sd40.service.SanPham.KichCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
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
    public ResponseEntity<GiamGIa> detail(Model model,
                                          @PathVariable("id")Long id){
        GiamGIa giamGIa = giamGiaService.detail(id);
        if (giamGIa != null) {
            return new ResponseEntity<>(giamGIa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    Date currentDate = new Date(System.currentTimeMillis());


    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id")String id,
                                    @RequestParam("ten")String ten,
                                    @RequestParam("trangThai")Integer status,
                                    @RequestParam("mucGiam")Integer mucGiam
    ) throws ParseException {
        List<GiamGIa> giamGIas = giamGiaService.findByNameUpdate(ten,Long.valueOf(id));
        if (!giamGIas.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        } else {
            GiamGIa giamGIa = giamGiaService.detail(Long.valueOf(id));

            giamGIa.setTen(ten);
            giamGIa.setTrangThai(status);
            giamGIa.setNgaySua(currentDate);
            giamGIa.setMucGiam(mucGiam);
            giamGiaService.update(giamGIa);
            return ResponseEntity.ok("ok");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestParam("ten")String ten,
            @RequestParam("trangThai")Integer status,
            @RequestParam("mucGiam")Integer mucGiam
    ) throws ParseException {
        List<GiamGIa> giamGIas = giamGiaService.findByName(ten);
        if (!giamGIas.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        } else {

            GiamGIa giamGIa = new GiamGIa();
            giamGIa.setTen(ten);
            giamGIa.setTrangThai(status);
            giamGIa.setNgayTao(currentDate);
            giamGIa.setMucGiam(mucGiam);
            giamGiaService.add(giamGIa);
            return ResponseEntity.ok("ok");
        }
    }
}
