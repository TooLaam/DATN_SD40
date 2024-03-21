package com.example.sd40.service.HoaDon.impl;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.repository.HoaDon.HoaDonChiTietRepository;
import com.example.sd40.service.HoaDon.HoaDonDetalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author quynh
 */
@Service
public class HoaDonDetalServiceImpl implements HoaDonDetalService {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Override
    public List<HoaDonChiTiet> findAllByHoaDon(HoaDon hoaDon) {
        return hoaDonChiTietRepository.findAllByHoaDon(hoaDon);
    }
}
