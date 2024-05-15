package com.example.sd40.service.HoaDon.impl;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.repository.HoaDon.*;
import com.example.sd40.service.HoaDon.HoaDonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @author quynh
 */
@Service
@Transactional
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private  HoaDonRepository hoaDonRepository;


    @Override
    public List<HoaDon> getHDByTrangThai(Integer trangThai) {
        return hoaDonRepository.getHDByTrangThai(trangThai);
    }

    @Override
    public List<HoaDon> getAll() {
        return hoaDonRepository.getAll();
    }

    @Override
    public void chuyenTrangThaiHoaDon(Integer trangThai, Long id, Date ngayCapNhat,Date ngayThanhToan) {
        hoaDonRepository.chuyenTrangThaiHoaDon(trangThai,id,ngayCapNhat,ngayThanhToan);
    }

    @Override
    public HoaDon detailHoaDon(Long id) {
        return hoaDonRepository.getById(id);
    }
}
