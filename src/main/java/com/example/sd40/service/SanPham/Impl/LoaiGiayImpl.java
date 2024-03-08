package com.example.sd40.service.SanPham.Impl;

import com.example.sd40.entity.San_pham.TheLoai;
import com.example.sd40.repository.SanPham.LoaiGiayRepository;
import com.example.sd40.service.SanPham.LoaiGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LoaiGiayImpl implements LoaiGiayService {
    @Autowired
    LoaiGiayRepository loaiGiayRepository;
    @Override
    public List<TheLoai> findAll() {
        return loaiGiayRepository.findAll();
    }

    @Override
    public TheLoai detail(Long id) {
        return loaiGiayRepository.getById(id);
    }

    @Override
    public List<TheLoai> findByNameUpdate(String ten, Long id) {
        return loaiGiayRepository.findByNameUpdate(ten,id);
    }

    @Override
    public void update(TheLoai theLoai) {
        loaiGiayRepository.save(theLoai);
    }

    @Override
    public List<TheLoai> findByName(String Ten) {
        return loaiGiayRepository.findByName(Ten);
    }

    @Override
    public void add(TheLoai theLoai) {
        loaiGiayRepository.save(theLoai);
    }
}
