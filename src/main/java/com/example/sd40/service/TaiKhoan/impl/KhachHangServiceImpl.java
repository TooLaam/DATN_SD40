package com.example.sd40.service.TaiKhoan.impl;

import com.example.sd40.entity.Tai_khoan.TaiKhoan;
import com.example.sd40.entity.Tai_khoan.VaiTro;
import com.example.sd40.repository.TaiKhoan.KhachHangRepository;
import com.example.sd40.repository.TaiKhoan.VaiTroRepository;
import com.example.sd40.repuest.KhachHangRequest;
import com.example.sd40.service.TaiKhoan.KhachHangService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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


    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Override
    public List<TaiKhoan> findByKhachHang() {
        Optional<VaiTro> vaiTro = vaiTroRepository.findById(Long.parseLong("1"));
        if(!vaiTro.isPresent()){
            return new ArrayList<>();
        }
        return vaiTro.get().getTaiKhoanList();
    }

    @Override
    public TaiKhoan create( KhachHangRequest requyest) {
        TaiKhoan taiKhoan = new TaiKhoan();
        BeanUtils.copyProperties(requyest, taiKhoan);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Chuyển chuỗi thành đối tượng Date
            Date date = dateFormat.parse(requyest.getNgaySinh());
            taiKhoan.setNgaySinh( date);
            taiKhoan.setTrangThai(0);
            taiKhoan.setTenTaiKhoan(requyest.getEmail());
            taiKhoan.setMatKhau("45345345");
            taiKhoan.setVaiTro(vaiTroRepository.findById(Long.parseLong("1")).get());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  khachHangRepository.save(taiKhoan);
    }

    @Override
    public TaiKhoan update(KhachHangRequest requyest) {
        TaiKhoan taiKhoan = new TaiKhoan();
        BeanUtils.copyProperties(requyest, taiKhoan);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(requyest.getNgaySinh());
            taiKhoan.setId(requyest.getIdNhanVien());
            taiKhoan.setNgaySinh(date);
            taiKhoan.setTenTaiKhoan(requyest.getEmail());
            taiKhoan.setVaiTro(vaiTroRepository.findById(Long.parseLong("1")).get());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  khachHangRepository.save(taiKhoan);
    }


    @Override
    public TaiKhoan detail(Long id) {
        Optional<TaiKhoan> taiKhoan = khachHangRepository.findById(id);
        return taiKhoan.get();
    }
}
