package com.example.sd40.repository.KhachHang;

import com.example.sd40.entity.KhachHang.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author quynh
 */
@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
    @Query("select th from KhachHang th where th.taiKhoan =?1")
    List<KhachHang> findByUserName(String ten);
    @Query("select th from KhachHang th where th.taiKhoan =?1 and th.id not in (?2)")
    List<KhachHang> findByUserNameUpdate(String ten,Long id);
    @Query("select th from KhachHang th where th.email =?1")
    List<KhachHang> findByEmal(String ten);
    @Query("select th from KhachHang th where th.email =?1 and th.id not in (?2)")
    List<KhachHang> findByEmailUpdate(String ten,Long id);
    @Query(value = "select top(1) id from khach_hang order by id desc",nativeQuery = true)
    Long IdKHCuoi();
    @Query("select th from KhachHang th order by th.id desc ")
    List<KhachHang> getAllDesc();
}
