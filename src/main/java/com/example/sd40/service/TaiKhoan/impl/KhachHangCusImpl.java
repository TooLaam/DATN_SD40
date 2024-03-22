package com.example.sd40.service.TaiKhoan.impl;

import com.example.sd40.entity.Gio_hang.GioHangDetail;
import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.repository.TaiKhoan.KhachHangCusRepository;
import com.example.sd40.service.TaiKhoan.KhachHangCusService;
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
    public List<GioHangDetail> detailSPGioHang(Long idKH) {
        return khachHangCusRepository.detailSPGioHang(idKH);
    }
}
