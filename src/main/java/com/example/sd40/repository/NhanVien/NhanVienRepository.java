package com.example.sd40.repository.NhanVien;

import com.example.sd40.entity.NhanVien.ChucVu;
import com.example.sd40.entity.NhanVien.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author quynh
 */
@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
    @Query("select th from NhanVien th where th.taiKhoan =?1")
    List<NhanVien> findByUserName(String ten);
    @Query("select th from NhanVien th where th.taiKhoan =?1 and th.id not in (?2)")
    List<NhanVien> findByUserNameUpdate(String ten,Long id);
    @Query("select cv from ChucVu cv where cv.Id = ?1")
    ChucVu detailChucVu(Long id);
    @Query(value = "select top(1) id from nhan_vien order by id desc",nativeQuery = true)
    Long IdNvCuoi();
    @Query("select nv from NhanVien nv where nv.taiKhoan = ?1 and nv.matKhau=?2")
    NhanVien login(String taiKhoan , String matKhau);
}
