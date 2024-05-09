package com.example.sd40.service.SanPham;

import com.example.sd40.entity.San_pham.GiamGIa;
import java.util.List;

public interface GiamGiaService {
    List<GiamGIa> getAll();
    void add(GiamGIa giamGIa);
    void update(GiamGIa giamGIa);
    GiamGIa detail(Long id);
    List<GiamGIa> findByNameUpdate(String ten,Long id);
    List<GiamGIa> findByName(String ten);
    List<GiamGIa> findByIDSP(Long idsp);
    void updateGG(Long giamGia,Long idsp);
    Object hienThiTienDaGiam(Long idsp,Long idms);
    List<GiamGIa> listGGConDung();
    List<GiamGIa> listGGNew();
    List<GiamGIa> listGGDetail(Long id);


}
