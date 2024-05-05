package com.example.sd40.repository.SanPham;

import com.example.sd40.entity.San_pham.GiamGIa;
import com.example.sd40.entity.San_pham.Mau_sac;
import com.example.sd40.entity.San_pham.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MauSacRepository extends JpaRepository<Mau_sac,Long> {
    @Query("select th from Mau_sac th where th.ten =?1")
    List<Mau_sac> findByName(String ten);
    @Query("select th from Mau_sac th where th.ten =?1 and th.Id not in (select t.Id from Mau_sac t where t.Id = ?2)")
    List<Mau_sac> findByNameUpdate(String ten,Long id);
    @Query("select th from Mau_sac th order by th.Id desc ")
    List<Mau_sac> listMSNew();
}
