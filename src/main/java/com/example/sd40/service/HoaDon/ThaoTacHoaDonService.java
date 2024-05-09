package com.example.sd40.service.HoaDon;

import com.example.sd40.entity.Hoa_don.ThaoTacHoaDon;
import java.util.List;

public interface ThaoTacHoaDonService {
    void save(ThaoTacHoaDon thaoTacHoaDon);
    List<ThaoTacHoaDon> getAllByHoaDon(Long idHoaDon);
}
