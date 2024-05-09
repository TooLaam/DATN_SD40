package com.example.sd40.repository.SanPham;

import com.example.sd40.entity.San_pham.KichCo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KichCoRepository extends JpaRepository<KichCo,Long> {

    @Query("select th from KichCo th where th.ten =?1")
    List<KichCo> findByName(String ten);
    @Query("select th from KichCo th where th.ten =?1 and th.Id not in (select t.Id from KichCo t where t.Id = ?2)")
    List<KichCo> findByNameUpdate(String ten,Long id);
    @Query("select th from KichCo th order by th.Id desc ")
    List<KichCo> listGGNew();
}
