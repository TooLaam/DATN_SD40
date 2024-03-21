package com.example.sd40.repository.HoaDon;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.LichSu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author quynh
 */
@Repository
public interface LichSuRepository extends JpaRepository<LichSu, Long> {

    List<LichSu> findAllByHoaDonOrderByNgayTaoDesc(HoaDon hoaDon);
}
