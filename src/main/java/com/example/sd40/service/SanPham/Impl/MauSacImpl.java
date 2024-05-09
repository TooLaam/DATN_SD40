package com.example.sd40.service.SanPham.Impl;

import com.example.sd40.entity.San_pham.Mau_sac;
import com.example.sd40.repository.SanPham.MauSacRepository;
import com.example.sd40.service.SanPham.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MauSacImpl implements MauSacService {
    @Autowired
    MauSacRepository mauSacRepository;
    @Override
    public List<Mau_sac> findAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public Mau_sac detail(Long id) {
        return mauSacRepository.getById(id);
    }

    @Override
    public List<Mau_sac> findByNameUpdate(String ten, Long id) {
        return mauSacRepository.findByNameUpdate(ten,id);
    }

    @Override
    public void update(Mau_sac theLoai) {
        mauSacRepository.save(theLoai);
    }

    @Override
    public List<Mau_sac> findByName(String Ten) {
        return mauSacRepository.findByName(Ten);
    }

    @Override
    public void add(Mau_sac theLoai) {
        mauSacRepository.save(theLoai);
    }

    @Override
    public List<Mau_sac> listMSNew() {
        return mauSacRepository.listMSNew();
    }
}
