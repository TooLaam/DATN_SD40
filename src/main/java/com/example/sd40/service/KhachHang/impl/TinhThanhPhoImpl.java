package com.example.sd40.service.KhachHang.impl;

import com.example.sd40.entity.KhachHang.tinhThanhPho;
import com.example.sd40.repository.KhachHang.TinhRepository;
import com.example.sd40.service.KhachHang.TinhThanhPhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TinhThanhPhoImpl implements TinhThanhPhoService {
    @Autowired
    TinhRepository tinhRepository;
    @Override
    public List<tinhThanhPho> getAllASC() {
        return tinhRepository.getAllASC();
    }
}
