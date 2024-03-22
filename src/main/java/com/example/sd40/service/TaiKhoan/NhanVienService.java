package com.example.sd40.service.TaiKhoan;

import com.example.sd40.entity.Tai_khoan.TaiKhoan;
import com.example.sd40.repuest.NhanVienRequyest;

import java.util.List;

/**
 * @author quynh
 */
public interface NhanVienService {

    List<TaiKhoan> findByNhanVien();

    TaiKhoan create(NhanVienRequyest requyest);

    TaiKhoan update(NhanVienRequyest requyest);

    
    boolean delete(Long id);

    TaiKhoan detail(Long id);
}
