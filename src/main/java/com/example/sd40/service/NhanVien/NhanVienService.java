package com.example.sd40.service.NhanVien;

import com.example.sd40.entity.NhanVien.NhanVien;
import com.example.sd40.repuest.NhanVienRequyest;

import java.util.List;

/**
 * @author quynh
 */
public interface NhanVienService {

    List<NhanVien> findByNhanVien();

    NhanVien create(NhanVienRequyest requyest);

    NhanVien update(NhanVienRequyest requyest);

    
    boolean delete(Long id);

    NhanVien detail(Long id);
}
