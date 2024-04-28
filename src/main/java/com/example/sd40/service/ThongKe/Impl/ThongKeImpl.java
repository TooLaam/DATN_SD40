package com.example.sd40.service.ThongKe.Impl;

import com.example.sd40.entity.ThongKe;
import com.example.sd40.repository.ThongKe.ThongKeRepository;
import com.example.sd40.service.ThongKe.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ThongKeImpl implements ThongKeService {
    @Autowired
    ThongKeRepository thongKeRepository;
    @Override
    public Object TongDonHangAll(String ngayBatDau, String ngayKetThuc) {
        return thongKeRepository.TongDonHangAll(ngayBatDau,ngayKetThuc);
    }

    @Override
    public Object DoanhSoVaSanPhamDaBanAll(String ngayBatDau, String ngayKetThuc) {
        return thongKeRepository.DoanhSoVaSanPhamDaBanAll(ngayBatDau,ngayKetThuc);
    }

    @Override
    public Integer hangTonKho() {
        return thongKeRepository.hangTonKho();
    }

    @Override
    public List<Object[]> getTongDoanhThuByMonthAndYear(String nam) {
        return thongKeRepository.getTongDoanhThuByMonthAndYear(nam);
    }
}
