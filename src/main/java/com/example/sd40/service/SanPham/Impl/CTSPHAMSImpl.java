package com.example.sd40.service.SanPham.Impl;

import com.example.sd40.entity.San_pham.ChiTietSanPhamMauSacHinhAnh;
import com.example.sd40.entity.San_pham.Mau_sac;
import com.example.sd40.repository.SanPham.CTSPMSHARepository;
import com.example.sd40.service.SanPham.CTSPMSHAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CTSPHAMSImpl implements CTSPMSHAService {
    @Autowired
    CTSPMSHARepository ctspmshaRepository;
    @Override
    public List<Object[]> findMSTheoSP(Long id) {
        return ctspmshaRepository.findMSTheoSP(id);
    }

    @Override
    public List<ChiTietSanPhamMauSacHinhAnh> getAllHinhAnhbyIDSP(Long id) {
        return ctspmshaRepository.getAllHinhAnhbyIDSP(id);
    }

    @Override
    public ChiTietSanPhamMauSacHinhAnh getHAbySPandMS(Long idsp, Long idms) {
        return ctspmshaRepository.getHAbySPandMS(idsp,idms);
    }

    @Override
    public List<Mau_sac> getMSNotInCTSPMSByIdsp(Long id) {
        return ctspmshaRepository.getMSNotInCTSPMSByIdsp(id);
    }

    @Override
    public void add(ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh) {
        ctspmshaRepository.save(chiTietSanPhamMauSacHinhAnh);
    }

    @Override
    public void updateHA(String hinhAnh, Long idsp, Long idms, Date ngaySua, BigDecimal giaHienHanh,Integer trangThai) {
        ctspmshaRepository.updateHA(hinhAnh,idsp,idms,ngaySua,giaHienHanh,trangThai);
    }

    @Override
    public List<Object[]> findMSTheoSPDangDung(Long id) {
        return ctspmshaRepository.findMSTheoSPDangDung(id);
    }

    @Override
    public Object addHoaDon(Long id) {
        return ctspmshaRepository.addHoaDon(id);
    }

}
