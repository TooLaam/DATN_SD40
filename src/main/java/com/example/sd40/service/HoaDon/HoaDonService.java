package com.example.sd40.service.HoaDon;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.repuest.HoaDonKhachHangResquest;

import java.util.List;

/**
 * @author quynh
 */
public interface HoaDonService {

    List<HoaDon> findAllByStatus(int status);
    String createHoaDonKhachHang(HoaDonKhachHangResquest resquest);

    HoaDon findByid(long id);
}
