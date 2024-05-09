package com.example.sd40.service.NhanVien;

import com.example.sd40.entity.NhanVien.ChucVu;
import com.example.sd40.entity.NhanVien.NhanVien;
import java.util.List;

/**
 * @author quynh
 */
public interface NhanVienService {
    List<NhanVien> getAll();
    void save(NhanVien nhanVien);
    NhanVien detail(Long id);
    List<NhanVien> findByUserNameUpdate(String ten,Long id);
    List<NhanVien> findByUserName(String ten);
    ChucVu detailChucVu(Long id);
    Long IdNvCuoi();
    NhanVien login(String taiKhoan , String matKhau);
}
