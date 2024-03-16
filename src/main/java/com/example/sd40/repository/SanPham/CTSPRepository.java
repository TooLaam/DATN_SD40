package com.example.sd40.repository.SanPham;

import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.San_pham.ChiTietSanPhamMauSacHinhAnh;
import com.example.sd40.entity.San_pham.KichCo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CTSPRepository extends JpaRepository<ChiTietSanPham,Long> {

    @Query("select kc.Id,kc.ten,ctsp.soLuong,ctsp.trangThai from ChiTietSanPhamMauSacHinhAnh ctsphams join ChiTietSanPham ctsp on ctsphams.Id = ctsp.chiTietSanPhamMauSacHinhAnh.Id" +
            " join KichCo kc on ctsp.kichCo.Id = kc.Id where ctsphams.sanPham.id = ?1 and ctsphams.mau_sac.Id=?2")
    List<Object[]> findKCTheoSP(Long idsp,Long idms);

    @Query("select kc from KichCo kc where kc.trangThai = 0 and kc.Id not in (select ct.kichCo.Id from ChiTietSanPham ct where ct.chiTietSanPhamMauSacHinhAnh.Id=?1)")
    List<KichCo> findKCNotInCTSP(Long idCTSPHAMMS);

    @Query("select ctsp from ChiTietSanPham  ctsp where ctsp.chiTietSanPhamMauSacHinhAnh.Id=?1")
    List<ChiTietSanPham> findCTSP2(Long idCTSPHAMS);

    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.chiTietSanPhamMauSacHinhAnh.Id=?1 and ctsp.kichCo.Id=?2")
    ChiTietSanPham findCTSP(Long idCTSPHAMS,Long idkc);

}
