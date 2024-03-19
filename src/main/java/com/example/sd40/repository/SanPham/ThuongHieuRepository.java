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
    @Query("select th from ThuongHieu th where th.ten =?1 and th.id not in (select t.id from ThuongHieu t where t.id = ?2)")
    List<ThuongHieu> findByNameUpdate(String ten,Long id);
    @Query("select th from ThuongHieu th where th.trangThai =0")
    List<ThuongHieu> listTLConDung();

    @Query(value = "select top(4) thuong_hieu.id, thuong_hieu.ten, thuong_hieu.hinh_anh,SUM(chi_tiet_san_pham.so_luong) from thuong_hieu join san_pham on thuong_hieu.id = san_pham.thuong_hieu_id\n" +
            " join chi_tiet_san_pham_mau_sac on san_pham.id = chi_tiet_san_pham_mau_sac.san_pham_id\n" +
            "  join chi_tiet_san_pham on chi_tiet_san_pham_mau_sac.id = chi_tiet_san_pham.chi_tiet_san_pham_mau_sac_id\n" +
            "  where thuong_hieu.trang_thai=0\n" +
            " group by thuong_hieu.id, thuong_hieu.ten, thuong_hieu.hinh_anh\n" +
            " order by SUM(chi_tiet_san_pham.so_luong) desc ",nativeQuery = true)
    List<Object> listTLHome();

}
