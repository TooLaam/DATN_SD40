package com.example.sd40.service.KhachHang.impl;


import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.repository.KhachHang.KhachHangRepository;
import com.example.sd40.repuest.KhachHangRequest;
import com.example.sd40.service.KhachHang.KhachHangService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author quynh
 */
@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;


    @Override
    public List<KhachHang> findByKhachHang() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang create( KhachHangRequest requyest) {
        KhachHang taiKhoan = new KhachHang();
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

        return  khachHangRepository.save(taiKhoan);
    }

    @Override
    public KhachHang update(KhachHangRequest requyest) {
        KhachHang taiKhoan = new KhachHang();
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
        return  khachHangRepository.save(taiKhoan);
    }


    @Override
    public KhachHang detail(Long id) {
        Optional<KhachHang> taiKhoan = khachHangRepository.findById(id);
        return taiKhoan.get();
    }
}
