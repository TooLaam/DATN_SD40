package com.example.sd40.service.GioHang;

import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.repuest.GioHangDetailRequest;

import java.util.List;

/**
 * @author quynh
 */
public interface GioHangDetailSerVice {

    List<GioHangChiTiet> gioHangDetails(Long idUser);

    GioHangChiTiet addSanPham(Long idUser, GioHangDetailRequest request);

    GioHangChiTiet updateSanPham(Long id, int quantity);

    boolean delete(Long id);
}
