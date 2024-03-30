package com.example.sd40.service.KhachHang.impl;

import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.entity.KhachHang.DiaChiChiTiet;
import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.KhachHang.tinhThanhPho;
import com.example.sd40.entity.Voucher.Voucher;
import com.example.sd40.repository.HoaDon.HoaDonChiTietRepository;
import com.example.sd40.repository.HoaDon.HoaDonRepository;
import com.example.sd40.repository.KhachHang.KhachHangCusRepository;
import com.example.sd40.service.KhachHang.KhachHangCusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class KhachHangCusImpl implements KhachHangCusService {
    @Autowired
    KhachHangCusRepository khachHangCusRepository;
    @Autowired
    HoaDonRepository hoaDonRepository;
    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;
    @Override
    public KhachHang login(String taiKhoan, String matKhau) {
        return khachHangCusRepository.login(taiKhoan,matKhau);
    }

    @Override
    public List<KhachHang> getAll() {
        return khachHangCusRepository.findAll();
    }

    @Override
    public List<GioHangChiTiet> detailSPGioHang(Long idKH) {
        return khachHangCusRepository.detailSPGioHang(idKH);
    }

    @Override
    public List<DiaChiChiTiet> layDiaChi(Long idKH) {
        return khachHangCusRepository.layDiaChi(idKH);
    }

    @Override
    public List<Object> getVoucherHoaDon() {
        return khachHangCusRepository.getVoucherHoaDon();
    }

    @Override
    public Object layIDTinh() {
        return khachHangCusRepository.layIDTinh();
    }

    @Override
    public void saveHD(Integer trangThai, Date ngayTao, BigDecimal tongTien, Integer phanTramKhuyenMai, Long idVoucher, String ghiChu, BigDecimal tongTienGiam, Long phuongThucThanhToanID, String tenNguoiNhan, String sdtNguoiNhan, String diaChiNguoiNhan, BigDecimal tongTienSanPhamChuaGiam,BigDecimal phiShip) {
        khachHangCusRepository.saveHD(trangThai,ngayTao,tongTien,phanTramKhuyenMai,idVoucher,ghiChu,tongTienGiam,phuongThucThanhToanID,tenNguoiNhan,sdtNguoiNhan,diaChiNguoiNhan,tongTienSanPhamChuaGiam,phiShip);
    }

    @Override
    public void saveHDKhongCoVoucher(Integer trangThai, Date ngayTao, BigDecimal tongTien, String ghiChu, BigDecimal tongTienGiam, Long phuongThucThanhToanID, String tenNguoiNhan, String sdtNguoiNhan, String diaChiNguoiNhan, BigDecimal tongTienSanPhamChuaGiam, BigDecimal phiShip) {
        khachHangCusRepository.saveHDKhongCoVoucher(trangThai,ngayTao,tongTien,ghiChu,tongTienGiam,phuongThucThanhToanID,tenNguoiNhan,sdtNguoiNhan,diaChiNguoiNhan,tongTienSanPhamChuaGiam,phiShip);

    }

    @Override
    public Long idHoaDonMoiTao() {
        return khachHangCusRepository.idHoaDonMoiTao();
    }


    @Override
    public void addHDCT(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public HoaDon detailHoaDon(Long idHD) {
        return hoaDonRepository.getById(idHD);
    }

    @Override
    public Long idVoucherOk() {
        return khachHangCusRepository.idVoucherOk();
    }


}
