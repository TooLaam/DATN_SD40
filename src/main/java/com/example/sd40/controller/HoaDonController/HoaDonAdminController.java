package com.example.sd40.controller.HoaDonController;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.entity.Hoa_don.ThaoTacHoaDon;
import com.example.sd40.entity.NhanVien.NhanVien;
import com.example.sd40.service.HoaDon.HoaDonService;
import com.example.sd40.service.HoaDon.ThaoTacHoaDonService;
import com.example.sd40.service.KhachHang.KhachHangCusService;
import com.example.sd40.service.MailService.MailService;
import com.example.sd40.service.NhanVien.NhanVienService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

/**
 * @author quynh
 */
@Controller
@RequestMapping("/billadmin")
public class HoaDonAdminController {

    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    ThaoTacHoaDonService thaoTacHoaDonService;
    @Autowired
    NhanVienService nhanVienService;
    @Autowired
    KhachHangCusService khachHangCusService;
    @Autowired
    MailService mailService;


    Date currentDate = new Date(System.currentTimeMillis());
    Instant currentInstant = Instant.now();
    java.util.Date currentDate1 = Date.from(currentInstant);


    @GetMapping("/index")
    private String findAllByStatus(Model model, HttpSession session){
        NhanVien nhanVien = (NhanVien) session.getAttribute("nhanVien");
        if (nhanVien == null){
            return "redirect:/admin/login";
        }else {
            model.addAttribute("choXacNhan", hoaDonService.getHDByTrangThai(0));
            model.addAttribute("xacNhan", hoaDonService.getHDByTrangThai(1));
            model.addAttribute("choGiaoHang", hoaDonService.getHDByTrangThai(2));
            model.addAttribute("dangGiaoHang", hoaDonService.getHDByTrangThai(3));
            model.addAttribute("hoanThanh", hoaDonService.getHDByTrangThai(4));
            model.addAttribute("huy", hoaDonService.getHDByTrangThai(5));
            model.addAttribute("tatCa", hoaDonService.getAll());

            model.addAttribute("choXacNhanSize", hoaDonService.getHDByTrangThai(0).size());
            model.addAttribute("xacNhanSize", hoaDonService.getHDByTrangThai(1).size());
            model.addAttribute("choGiaoHangSize", hoaDonService.getHDByTrangThai(2).size());
            model.addAttribute("dangGiaoHangSize", hoaDonService.getHDByTrangThai(3).size());
            model.addAttribute("hoanThanhSize", hoaDonService.getHDByTrangThai(4).size());
            model.addAttribute("huySize", hoaDonService.getHDByTrangThai(5).size());
            model.addAttribute("tatCaSize", hoaDonService.getAll().size());

            model.addAttribute("nhanvien",nhanVien);
            model.addAttribute("view", "/HoaDonAdmin/index.jsp");
            return "index";
        }
    }
    @GetMapping("/detailHoaDon/{idhd}")
    public String detailHoaDon(@PathVariable("idhd")Long idhd, Model model,HttpSession session){
        NhanVien nhanVien = (NhanVien) session.getAttribute("nhanVien");
        if (nhanVien == null){
            return "redirect:/admin/login";
        }else {
            HoaDon hoaDon = khachHangCusService.detailHoaDon(idhd);
            List<HoaDonChiTiet> hoaDonChiTiets = khachHangCusService.fidHDCTByHD(idhd);
            model.addAttribute("HD", hoaDon);
            model.addAttribute("HDCT", hoaDonChiTiets);
            model.addAttribute("nhanvien",nhanVien);
            model.addAttribute("view", "/HoaDonAdmin/detail.jsp");
            return "index";
        }
    }

