package com.example.sd40.service.TaiKhoan;

import com.example.sd40.entity.Hoa_don.GioHangChiTiet;
import com.example.sd40.entity.KhachHang.KhachHang;

import java.util.List;

public interface KhachHangCusService {
    KhachHang login(String taiKhoan, String matKhau);
    List<KhachHang> getAll();
    List<GioHangChiTiet> detailSPGioHang(Long idKH);
}
