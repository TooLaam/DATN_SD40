package com.example.sd40.service.GioHang;

import com.example.sd40.entity.Gio_hang.GioHang;
import com.example.sd40.entity.Gio_hang.GioHangDetail;
import com.example.sd40.repuest.GioHangDetailRequest;

import java.util.List;

/**
 * @author thangdt
 */
public interface GioHangDetailSerVice {

    List<GioHangDetail> gioHangDetails(Long idUser);

    GioHangDetail addSanPham(Long idUser, GioHangDetailRequest request);

    GioHangDetail updateSanPham(Long id, GioHangDetailRequest request);

    boolean delete(Long id);
}
