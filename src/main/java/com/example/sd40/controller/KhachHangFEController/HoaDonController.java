package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.entity.KhachHang.DiaChiChiTiet;
import com.example.sd40.entity.KhachHang.Dia_Chi;
import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.Mail.MailStructure;
import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.San_pham.ChiTietSanPhamMauSacHinhAnh;

import com.example.sd40.repository.KhachHang.DiaChiChiTietRepository;
import com.example.sd40.service.KhachHang.KhachHangCusService;
import com.example.sd40.service.KhachHang.TinhThanhPhoService;
import com.example.sd40.service.MailService.MailService;
import com.example.sd40.service.SanPham.*;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


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
    @Autowired
    MailService mailService;

    Date currentDate = new Date(System.currentTimeMillis());


    @GetMapping("/hienthisanpham/{idsp}")
    public String hienthisanpham(Model model,
                                 @PathVariable("idsp")Long idsp,
                                 @RequestParam("kichCo") Long idkc,
                                 @RequestParam("mauSac") Long idms,
                                 @RequestParam("soLuong") Integer soLuong,HttpSession session
    ){
//        Long idKH = (Long) session.getAttribute("idKhachHang");
        int idKH = 1;
//        if (idKH == null){
//
//        ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh = ctspmshaService.getHAbySPandMS(idsp,idms);
//        ChiTietSanPham chiTietSanPham = ctspService.findCTSP(chiTietSanPhamMauSacHinhAnh.getId(),idkc);
//        model.addAttribute("sp",sanPhamService.detail(idsp));
//        model.addAttribute("ctsp",chiTietSanPham);
//        model.addAttribute("soLuong",soLuong);
//        model.addAttribute("voucher0",khachHangCusService.idVoucherOk());
//        model.addAttribute("listTinh",tinhThanhPhoService.getAllASC());
//        model.addAttribute("voucher",khachHangCusService.getVoucherHoaDon());
//        model.addAttribute("idHaNoi",khachHangCusService.layIDTinh());
//        model.addAttribute("view", "/bill/index.jsp");
//
//        return "/customerFE/index";
//    }else {
            ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh = ctspmshaService.getHAbySPandMS(idsp,idms);
            ChiTietSanPham chiTietSanPham = ctspService.findCTSP(chiTietSanPhamMauSacHinhAnh.getId(),idkc);
            model.addAttribute("sp",sanPhamService.detail(idsp));
            model.addAttribute("ctsp",chiTietSanPham);
            model.addAttribute("soLuong",soLuong);
            model.addAttribute("diachimacdinh",khachHangCusService.layDiaChi(Long.valueOf(1)));
            model.addAttribute("listDiaChi",khachHangCusService.danhSachDiaChi(Long.valueOf(1)));
            model.addAttribute("voucher0",khachHangCusService.idVoucherOk());
            model.addAttribute("voucher",khachHangCusService.getVoucherHoaDon());
            model.addAttribute("idHaNoi",khachHangCusService.layIDTinh());
            model.addAttribute("listTinh",tinhThanhPhoService.getAllASC());
            model.addAttribute("view", "/bill/indexCus.jsp");
            return "/customerFE/index";
        }
