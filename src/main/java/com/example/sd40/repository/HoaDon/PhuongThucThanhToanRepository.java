package com.example.sd40.repository.HoaDon;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.PhuongThucThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author quynh
 */
public interface PhuongThucThanhToanRepository extends JpaRepository<PhuongThucThanhToan, Long> {
}
