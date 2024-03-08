package com.example.sd40.repository.SanPham;

import com.example.sd40.entity.San_pham.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu,Long> {
    @Query("select th from ThuongHieu th where th.ten =?1")
    List<ThuongHieu> findByName(String ten);
    @Query("select th from ThuongHieu th where th.ten =?1 and th.id not in (select t from ThuongHieu t where t.id = ?2)")
    List<ThuongHieu> findByNameUpdate(String ten,Long id);

}
