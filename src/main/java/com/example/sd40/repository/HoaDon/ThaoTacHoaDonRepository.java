package com.example.sd40.repository.HoaDon;

import com.example.sd40.entity.Hoa_don.ThaoTacHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThaoTacHoaDonRepository extends JpaRepository<ThaoTacHoaDon,Long> {
    @Query("select tt from ThaoTacHoaDon tt where tt.hoaDon.id=?1")
    List<ThaoTacHoaDon> getAllByHoaDon(Long idHoaDon);
}
