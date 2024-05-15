package com.example.sd40.service.HoaDon.impl;

import com.example.sd40.entity.Hoa_don.ThaoTacHoaDon;
import com.example.sd40.repository.HoaDon.ThaoTacHoaDonRepository;
import com.example.sd40.service.HoaDon.ThaoTacHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ThaoTacHoaDonImpl implements ThaoTacHoaDonService {
    @Autowired
    ThaoTacHoaDonRepository thaoTacHoaDonRepository;

    @Override
    public void save(ThaoTacHoaDon thaoTacHoaDon) {
        thaoTacHoaDonRepository.save(thaoTacHoaDon);
    }

    @Override
    public List<ThaoTacHoaDon> getAllByHoaDon(Long idHoaDon) {
        return thaoTacHoaDonRepository.getAllByHoaDon(idHoaDon);
    }
}
