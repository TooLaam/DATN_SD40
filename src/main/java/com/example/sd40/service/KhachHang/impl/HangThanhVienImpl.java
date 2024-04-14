package com.example.sd40.service.KhachHang.impl;
import com.example.sd40.entity.KhachHang.HangThanhVien;
import com.example.sd40.repository.KhachHang.HangThanhVienRepository;
import com.example.sd40.service.KhachHang.HangThanhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HangThanhVienImpl implements HangThanhVienService {
    @Autowired
    HangThanhVienRepository hangThanhVienRepository;

    @Override
    public HangThanhVien detail(Long id) {
        return hangThanhVienRepository.getById(id);
    }
}
