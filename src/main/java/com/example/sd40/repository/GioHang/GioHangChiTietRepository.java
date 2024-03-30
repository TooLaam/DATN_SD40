package com.example.sd40.repository.GioHang;

import com.example.sd40.entity.Gio_hang.GioHang;
import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author quynh
 */
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Long> {

    List<GioHangChiTiet> findAllByGioHang(GioHang gioHang);

    void  deleteAllByGioHang(GioHang gioHang);

    @Query("select ghct from GioHangChiTiet ghct where ghct.gioHang.id =?1 ")
    List<GioHangChiTiet> hienthigiohangchitiet(Long idGH);

    @Query("select ghct from GioHangChiTiet ghct where ghct.chiTietSanPham.Id =?1 ")
    List<GioHangChiTiet> kiemTraAddGHCT(Long idctsp);

    @Query("select gh from GioHangChiTiet gh where gh.id =?1")
    GioHangChiTiet kiemTraSoLuongTruocKhiUpdate(Long idGHCT);

    @Transactional
    @Modifying
    @Query("update GioHangChiTiet ghct set ghct.soLuong =ghct.soLuong +?1 where ghct.chiTietSanPham.Id =?2")
    void updateSoLuongKhiThem(Integer soLuong,Long idsp);

    @Transactional
    @Modifying
    @Query("update GioHangChiTiet ghct set ghct.soLuong =?1 where ghct.id =?2")
    void updateSoLuong(Integer soLuong,Long idGHCT);
}
