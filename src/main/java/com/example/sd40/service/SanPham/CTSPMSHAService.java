package com.example.sd40.service.SanPham;

import com.example.sd40.entity.San_pham.ChiTietSanPhamMauSacHinhAnh;
import com.example.sd40.entity.San_pham.Mau_sac;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public interface CTSPMSHAService {
    List<Object[]> findMSTheoSP(Long id);
    List<ChiTietSanPhamMauSacHinhAnh> getAllHinhAnhbyIDSP(Long id);
    ChiTietSanPhamMauSacHinhAnh getHAbySPandMS(Long idsp,Long idms);
    List<Mau_sac> getMSNotInCTSPMSByIdsp(Long id );
    void add(ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh);
    void updateHA(String hinhAnh, Long idsp, Long idms, Date ngaySua, BigDecimal giaHienHanh,Integer trangThai);
    List<Object[]> findMSTheoSPDangDung (Long id);
    Object addHoaDon (Long id);


}
