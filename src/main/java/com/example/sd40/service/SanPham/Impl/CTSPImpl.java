package com.example.sd40.service.SanPham.Impl;

import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.San_pham.KichCo;
import com.example.sd40.repository.SanPham.CTSPRepository;
import com.example.sd40.service.SanPham.CTSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ChiTietSanPham findCTSP(Long idCTSPHAMS, Long idkc) {
        return ctspRepository.findCTSP(idCTSPHAMS,idkc);
    }

    @Override
    public void updateCTSP(ChiTietSanPham chiTietSanPham) {
        ctspRepository.save(chiTietSanPham);
    }
}