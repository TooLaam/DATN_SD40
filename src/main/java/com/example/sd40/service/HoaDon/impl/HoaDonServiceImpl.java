package com.example.sd40.service.HoaDon.impl;

import com.example.sd40.entity.Gio_hang.GioHang;
import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.entity.Hoa_don.PhuongThucThanhToan;
import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.repository.HoaDon.HoaDonRepository;
import com.example.sd40.repository.HoaDon.PhuongThucThanhToanRepository;
import com.example.sd40.repository.HoaDon.VoucherRepository;
import com.example.sd40.repository.SanPham.CTSPMSHARepository;
import com.example.sd40.repository.SanPham.CTSPRepository;
import com.example.sd40.repository.TaiKhoan.KhachHangRepository;
import com.example.sd40.repuest.HoaDonKhachHangResquest;
import com.example.sd40.service.HoaDon.HoaDonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/**
 * @author quynh
 */
@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private PhuongThucThanhToanRepository phuongThucThanhToanRepository;
    
    @Autowired
    private VoucherRepository voucherRepository;
    
    @Autowired
    private CTSPRepository ctspRepository;
    

    @Override
    public String createHoaDonKhachHang(HoaDonKhachHangResquest resquest) {
        HoaDon hoaDon = new HoaDon();
        BeanUtils.copyProperties(resquest, hoaDon);
        hoaDon.setMaHoaDon("HD"+ new Random().nextInt(900) + 100);
        hoaDon.setNgayThanhToan(new Date());
        hoaDon.setLoaiHoaDon(1);
        hoaDon.setTrangThai(0);
        hoaDon.setVoucher(voucherRepository.findById(resquest.getMaVoucher()).get());
        if(!resquest.getIdUser().isEmpty()){
            hoaDon.setTaiKhoan(khachHangRepository.findById(Long.parseLong(resquest.getIdUser())).get());
        }
        
        PhuongThucThanhToan phuongThucThanhToan = new PhuongThucThanhToan();
        phuongThucThanhToan.setMaPhuongThuc(resquest.getMaPhuongThuc());
        phuongThucThanhToan.setTrangThai(resquest.getTrangThai());
        phuongThucThanhToan.setPrice(resquest.getPriceThanhToan());
        hoaDon.setPhuongThucThanhToan(phuongThucThanhToanRepository.save(phuongThucThanhToan));
        hoaDonRepository.save(hoaDon);
        resquest.getProductDetaiRequestList().stream().forEach(item ->{
            ChiTietSanPham chiTietSanPham = ctspRepository.findById(item.getId()).get();
            if(chiTietSanPham.getSoLuong() < item.getQuantity()){
                throw new RuntimeException("Vượt quá số lượng sản  phẩm");
            }
            chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - item.getQuantity());
            ctspRepository.save(chiTietSanPham);
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setChiTietSanPham(chiTietSanPham);
            hoaDonChiTiet.setQuantity(item.getQuantity());
            hoaDonChiTiet.setPrice(item.getPrice());
            hoaDonChiTiet.setHoaDon(hoaDon);
        });
         return "Thành công";
    }
}
