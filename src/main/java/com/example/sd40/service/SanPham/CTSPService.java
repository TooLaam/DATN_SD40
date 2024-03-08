package com.example.sd40.service.SanPham;

import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.San_pham.KichCo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CTSPService {
    List<Object[]> findKCTheoSP(Long idsp, Long idms);
    List<KichCo> findKCNotInCTSP(Long idCTSPHAMMS);
    List<ChiTietSanPham> findCTSP2(Long idCTSPHAMS);
    ChiTietSanPham findCTSP(Long idCTSPHAMS,Long idkc);
    void updateCTSP(ChiTietSanPham chiTietSanPham);



}
