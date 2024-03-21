package com.example.sd40.service.HoaDon;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.LichSu;

import java.util.List;

/**
 * @author quynh
 */
public interface LichSuService {

    List<LichSu> findAllByHoaDon(long id);
}
