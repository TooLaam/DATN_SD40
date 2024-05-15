package com.example.sd40.service.ThongKe;

import java.util.List;

public interface ThongKeService {
    Object TongDonHangAll(String ngayBatDau, String ngayKetThuc);
    Integer SanPhamDaBanAll(String ngayBatDau, String ngayKetThuc);
    Object DoanhThu(String ngayBatDau, String ngayKetThuc);
    Integer hangTonKho();
    List<Object[]> getTongDoanhThuByMonthAndYear(String nam);
}
