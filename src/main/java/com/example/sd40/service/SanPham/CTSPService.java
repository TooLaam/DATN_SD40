package com.example.sd40.service.SanPham;

import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.San_pham.KichCo;
import java.math.BigDecimal;
import java.util.List;


public interface CTSPService {
    List<Object[]> findKCTheoSP(Long idsp, Long idms);
    List<KichCo> findKCNotInCTSP(Long idCTSPHAMMS);
    List<ChiTietSanPham> findCTSP2(Long idCTSPHAMS);
    List<ChiTietSanPham> findAll();
    ChiTietSanPham findById(Long id);
    ChiTietSanPham findCTSP(Long idCTSPHAMS,Long idkc);
    void updateCTSP(ChiTietSanPham chiTietSanPham);
    List<Object> getAllSPCus();
    Object tongSLSP();
    List<Object> getAllSPCusByTL(Long idTL);
    List<Object> getAllSPCusByTH(Long idTH);
    Object tongSLSPByTH(Long idTH);
    Object tongSLSPByTL(Long idTL);
    List<Object> getAllSPCusByGia(BigDecimal giaMin, BigDecimal giaMax);
    List<Object> getAllSPCusByGiamGia();
    Object detailSanPhamCus(Long idsp);
    List<Object[]> findKCTheoSPDangDung(Long idsp,Long idms);
    ChiTietSanPham detail(Long idctsp);
    void truSanPhamSauKhiMua(Integer soLuongMua,Long idctsp);



}
