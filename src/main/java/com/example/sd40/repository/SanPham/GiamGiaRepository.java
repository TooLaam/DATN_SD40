package com.example.sd40.repository.SanPham;

import com.example.sd40.entity.San_pham.GiamGIa;
import com.example.sd40.entity.San_pham.KichCo;
import com.example.sd40.entity.San_pham.TheLoai;
import com.example.sd40.entity.San_pham.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface GiamGiaRepository extends JpaRepository<GiamGIa, Long> {
    @Query("select th from GiamGIa th where th.ten =?1")
    List<GiamGIa> findByName(String ten);

    @Query("select th from GiamGIa th where th.trangThai=0 ORDER BY CASE WHEN th.ten = 'Không' THEN 0 ELSE 1 END, th.Id")
    List<GiamGIa> listGGConDung();

    @Query("select th from GiamGIa th where th.ten =?1 and th.Id not in (select t.Id from GiamGIa t where t.Id = ?2)")
    List<GiamGIa> findByNameUpdate(String ten,Long id);

    @Query("select th from GiamGIa th where th.Id not in (select sp.giamGIa.Id from SanPham sp where sp.id = ?1) order by th.mucGiam asc ")
    List<GiamGIa> findByIDSP(Long idsp);

    @Transactional
    @Modifying
    @Query("update SanPham set giamGIa.Id = ?1 where id=?2 ")
    void updateGG(Long giamGia,Long idsp);

    @Query("select ct.mau_sac.Id,ct.giaHienHanh,(ct.giaHienHanh*(100-gg.mucGiam))/100,ct.hinhAnh from SanPham sp join GiamGIa gg on sp.giamGIa.Id= gg.Id join ChiTietSanPhamMauSacHinhAnh ct on sp.id=ct.sanPham.id where sp.id=?1 and ct.mau_sac.Id=?2")
    Object hienThiTienDaGiam(Long idsp,Long idms);

    @Query("select th from GiamGIa th order by th.Id desc ")
    List<GiamGIa> listGGNew();

    @Query("select th from GiamGIa  th where th.Id not in (?1)")
    List<GiamGIa> listGGDetail(Long id);

    @Query("select th from GiamGIa th where th.Id not in (select t.Id from GiamGIa t where t.mucGiam = 0) order by th.mucGiam asc ")
    List<GiamGIa> getAllAscNotInMucGiam0();

    @Query("select th from GiamGIa th order by th.mucGiam asc ")
    List<GiamGIa> getAllAsc();
}
