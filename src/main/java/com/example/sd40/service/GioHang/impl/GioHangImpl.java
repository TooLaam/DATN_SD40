package com.example.sd40.service.GioHang.impl;

import com.example.sd40.entity.Gio_hang.GioHang;
import com.example.sd40.repository.GioHang.GioHangRepository;
import com.example.sd40.service.GioHang.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GioHangImpl implements GioHangService {
    @Autowired
    GioHangRepository gioHangRepository;
    @Override
    public GioHang detail(Long id) {
        return gioHangRepository.getById(id);
    }

    @Override
    public GioHang findGioHangByKhachHang(Long idkh) {
        return gioHangRepository.findGioHangByKhachHang(idkh);
    }
}
