package com.example.sd40.service.KhachHang;

import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.entity.KhachHang.DiaChiChiTiet;
import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.KhachHang.tinhThanhPho;
import com.example.sd40.entity.Voucher.Voucher;

import java.util.List;

public interface KhachHangCusService {
    KhachHang login(String taiKhoan, String matKhau);
    List<KhachHang> getAll();
    List<GioHangChiTiet> detailSPGioHang(Long idKH);
    List<DiaChiChiTiet> layDiaChi(Long idKH);
    List<Object> getVoucherHoaDon();
    Object layIDTinh();
}
