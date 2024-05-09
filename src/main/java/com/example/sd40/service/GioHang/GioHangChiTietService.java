package com.example.sd40.service.GioHang;

import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface GioHangChiTietService {
    void add(GioHangChiTiet gioHangChiTiet);
    List<GioHangChiTiet> hienthigiohangchitiet(Long idGH);
    GioHangChiTiet detail(Long id);
    void updateSoLuong(Integer soLuong,Long idGHCT);
    void deleteCTGH(Long id);
    void updateSoLuongKhiThem(Integer soLuong,Long idsp);
    List<GioHangChiTiet> kiemTraAddGHCT(Long idctsp,Long idKH);
    GioHangChiTiet kiemTraSoLuongTruocKhiUpdate(Long idGHCT);
    List<GioHangChiTiet> findAllById(@Param("ids") List<Long> ids);
    void deleteGHCTByCTSP(Long idGHCT);
    void deleteGHCTByCTSPAndIDKH(Long idGH, Long idCTSP);

}
