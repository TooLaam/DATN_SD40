package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.entity.Gio_hang.GioHang;
import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.entity.KhachHang.DiaChiChiTiet;
import com.example.sd40.entity.KhachHang.Dia_Chi;
import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.Mail.MailStructure;
import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.San_pham.ChiTietSanPhamMauSacHinhAnh;

import com.example.sd40.repository.KhachHang.DiaChiChiTietRepository;
import com.example.sd40.service.GioHang.GioHangChiTietService;
import com.example.sd40.service.GioHang.GioHangService;
import com.example.sd40.service.HoaDon.HoaDonService;
import com.example.sd40.service.HoaDon.PhuongThucThanhToanService;
import com.example.sd40.service.KhachHang.KhachHangCusService;
import com.example.sd40.service.KhachHang.TinhThanhPhoService;
import com.example.sd40.service.MailService.MailService;
import com.example.sd40.service.SanPham.*;
import com.example.sd40.vnpay.CreatePayMentMethodTransferRequest;
import com.example.sd40.vnpay.PayMentVnpayRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    GioHangChiTietService gioHangChiTietService;
    @Autowired
    GioHangService gioHangService;

    Date currentDate = new Date(System.currentTimeMillis());

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private PhuongThucThanhToanService phuongThucThanhToanService;


    @GetMapping("/hienthisanpham/{idsp}")
    public String hienthisanpham(Model model,
                                 @PathVariable("idsp")Long idsp,
                                 @RequestParam("kichCo") Long idkc,
                                 @RequestParam("mauSac") Long idms,
                                 @RequestParam("soLuong") Integer soLuong,HttpSession session
    ){
        Long idKH = (Long) session.getAttribute("idKhachHang");
//        int idKH = 1;
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
            model.addAttribute("diachimacdinh",khachHangCusService.layDiaChi(idKH));
            model.addAttribute("listDiaChi",khachHangCusService.danhSachDiaChi(idKH));
            model.addAttribute("voucher0",khachHangCusService.idVoucherOk());
            model.addAttribute("voucher",khachHangCusService.getVoucherHoaDon());
            model.addAttribute("idHaNoi",khachHangCusService.layIDTinh());
            model.addAttribute("listTinh",tinhThanhPhoService.getAllASC());
            model.addAttribute("idkh",idKH );
            model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(Long.valueOf(idKH)).size());
            model.addAttribute("view", "/bill/indexCus.jsp");
            return "/customerFE/index";
        }
    }

    @GetMapping("/save")
    public String createHoaDon(HttpServletRequest request){
//      HoaDon hoaDon =   hoaDonService.createHoaDon();
//        if (thanhtoan!= vnpay){
//            return "redirect:/home";
//        }else {
        CreatePayMentMethodTransferRequest request1 = new CreatePayMentMethodTransferRequest();
        request1.setVnp_Ammount("1000000");   // tổng tiền hoaDon.getTongTien
        request1.setVnp_TxnRef("HD001"); // mã hóa đơn hoaDon.getMa
            return "redirect:" + phuongThucThanhToanService.createVnpay(request1, request);
//        }
    }
    @GetMapping("/payment-success")
    public String updateHoaDonWhenPaymentVnpay(PayMentVnpayRequest request){
       boolean check =   phuongThucThanhToanService.paymentSuccess(request);
        if(check){
            return "redirect:/home";
        }else{
//            return "redirect:/"; trả về màn thanh toán
            return "redirect:/home";
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

            khachHangCusService.saveHD(0, currentDate, tongTien, phamTramKhuyenMai, idVoucher, "Hóa đơn thanh toán khi nhận hàng", tongTienGiam, Long.valueOf(1), tenNguoiNhan, sdt, diaCHiChiTiet + ",Tỉnh/Thành phố: " + diaChiNguoiNhan, tongTienSanPhamChuaGiam, phiShip);
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
            khachHangCusService.capNhatSoLuongSPDaBan(soLuong , hoaDonChiTiet.getChiTietSanPham().getChiTietSanPhamMauSacHinhAnh().getSanPham().getId());
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
                            @RequestParam("tinh")String tinh,
                            @RequestParam("tongTienSanPhamChuaGiam")BigDecimal tongTienSanPhamChuaGiam,
                            @RequestParam("phiShip")BigDecimal phiShip,
                            @RequestParam("soLuong")Integer soLuong,
                            @RequestParam("giaHienHanh")BigDecimal giaHienHanh,
                            @RequestParam("giaDaGiam")BigDecimal giaDaGiam,Model model,HttpSession session
    ) throws MessagingException {
        Long idKH = (Long) session.getAttribute("idKhachHang");
        KhachHang khachHang = khachHangCusService.detailKhachHang(Long.valueOf(idKH));
        khachHangCusService.saveHDKhachHang(0, currentDate, tongTien, phamTramKhuyenMai, idVoucher, "Hóa đơn thanh toán khi nhận hàng", tongTienGiam, Long.valueOf(1), tenNguoiNhan, sdt, diaChiNguoiNhan+", Tỉnh/Thành phố: "+tinh, tongTienSanPhamChuaGiam, phiShip,idKH);
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
        khachHangCusService.capNhatSoLuongSPDaBan(soLuong , hoaDonChiTiet.getChiTietSanPham().getChiTietSanPhamMauSacHinhAnh().getSanPham().getId());
        ChiTietSanPham chiTietSanPham = ctspService.findCTSP(ctspService.detail(idctsp).getChiTietSanPhamMauSacHinhAnh().getId(),ctspService.detail(idctsp).getKichCo().getId());

        model.addAttribute("ctsp",ctspService.findCTSP(ctspService.detail(idctsp).getChiTietSanPhamMauSacHinhAnh().getId(),ctspService.detail(idctsp).getKichCo().getId()));
        model.addAttribute("soLuong",soLuong);
        model.addAttribute("hoaDon",khachHangCusService.detailHoaDon(idHoaDonMoiNhat));
        model.addAttribute("idkh",idKH );
        model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(idKH).size());

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
    public ResponseEntity<?> addDiaChict(@RequestParam("province") Long idTinh, @RequestParam("detailAddress")String dcct,HttpSession session) {
        try {
            Long idKH = (Long) session.getAttribute("idKhachHang");
            Dia_Chi diaChi = new Dia_Chi();
            diaChi.setTrangThai(0);
            diaChi.setMoTa(dcct);
            diaChi.setNgaySua(currentDate);
            diaChi.setNgayTao(currentDate);
            diaChi.setTinhThanhPho(khachHangCusService.detailTinhThanhPho(idTinh));
            khachHangCusService.addDiaChi(diaChi);

            List<DiaChiChiTiet> diaChiChiTiets = khachHangCusService.danhSachDiaChi(idKH);
            DiaChiChiTiet diaChiChiTiet1 = new DiaChiChiTiet();
            if (diaChiChiTiets.isEmpty()){
                diaChiChiTiet1.setMacDinh(0);
            }else {
                diaChiChiTiet1.setMacDinh(1);
            }
            diaChiChiTiet1.setKhachHang(khachHangCusService.detailKhachHang(idKH));
            diaChiChiTiet1.setDiaChi(khachHangCusService.detailDiaChi(khachHangCusService.idDiaChiMoiNhat()));
            khachHangCusService.addDiaChiChiTiet(diaChiChiTiet1);



            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating quantity");
        }

    }


    @GetMapping("/layDiaChiMoiNhat")
    public ResponseEntity<List<Object[]>> layDiaChiMoiNhat(HttpSession session
    ){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        List<Object[]> diaChiChiTiet = khachHangCusService.listDiaCHiMoiNhat(idKH);
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
    public ResponseEntity<?> updateMacDinh(@PathVariable("addressId") Long addressId,HttpSession session) {
        try {
            Long idKH = (Long) session.getAttribute("idKhachHang");
            khachHangCusService.updateMacDinh(addressId);
            khachHangCusService.updateMacDinhKhac(addressId,idKH);
            List<Object[]> diaChiChiTiet = khachHangCusService.listDiaCHiMoiNhat(idKH);
            if (diaChiChiTiet != null && !diaChiChiTiet.isEmpty()) {
                return new ResponseEntity<>(diaChiChiTiet, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi xóa địa chỉ.");
        }
    }


    @PostMapping("/datHangTuGioHang")
    public ResponseEntity<?> processSelectedProducts(@RequestBody List<Long> selectedProductIds,HttpSession session) {

        try {
                session.setAttribute("listLong",selectedProductIds);
                return new ResponseEntity<>(selectedProductIds, HttpStatus.OK);
            }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi xóa địa chỉ.");
        }

    }


    @GetMapping("/hienThiSanPhamHoaDon")
    public  String hienThiSanPhamHoaDon(HttpSession session,Model model){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        List<Long> ids = (List<Long>) session.getAttribute("listLong");
        List<GioHangChiTiet> gioHangChiTiets = gioHangChiTietService.findAllById(ids);
        model.addAttribute("diachimacdinh",khachHangCusService.layDiaChi(idKH));
        model.addAttribute("listGHCT",gioHangChiTiets);
        model.addAttribute("listDiaChi",khachHangCusService.danhSachDiaChi(idKH));
        model.addAttribute("voucher0",khachHangCusService.idVoucherOk());
        model.addAttribute("voucher",khachHangCusService.getVoucherHoaDon());
        model.addAttribute("idHaNoi",khachHangCusService.layIDTinh());
        model.addAttribute("listTinh",tinhThanhPhoService.getAllASC());
        model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(idKH).size());
        model.addAttribute("idkh",idKH );
        model.addAttribute("view", "/bill/hoaDonCoNhieuSanPham.jsp");
        return "/customerFE/index";
    }

    @PostMapping("/addHoaDonKhachHangCoNhieuSanPham")
    public ResponseEntity<?> addHoaDonKhachHangCoNhieuSanPham(
                                                                @RequestBody List<Long> productIds,
                                                                HttpSession session
    ) {
        List<GioHangChiTiet> gioHangChiTiets = gioHangChiTietService.findAllById(productIds);
        session.setAttribute("gioHangChiTiets",gioHangChiTiets);
        return new ResponseEntity<>(gioHangChiTiets, HttpStatus.OK);
    }

    @PostMapping("/HienThiHoaDonKhachHangCoNhieuSanPham")
    @Transactional
    public String HienThiHoaDonKhachHangCoNhieuSanPham(
            @RequestParam("tongTien")BigDecimal tongTien,
            @RequestParam("phanTramKhuyenMai")Integer phamTramKhuyenMai,
            @RequestParam("idVoucher")Long idVoucher,
            @RequestParam("tongTienGiam")BigDecimal tongTienGiam,
            @RequestParam("tenNguoiNhan")String tenNguoiNhan,
            @RequestParam("sdt")String sdt,
            @RequestParam("diaChiNguoiNhan")String diaChiNguoiNhan,
            @RequestParam("tinh")String tinh,
            @RequestParam("tongTienSanPhamChuaGiam")BigDecimal tongTienSanPhamChuaGiam,
            @RequestParam("phiShip")BigDecimal phiShip,
            Model model,HttpSession session
    ) throws MessagingException {
        Long idKH = (Long) session.getAttribute("idKhachHang");
        KhachHang khachHang = khachHangCusService.detailKhachHang(Long.valueOf(idKH));
        khachHangCusService.saveHDKhachHang(0, currentDate, tongTien, phamTramKhuyenMai, idVoucher, "Hóa đơn thanh toán khi nhận hàng", tongTienGiam, Long.valueOf(1), tenNguoiNhan, sdt, diaChiNguoiNhan+", Tỉnh/Thành phố: "+tinh, tongTienSanPhamChuaGiam, phiShip,idKH);
        Long idHoaDonMoiNhat = khachHangCusService.idHoaDonMoiTao();
        List<GioHangChiTiet> gioHangChiTiets = (List<GioHangChiTiet>) session.getAttribute("gioHangChiTiets");

        for (GioHangChiTiet ghct : gioHangChiTiets) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(khachHangCusService.detailHoaDon(idHoaDonMoiNhat));
            hoaDonChiTiet.setChiTietSanPham(ctspService.detail(ghct.getChiTietSanPham().getId()));
            hoaDonChiTiet.setTrangThai(0);
            hoaDonChiTiet.setSoLuong(ghct.getSoLuong());
            khachHangCusService.addHDCT(hoaDonChiTiet);
            ctspService.truSanPhamSauKhiMua(ghct.getSoLuong(),ghct.getChiTietSanPham().getId());
            gioHangChiTietService.deleteGHCTByCTSP(ghct.getId());
        }

        List<Object[]>objects = khachHangCusService.soLuongDaBan(idHoaDonMoiNhat);
        for (Object[] ob : objects){
            khachHangCusService.capNhatSoLuongSPDaBan(Integer.valueOf(String.valueOf(ob[0])),Long.valueOf(String.valueOf(ob[1])));
        }
        HoaDon hoaDon = khachHangCusService.detailHoaDon(idHoaDonMoiNhat);
        List<HoaDonChiTiet> hoaDonChiTiets = khachHangCusService.listHDCT(idHoaDonMoiNhat);
        model.addAttribute("HD",hoaDon);
        model.addAttribute("HDCT",hoaDonChiTiets);
        model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(idKH).size());
        model.addAttribute("idkh",idKH );

        String messeage = ("Xinn chào "+ khachHang.getHoTen()
                + "\n Đơn hàng "+ hoaDon.getMaHoaDon() + " đã được đặt hàng thành công"
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

        model.addAttribute("view", "/bill/hoanThanhDatHangCoNhieuSanPham.jsp");
        return "/customerFE/index";
    }


    @GetMapping("/hienThiHoaDon")
    public String hienThiHoaDon(Model model,HttpSession session){
        Long idKH = (Long) session.getAttribute("idKhachHang");
        model.addAttribute("hoaDonChoXacNhan",khachHangCusService.listHoaDon(idKH,0));
        model.addAttribute("hoaDonDaXacNhan",khachHangCusService.listHoaDon(idKH,1));
        model.addAttribute("hoaDonChoGiao",khachHangCusService.listHoaDon(idKH,2));
        model.addAttribute("hoaDonDangGiao",khachHangCusService.listHoaDon(idKH,3));
        model.addAttribute("hoaDonThanhCong",khachHangCusService.listHoaDon(idKH,4));
        model.addAttribute("hoaDonHuy",khachHangCusService.listHoaDon(idKH,5));
        model.addAttribute("allHoaDon",khachHangCusService.getAllHDByIdKhachHang(idKH));
        model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(idKH).size());
        model.addAttribute("idkh",idKH );

        model.addAttribute("view", "/bill/HoaDon.jsp");
        return "/customerFE/index";
    }

    @GetMapping("/hienthiHoaDonChiTiet/{idhd}")
    public ResponseEntity<List<Object[]>> hienthiKCCus(Model model,
                                                       @PathVariable("idhd") Long idsp
    ){

        List<Object[]> objects = khachHangCusService.listHDCTByHoaDon(idsp);

        if (objects != null && !objects.isEmpty()) {
            return new ResponseEntity<>(objects, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/detailHoaDon/{idhd}")
    public String detailHoaDon(@PathVariable("idhd")Long idhd,Model model,HttpSession session){
        HoaDon hoaDon = khachHangCusService.detailHoaDon(idhd);
        List<HoaDonChiTiet> hoaDonChiTiets = khachHangCusService.fidHDCTByHD(idhd);
        List<ChiTietSanPham> chiTietSanPhams = khachHangCusService.fidCTSPByHD(idhd);
        Long idKH = (Long) session.getAttribute("idKhachHang");
        model.addAttribute("HD",hoaDon);
        model.addAttribute("HDCT",hoaDonChiTiets);
        model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(idKH).size());
        model.addAttribute("idkh",idKH );
        model.addAttribute("view", "/bill/hoanThanhDatHangCoNhieuSanPham.jsp");
        return "/customerFE/index";
    }

    @PostMapping("/huyHoaDon/{idHD}")
    public ResponseEntity<?> updateProductQuantity(@PathVariable("idHD")Long idhd,
                                                   @RequestParam("lyDo") String lyDo) {
        try {
                khachHangCusService.HuyHoaDon(idhd,"Khách hàng đã hủy hóa đơn với lý do : "+lyDo);
                List<HoaDonChiTiet> hoaDonChiTiets = khachHangCusService.fidHDCTByHD(idhd);
                for(HoaDonChiTiet hoaDonChiTiet :hoaDonChiTiets){
                    khachHangCusService.capNhatSoLuongSPSauKhiXoa(hoaDonChiTiet.getSoLuong(),hoaDonChiTiet.getChiTietSanPham().getId());
                    List<Object[]>objects = khachHangCusService.soLuongDaBan(idhd);
                    for (Object[] ob : objects){
                        khachHangCusService.capNhatSoLuongSPDaBan(Integer.valueOf(String.valueOf(ob[1])),Long.valueOf(String.valueOf(ob[2])));
                    }                }
                return ResponseEntity.ok("ok");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating quantity");
        }
    }

    @PostMapping("/hoanThanhHoaDonCus/{idHD}")
    public ResponseEntity<?> hoanThanhHoaDonCus(@PathVariable("idHD")Long idhd) {
        try {
            khachHangCusService.hoanThanhHoaDon(idhd);
            return ResponseEntity.ok("ok");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating quantity");
        }
    }
}
