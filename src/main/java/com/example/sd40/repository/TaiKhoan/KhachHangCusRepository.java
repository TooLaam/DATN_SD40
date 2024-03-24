package com.example.sd40.repository.TaiKhoan;

import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.entity.KhachHang.KhachHang;
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
}
