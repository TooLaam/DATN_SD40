package com.example.sd40.service.HoaDon;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;

import java.util.List;

/**
 * @author quynh
 */
public interface HoaDonDetalService {

    List<HoaDonChiTiet> findAllByHoaDon(HoaDon hoaDon);
}
