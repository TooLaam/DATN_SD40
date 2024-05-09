package com.example.sd40.service.HoaDon;

import com.example.sd40.entity.Hoa_don.HoaDon;
import java.util.Date;
import java.util.List;

/**
 * @author quynh
 */
public interface HoaDonService {

    List<HoaDon> getHDByTrangThai(Integer trangThai);
    List<HoaDon> getAll();
    void chuyenTrangThaiHoaDon(Integer trangThai, Long id, Date ngayCapNhat, Date ngayThanhToan);
    HoaDon detailHoaDon(Long id);

}
