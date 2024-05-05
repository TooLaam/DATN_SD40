package com.example.sd40.repository.SanPham;

import com.example.sd40.entity.San_pham.TheLoai;
import com.example.sd40.entity.San_pham.ThuongHieu;
import com.example.sd40.service.SanPham.Impl.LoaiGiayImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LoaiGiayRepository extends JpaRepository<TheLoai, Long> {

    @Query("select th from TheLoai th where th.ten =?1")
    List<TheLoai> findByName(String ten);
    @Query("select th from TheLoai th where th.ten =?1 and th.id not in (select t.id from TheLoai t where t.id = ?2)")
    List<TheLoai> findByNameUpdate(String ten,Long id);
    @Query("select th from TheLoai th where th.trangThai = 0")
    List<TheLoai> listTLConDung();

    @Query("select th from TheLoai th order by th.id desc ")
    List<TheLoai> listTLNewwww();

    @Query("select th from TheLoai  th where th.id not in (?1)")
    List<TheLoai> listTlDetail(Long id);
}
