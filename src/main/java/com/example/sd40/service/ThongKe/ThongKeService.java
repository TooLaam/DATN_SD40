package com.example.sd40.service.ThongKe;

import com.example.sd40.entity.ThongKe;

import java.util.Date;
import java.util.List;

public interface ThongKeService {
    Object TongDonHangAll(String ngayBatDau, String ngayKetThuc);
    Object DoanhSoVaSanPhamDaBanAll(String ngayBatDau, String ngayKetThuc);
    Integer hangTonKho();
    List<Object[]> getTongDoanhThuByMonthAndYear(String nam);
}
