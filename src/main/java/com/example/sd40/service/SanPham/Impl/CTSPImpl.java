package com.example.sd40.service.SanPham.Impl;

import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.San_pham.KichCo;
import com.example.sd40.repository.SanPham.CTSPRepository;
import com.example.sd40.service.SanPham.CTSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class CTSPImpl implements CTSPService {
    @Autowired
    CTSPRepository ctspRepository;
    @Override
    public List<Object[]> findKCTheoSP(Long idsp, Long idms) {
        return ctspRepository.findKCTheoSP(idsp,idms);
    }

    @Override
    public List<KichCo> findKCNotInCTSP(Long idCTSPHAMMS) {
        return ctspRepository.findKCNotInCTSP(idCTSPHAMMS);
    }

    @Override
    public List<ChiTietSanPham> findCTSP2(Long idCTSPHAMS) {
        return ctspRepository.findCTSP2(idCTSPHAMS);
    }

    @Override
    public List<ChiTietSanPham> findAll() {
        return ctspRepository.findAll();
    }

    @Override
    public ChiTietSanPham findById(Long id) {
        return ctspRepository.findById(id).get();
    }

    @Override
    public ChiTietSanPham findCTSP(Long idCTSPHAMS, Long idkc) {
        return ctspRepository.findCTSP(idCTSPHAMS,idkc);
    }

    @Override
    public void updateCTSP(ChiTietSanPham chiTietSanPham) {
        ctspRepository.save(chiTietSanPham);
    }

    @Override
    public List<Object> getAllSPCus() {
        return ctspRepository.getAllSPCus();
    }

    @Override
    public Object tongSLSP() {
        return ctspRepository.tongSLSP();
    }

    @Override
    public List<Object> getAllSPCusByTL(Long idTL) {
        return ctspRepository.getAllSPCusByTL(idTL);
    }

    @Override
    public List<Object> getAllSPCusByTH(Long idTH) {
        return ctspRepository.getAllSPCusByTH(idTH);
    }

    @Override
    public Object tongSLSPByTH(Long idTH) {
        return ctspRepository.tongSLSPByTH(idTH);
    }

    @Override
    public Object tongSLSPByTL(Long idTL) {
        return ctspRepository.tongSLSPByTL(idTL);
    }

    @Override
    public List<Object> getAllSPCusByGia(BigDecimal giaMin, BigDecimal giaMax) {
        return ctspRepository.getAllSPCusByGia(giaMin,giaMax);
    }

    @Override
    public List<Object> getAllSPCusByGiamGia() {
        return ctspRepository.getAllSPCusByGiamGia();
    }

    @Override
    public Object detailSanPhamCus(Long idsp) {
        return ctspRepository.detailSanPhamCus(idsp);
    }
}
