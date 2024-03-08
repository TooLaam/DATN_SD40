package com.example.sd40.repository.SanPham;

import com.example.sd40.entity.San_pham.KichCo;
import com.example.sd40.entity.San_pham.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface KichCoRepository extends JpaRepository<KichCo,Long> {

    @Query("select th from KichCo th where th.ten =?1")
    List<KichCo> findByName(String ten);
    @Query("select th from KichCo th where th.ten =?1 and th.Id not in (select t from KichCo t where t.Id = ?2)")
    List<KichCo> findByNameUpdate(String ten,Long id);
}
