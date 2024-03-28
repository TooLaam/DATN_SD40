package com.example.sd40.service.HoaDon.impl;

//import com.example.sd40.entity.Gio_hang.GioHang;
import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.entity.Hoa_don.LichSu;
import com.example.sd40.entity.Hoa_don.PhuongThucThanhToan;
import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.repository.GioHang.GioHangRepository;
import com.example.sd40.repository.HoaDon.*;
import com.example.sd40.repository.SanPham.CTSPRepository;
import com.example.sd40.repository.KhachHang.KhachHangRepository;
import com.example.sd40.repuest.HoaDonKhachHangResquest;
import com.example.sd40.service.HoaDon.HoaDonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private  HoaDonRepository hoaDonRepository;

    @Autowired
    private  KhachHangRepository khachHangRepository;

    @Autowired
    private  PhuongThucThanhToanRepository phuongThucThanhToanRepository;

    @Autowired
    private  CTSPRepository ctspRepository;

    @Autowired
    private  HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private   GioHangRepository gioHangRepository;

    @Autowired
    private  LichSuRepository lichSuRepository;


    @Override
    public List<HoaDon> findAllByStatus(int status) {
        return hoaDonRepository.findAllByTrangThai(status);
    }


    @Override
    public String changeStatus(long id, String note) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        hoaDon.setTrangThai(hoaDon.getTrangThai() + 1);
        hoaDonRepository.save(hoaDon);
        LichSu lichSu = new LichSu();
        lichSu.setHoaDon(hoaDon);
        lichSu.setNgayTao(System.currentTimeMillis());
        lichSu.setNote(note);
        lichSu.setStatus(hoaDon.getTrangThai());
        lichSuRepository.save(lichSu);
        return null;
    }

    @Override
    public String huyHoaDon(long id, String note) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        hoaDon.setTrangThai(5);
        hoaDonRepository.save(hoaDon);
        LichSu lichSu = new LichSu();
        lichSu.setHoaDon(hoaDon);
        lichSu.setNgayTao(System.currentTimeMillis());
        lichSu.setNote(note);
        lichSu.setStatus(hoaDon.getTrangThai());
        lichSuRepository.save(lichSu);
        hoaDonChiTietRepository.findAllByHoaDon(hoaDon).stream().forEach(item ->{
            ChiTietSanPham chiTietSanPham = item.getChiTietSanPham();
            chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + item.getSoLuong());
            ctspRepository.save(chiTietSanPham);
        });
        return null;
    }


    @Override
    public HoaDon findByid(long id) {
        return hoaDonRepository.findById(id).get();
    }

    private  final Random random = new Random();

    public  HoaDonKhachHangResquest generateFakeData() {
        HoaDonKhachHangResquest hoaDon = new HoaDonKhachHangResquest();

        hoaDon.setPhiShip(BigDecimal.valueOf(random.nextDouble() * 1000));
        hoaDon.setTienGiam(BigDecimal.valueOf(random.nextDouble() * 500));
        hoaDon.setTongTien(BigDecimal.valueOf(random.nextDouble() * 5000));
        hoaDon.setTongTienKhiGiam(hoaDon.getTongTien().subtract(hoaDon.getTienGiam()));
        hoaDon.setGhiChu("Ghi chú đơn hàng");
        hoaDon.setNguoiNhan("Người nhận đơn hàng");
        hoaDon.setSdtNguoiNhan("0123456789");
        hoaDon.setThanhPho("Thành phố");
        hoaDon.setQuanHuyen("Quận/Huyện");
        hoaDon.setPhuongXa("Phường/Xã");
        hoaDon.setDiaChiNguoiNhan("Địa chỉ người nhận");
        hoaDon.setEmailNguoiNhan("example@example.com");
        hoaDon.setNgayNhan("2024-03-23");
        hoaDon.setMaPhuongThuc("MaPhuongThuc");
        hoaDon.setTrangThai(0); // Giả sử trạng thái từ 0 đến 3
        hoaDon.setMaVoucher(random.nextLong());
        hoaDon.setIdUser("1");
        hoaDon.setPrice(BigDecimal.valueOf(random.nextDouble() * 500));
        hoaDon.setPriceThanhToan(hoaDon.getPrice().subtract(hoaDon.getTienGiam()));

        return hoaDon;
    }



    @Override
    public  void create() {
        HoaDonKhachHangResquest resquest = generateFakeData();
        HoaDon hoaDon = new HoaDon();
        BeanUtils.copyProperties(resquest, hoaDon);
        hoaDon.setMaHoaDon("HD"+ new Random().nextInt(900) + 100);
        hoaDon.setNgayThanhToan(new Date());
        hoaDon.setTrangThai(0);
//        hoaDon.setVoucher(voucherRepository.findById(resquest.getMaVoucher()).get());
        if(!resquest.getIdUser().isEmpty()){
            hoaDon.setKhachHang(khachHangRepository.findById(Long.parseLong(resquest.getIdUser())).get());
        }
        DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");

        try {
            // Chuyển chuỗi thành đối tượng Date
            Date date = dateFormat.parse(resquest.getNgayNhan());
            hoaDon.setNgayThanhToan(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hoaDon.setNgayTao(new Date());
        PhuongThucThanhToan phuongThucThanhToan = new PhuongThucThanhToan();
        phuongThucThanhToan.setTrangThai(0);
        hoaDon.setPhuongThucThanhToan(phuongThucThanhToanRepository.save(phuongThucThanhToan));
        hoaDonRepository.save(hoaDon);
        ChiTietSanPham chiTietSanPham = ctspRepository.findById(Long.valueOf("1")).get();
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setChiTietSanPham(chiTietSanPham);
        hoaDonChiTiet.setSoLuong(2);
        hoaDonChiTiet.setGiaHienHanh(new BigDecimal("11313"));
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTietRepository.save(hoaDonChiTiet);
        LichSu lichSu = new LichSu();
        lichSu.setHoaDon(hoaDon);
        lichSu.setNgayTao(System.currentTimeMillis());
        lichSu.setNote("Tạo hóa đơn");
        lichSu.setStatus(0);
        lichSuRepository.save(lichSu);
    }
}
