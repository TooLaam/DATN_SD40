package com.example.sd40.service.SanPham;

import com.example.sd40.entity.San_pham.ThuongHieu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ThuongHieuService {
    List<ThuongHieu> getAll();
    ThuongHieu detail(Long id);
    List<ThuongHieu> findByNameUpdate(String ten,Long id);
    void update(ThuongHieu thuongHieu);
    List<ThuongHieu> findByName(String ten);
    void add(ThuongHieu thuongHieu);
    List<ThuongHieu> listTLConDung();
    List<Object> listTLHome();
    List<ThuongHieu> listTHNew();
    List<ThuongHieu> listTHDetail(Long id);


}
