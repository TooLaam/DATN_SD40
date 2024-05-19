package com.example.sd40.service.SanPham.Impl;

import com.example.sd40.entity.San_pham.GiamGIa;
import com.example.sd40.repository.SanPham.GiamGiaRepository;
import com.example.sd40.service.SanPham.GiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GiamGiaImpl implements GiamGiaService {
    @Autowired
    GiamGiaRepository giamGiaRepository;
    @Override
    public List<GiamGIa> getAll() {
        return giamGiaRepository.findAll();
    }

    @Override
    public void add(GiamGIa giamGIa) {
        giamGiaRepository.save(giamGIa);
    }

    @Override
    public void update(GiamGIa giamGIa) {
        giamGiaRepository.save(giamGIa);
    }

    @Override
    public GiamGIa detail(Long id) {
        return giamGiaRepository.getOne(id);
    }

    @Override
    public List<GiamGIa> findByNameUpdate(String ten, Long id) {
        return giamGiaRepository.findByNameUpdate(ten,id);
    }

    @Override
    public List<GiamGIa> findByName(String ten) {
        return giamGiaRepository.findByName(ten);
    }

    @Override
    public List<GiamGIa> findByIDSP(Long idsp) {
        return giamGiaRepository.findByIDSP(idsp);
    }

    @Override
    public void updateGG(Long giamGia, Long idsp) {
        giamGiaRepository.updateGG(giamGia,idsp);
    }

    @Override
    public Object hienThiTienDaGiam(Long idsp,Long idms) {
        return giamGiaRepository.hienThiTienDaGiam(idsp,idms);
    }

    @Override
    public List<GiamGIa> listGGConDung() {
        return giamGiaRepository.listGGConDung();
    }

    @Override
    public List<GiamGIa> listGGNew() {
        return giamGiaRepository.listGGNew();
    }

    @Override
    public List<GiamGIa> listGGDetail(Long id) {
        return giamGiaRepository.listGGDetail(id);
    }

    @Override
    public List<GiamGIa> getAllAscNotInMucGiam0() {
        return giamGiaRepository.getAllAscNotInMucGiam0();
    }

    @Override
    public List<GiamGIa> getAllAsc() {
        return giamGiaRepository.getAllAsc();
    }
}
