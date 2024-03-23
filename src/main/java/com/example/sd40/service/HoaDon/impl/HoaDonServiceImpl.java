package com.example.sd40.service.HoaDon.impl;

import com.example.sd40.entity.Gio_hang.GioHang;
import com.example.sd40.entity.Gio_hang.GioHangDetail;
import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.entity.Hoa_don.LichSu;
import com.example.sd40.entity.Hoa_don.PhuongThucThanhToan;
import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.Tai_khoan.TaiKhoan;
import com.example.sd40.repository.GioHang.GioHangDetailRepository;
import com.example.sd40.repository.GioHang.GioHangRepository;
import com.example.sd40.repository.HoaDon.*;
import com.example.sd40.repository.SanPham.CTSPRepository;
import com.example.sd40.repository.TaiKhoan.KhachHangRepository;
import com.example.sd40.repuest.HoaDonKhachHangResquest;
import com.example.sd40.service.HoaDon.HoaDonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private LichSuRepository lichSuRepository;


    @Override
    public List<HoaDon> findAllByStatus(int status) {
        return hoaDonRepository.findAllByTrangThai(status);
    }

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
        DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");

        try {
            Date date = dateFormat.parse(resquest.getNgayNhan());
            hoaDon.setNgayThanhToan(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hoaDon.setNgayTao(new Date());
        PhuongThucThanhToan phuongThucThanhToan = new PhuongThucThanhToan();
        phuongThucThanhToan.setMaPhuongThuc(resquest.getMaPhuongThuc());
        phuongThucThanhToan.setTrangThai(0);
        phuongThucThanhToan.setPrice(resquest.getTongTien());
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
            hoaDonChiTiet.setPrice(item.getPrice());
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTietRepository.save(hoaDonChiTiet);
        });
        LichSu lichSu = new LichSu();
        lichSu.setHoaDon(hoaDon);
        lichSu.setNgayTao(System.currentTimeMillis());
        lichSu.setNote("Tạo hóa đơn");
        lichSu.setStatus(0);
        lichSuRepository.save(lichSu);
        gioHangDetailRepository.deleteAllByGioHang(gioHang);
         return "Thành công";
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
            chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + item.getQuantity());
            ctspRepository.save(chiTietSanPham);
        });
        return null;
    }


    @Override
    public HoaDon findByid(long id) {
        return hoaDonRepository.findById(id).get();
    }
}
