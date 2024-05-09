package com.example.sd40.repository.GioHang;

import com.example.sd40.entity.Gio_hang.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author quynh
 */
public interface GioHangRepository extends JpaRepository<GioHang, Long> {
    @Query("select gh from GioHang gh where gh.khachHang.id=?1")
    GioHang findGioHangByKhachHang(Long idkh);
}
