package com.example.sd40.service.GioHang;

import com.example.sd40.entity.Gio_hang.GioHang;

public interface GioHangService {
    GioHang detail(Long id);
    GioHang findGioHangByKhachHang(Long idkh);
}
