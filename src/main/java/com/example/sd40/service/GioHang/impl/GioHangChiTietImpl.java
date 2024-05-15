package com.example.sd40.service.GioHang.impl;

import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.repository.GioHang.GioHangChiTietRepository;
import com.example.sd40.service.GioHang.GioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GioHangChiTietImpl implements GioHangChiTietService {
    @Autowired
    GioHangChiTietRepository gioHangChiTietRepository;
    @Override
    public void add(GioHangChiTiet gioHangChiTiet) {
        gioHangChiTietRepository.save(gioHangChiTiet);
    }

    @Override
    public List<GioHangChiTiet> hienthigiohangchitiet(Long idGH) {
        return gioHangChiTietRepository.hienthigiohangchitiet(idGH);
    }

    @Override
    public GioHangChiTiet detail(Long id) {
        return gioHangChiTietRepository.getById(id);
    }

    @Override
    public void updateSoLuong(Integer soLuong, Long idGHCT) {
        gioHangChiTietRepository.updateSoLuong(soLuong,idGHCT);
    }

    @Override
    public void deleteCTGH(Long id) {
        gioHangChiTietRepository.deleteById(id);
    }

    @Override
    public void updateSoLuongKhiThem(Integer soLuong, Long idsp) {
        gioHangChiTietRepository.updateSoLuongKhiThem(soLuong,idsp);
    }

    @Override
    public List<GioHangChiTiet> kiemTraAddGHCT(Long idctsp,Long idKH) {
        return gioHangChiTietRepository.kiemTraAddGHCT(idctsp,idKH);
    }

    @Override
    public GioHangChiTiet kiemTraSoLuongTruocKhiUpdate(Long idGHCT) {
        return gioHangChiTietRepository.kiemTraSoLuongTruocKhiUpdate(idGHCT);
    }

    @Override
    public List<GioHangChiTiet> findAllById(List<Long> ids) {
        return gioHangChiTietRepository.findAllById(ids);
    }

    @Override
    public void deleteGHCTByCTSP(Long idGHCT) {
        gioHangChiTietRepository.deleteGHCTByCTSP(idGHCT);
    }

    @Override
    public void deleteGHCTByCTSPAndIDKH(Long idGH, Long idCTSP) {
        gioHangChiTietRepository.deleteGHCTByCTSPAndIDKH(idGH,idCTSP);
    }

}
