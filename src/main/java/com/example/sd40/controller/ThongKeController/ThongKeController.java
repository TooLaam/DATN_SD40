package com.example.sd40.controller.ThongKeController;

import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.San_pham.GiamGIa;
import com.example.sd40.entity.ThongKe;
import com.example.sd40.service.ThongKe.ThongKeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/thongke")
public class ThongKeController {
    @Autowired
    ThongKeService thongKeService;
    @GetMapping("/index")
    public String index(Model model) throws ParseException {
        model.addAttribute("thongKeDonHangAll",thongKeService.TongDonHangAll("1999/12/12","2099/12/12"));
        model.addAttribute("doanhSovaSanPhamDaBanAll",thongKeService.DoanhSoVaSanPhamDaBanAll("1999/12/12","2099/12/12"));
        model.addAttribute("hangTonKho",thongKeService.hangTonKho());
        model.addAttribute("view","/ThongKe/thongke.jsp");
        return "index";
    }
    @GetMapping("/bieuDo/{nam}")
    public ResponseEntity<List<Object[]>> bieuDo(@PathVariable("nam")String nam){
       List<Object[]> objects = thongKeService.getTongDoanhThuByMonthAndYear(nam);
        if (objects != null) {
            return new ResponseEntity<>(objects, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/thongKeTongDonHangTheoNgay/{ngayBD}/{ngayKT}")
    public ResponseEntity<Object> thongKeTongDonHangTheoNgay(@PathVariable("ngayBD")String ngayBD,@PathVariable("ngayKT")String ngayKT){
        Object objects = thongKeService.TongDonHangAll(ngayBD,ngayKT);
        if (objects != null) {
            return new ResponseEntity<>(objects, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/thongKeDoanhSoVaSanPhamDaBanAllTheoNgay/{ngayBD}/{ngayKT}")
    public ResponseEntity<Object> thongKeDoanhSoVaSanPhamDaBanAllTheoNgay(@PathVariable("ngayBD")String ngayBD,@PathVariable("ngayKT")String ngayKT){
        Object objects = thongKeService.DoanhSoVaSanPhamDaBanAll(ngayBD,ngayKT);
        if (objects != null) {
            return new ResponseEntity<>(objects, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
