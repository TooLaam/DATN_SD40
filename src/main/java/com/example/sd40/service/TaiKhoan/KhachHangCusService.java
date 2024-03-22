package com.example.sd40.service.TaiKhoan;

import com.example.sd40.entity.Gio_hang.GioHangDetail;
import com.example.sd40.entity.KhachHang.KhachHang;

import java.util.List;

public interface KhachHangCusService {
    KhachHang login(String taiKhoan, String matKhau);
    List<KhachHang> getAll();
    List<GioHangDetail> detailSPGioHang(Long idKH);
}
