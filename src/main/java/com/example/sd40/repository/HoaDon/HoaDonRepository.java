package com.example.sd40.repository.HoaDon;

import com.example.sd40.entity.Hoa_don.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author quynh
 */
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {
    @Query("select hd from HoaDon hd order by hd.id desc ")
    List<HoaDon> getAll();

    @Query("select hd from HoaDon hd where hd.trangThai=?1 order by hd.id desc")
    List<HoaDon> getHDByTrangThai(Integer trangThai);

    @Transactional
    @Modifying
    @Query("update HoaDon hd set hd.trangThai = ?1,hd.ngayCapNhat = ?3,hd.ngayThanhToan=?4 where hd.id = ?2")
    void chuyenTrangThaiHoaDon(Integer trangThai, Long id, Date ngayCapNhat,Date ngayThanhToan);
}
