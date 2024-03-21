package com.example.sd40.repository.HoaDon;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.LichSu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author quynh
 */
public interface LichSuRepositoryRepository extends JpaRepository<LichSu, Long> {

    List<LichSu> findAllByHoaDon(HoaDon hoaDon);
}
