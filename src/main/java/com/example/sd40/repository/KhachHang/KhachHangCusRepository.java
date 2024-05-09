package com.example.sd40.repository.KhachHang;

import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.entity.KhachHang.DiaChiChiTiet;
import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.San_pham.ChiTietSanPham;
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

    @Query(value = "select id, ten_voucher,phan_tram_giam,giam_toi_da,gia_tri_don_toi_thieu,so_luong from voucher where ngay_bat_dau<=GETDATE() and ngay_ket_thuc>= getdate() and trang_thai=0 and so_luong >0 and phan_tram_giam >0 order by phan_tram_giam  desc",nativeQuery = true)
    List<Object> getVoucherHoaDon();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO hoa_don (trang_thai, ngay_tao, tong_tien, phan_tram_khuyen_mai, voucher_id,ghi_chu,tong_tien_giam,phuong_thuc_thanh_toan_id,ten_nguoi_nhan,sdt_nguoi_nhan,dia_chi_nguoi_nhan,tong_tien_san_pham_chua_giam,phi_ship,ngay_cap_nhat,ma) \n" +
            "VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14,?15)",nativeQuery = true)
    void saveHD(Integer trangThai, Date ngayTao, BigDecimal tongTien, Integer phanTramKhuyenMai, Long idVoucher, String ghiChu,BigDecimal tongTienGiam,Long phuongThucThanhToanID,String tenNguoiNhan,String sdtNguoiNhan,String diaChiNguoiNhan,BigDecimal tongTienSanPhamChuaGiam,BigDecimal phiShip,Date ngayCapNhat,String ma);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO hoa_don (trang_thai, ngay_tao, tong_tien, phan_tram_khuyen_mai, voucher_id,ghi_chu,tong_tien_giam,phuong_thuc_thanh_toan_id,ten_nguoi_nhan,sdt_nguoi_nhan,dia_chi_nguoi_nhan,tong_tien_san_pham_chua_giam,phi_ship,khach_hang_id,ngay_cap_nhat,ma,ngay_thanh_toan) \n" +
            "VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14,?15,?16,?17)",nativeQuery = true)
    void saveHDKhachHang(Integer trangThai, Date ngayTao, BigDecimal tongTien, Integer phanTramKhuyenMai, Long idVoucher, String ghiChu,BigDecimal tongTienGiam,Long phuongThucThanhToanID,String tenNguoiNhan,String sdtNguoiNhan,String diaChiNguoiNhan,BigDecimal tongTienSanPhamChuaGiam,BigDecimal phiShip,Long idKhachHang,Date ngayCapNhat,String ma,Date ngayThanhToan);


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

    @Query("select hd from HoaDon hd where hd.khachHang.id = ?1 and hd.trangThai = ?2 order by hd.id desc ")
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

    @Transactional
    @Modifying
    @Query("update HoaDon hd set hd.trangThai =4,hd.ngayThanhToan = ?2,hd.ngayCapNhat=?3 where hd.id=?1")
    void hoanThanhHoaDon(Long idhd,Date ngayThanhToan,Date ngayCapNhat);

    @Transactional
    @Modifying
    @Query("update HoaDon hd set hd.trangThai =4,hd.ngayCapNhat=?2 where hd.id=?1")
    void hoanThanhHoaDonVNPAY(Long idhd,Date ngayCapNhat);

    @Transactional
    @Modifying
    @Query("update HoaDon hd set hd.trangThai =5, hd.ghiChu = ?2,hd.ngayCapNhat=?3 where hd.id = ?1")
    void HuyHoaDon(Long idhd,String lyDo,Date ngayCapNhat);

    @Transactional
    @Modifying
    @Query("update ChiTietSanPham ct set ct.soLuong = ct.soLuong+?1 where ct.Id = ?2")
    void capNhatSoLuongSPSauKhiXoa(Integer soLuong, Long idctsp);

    @Transactional
    @Modifying
    @Query("update SanPham ct set ct.soLuongDaBan = ct.soLuongDaBan+?1 where ct.id = ?2")
    void capNhatSoLuongSPDaBan(Integer soLuong, Long idSP);

    @Query("select sum(hd.soLuong),ct.sanPham.id from HoaDonChiTiet hd join ChiTietSanPham ctsp on hd.chiTietSanPham.Id = ctsp.Id" +
            " join ChiTietSanPhamMauSacHinhAnh ct on ctsp.chiTietSanPhamMauSacHinhAnh.Id = ct.Id where hd.hoaDon.id=?1 " +
            " group by ct.sanPham.id")
    List<Object[]> soLuongDaBan(Long idHD);

    @Query(value = "select top(1) id from khach_hang order by id desc",nativeQuery = true)
    Long idKhachHangMoiTao();

    @Query(value = "select top(1) ma from hoa_don order by id desc",nativeQuery = true)
    String MaHDCuoi();

    @Query("select kh from KhachHang kh where kh.email = ?1")
    KhachHang quenMatKhau(String email);

    @Query("select kh from KhachHang kh where kh.email = ?1 and kh.id not in (?2)")
    KhachHang KtraEmail(String email, Long id);
}
