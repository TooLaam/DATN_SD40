package com.example.sd40.service.SanPham;

import com.example.sd40.entity.San_pham.SanPham;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface SanPhamService {
    List<Object[]> getAllTongSL();
    List<Object[]> getAllSPChuaSL();
    SanPham detail(Long id);
    void update(String ten, String moTa,Integer trangThai, String hinhAnh, Long thuongHieu, Long theLoai, Long id, Date ngayTao);
    SanPham findSPCoTonTaiKhong(String ten, Long thuongHien, Long theLoai);
    SanPham timSPCuoi();
    List<SanPham> findByName(String ten, Long idSP);
    void save(String ten,String moTa,String hinhAnh,Long thuongHieu,Long theLoai,Long giamGia);
    List<Object> findAllByOrderByNgayTaoDesc();
    List<Object> findAllByOrderSLDaBanDesc();
    List<Object[]> searchByTenOrThuongHieuOrTheLoai(@Param("keyword") String keyword);
    List<SanPham> getList();
    SanPham findTheoId(Long id);
    List<Object> getAllSPBySearch(@Param("keyword") String keyword);
}
