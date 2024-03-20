package com.example.sd40.service.GioHang.impl;

import com.example.sd40.entity.Gio_hang.GioHang;
import com.example.sd40.entity.Gio_hang.GioHangDetail;
import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.San_pham.ChiTietSanPhamMauSacHinhAnh;
import com.example.sd40.entity.Tai_khoan.TaiKhoan;
import com.example.sd40.repository.GioHang.GioHangDetailRepository;
import com.example.sd40.repository.GioHang.GioHangRepository;
import com.example.sd40.repository.SanPham.CTSPRepository;
import com.example.sd40.repository.TaiKhoan.KhachHangRepository;
import com.example.sd40.repuest.GioHangDetailRequest;
import com.example.sd40.service.GioHang.GioHangDetailSerVice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author quynh
 */
@Service
public class GioHangDetailSerViceImpl implements GioHangDetailSerVice {
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private CTSPRepository ctspRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private GioHangDetailRepository gioHangDetailRepository;


    @Override
    public List<GioHangDetail> gioHangDetails(Long idUser) {
        TaiKhoan taiKhoan = khachHangRepository.findById(idUser).get();
        GioHang gioHang = gioHangRepository.findByTkGioHang(taiKhoan).get();
        return gioHangDetailRepository.findAllByGioHang(gioHang);
    }

    @Override
    public GioHangDetail addSanPham(Long idUser, GioHangDetailRequest request) {
        TaiKhoan taiKhoan = khachHangRepository.findById(idUser).get();
        GioHang gioHang = gioHangRepository.findByTkGioHang(taiKhoan).get();
        ChiTietSanPham chiTietSanPham = ctspRepository.findById(request.getIdProduct()).get();
        GioHangDetail gioHangDetail = new GioHangDetail();
        gioHangDetail.setGioHang(gioHang);
        gioHangDetail.setChiTietSanPham(chiTietSanPham);
        gioHangDetail.setQuantity(request.getQuantity());
        gioHangDetail.setPrice(request.getPrice());
        return gioHangDetailRepository.save(gioHangDetail);
    }

    @Override
    public GioHangDetail updateSanPham(Long id, GioHangDetailRequest request) {
        GioHangDetail gioHangDetail = gioHangDetailRepository.findById(id).get();
        gioHangDetail.setQuantity(request.getQuantity());
        gioHangDetail.setPrice(request.getPrice());
        return gioHangDetailRepository.save(gioHangDetail);
    }

    @Override
    public boolean delete(Long id) {
        gioHangDetailRepository.deleteById(id);
        return true;
    }
}
