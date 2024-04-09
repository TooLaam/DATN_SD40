package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.San_pham.ChiTietSanPhamMauSacHinhAnh;

import com.example.sd40.service.HoaDon.HoaDonService;
import com.example.sd40.service.HoaDon.PhuongThucThanhToanService;
import com.example.sd40.service.KhachHang.KhachHangCusService;
import com.example.sd40.service.KhachHang.TinhThanhPhoService;
import com.example.sd40.service.SanPham.*;
import com.example.sd40.vnpay.CreatePayMentMethodTransferRequest;
import com.example.sd40.vnpay.PayMentVnpayRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
        if (idKH == null){

        ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh = ctspmshaService.getHAbySPandMS(idsp,idms);
        ChiTietSanPham chiTietSanPham = ctspService.findCTSP(chiTietSanPhamMauSacHinhAnh.getId(),idkc);
        model.addAttribute("sp",sanPhamService.detail(idsp));
        model.addAttribute("ctsp",chiTietSanPham);
        model.addAttribute("soLuong",soLuong);
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
            model.addAttribute("listTinh",tinhThanhPhoService.getAllASC());
            model.addAttribute("diachimacdinh",khachHangCusService.layDiaChi(idKH));
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


}
