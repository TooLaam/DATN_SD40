package com.example.sd40.repository.KhachHang;

import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.entity.KhachHang.DiaChiChiTiet;
import com.example.sd40.entity.KhachHang.tinhThanhPho;
import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.Voucher.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhachHangCusRepository extends JpaRepository<KhachHang,Long> {

    @Query("select kh from KhachHang kh where kh.taiKhoan=?1 and kh.matKhau = ?2")
    KhachHang login(String taiKhoan, String matKhau);

    @Query("select ghct from GioHang gh join GioHangChiTiet ghct on gh.id = ghct.gioHang.id join ChiTietSanPham ctsp on ctsp.Id = ghct.chiTietSanPham.Id where gh.khachHang.id = ?1")
    List<GioHangChiTiet> detailSPGioHang(Long idKH);

    @Query("select dc from DiaChiChiTiet dc where dc.khachHang.id = ?1 and dc.macDinh = 1")
    List<DiaChiChiTiet> layDiaChi(Long idKH);

    @Query(value = "select id from tinh_thanh_pho where ten like N'Hà Nội'",nativeQuery = true )
    Object layIDTinh();

    @Query(value = "select id, ten_voucher,phan_tram_giam,giam_toi_da,gia_tri_don_toi_thieu from voucher where ngay_bat_dau<=GETDATE() and ngay_ket_thuc>= getdate() and trang_thai=1 order by phan_tram_giam  desc",nativeQuery = true)
    List<Object> getVoucherHoaDon();
}
