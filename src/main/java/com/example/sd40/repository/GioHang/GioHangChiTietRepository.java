package com.example.sd40.repository.GioHang;

import com.example.sd40.entity.Gio_hang.GioHang;
import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.entity.San_pham.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("select ghct from GioHangChiTiet ghct where ghct.chiTietSanPham.Id =?1 and ghct.gioHang.khachHang.id = ?2 ")
    List<GioHangChiTiet> kiemTraAddGHCT(Long idctsp,Long idKH);

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

    @Query("SELECT p FROM GioHangChiTiet p WHERE p.id IN :ids")
    List<GioHangChiTiet> findAllById(@Param("ids") List<Long> ids);

    @Modifying
    @Query("delete from GioHangChiTiet gh where gh.id=?1")
    void deleteGHCTByCTSP(Long idGHCT);

    @Modifying
    @Query("delete from GioHangChiTiet gh where gh.gioHang.id=?1 and gh.chiTietSanPham.Id=?2")
    void deleteGHCTByCTSPAndIDKH(Long idGH, Long idCTSP);

    @Query("SELECT g FROM GioHangChiTiet g JOIN g.chiTietSanPham c WHERE g.soLuong > c.soLuong+1 AND g.id IN :ids")
    List<GioHangChiTiet> findAllByIdsAndSoLuongLessThanChiTietSanPham(@Param("ids") List<Long> ids);

    @Query("SELECT c.chiTietSanPham FROM GioHangChiTiet c WHERE c.id IN :ids")
    List<ChiTietSanPham> findByGioHangChiTietIds(@Param("ids") List<Long> gioHangChiTietIds);
}
