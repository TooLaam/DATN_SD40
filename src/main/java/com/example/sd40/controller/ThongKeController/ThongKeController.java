package com.example.sd40.controller.ThongKeController;


import com.example.sd40.entity.NhanVien.NhanVien;
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
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/thongke")
public class ThongKeController {
    @Autowired
    ThongKeService thongKeService;
    @GetMapping("/index")
    public String index(Model model, HttpSession session) throws ParseException {
        NhanVien nhanVien = (NhanVien) session.getAttribute("nhanVien");
        if (nhanVien == null){
            return "redirect:/admin/login";
        }else {
            if (nhanVien.getChucVu().getId() == 2){
                return "redirect:/admin/login";
            }else {
                model.addAttribute("nhanvien",nhanVien);
                model.addAttribute("hangTonKho",thongKeService.hangTonKho());
                model.addAttribute("view","/ThongKe/thongke.jsp");
                return "index";
            }
        }

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
    @GetMapping("/thongKeSanPhamDaBanAllTheoNgay/{ngayBD}/{ngayKT}")
    public ResponseEntity<Object> thongKeDoanhSoVaSanPhamDaBanAllTheoNgay(@PathVariable("ngayBD")String ngayBD,@PathVariable("ngayKT")String ngayKT){
        Integer objects = thongKeService.SanPhamDaBanAll(ngayBD,ngayKT);

            return new ResponseEntity<>(objects, HttpStatus.OK);

    }
    @GetMapping("/thongKeDoanhThuTheoNgay/{ngayBD}/{ngayKT}")
    public ResponseEntity<Object> thongKeDoanhThuTheoNgay(@PathVariable("ngayBD")String ngayBD,@PathVariable("ngayKT")String ngayKT){
        Object objects = thongKeService.DoanhThu(ngayBD,ngayKT);

            return new ResponseEntity<>(objects, HttpStatus.OK);
    }
}
