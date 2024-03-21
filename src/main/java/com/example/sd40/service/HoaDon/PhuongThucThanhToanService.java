package com.example.sd40.service.HoaDon;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.PhuongThucThanhToan;

import java.util.List;

/**
 * @author quynh
 */
public interface PhuongThucThanhToanService {

    List<PhuongThucThanhToan> findAllByHoaDon(HoaDon hoaDon);
}
