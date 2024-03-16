package com.example.sd40.service.SanPham.Impl;

import com.example.sd40.entity.San_pham.SanPham;
import com.example.sd40.repository.SanPham.SanPhamRepository;
import com.example.sd40.service.SanPham.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class SanPhamImpl implements SanPhamService {
    @Autowired
    SanPhamRepository sanPhamRepository;
    @Override
    public List<Object[]> getAllTongSL() {
        return sanPhamRepository.getAllTongSL();
    }

    @Override
    public List<Object[]> getAllSPChuaSL() {
        return sanPhamRepository.getAllSPChuaSL();
    }

    @Override
    public SanPham detail(Long id) {
        return sanPhamRepository.getOne(id);
    }

    @Override
    public void update(String ten, String moTa, Integer trangThai, String hinhAnh, Long thuongHieu, Long theLoai, Long id, Date ngayTao) {
        sanPhamRepository.update(ten,moTa,trangThai,hinhAnh,thuongHieu,theLoai,id,ngayTao);
    }

    @Override
    public SanPham findSPCoTonTaiKhong(String ten, Long thuongHien, Long theLoai) {
        return sanPhamRepository.findSPCoTonTaiKhong(ten,thuongHien,theLoai);
    }

    @Override
    public SanPham timSPCuoi() {

        return sanPhamRepository.findFirstByOrderByIdDesc();
    }

    @Override
    public List<SanPham> findByName(String ten, Long idSP) {
        return sanPhamRepository.findByName(ten,idSP);
    }

    @Override
    public void save(String ten, String moTa, String hinhAnh, Long thuongHieu, Long theLoai) {
        sanPhamRepository.save(ten,moTa,hinhAnh,thuongHieu,theLoai);
    }
}
