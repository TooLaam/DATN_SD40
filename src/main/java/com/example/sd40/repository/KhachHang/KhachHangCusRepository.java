package com.example.sd40.repository.KhachHang;

import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.entity.KhachHang.DiaChiChiTiet;
import com.example.sd40.entity.KhachHang.tinhThanhPho;
import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.Voucher.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface KhachHangCusRepository extends JpaRepository<KhachHang,Long> {

    @Query("select kh from KhachHang kh where kh.taiKhoan=?1 and kh.matKhau = ?2")
    KhachHang login(String taiKhoan, String matKhau);

    @Query("select ghct from GioHang gh join GioHangChiTiet ghct on gh.id = ghct.gioHang.id join ChiTietSanPham ctsp on ctsp.Id = ghct.chiTietSanPham.Id where gh.khachHang.id = ?1")
    List<GioHangChiTiet> detailSPGioHang(Long idKH);

    @Query("select dc from DiaChiChiTiet dc where dc.khachHang.id = ?1 and dc.macDinh = 0")
    DiaChiChiTiet layDiaChi(Long idKH);

    @Query("select dc from DiaChiChiTiet dc where dc.khachHang.id = ?1")
    List <DiaChiChiTiet> danhSachDiaChi(Long idKH);

    @Query(value = "select id from tinh_thanh_pho where ten like N'Hà Nội'",nativeQuery = true )
    Object layIDTinh();

    @Query(value = "select id, ten_voucher,phan_tram_giam,giam_toi_da,gia_tri_don_toi_thieu from voucher where ngay_bat_dau<=GETDATE() and ngay_ket_thuc>= getdate() and trang_thai=1 order by phan_tram_giam  desc",nativeQuery = true)
    List<Object> getVoucherHoaDon();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO hoa_don (trang_thai, ngay_tao, tong_tien, phan_tram_khuyen_mai, voucher_id,ghi_chu,tong_tien_giam,phuong_thuc_thanh_toan_id,ten_nguoi_nhan,sdt_nguoi_nhan,dia_chi_nguoi_nhan,tong_tien_san_pham_chua_giam,phi_ship) \n" +
            "VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13)",nativeQuery = true)
    void saveHD(Integer trangThai, Date ngayTao, BigDecimal tongTien, Integer phanTramKhuyenMai, Long idVoucher, String ghiChu,BigDecimal tongTienGiam,Long phuongThucThanhToanID,String tenNguoiNhan,String sdtNguoiNhan,String diaChiNguoiNhan,BigDecimal tongTienSanPhamChuaGiam,BigDecimal phiShip);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO hoa_don (trang_thai, ngay_tao, tong_tien, phan_tram_khuyen_mai, voucher_id,ghi_chu,tong_tien_giam,phuong_thuc_thanh_toan_id,ten_nguoi_nhan,sdt_nguoi_nhan,dia_chi_nguoi_nhan,tong_tien_san_pham_chua_giam,phi_ship,khach_hang_id) \n" +
            "VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14)",nativeQuery = true)
    void saveHDKhachHang(Integer trangThai, Date ngayTao, BigDecimal tongTien, Integer phanTramKhuyenMai, Long idVoucher, String ghiChu,BigDecimal tongTienGiam,Long phuongThucThanhToanID,String tenNguoiNhan,String sdtNguoiNhan,String diaChiNguoiNhan,BigDecimal tongTienSanPhamChuaGiam,BigDecimal phiShip,Long idKhachHang);


    @Query(value = "select top(1) id from hoa_don order by id desc",nativeQuery = true)
    Long idHoaDonMoiTao();
    @Query("select v.id from Voucher v where v.phanTramGiam=0")
    Long idVoucherOk();

    @Query(value = "select top(1) id from dia_chi order by id desc",nativeQuery = true)
    Long idDiaChiMoiNhat();


    @Query("select dc.id, dc.diaChi.moTa,dc.diaChi.tinhThanhPho.ten,dc.diaChi.tinhThanhPho.id,dc.macDinh from DiaChiChiTiet dc where dc.khachHang.id = ?1")
    List<Object[]> listDiaCHiMoiNhat(Long idKH);

    @Transactional
    @Modifying
    @Query("update DiaChiChiTiet dc set dc.macDinh=0 where dc.id=?1")
    void updateMacDinh(Long idDCCT);

    @Transactional
    @Modifying
    @Query("update DiaChiChiTiet dc set dc.macDinh=1 where dc.id not in (?1) and dc.khachHang.id = ?2")
    void updateMacDinhKhac(Long idDCCT,Long idKH);

    @Query("select hdct from HoaDonChiTiet hdct where hdct.hoaDon.id=?1")
    List<HoaDonChiTiet> listHDCT(Long idHD);

    @Query("select hd from HoaDon hd where hd.khachHang.id = ?1 and hd.trangThai = ?2")
    List<HoaDon> listHoaDon(Long idKH, Integer trangThai);

    @Query("select hd from HoaDon hd where hd.khachHang.id = ?1")
    List<HoaDon> getAllHDByIdKhachHang(Long idkh);

    @Query("select sp.ten, ms.ten, kc.ten, hdct.soLuong , hdct.giaHienHanh, hdct.giaDaGiam,ctspms.hinhAnh from HoaDonChiTiet hdct join ChiTietSanPham ctsp on ctsp.Id = hdct.chiTietSanPham.Id " +
            " join ChiTietSanPhamMauSacHinhAnh ctspms on ctsp.chiTietSanPhamMauSacHinhAnh.Id = ctspms.Id" +
            " join SanPham sp on ctspms.sanPham.id = sp.id" +
            " join Mau_sac ms on ctspms.mau_sac.Id = ms.Id" +
            " join KichCo kc on kc.Id = ctsp.kichCo.Id" +
            " where hdct.hoaDon.id = ?1")
    List<Object[]> listHDCTByHoaDon(Long idHD);

    @Query("select ctsp from HoaDonChiTiet hdct join ChiTietSanPham ctsp on hdct.chiTietSanPham.Id = ctsp.Id where hdct.hoaDon.id = ?1")
    List<ChiTietSanPham> fidCTSPByHD(Long idhd);

    @Query("select hdct from HoaDonChiTiet hdct where hdct.hoaDon.id = ?1")
    List<HoaDonChiTiet> fidHDCTByHD(Long idhd);

    @Transactional
    @Modifying
    @Query("update KhachHang dc set dc.taiKhoan=?1, dc.matKhau = ?2 where dc.id = ?3 ")
    void doiMatKhau(String taiKhoan, String matKhau,Long idKH);

    @Query("select kh from KhachHang kh where kh.id not in (?1) and kh.taiKhoan = ?2")
    List<KhachHang> checkTaiKhoan(Long idkh, String taiKhoan);

    @Query("select kh from KhachHang kh where kh.taiKhoan = ?1")
    List<KhachHang> checlTaiKhoanAdd(String taiKhoan);



}
