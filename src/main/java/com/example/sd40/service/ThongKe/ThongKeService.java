package com.example.sd40.service.ThongKe;

import com.example.sd40.entity.ThongKe;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ThongKeService {
    Object TongDonHangAll(String ngayBatDau, String ngayKetThuc);
    Integer SanPhamDaBanAll(String ngayBatDau, String ngayKetThuc);
    Object DoanhThu(String ngayBatDau, String ngayKetThuc);
    Integer hangTonKho();
    List<Object[]> getTongDoanhThuByMonthAndYear(String nam);
}
