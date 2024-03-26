package com.example.sd40.service.HoaDon;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.repuest.HoaDonDetailRequest;

import java.util.List;

/**
 * @author quynh
 */
public interface HoaDonDetalService {

    List<HoaDonChiTiet> findAllByHoaDon(Long id);

    HoaDonChiTiet save(HoaDonDetailRequest request);

    HoaDonChiTiet delete(long id, String note);

    HoaDonChiTiet update(Long id, int quantity, String note);
}
