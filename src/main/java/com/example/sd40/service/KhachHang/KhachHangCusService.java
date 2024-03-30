package com.example.sd40.service.KhachHang;

import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.entity.KhachHang.DiaChiChiTiet;
import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.KhachHang.tinhThanhPho;
import com.example.sd40.entity.Voucher.Voucher;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface KhachHangCusService {
    KhachHang login(String taiKhoan, String matKhau);
    List<KhachHang> getAll();
    List<GioHangChiTiet> detailSPGioHang(Long idKH);
    List<DiaChiChiTiet> layDiaChi(Long idKH);
    List<Object> getVoucherHoaDon();
    Object layIDTinh();
    void saveHD(Integer trangThai, Date ngayTao, BigDecimal tongTien, Integer phanTramKhuyenMai, Long idVoucher, String ghiChu, BigDecimal tongTienGiam, Long phuongThucThanhToanID, String tenNguoiNhan, String sdtNguoiNhan, String diaChiNguoiNhan, BigDecimal tongTienSanPhamChuaGiam,BigDecimal phiShip);
    void saveHDKhongCoVoucher(Integer trangThai, Date ngayTao, BigDecimal tongTien, String ghiChu,BigDecimal tongTienGiam,Long phuongThucThanhToanID,String tenNguoiNhan,String sdtNguoiNhan,String diaChiNguoiNhan,BigDecimal tongTienSanPhamChuaGiam,BigDecimal phiShip);
    Long idHoaDonMoiTao();
    void addHDCT(HoaDonChiTiet hoaDonChiTiet);
    HoaDon detailHoaDon(Long idHD);
    Long idVoucherOk();
}
