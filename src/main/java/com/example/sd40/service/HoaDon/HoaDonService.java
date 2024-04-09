package com.example.sd40.service.HoaDon;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.repuest.HoaDonKhachHangResquest;

import java.util.List;

/**
 * @author quynh
 */
public interface HoaDonService {

    List<HoaDon> findAllByStatus(int status);
    void create();
    String changeStatus(long id, String note);
    String huyHoaDon(long id, String note);
    HoaDon findByid(long id);

    HoaDon createHoaDon();

}
