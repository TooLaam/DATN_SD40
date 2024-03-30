package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.San_pham.ChiTietSanPhamMauSacHinhAnh;

import com.example.sd40.service.KhachHang.KhachHangCusService;
import com.example.sd40.service.KhachHang.TinhThanhPhoService;
import com.example.sd40.service.SanPham.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.net.PasswordAuthentication;
import java.sql.Date;
import java.util.Properties;


@Controller
@RequestMapping("/bill")
public class HoaDonController {
    @Autowired
    TinhThanhPhoService tinhThanhPhoService;
    @Autowired
    SanPhamService sanPhamService;
    @Autowired
    KichCoService kichCoService;
    @Autowired
    CTSPService ctspService;
    @Autowired
    MauSacService mauSacService;
    @Autowired
    CTSPMSHAService ctspmshaService;
    @Autowired
    KhachHangCusService khachHangCusService;

    Date currentDate = new Date(System.currentTimeMillis());
    @GetMapping("/hienthisanpham/{idsp}")
    public String hienthisanpham(Model model,
                                 @PathVariable("idsp")Long idsp,
                                 @RequestParam("kichCo") Long idkc,
                                 @RequestParam("mauSac") Long idms,
                                 @RequestParam("soLuong") Integer soLuong,HttpSession session
    ){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        if (idKH == null){

        ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh = ctspmshaService.getHAbySPandMS(idsp,idms);
        ChiTietSanPham chiTietSanPham = ctspService.findCTSP(chiTietSanPhamMauSacHinhAnh.getId(),idkc);
        model.addAttribute("sp",sanPhamService.detail(idsp));
        model.addAttribute("ctsp",chiTietSanPham);
        model.addAttribute("soLuong",soLuong);
        model.addAttribute("voucher0",khachHangCusService.idVoucherOk());
        model.addAttribute("listTinh",tinhThanhPhoService.getAllASC());
        model.addAttribute("voucher",khachHangCusService.getVoucherHoaDon());
        model.addAttribute("idHaNoi",khachHangCusService.layIDTinh());
        model.addAttribute("view", "/bill/index.jsp");

        return "/customerFE/index";
    }else {
            ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh = ctspmshaService.getHAbySPandMS(idsp,idms);
            ChiTietSanPham chiTietSanPham = ctspService.findCTSP(chiTietSanPhamMauSacHinhAnh.getId(),idkc);
            model.addAttribute("sp",sanPhamService.detail(idsp));
            model.addAttribute("ctsp",chiTietSanPham);
            model.addAttribute("soLuong",soLuong);
            model.addAttribute("voucher0",khachHangCusService.idVoucherOk());
            model.addAttribute("listTinh",tinhThanhPhoService.getAllASC());
            model.addAttribute("diachimacdinh",khachHangCusService.layDiaChi(idKH));
            model.addAttribute("view", "/bill/indexCus.jsp");
            return "/customerFE/index";
        }
    }

     @PostMapping("/addHoaDon/{idctsp}")
     public String addHoaDon(@PathVariable("idctsp")Long idctsp,
                     @RequestParam("tongTien")BigDecimal tongTien,
                     @RequestParam("phanTramKhuyenMai")Integer phamTramKhuyenMai,
                     @RequestParam("idVoucher")Long idVoucher,
                     @RequestParam("tongTienGiam")BigDecimal tongTienGiam,
                     @RequestParam("tenNguoiNhan")String tenNguoiNhan,
                     @RequestParam("sdt")String sdt,
                     @RequestParam("diaChiNguoiNhan")String diaChiNguoiNhan,
                     @RequestParam("diaCHiChiTiet")String diaCHiChiTiet,
                     @RequestParam("tongTienSanPhamChuaGiam")BigDecimal tongTienSanPhamChuaGiam,
                     @RequestParam("phiShip")BigDecimal phiShip,
                     @RequestParam("soLuong")Integer soLuong,
                     @RequestParam("giaHienHanh")BigDecimal giaHienHanh,
                     @RequestParam("email")String email,
                     @RequestParam("giaDaGiam")BigDecimal giaDaGiam,Model model
                     ) throws MessagingException {

            khachHangCusService.saveHD(0, currentDate, tongTien, phamTramKhuyenMai, idVoucher, "Hóa đơn thanh toán khi nhận hàng", tongTienGiam, Long.valueOf(1), tenNguoiNhan, sdt, diaCHiChiTiet + ",Tỉnh " + diaChiNguoiNhan, tongTienSanPhamChuaGiam, phiShip);
            Long idHoaDonMoiNhat = khachHangCusService.idHoaDonMoiTao();
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(khachHangCusService.detailHoaDon(idHoaDonMoiNhat));
            hoaDonChiTiet.setChiTietSanPham(ctspService.detail(idctsp));
            hoaDonChiTiet.setGiaHienHanh(giaHienHanh);
            hoaDonChiTiet.setTrangThai(0);
            hoaDonChiTiet.setSoLuong(soLuong);
            hoaDonChiTiet.setGiaDaGiam(giaDaGiam);
            khachHangCusService.addHDCT(hoaDonChiTiet);

            ctspService.truSanPhamSauKhiMua(soLuong,idctsp);

            model.addAttribute("ctsp",ctspService.findCTSP(ctspService.detail(idctsp).getChiTietSanPhamMauSacHinhAnh().getId(),ctspService.detail(idctsp).getKichCo().getId()));
            model.addAttribute("soLuong",soLuong);
            model.addAttribute("hoaDon",khachHangCusService.detailHoaDon(idHoaDonMoiNhat));



            model.addAttribute("view", "/bill/hoanThanhDatHang.jsp");



            return "/customerFE/index";
        }

}
