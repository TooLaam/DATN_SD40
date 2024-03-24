package com.example.sd40.service.NhanVien.impl;

import com.example.sd40.entity.NhanVien.NhanVien;
import com.example.sd40.repository.NhanVien.NhanVienRepository;
import com.example.sd40.repuest.NhanVienRequyest;
import com.example.sd40.service.NhanVien.NhanVienService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author quynh
 */
@Service
public class NhanVienServiceImpl implements NhanVienService {


    @Autowired
    private NhanVienRepository nhanVienRepository;


    @Override
    public List<NhanVien> findByNhanVien() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien create(NhanVienRequyest requyest) {
        NhanVien taiKhoan = new NhanVien();
        BeanUtils.copyProperties(requyest, taiKhoan);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Chuyển chuỗi thành đối tượng Date
            Date date = dateFormat.parse(requyest.getNgaySinh());
            taiKhoan.setNgaySinh( date);
            taiKhoan.setTrangThai(0);
            taiKhoan.setTaiKhoan(requyest.getEmail());
            taiKhoan.setMatKhau("45345345");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  nhanVienRepository.save(taiKhoan);
    }

    @Override
    public NhanVien update(NhanVienRequyest requyest) {
        NhanVien taiKhoan = new NhanVien();
        BeanUtils.copyProperties(requyest, taiKhoan);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(requyest.getNgaySinh());
            taiKhoan.setId(requyest.getIdNhanVien());
            taiKhoan.setNgaySinh(date);
            taiKhoan.setTaiKhoan(requyest.getEmail());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  nhanVienRepository.save(taiKhoan);
    }

    @Override
    public boolean delete(Long id) {
        Optional<NhanVien> taiKhoan = nhanVienRepository.findById(id);
        if(taiKhoan.get().getTrangThai() == 0){
            taiKhoan.get().setTrangThai(1);
        }else{
            taiKhoan.get().setTrangThai(0);
        }
        nhanVienRepository.save(taiKhoan.get());
        return true;
    }

    @Override
    public NhanVien detail(Long id) {
        Optional<NhanVien> taiKhoan = nhanVienRepository.findById(id);
        return taiKhoan.get();
    }
}
