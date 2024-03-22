package com.example.sd40.repository.GioHang;

import com.example.sd40.entity.Gio_hang.GioHang;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author quynh
 */
public interface GioHangRepository extends JpaRepository<GioHang, Long> {

//    Optional<GioHang> findByTkGioHang(TaiKhoan taiKhoan);
}
