package com.example.sd40.service.HoaDon.impl;

import com.example.sd40.entity.Gio_hang.GioHang;
import com.example.sd40.entity.Gio_hang.GioHangDetail;
import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.entity.Hoa_don.PhuongThucThanhToan;
import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.Tai_khoan.TaiKhoan;
import com.example.sd40.repository.GioHang.GioHangDetailRepository;
import com.example.sd40.repository.GioHang.GioHangRepository;
import com.example.sd40.repository.HoaDon.HoaDonChiTietRepository;
import com.example.sd40.repository.HoaDon.HoaDonRepository;
import com.example.sd40.repository.HoaDon.PhuongThucThanhToanRepository;
import com.example.sd40.repository.HoaDon.VoucherRepository;
import com.example.sd40.repository.SanPham.CTSPMSHARepository;
import com.example.sd40.repository.SanPham.CTSPRepository;
import com.example.sd40.repository.TaiKhoan.KhachHangRepository;
import com.example.sd40.repuest.HoaDonKhachHangResquest;
import com.example.sd40.service.GioHang.GioHangDetailSerVice;
import com.example.sd40.service.HoaDon.HoaDonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author quynh
 */
@Service
@Transactional
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

    @Autowired
    private GioHangDetailRepository gioHangDetailRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private GioHangRepository gioHangRepository;
    

    @Override
    public String createHoaDonKhachHang(HoaDonKhachHangResquest resquest) {
        HoaDon hoaDon = new HoaDon();
        BeanUtils.copyProperties(resquest, hoaDon);
        hoaDon.setMaHoaDon("HD"+ new Random().nextInt(900) + 100);
        hoaDon.setNgayThanhToan(new Date());
        hoaDon.setLoaiHoaDon(1);
        hoaDon.setTrangThai(0);
//        hoaDon.setVoucher(voucherRepository.findById(resquest.getMaVoucher()).get());
        if(!resquest.getIdUser().isEmpty()){
            hoaDon.setTaiKhoan(khachHangRepository.findById(Long.parseLong(resquest.getIdUser())).get());
        }
        
        PhuongThucThanhToan phuongThucThanhToan = new PhuongThucThanhToan();
        phuongThucThanhToan.setMaPhuongThuc(resquest.getMaPhuongThuc());
        phuongThucThanhToan.setTrangThai(resquest.getTrangThai());
        phuongThucThanhToan.setPrice(resquest.getPriceThanhToan());
        hoaDon.setPhuongThucThanhToan(phuongThucThanhToanRepository.save(phuongThucThanhToan));
        hoaDonRepository.save(hoaDon);
        TaiKhoan taiKhoan = khachHangRepository.findById(Long.parseLong(resquest.getIdUser())).get();
        GioHang gioHang = gioHangRepository.findByTkGioHang(taiKhoan).get();
        List<GioHangDetail> gioHangDetails =  gioHangDetailRepository.findAllByGioHang(gioHang);
        gioHangDetails.stream().forEach(item ->{
            ChiTietSanPham chiTietSanPham = item.getChiTietSanPham();
            if(chiTietSanPham.getSoLuong() < item.getQuantity()){
                throw new RuntimeException("Vượt quá số lượng sản  phẩm");
            }
            chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - item.getQuantity());
            ctspRepository.save(chiTietSanPham);
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setChiTietSanPham(chiTietSanPham);
            hoaDonChiTiet.setQuantity(item.getQuantity());
            hoaDonChiTiet.setPrice(new BigDecimal(item.getPrice()));
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTietRepository.save(hoaDonChiTiet);
        });
        gioHangDetailRepository.deleteAllByGioHang(gioHang);
         return "Thành công";
    }
}