//    }


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
            HoaDon hoaDon = khachHangCusService.detailHoaDon(idHoaDonMoiNhat);
            ctspService.truSanPhamSauKhiMua(soLuong,idctsp);
            ChiTietSanPham chiTietSanPham = ctspService.findCTSP(ctspService.detail(idctsp).getChiTietSanPhamMauSacHinhAnh().getId(),ctspService.detail(idctsp).getKichCo().getId());

            model.addAttribute("ctsp",ctspService.findCTSP(ctspService.detail(idctsp).getChiTietSanPhamMauSacHinhAnh().getId(),ctspService.detail(idctsp).getKichCo().getId()));
            model.addAttribute("soLuong",soLuong);
            model.addAttribute("hoaDon",khachHangCusService.detailHoaDon(idHoaDonMoiNhat));

             String messeage = ("Xinn chào "+ tenNguoiNhan
                 + "\n Đơn hàng "+ hoaDon.getMaHoaDon() + " đã được đặt hàng thành công"
                 + "\n Tên sản phẩm " +  chiTietSanPham.getChiTietSanPhamMauSacHinhAnh().getSanPham().getTen()
                 + "\n Màu sắc : " +  chiTietSanPham.getChiTietSanPhamMauSacHinhAnh().getMau_sac().getTen()
                 + "\n Kích cỡ : " +  chiTietSanPham.getKichCo().getTen()
                 + "\n Số lượng : " +  soLuong
                 + "\n Số điện thoai người nhận : " +sdt
                 + "\n Địa chỉ người nhận:  " +  diaCHiChiTiet+", tỉnh(Thành phố): "+diaChiNguoiNhan
                 + "\n Phương thức thanh toán : Thanh toán khi nhận hàng"
                 + "\n Tổng tiền thanh toán:  " + tongTien
                 + "\n Cảm ơn bạn đã tin tưởng và sử dụng sản phẩm của SD40"
                 + "\n Bạn vui lòng chờ xác nhận từ shop nhé !!!"
         );
             MailStructure mailStructure = new MailStructure();
             mailStructure.setMessage(messeage);
             mailStructure.setSubject("Đặt hàng thành công SD40 sport");
             mailStructure.setToEmail(email);
             mailService.sendMail(mailStructure);




            model.addAttribute("view", "/bill/hoanThanhDatHang.jsp");



            return "/customerFE/index";
        }


    @PostMapping("/addHoaDonKhachHang/{idctsp}")
    public String addHoaDonKhachHang(@PathVariable("idctsp")Long idctsp,
                            @RequestParam("tongTien")BigDecimal tongTien,
                            @RequestParam("phanTramKhuyenMai")Integer phamTramKhuyenMai,
                            @RequestParam("idVoucher")Long idVoucher,
                            @RequestParam("tongTienGiam")BigDecimal tongTienGiam,
                            @RequestParam("tenNguoiNhan")String tenNguoiNhan,
                            @RequestParam("sdt")String sdt,
                            @RequestParam("diaChiNguoiNhan")String diaChiNguoiNhan,
                            @RequestParam("tongTienSanPhamChuaGiam")BigDecimal tongTienSanPhamChuaGiam,
                            @RequestParam("phiShip")BigDecimal phiShip,
                            @RequestParam("soLuong")Integer soLuong,
                            @RequestParam("giaHienHanh")BigDecimal giaHienHanh,
                            @RequestParam("giaDaGiam")BigDecimal giaDaGiam,Model model
    ) throws MessagingException {
        KhachHang khachHang = khachHangCusService.detailKhachHang(Long.valueOf(1));


        khachHangCusService.saveHDKhachHang(0, currentDate, tongTien, phamTramKhuyenMai, idVoucher, "Hóa đơn thanh toán khi nhận hàng", tongTienGiam, Long.valueOf(1), tenNguoiNhan, sdt, diaChiNguoiNhan, tongTienSanPhamChuaGiam, phiShip,Long.valueOf(1));
        Long idHoaDonMoiNhat = khachHangCusService.idHoaDonMoiTao();
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(khachHangCusService.detailHoaDon(idHoaDonMoiNhat));
        hoaDonChiTiet.setChiTietSanPham(ctspService.detail(idctsp));
        hoaDonChiTiet.setGiaHienHanh(giaHienHanh);
        hoaDonChiTiet.setTrangThai(0);
        hoaDonChiTiet.setSoLuong(soLuong);
        hoaDonChiTiet.setGiaDaGiam(giaDaGiam);
        khachHangCusService.addHDCT(hoaDonChiTiet);
        HoaDon hoaDon = khachHangCusService.detailHoaDon(idHoaDonMoiNhat);
        ctspService.truSanPhamSauKhiMua(soLuong,idctsp);
        ChiTietSanPham chiTietSanPham = ctspService.findCTSP(ctspService.detail(idctsp).getChiTietSanPhamMauSacHinhAnh().getId(),ctspService.detail(idctsp).getKichCo().getId());

        model.addAttribute("ctsp",ctspService.findCTSP(ctspService.detail(idctsp).getChiTietSanPhamMauSacHinhAnh().getId(),ctspService.detail(idctsp).getKichCo().getId()));
        model.addAttribute("soLuong",soLuong);
        model.addAttribute("hoaDon",khachHangCusService.detailHoaDon(idHoaDonMoiNhat));

        String messeage = ("Xinn chào "+ khachHang.getHoTen()
                + "\n Đơn hàng "+ hoaDon.getMaHoaDon() + " đã được đặt hàng thành công"
                + "\n Tên sản phẩm " +  chiTietSanPham.getChiTietSanPhamMauSacHinhAnh().getSanPham().getTen()
                + "\n Màu sắc : " +  chiTietSanPham.getChiTietSanPhamMauSacHinhAnh().getMau_sac().getTen()
                + "\n Kích cỡ : " +  chiTietSanPham.getKichCo().getTen()
                + "\n Số lượng : " +  soLuong
                + "\n Tên người nhận : " +  tenNguoiNhan
                + "\n Số điện thoai người nhận : " +sdt
                + "\n Địa chỉ người nhận : "+diaChiNguoiNhan
                + "\n Phương thức thanh toán : Thanh toán khi nhận hàng"
                + "\n Tổng tiền thanh toán:  " + tongTien
                + "\n Cảm ơn bạn đã tin tưởng và sử dụng sản phẩm của SD40"
                + "\n Bạn vui lòng chờ xác nhận từ shop nhé !!!"
        );
        MailStructure mailStructure = new MailStructure();
        mailStructure.setMessage(messeage);
        mailStructure.setSubject("Đặt hàng thành công SD40 sport");
        mailStructure.setToEmail(khachHang.getEmail());
        mailService.sendMail(mailStructure);




        model.addAttribute("view", "/bill/hoanThanhDatHang.jsp");



        return "/customerFE/index";
    }

    @PostMapping("/addDiaChiChiTiet")
    public ResponseEntity<?> addDiaChict(@RequestParam("province") Long idTinh, @RequestParam("detailAddress")String dcct) {
        try {
            int idKH = 1;
            Dia_Chi diaChi = new Dia_Chi();
            diaChi.setTrangThai(0);
            diaChi.setMoTa(dcct);
            diaChi.setNgaySua(currentDate);
            diaChi.setNgayTao(currentDate);
            diaChi.setTinhThanhPho(khachHangCusService.detailTinhThanhPho(idTinh));
            khachHangCusService.addDiaChi(diaChi);

            List<DiaChiChiTiet> diaChiChiTiets = khachHangCusService.danhSachDiaChi(Long.valueOf(1));
            DiaChiChiTiet diaChiChiTiet1 = new DiaChiChiTiet();
            if (diaChiChiTiets.isEmpty()){
                diaChiChiTiet1.setMacDinh(0);
            }else {
                diaChiChiTiet1.setMacDinh(1);
            }
            diaChiChiTiet1.setKhachHang(khachHangCusService.detailKhachHang(Long.valueOf(1)));
            diaChiChiTiet1.setDiaChi(khachHangCusService.detailDiaChi(khachHangCusService.idDiaChiMoiNhat()));
            khachHangCusService.addDiaChiChiTiet(diaChiChiTiet1);



            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating quantity");
        }

    }


    @GetMapping("/layDiaChiMoiNhat")
    public ResponseEntity<List<Object[]>> layDiaChiMoiNhat(
    ){

        List<Object[]> diaChiChiTiet = khachHangCusService.listDiaCHiMoiNhat(Long.valueOf(1));
        if (diaChiChiTiet != null && !diaChiChiTiet.isEmpty()) {
            return new ResponseEntity<>(diaChiChiTiet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteAddress/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("addressId") Long addressId) {
        try {
            DiaChiChiTiet diaChiChiTiet = khachHangCusService.detailDiaChiChiTiet(addressId);
            if (diaChiChiTiet.getMacDinh() == 0){
                return ResponseEntity.ok("Ko");
            }else {
                DiaChiChiTiet diaChiChiTiet1 = khachHangCusService.detailDiaChiChiTiet(addressId);
                khachHangCusService.deleteDCCT(addressId);
                khachHangCusService.deleteDiaChi(diaChiChiTiet1.getDiaChi().getId());

                return ResponseEntity.ok("Địa chỉ đã được xóa thành công.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi xóa địa chỉ.");
        }
    }

    @PostMapping("/updateMacDinh/{addressId}")
    public ResponseEntity<?> updateMacDinh(@PathVariable("addressId") Long addressId) {
        try {
            khachHangCusService.updateMacDinh(addressId);
            khachHangCusService.updateMacDinhKhac(addressId,Long.valueOf(1));
            List<Object[]> diaChiChiTiet = khachHangCusService.listDiaCHiMoiNhat(Long.valueOf(1));
            if (diaChiChiTiet != null && !diaChiChiTiet.isEmpty()) {
                return new ResponseEntity<>(diaChiChiTiet, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi xóa địa chỉ.");
        }
    }

}