    @PostMapping("/huyHoaDon/{idHD}")
    public ResponseEntity<?> updateProductQuantity(@PathVariable("idHD")Long idhd,
                                                   @RequestParam("lyDo") String lyDo,HttpSession session) {
        try {
            NhanVien nhanVien = (NhanVien) session.getAttribute("nhanVien");
            khachHangCusService.HuyHoaDon(idhd,"Cửa hàng đã hủy hóa đơn với lý do : "+lyDo,currentDate);
            List<HoaDonChiTiet> hoaDonChiTiets = khachHangCusService.fidHDCTByHD(idhd);
            for(HoaDonChiTiet hoaDonChiTiet :hoaDonChiTiets){
                khachHangCusService.capNhatSoLuongSPSauKhiXoa(hoaDonChiTiet.getSoLuong(),hoaDonChiTiet.getChiTietSanPham().getId());
            }
            List<Object[]>objects = khachHangCusService.soLuongDaBan(idhd);
            for (Object[] ob : objects){
                khachHangCusService.capNhatSoLuongSPDaBan(-Integer.valueOf(String.valueOf(ob[0])),Long.valueOf(String.valueOf(ob[1])));
            }
            ThaoTacHoaDon thaoTacHoaDon = new ThaoTacHoaDon();
            thaoTacHoaDon.setHoaDon(hoaDonService.detailHoaDon(idhd));
            thaoTacHoaDon.setThaoTac("Nhân viên hủy đơn hàng");
            thaoTacHoaDon.setTrangThai(0);
            thaoTacHoaDon.setNgayTao(currentDate);
            thaoTacHoaDon.setNhanVien(nhanVien);
            thaoTacHoaDonService.save(thaoTacHoaDon);
            return ResponseEntity.ok("ok");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating quantity");
        }
    }

