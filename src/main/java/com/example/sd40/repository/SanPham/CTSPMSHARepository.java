package com.example.sd40.repository.SanPham;

import com.example.sd40.entity.San_pham.ChiTietSanPhamMauSacHinhAnh;
import com.example.sd40.entity.San_pham.Mau_sac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CTSPMSHARepository extends JpaRepository<ChiTietSanPhamMauSacHinhAnh,Long> {
    @Query("select ms.Id,ms.ten,ms.hinhAnh, ctmsha.giaHienHanh,ctmsha.sanPham.giamGIa.mucGiam,(ctmsha.giaHienHanh*(100-ctmsha.sanPham.giamGIa.mucGiam))/100,ctmsha.hinhAnh from SanPham sp join ChiTietSanPhamMauSacHinhAnh ctmsha on sp.id = ctmsha.sanPham.id" +
            " join Mau_sac ms on ctmsha.mau_sac.Id = ms.Id where sp.id =?1 and ctmsha.trangThai = 0")
    List<Object[]> findMSTheoSPDangDung (Long id);
    @Query("select ms.Id,ms.ten,ms.hinhAnh from SanPham sp join ChiTietSanPhamMauSacHinhAnh ctmsha on sp.id = ctmsha.sanPham.id" +
            " join Mau_sac ms on ctmsha.mau_sac.Id = ms.Id where sp.id =?1")
    List<Object[]> findMSTheoSP (Long id);

    @Query("select ha from SanPham sp join ChiTietSanPhamMauSacHinhAnh ha on sp.id = ha.sanPham.id where sp.id =?1 and ha.trangThai = 0")
    List<ChiTietSanPhamMauSacHinhAnh> getAllHinhAnhbyIDSP(Long id);

    @Query("select ct from ChiTietSanPhamMauSacHinhAnh ct where ct.sanPham.id =?1 and ct.mau_sac.Id=?2")
    ChiTietSanPhamMauSacHinhAnh getHAbySPandMS(Long idsp, Long idms);


    @Query("select ms from Mau_sac ms where ms.trangThai = 0 and ms.Id not in (select ct.mau_sac.Id from ChiTietSanPhamMauSacHinhAnh ct where ct.sanPham.id =?1)")
    List<Mau_sac> getMSNotInCTSPMSByIdsp(Long id );

    @Transactional
    @Modifying
    @Query("update ChiTietSanPhamMauSacHinhAnh set hinhAnh = ?1,ngaySua=?4,giaHienHanh=?5,trangThai=?6 where sanPham.id=?2 and mau_sac.Id =?3")
    void updateHA(String hinhAnh, Long idsp, Long idms, Date ngaySua, BigDecimal giaHienHanh,Integer trangThai);


    @Query("select ctmsha.giaHienHanh,(ctmsha.giaHienHanh*(100-ctmsha.sanPham.giamGIa.mucGiam))/100 from ChiTietSanPhamMauSacHinhAnh ctmsha where ctmsha.Id=?1")
    Object addHoaDon (Long id);
}
