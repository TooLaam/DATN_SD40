package com.example.sd40.service.NhanVien.impl;

import com.example.sd40.entity.NhanVien.ChucVu;
import com.example.sd40.entity.NhanVien.NhanVien;
import com.example.sd40.repository.NhanVien.NhanVienRepository;
import com.example.sd40.service.NhanVien.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author quynh
 */
@Service
public class NhanVienServiceImpl implements NhanVienService {


    @Autowired
    private NhanVienRepository nhanVienRepository;


    @Override
    public List<NhanVien> getAll() {
        return nhanVienRepository.findAll();
    }

    @Override
    public void save(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
    }

    @Override
    public NhanVien detail(Long id) {
        return nhanVienRepository.getById(id);
    }

    @Override
    public List<NhanVien> findByUserNameUpdate(String ten, Long id) {
        return nhanVienRepository.findByUserNameUpdate(ten,id);
    }

    @Override
    public List<NhanVien> findByUserName(String ten) {
        return nhanVienRepository.findByUserName(ten);
    }

    @Override
    public ChucVu detailChucVu(Long id) {
        return nhanVienRepository.detailChucVu(id);
    }

    @Override
    public Long IdNvCuoi() {
        return nhanVienRepository.IdNvCuoi();
    }

    @Override
    public NhanVien login(String taiKhoan, String matKhau) {
        return nhanVienRepository.login(taiKhoan,matKhau);
    }

    @Override
    public List<NhanVien> getAllDesc() {
        return nhanVienRepository.getAllDesc();
    }


}
