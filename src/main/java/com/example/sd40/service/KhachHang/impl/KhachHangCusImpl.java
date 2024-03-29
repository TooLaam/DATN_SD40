package com.example.sd40.service.KhachHang.impl;

import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.repository.TaiKhoan.KhachHangCusRepository;
import com.example.sd40.service.KhachHang.KhachHangCusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangCusImpl implements KhachHangCusService {
    @Autowired
    KhachHangCusRepository khachHangCusRepository;
    @Override
    public KhachHang login(String taiKhoan, String matKhau) {
        return khachHangCusRepository.login(taiKhoan,matKhau);
    }

    @Override
    public List<KhachHang> getAll() {
        return khachHangCusRepository.findAll();
    }

    @Override
    public List<GioHangChiTiet> detailSPGioHang(Long idKH) {
        return khachHangCusRepository.detailSPGioHang(idKH);
    }
}
