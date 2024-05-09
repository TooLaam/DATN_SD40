package com.example.sd40.repository.SanPham;

import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.San_pham.ChiTietSanPhamMauSacHinhAnh;
import com.example.sd40.entity.San_pham.KichCo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CTSPRepository extends JpaRepository<ChiTietSanPham,Long> {

    @Query("select kc.Id,kc.ten,ctsp.soLuong,ctsp.trangThai from ChiTietSanPhamMauSacHinhAnh ctsphams join ChiTietSanPham ctsp on ctsphams.Id = ctsp.chiTietSanPhamMauSacHinhAnh.Id" +
            " join KichCo kc on ctsp.kichCo.Id = kc.Id where ctsphams.sanPham.id = ?1 and ctsphams.mau_sac.Id=?2")
    List<Object[]> findKCTheoSP(Long idsp,Long idms);

    @Query("select kc.Id,kc.ten,ctsp.soLuong,ctsp.trangThai,ctsphams.hinhAnh from ChiTietSanPhamMauSacHinhAnh ctsphams join ChiTietSanPham ctsp on ctsphams.Id = ctsp.chiTietSanPhamMauSacHinhAnh.Id" +
            " join KichCo kc on ctsp.kichCo.Id = kc.Id where ctsphams.sanPham.id = ?1 and ctsphams.mau_sac.Id=?2 and ctsp.trangThai = 0")
    List<Object[]> findKCTheoSPDangDung(Long idsp,Long idms);

    @Query("select kc from KichCo kc where kc.trangThai = 0 and kc.Id not in (select ct.kichCo.Id from ChiTietSanPham ct where ct.chiTietSanPhamMauSacHinhAnh.Id=?1)")
    List<KichCo> findKCNotInCTSP(Long idCTSPHAMMS);

    @Query("select ctsp from ChiTietSanPham  ctsp where ctsp.chiTietSanPhamMauSacHinhAnh.Id=?1")
    List<ChiTietSanPham> findCTSP2(Long idCTSPHAMS);

    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.chiTietSanPhamMauSacHinhAnh.Id=?1 and ctsp.kichCo.Id=?2")
    ChiTietSanPham findCTSP(Long idCTSPHAMS,Long idkc);

    @Query("select sp.id,sp.ten,sp.hinhAnhDaiDien,sp.soLuongDaBan,sp.giamGIa.mucGiam,sum(ctsp.soLuong)," +
            " max((ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100),Min((ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100)," +
            " Max(ctsphams.giaHienHanh),Min(ctsphams.giaHienHanh)" +
            " from ChiTietSanPham ctsp join ChiTietSanPhamMauSacHinhAnh ctsphams on ctsp.chiTietSanPhamMauSacHinhAnh.Id = ctsphams.Id" +
            " join SanPham sp on ctsphams.sanPham.id = sp.id where ctsp.soLuong >0" +
            " group by sp.id,sp.ten,sp.hinhAnhDaiDien,sp.soLuongDaBan,sp.giamGIa.mucGiam")
    List<Object> getAllSPCus();

    @Query("select sum(ct.soLuong) from ChiTietSanPham ct ")
    Object tongSLSP();

    @Query("select sum(ct.soLuong) from ChiTietSanPham ct where ct.chiTietSanPhamMauSacHinhAnh.sanPham.theLoai.id=?1")
    Object tongSLSPByTL(Long idTL);

    @Query("select sum(ct.soLuong) from ChiTietSanPham ct where ct.chiTietSanPhamMauSacHinhAnh.sanPham.thuongHieu.id=?1")
    Object tongSLSPByTH(Long idTH);

    @Query("select sp.id,sp.ten,sp.hinhAnhDaiDien,sp.soLuongDaBan,sp.giamGIa.mucGiam,sum(ctsp.soLuong)," +
            " max((ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100),Min((ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100)," +
            " Max(ctsphams.giaHienHanh),Min(ctsphams.giaHienHanh)" +
            " from ChiTietSanPham ctsp join ChiTietSanPhamMauSacHinhAnh ctsphams on ctsp.chiTietSanPhamMauSacHinhAnh.Id = ctsphams.Id" +
            " join SanPham sp on ctsphams.sanPham.id = sp.id where ctsp.soLuong >0 and sp.thuongHieu.id = ?1" +
            " group by sp.id,sp.ten,sp.hinhAnhDaiDien,sp.soLuongDaBan,sp.giamGIa.mucGiam,sp.thuongHieu.id")
    List<Object> getAllSPCusByTH(Long idTH);

    @Query("select sp.id,sp.ten,sp.hinhAnhDaiDien,sp.soLuongDaBan,sp.giamGIa.mucGiam,sum(ctsp.soLuong)," +
            " max((ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100),Min((ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100)," +
            " Max(ctsphams.giaHienHanh),Min(ctsphams.giaHienHanh)" +
            " from ChiTietSanPham ctsp join ChiTietSanPhamMauSacHinhAnh ctsphams on ctsp.chiTietSanPhamMauSacHinhAnh.Id = ctsphams.Id" +
            " join SanPham sp on ctsphams.sanPham.id = sp.id where ctsp.soLuong >0 and sp.theLoai.id = ?1" +
            " group by sp.id,sp.ten,sp.hinhAnhDaiDien,sp.soLuongDaBan,sp.giamGIa.mucGiam,sp.theLoai.id")
    List<Object> getAllSPCusByTL(Long idTL);

    @Query("select sp.id,sp.ten,sp.hinhAnhDaiDien,sp.soLuongDaBan,sp.giamGIa.mucGiam,sum(ctsp.soLuong)," +
            " max((ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100),Min((ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100)," +
            " Max(ctsphams.giaHienHanh),Min(ctsphams.giaHienHanh)" +
            " from ChiTietSanPham ctsp join ChiTietSanPhamMauSacHinhAnh ctsphams on ctsp.chiTietSanPhamMauSacHinhAnh.Id = ctsphams.Id" +
            " join SanPham sp on ctsphams.sanPham.id = sp.id where ctsp.soLuong >0  and " +
            " (ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100 >=?1 and (ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100 <?2 " +
            " group by sp.id,sp.ten,sp.hinhAnhDaiDien,sp.soLuongDaBan,sp.giamGIa.mucGiam,sp.theLoai.id")
    List<Object> getAllSPCusByGia(BigDecimal giaMin, BigDecimal giaMax);


    @Query("select sp.id,sp.ten,sp.hinhAnhDaiDien,sp.soLuongDaBan,sp.giamGIa.mucGiam,sum(ctsp.soLuong)," +
            " max((ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100),Min((ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100)," +
            " Max(ctsphams.giaHienHanh),Min(ctsphams.giaHienHanh)" +
            " from ChiTietSanPham ctsp join ChiTietSanPhamMauSacHinhAnh ctsphams on ctsp.chiTietSanPhamMauSacHinhAnh.Id = ctsphams.Id" +
            " join SanPham sp on ctsphams.sanPham.id = sp.id where ctsp.soLuong >0  and sp.giamGIa.mucGiam >0 " +
            " group by sp.id,sp.ten,sp.hinhAnhDaiDien,sp.soLuongDaBan,sp.giamGIa.mucGiam,sp.theLoai.id")
    List<Object> getAllSPCusByGiamGia();

    @Query("select sp.id,sp.ten,sp.hinhAnhDaiDien,sp.soLuongDaBan,sp.giamGIa.mucGiam,sum(ctsp.soLuong)," +
            " max((ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100),Min((ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100)," +
            " Max(ctsphams.giaHienHanh),Min(ctsphams.giaHienHanh)" +
            " from ChiTietSanPham ctsp join ChiTietSanPhamMauSacHinhAnh ctsphams on ctsp.chiTietSanPhamMauSacHinhAnh.Id = ctsphams.Id" +
            " join SanPham sp on ctsphams.sanPham.id = sp.id where sp.id = ?1" +
            " group by sp.id,sp.ten,sp.hinhAnhDaiDien,sp.soLuongDaBan,sp.giamGIa.mucGiam,sp.theLoai.id")
    Object detailSanPhamCus(Long idsp);

    @Transactional
    @Modifying
    @Query("update ChiTietSanPham ct set ct.soLuong = ct.soLuong-?1 where ct.Id = ?2")
    void truSanPhamSauKhiMua(Integer soLuongMua,Long idctsp);

}
