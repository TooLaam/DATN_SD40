//package com.example.sd40.service.NhanVien.impl;
//
//import com.example.sd40.entity.Tai_khoan.TaiKhoan;
//import com.example.sd40.entity.Tai_khoan.VaiTro;
//import com.example.sd40.repository.NhanVien.NhanVienRepository;
//import com.example.sd40.repuest.NhanVienRequyest;
//import com.example.sd40.service.NhanVien.NhanVienService;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
///**
// * @author quynh
// */
//@Service
//public class NhanVienServiceImpl implements NhanVienService {
//
//
//    @Autowired
//    private NhanVienRepository nhanVienRepository;
//
//
//    @Autowired
//    private VaiTroRepository vaiTroRepository;
//
//    @Override
//    public List<TaiKhoan> findByNhanVien() {
//        Optional<VaiTro> vaiTro = vaiTroRepository.findById(Long.parseLong("2"));
//        if(!vaiTro.isPresent()){
//            return new ArrayList<>();
//        }
//        return vaiTro.get().getTaiKhoanList();
//    }
//
//    @Override
//    public TaiKhoan create(NhanVienRequyest requyest) {
//        TaiKhoan taiKhoan = new TaiKhoan();
//        BeanUtils.copyProperties(requyest, taiKhoan);
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//            // Chuyển chuỗi thành đối tượng Date
//            Date date = dateFormat.parse(requyest.getNgaySinh());
//            taiKhoan.setNgaySinh( date);
//            taiKhoan.setTrangThai(0);
//            taiKhoan.setTenTaiKhoan(requyest.getEmail());
//            taiKhoan.setMatKhau("45345345");
//            taiKhoan.setVaiTro(vaiTroRepository.findById(Long.parseLong("2")).get());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return  nhanVienRepository.save(taiKhoan);
//    }
//
//    @Override
//    public TaiKhoan update(NhanVienRequyest requyest) {
//        TaiKhoan taiKhoan = new TaiKhoan();
//        BeanUtils.copyProperties(requyest, taiKhoan);
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date date = dateFormat.parse(requyest.getNgaySinh());
//            taiKhoan.setId(requyest.getIdNhanVien());
//            taiKhoan.setNgaySinh(date);
//            taiKhoan.setTenTaiKhoan(requyest.getEmail());
//            taiKhoan.setVaiTro(vaiTroRepository.findById(Long.parseLong("2")).get());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return  nhanVienRepository.save(taiKhoan);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//        Optional<TaiKhoan> taiKhoan = nhanVienRepository.findById(id);
//        if(taiKhoan.get().getTrangThai() == 0){
//            taiKhoan.get().setTrangThai(1);
//        }else{
//            taiKhoan.get().setTrangThai(0);
//        }
//        nhanVienRepository.save(taiKhoan.get());
//        return true;
//    }
//
//    @Override
//    public TaiKhoan detail(Long id) {
//        Optional<TaiKhoan> taiKhoan = nhanVienRepository.findById(id);
//        return taiKhoan.get();
//    }
//}
