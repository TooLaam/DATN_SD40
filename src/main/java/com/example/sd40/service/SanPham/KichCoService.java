package com.example.sd40.service.SanPham;

import com.example.sd40.entity.San_pham.KichCo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface KichCoService {
    List<KichCo> findAll();
    KichCo detail(Long id);
    List<KichCo> findByNameUpdate(String ten, Long id);
    void update(KichCo theLoai);
    List<KichCo> findByName(String Ten);
    void add(KichCo theLoai);
    List<KichCo> listGGNew();

}
