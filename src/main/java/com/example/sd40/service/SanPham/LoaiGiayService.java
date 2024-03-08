package com.example.sd40.service.SanPham;

import com.example.sd40.controller.SanPhamController.TheLoaiController;
import com.example.sd40.entity.San_pham.TheLoai;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoaiGiayService {
    List<TheLoai> findAll();
    TheLoai detail(Long id);
    List<TheLoai> findByNameUpdate(String ten,Long id);
    void update(TheLoai theLoai);
    List<TheLoai> findByName(String Ten);
    void add(TheLoai theLoai);
}
