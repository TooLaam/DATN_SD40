package com.example.sd40.service.SanPham.Impl;

import com.example.sd40.entity.San_pham.KichCo;
import com.example.sd40.repository.SanPham.KichCoRepository;
import com.example.sd40.service.SanPham.KichCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KichCoImpl implements KichCoService {
    @Autowired
    KichCoRepository kichCoRepository;
    @Override
    public List<KichCo> findAll() {
        return kichCoRepository.findAll();
    }

    @Override
    public KichCo detail(Long id) {
        return kichCoRepository.getById(id);
    }

    @Override
    public List<KichCo> findByNameUpdate(String ten, Long id) {
        return kichCoRepository.findByNameUpdate(ten,id);
    }

    @Override
    public void update(KichCo theLoai) {
        kichCoRepository.save(theLoai);
    }

    @Override
    public List<KichCo> findByName(String Ten) {
        return kichCoRepository.findByName(Ten);
    }

    @Override
    public void add(KichCo theLoai) {
        kichCoRepository.save(theLoai);
    }

    @Override
    public List<KichCo> listGGNew() {
        return kichCoRepository.listGGNew();
    }
}
