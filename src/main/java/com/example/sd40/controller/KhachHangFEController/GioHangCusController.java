package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.service.GioHang.GioHangChiTietService;
import com.example.sd40.service.GioHang.GioHangService;
import com.example.sd40.service.KhachHang.KhachHangCusService;
import com.example.sd40.service.KhachHang.TinhThanhPhoService;
import com.example.sd40.service.SanPham.CTSPMSHAService;
import com.example.sd40.service.SanPham.CTSPService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
public class GioHangCusController {
    @Autowired
    GioHangChiTietService gioHangChiTietService;
    @Autowired
    GioHangService gioHangService;
    @Autowired
    CTSPService ctspService;
    @Autowired
    CTSPMSHAService ctspmshaService;
    @Autowired
    TinhThanhPhoService tinhThanhPhoService;

    @Autowired
    KhachHangCusService khachHangCusService;
    @GetMapping("/hienthiAddHD/{idsp}")
    public ResponseEntity<?> hienthiAddHD(Model model, HttpSession session,
                                          @PathVariable("idsp") Long idsp,
                                          @RequestParam("mauSac") Long idms,
                                          @RequestParam("soLuong") int soLuong,
                                          @RequestParam("kichCo") Long kichCo) {
        Long idKH = (Long) session.getAttribute("idKhachHang");
        List<GioHangChiTiet> gioHangChiTiets = gioHangChiTietService.kiemTraAddGHCT(ctspService.findCTSP(ctspmshaService.getHAbySPandMS(idsp, idms).getId(), kichCo).getId(),idKH);
        if (!gioHangChiTiets.isEmpty()) {
            gioHangChiTietService.updateSoLuongKhiThem(soLuong, ctspService.findCTSP(ctspmshaService.getHAbySPandMS(idsp, idms).getId(), kichCo).getId());
            session.setAttribute("update", "update");
            return ResponseEntity.ok("update");
        } else {
            GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setGioHang(gioHangService.findGioHangByKhachHang(idKH));
            gioHangChiTiet.setChiTietSanPham(ctspService.findCTSP(ctspmshaService.getHAbySPandMS(idsp, idms).getId(), kichCo));
            gioHangChiTiet.setTrangThai(0);
            gioHangChiTiet.setSoLuong(soLuong);
            gioHangChiTietService.add(gioHangChiTiet);

            return ResponseEntity.ok("success");
        }
    }

    @GetMapping("/hienthigiohangchitiet/{idkh}")
    public String hienthigiohangchitiet(Model model, HttpSession session,
                                        @PathVariable("idkh") Long idkh

    ) {
        model.addAttribute("slspgh",khachHangCusService.detailSPGioHang(idkh).size());

        List<GioHangChiTiet> gioHangChiTiets = gioHangChiTietService.hienthigiohangchitiet(gioHangService.findGioHangByKhachHang(idkh).getId());
        model.addAttribute("listghct",gioHangChiTiets);
        model.addAttribute("view", "/cart/index.jsp");
        return "/customerFE/index";

    }


    @PostMapping("/updatesoluong")
    public ResponseEntity<?> updateProductQuantity(@RequestParam("idghct")Long idghct,
                                                   @RequestParam("soluong")Integer soluong) {
        try {
            // Gọi phương thức trong service để cập nhật số lượng sản phẩm
            GioHangChiTiet gioHangChiTiet = gioHangChiTietService.kiemTraSoLuongTruocKhiUpdate(idghct);
            if (soluong > gioHangChiTiet.getChiTietSanPham().getSoLuong()){
                return ResponseEntity.ok("ko");
            }else {
                gioHangChiTietService.updateSoLuong(soluong, idghct);
                return ResponseEntity.ok("ok");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating quantity");
        }
    }

    @PostMapping("/deleteGHCT")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@RequestParam("productId") Long productId) {
        // Gọi phương thức trong service để xóa sản phẩm từ cơ sở dữ liệu
        gioHangChiTietService.deleteCTGH(productId);
        // Trả về phản hồi cho máy khách
        return ResponseEntity.ok("Product deleted successfully");
    }
    Date currentDate = new Date(System.currentTimeMillis());



}
