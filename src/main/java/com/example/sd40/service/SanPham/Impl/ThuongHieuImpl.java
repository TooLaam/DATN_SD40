package com.example.sd40.service.SanPham.Impl;

import com.example.sd40.entity.San_pham.ThuongHieu;
import com.example.sd40.repository.SanPham.ThuongHieuRepository;
import com.example.sd40.service.SanPham.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ThuongHieuImpl implements ThuongHieuService {
    @Autowired
    ThuongHieuRepository thuongHieuRepository;

    @Override
    public List<ThuongHieu> getAll() {
        return thuongHieuRepository.findAll();
    }

    @Override
    public ThuongHieu detail(Long id) {
        return thuongHieuRepository.getById(id);
    }

    @Override
    public List<ThuongHieu> findByNameUpdate(String ten, Long id) {
        return thuongHieuRepository.findByNameUpdate(ten,id);
    }

    @Override
    public void update(ThuongHieu thuongHieu) {
        thuongHieuRepository.save(thuongHieu);
    }

    @Override
    public List<ThuongHieu> findByName(String ten) {
        return thuongHieuRepository.findByName(ten);
    }

    @Override
    public void add(ThuongHieu thuongHieu) {
        thuongHieuRepository.save(thuongHieu);
    }

    @Override
    public List<ThuongHieu> listTLConDung() {
        return thuongHieuRepository.listTLConDung();
    }

    @Override
    public List<Object> listTLHome() {
        return thuongHieuRepository.listTLHome();
    }
}
