package com.example.sd40.service.HoaDon.impl;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.LichSu;
import com.example.sd40.repository.HoaDon.HoaDonRepository;
import com.example.sd40.repository.HoaDon.LichSuRepository;
import com.example.sd40.service.HoaDon.LichSuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author quynh
 */
@Service
public class LichSuServiceImpl implements LichSuService {

    @Autowired
    private LichSuRepository lichSuRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;


    @Override
    public List<LichSu> findAllByHoaDon(long id) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        return lichSuRepository.findAllByHoaDonOrderByNgayTaoDesc(hoaDon);
    }
}
