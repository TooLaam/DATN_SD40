package com.example.sd40.service.KhachHang.impl;


import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.repository.KhachHang.KhachHangRepository;
import com.example.sd40.service.KhachHang.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author quynh
 */
@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;


    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public void save(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
    }


    @Override
    public KhachHang detail(Long id) {
        return khachHangRepository.getById(id);
    }

    @Override
    public List<KhachHang> findByUserName(String ten) {
        return khachHangRepository.findByUserName(ten);
    }

    @Override
    public List<KhachHang> findByUserNameUpdate(String ten, Long id) {
        return khachHangRepository.findByUserNameUpdate(ten,id);
    }

    @Override
    public Long IdKHCuoi() {
        return khachHangRepository.IdKHCuoi();
    }
}
