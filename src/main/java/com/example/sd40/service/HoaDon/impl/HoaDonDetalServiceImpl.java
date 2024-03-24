package com.example.sd40.service.HoaDon.impl;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.entity.Hoa_don.LichSu;
import com.example.sd40.entity.Hoa_don.PhuongThucThanhToan;
import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.repository.HoaDon.HoaDonChiTietRepository;
import com.example.sd40.repository.HoaDon.HoaDonRepository;
import com.example.sd40.repository.HoaDon.LichSuRepository;
import com.example.sd40.repository.HoaDon.PhuongThucThanhToanRepository;
import com.example.sd40.repository.SanPham.CTSPRepository;
import com.example.sd40.repuest.HoaDonDetailRequest;
import com.example.sd40.service.HoaDon.HoaDonDetalService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ClientInfoStatus;
import java.util.List;

/**
 * @author quynh
 */
@Service
@Transactional
public class HoaDonDetalServiceImpl implements HoaDonDetalService {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private CTSPRepository ctspRepository;

    @Autowired
    private LichSuRepository lichSuRepository;

    @Autowired
    private PhuongThucThanhToanRepository phuongThucThanhToanRepository;

    @Override
    public List<HoaDonChiTiet> findAllByHoaDon(Long id) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        return hoaDonChiTietRepository.findAllByHoaDon(hoaDon);
    }

    @Override
    public HoaDonChiTiet save(HoaDonDetailRequest request) {
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        BeanUtils.copyProperties(request, hoaDonChiTiet);
        HoaDon hoaDon = hoaDonRepository.findById(request.getIdBill()).get();
        ChiTietSanPham chiTietSanPham = ctspRepository.findById(request.getIdChiTietSanPham()).get();
        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - request.getQuantity());
        ctspRepository.save(chiTietSanPham);
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setChiTietSanPham(chiTietSanPham);
        hoaDonChiTietRepository.save(hoaDonChiTiet);
        LichSu lichSu = new LichSu();
        lichSu.setHoaDon(hoaDon);
        lichSu.setNgayTao(System.currentTimeMillis());
        lichSu.setNote(request.getNote());
        lichSu.setStatus(0);
        lichSuRepository.save(lichSu);
        return hoaDonChiTiet;
    }

    @Override
    public HoaDonChiTiet delete(long id, String note) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findById(id).get();
        ChiTietSanPham chiTietSanPham =hoaDonChiTiet.getChiTietSanPham();
        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + hoaDonChiTiet.getSoLuong());
        ctspRepository.save(chiTietSanPham);
        hoaDonChiTietRepository.deleteById(id);
        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.findAllByHoaDon(hoaDonChiTiet.getHoaDon());
        BigDecimal total = BigDecimal.ZERO;
        for (HoaDonChiTiet item : hoaDonChiTiets) {
            BigDecimal itemPrice = item.getGiaHienHanh().multiply(BigDecimal.valueOf(item.getSoLuong()));
            total = total.add(itemPrice);
        }
        HoaDon hoaDon = hoaDonChiTiet.getHoaDon();
        hoaDon.setTongTien(total.subtract(hoaDon.getTongTienGiam() == null ? new BigDecimal(0) : hoaDon.getTongTienGiam()));
        hoaDonRepository.save(hoaDon);
        LichSu lichSu = new LichSu();
        lichSu.setHoaDon(hoaDonChiTiet.getHoaDon());
        lichSu.setNgayTao(System.currentTimeMillis());
        lichSu.setNote(note);
        lichSu.setStatus(0);
        lichSuRepository.save(lichSu);
        return hoaDonChiTiet;
    }

    @Override
    public HoaDonChiTiet update(Long id, int quantity, String note) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findById(id).get();

        ChiTietSanPham chiTietSanPham =hoaDonChiTiet.getChiTietSanPham();
        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + hoaDonChiTiet.getSoLuong() - quantity);
        hoaDonChiTiet.setSoLuong(quantity);
        ctspRepository.save(chiTietSanPham);
        hoaDonChiTietRepository.save(hoaDonChiTiet);
        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.findAllByHoaDon(hoaDonChiTiet.getHoaDon());
        BigDecimal total = BigDecimal.ZERO;
        for (HoaDonChiTiet item : hoaDonChiTiets) {
            BigDecimal itemPrice = item.getGiaHienHanh().multiply(BigDecimal.valueOf(item.getSoLuong()));
            total = total.add(itemPrice);
        }
        HoaDon hoaDon = hoaDonChiTiet.getHoaDon();
        hoaDon.setTongTien(total.subtract(hoaDon.getTongTienGiam() == null ? new BigDecimal(0) : hoaDon.getTongTienGiam()));
        hoaDonRepository.save(hoaDon);
        LichSu lichSu = new LichSu();
        lichSu.setHoaDon(hoaDonChiTiet.getHoaDon());
        lichSu.setNgayTao(System.currentTimeMillis());
        lichSu.setNote(note);
        lichSu.setStatus(0);
        lichSuRepository.save(lichSu);
        return hoaDonChiTiet;
    }
}