    @PostMapping("/chuyenTrangThaiHoaDon/{idHD}")
    public ResponseEntity<?> chuyenTrangThaiHoaDon(@PathVariable("idHD")Long idHD,@RequestParam("trangThai")Integer trangThai,HttpSession session){
        NhanVien nhanVien = (NhanVien) session.getAttribute("nhanVien");
        HoaDon hoaDon = hoaDonService.detailHoaDon(idHD);
        if (hoaDon.getPhuongThucThanhToan().getId() == 1){
            if (trangThai == 0){
                hoaDonService.chuyenTrangThaiHoaDon(1,idHD,currentDate1,null);
                ThaoTacHoaDon thaoTacHoaDon = new ThaoTacHoaDon();
                thaoTacHoaDon.setHoaDon(hoaDonService.detailHoaDon(idHD));
                thaoTacHoaDon.setThaoTac("Nhân viên xác nhận đơn hàng");
                thaoTacHoaDon.setTrangThai(0);
                thaoTacHoaDon.setNgayTao(currentDate1);
                thaoTacHoaDon.setNhanVien(nhanVien);
                thaoTacHoaDonService.save(thaoTacHoaDon);
                return ResponseEntity.ok("ok");
            }
            if (trangThai == 1){
                hoaDonService.chuyenTrangThaiHoaDon(2,idHD,currentDate1,null);
                ThaoTacHoaDon thaoTacHoaDon = new ThaoTacHoaDon();
                thaoTacHoaDon.setHoaDon(hoaDonService.detailHoaDon(idHD));
                thaoTacHoaDon.setThaoTac("Nhân viên chuyển sang trạng thái chờ giao");
                thaoTacHoaDon.setTrangThai(0);
                thaoTacHoaDon.setNgayTao(currentDate1);
                thaoTacHoaDon.setNhanVien(nhanVien);
                thaoTacHoaDonService.save(thaoTacHoaDon);
                return ResponseEntity.ok("ok");
            }
            if (trangThai == 2){
                hoaDonService.chuyenTrangThaiHoaDon(3,idHD,currentDate1,null);
                ThaoTacHoaDon thaoTacHoaDon = new ThaoTacHoaDon();
                thaoTacHoaDon.setHoaDon(hoaDonService.detailHoaDon(idHD));
                thaoTacHoaDon.setThaoTac("Nhân viên chuyển sang trạng thái đang giao");
                thaoTacHoaDon.setTrangThai(0);
                thaoTacHoaDon.setNgayTao(currentDate1);
                thaoTacHoaDon.setNhanVien(nhanVien);
                thaoTacHoaDonService.save(thaoTacHoaDon);
                return ResponseEntity.ok("ok");
            }
            if (trangThai == 3){
                hoaDonService.chuyenTrangThaiHoaDon(4,idHD,currentDate1,currentDate1);
                ThaoTacHoaDon thaoTacHoaDon = new ThaoTacHoaDon();
                thaoTacHoaDon.setHoaDon(hoaDonService.detailHoaDon(idHD));
                thaoTacHoaDon.setThaoTac("Nhân viên hoàn thành đơn hàng");
                thaoTacHoaDon.setTrangThai(0);
                thaoTacHoaDon.setNgayTao(currentDate1);
                thaoTacHoaDon.setNhanVien(nhanVien);
                thaoTacHoaDonService.save(thaoTacHoaDon);



                return ResponseEntity.ok("ok");
            }
        }
        else {
            if (trangThai == 0){
                hoaDonService.chuyenTrangThaiHoaDon(1,idHD,currentDate1,hoaDon.getNgayThanhToan());
                ThaoTacHoaDon thaoTacHoaDon = new ThaoTacHoaDon();
                thaoTacHoaDon.setHoaDon(hoaDonService.detailHoaDon(idHD));
                thaoTacHoaDon.setThaoTac("Nhân viên xác nhận đơn hàng");
                thaoTacHoaDon.setTrangThai(0);
                thaoTacHoaDon.setNgayTao(currentDate1);
                thaoTacHoaDon.setNhanVien(nhanVien);
                thaoTacHoaDonService.save(thaoTacHoaDon);
                return ResponseEntity.ok("ok");
            }
            if (trangThai == 1){
                hoaDonService.chuyenTrangThaiHoaDon(2,idHD,currentDate1,hoaDon.getNgayThanhToan());
                ThaoTacHoaDon thaoTacHoaDon = new ThaoTacHoaDon();
                thaoTacHoaDon.setHoaDon(hoaDonService.detailHoaDon(idHD));
                thaoTacHoaDon.setThaoTac("Nhân viên chuyển sang trạng thái chờ giao");
                thaoTacHoaDon.setTrangThai(0);
                thaoTacHoaDon.setNgayTao(currentDate1);
                thaoTacHoaDon.setNhanVien(nhanVien);
                thaoTacHoaDonService.save(thaoTacHoaDon);
                return ResponseEntity.ok("ok");
            }
            if (trangThai == 2){
                hoaDonService.chuyenTrangThaiHoaDon(3,idHD,currentDate1,hoaDon.getNgayThanhToan());
                ThaoTacHoaDon thaoTacHoaDon = new ThaoTacHoaDon();
                thaoTacHoaDon.setHoaDon(hoaDonService.detailHoaDon(idHD));
                thaoTacHoaDon.setThaoTac("Nhân viên chuyển sang trạng thái đang giao");
                thaoTacHoaDon.setTrangThai(0);
                thaoTacHoaDon.setNgayTao(currentDate1);
                thaoTacHoaDon.setNhanVien(nhanVien);
                thaoTacHoaDonService.save(thaoTacHoaDon);
                return ResponseEntity.ok("ok");
            }
            if (trangThai == 3){
                hoaDonService.chuyenTrangThaiHoaDon(4,idHD,currentDate1,hoaDon.getNgayThanhToan());
                ThaoTacHoaDon thaoTacHoaDon = new ThaoTacHoaDon();
                thaoTacHoaDon.setHoaDon(hoaDonService.detailHoaDon(idHD));
                thaoTacHoaDon.setThaoTac("Nhân viên hoàn thành đơn hàng");
                thaoTacHoaDon.setTrangThai(0);
                thaoTacHoaDon.setNgayTao(currentDate1);
                thaoTacHoaDon.setNhanVien(nhanVien);
                thaoTacHoaDonService.save(thaoTacHoaDon);
                return ResponseEntity.ok("ok");
            }
        }

        return ResponseEntity.ok("ko");
    }

    @GetMapping("/lichSu/{idHD}")
    public ResponseEntity<?> lichSu(@PathVariable("idHD")Long idHD){
        List<ThaoTacHoaDon> thaoTacHoaDons = thaoTacHoaDonService.getAllByHoaDon(idHD);
        return new ResponseEntity<>(thaoTacHoaDons, HttpStatus.OK);
    }

}

