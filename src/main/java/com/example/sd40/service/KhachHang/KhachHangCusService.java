package com.example.sd40.service.KhachHang;

import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.entity.KhachHang.DiaChiChiTiet;
import com.example.sd40.entity.KhachHang.Dia_Chi;
import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.KhachHang.tinhThanhPho;
import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.Voucher.Voucher;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface KhachHangCusService {
    KhachHang login(String taiKhoan, String matKhau);
    List<KhachHang> getAll();
    List<GioHangChiTiet> detailSPGioHang(Long idKH);
    DiaChiChiTiet layDiaChi(Long idKH);
    List<Object> getVoucherHoaDon();
    Object layIDTinh();
    void saveHD(Integer trangThai, Date ngayTao, BigDecimal tongTien, Integer phanTramKhuyenMai, Long idVoucher, String ghiChu, BigDecimal tongTienGiam, Long phuongThucThanhToanID, String tenNguoiNhan, String sdtNguoiNhan, String diaChiNguoiNhan, BigDecimal tongTienSanPhamChuaGiam,BigDecimal phiShip);
    Long idHoaDonMoiTao();
    void addHDCT(HoaDonChiTiet hoaDonChiTiet);
    HoaDon detailHoaDon(Long idHD);
    Long idVoucherOk();
    List <DiaChiChiTiet> danhSachDiaChi(Long idKH);
    void addDiaChi(Dia_Chi diaChi);
    tinhThanhPho detailTinhThanhPho(Long id);
    Long idDiaChiMoiNhat();
    void addDiaChiChiTiet(DiaChiChiTiet diaChiChiTiet);
    KhachHang detailKhachHang(Long id);
    DiaChiChiTiet detailDiaChiChiTiet(Long id);
    Dia_Chi detailDiaChi(Long id);
    List<Object[]> listDiaCHiMoiNhat(Long idKH);
    void deleteDCCT(Long id);
    void updateMacDinhKhac(Long idDCCT,Long idKH);
    void updateMacDinh(Long idDCCT);
    void deleteDiaChi(Long id);
    void saveHDKhachHang(Integer trangThai, Date ngayTao, BigDecimal tongTien, Integer phanTramKhuyenMai, Long idVoucher, String ghiChu,BigDecimal tongTienGiam,Long phuongThucThanhToanID,String tenNguoiNhan,String sdtNguoiNhan,String diaChiNguoiNhan,BigDecimal tongTienSanPhamChuaGiam,BigDecimal phiShip,Long idKhachHang);
    List<HoaDonChiTiet> listHDCT(Long idHD);
    List<HoaDon> listHoaDon(Long idKH, Integer trangThai);
    List<Object[]> listHDCTByHoaDon(Long idHD);
    List<HoaDon> getAllHDByIdKhachHang(Long idkh);
    List<HoaDonChiTiet> fidHDCTByHD(Long idhd);
    List<ChiTietSanPham> fidCTSPByHD(Long idhd);



}
