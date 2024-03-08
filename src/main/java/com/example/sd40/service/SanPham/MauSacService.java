package com.example.sd40.service.SanPham;

import com.example.sd40.entity.San_pham.KichCo;
import com.example.sd40.entity.San_pham.Mau_sac;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MauSacService {
    List<Mau_sac> findAll();
    Mau_sac detail(Long id);
    List<Mau_sac> findByNameUpdate(String ten, Long id);
    void update(Mau_sac theLoai);
    List<Mau_sac> findByName(String Ten);
    void add(Mau_sac theLoai);
}
