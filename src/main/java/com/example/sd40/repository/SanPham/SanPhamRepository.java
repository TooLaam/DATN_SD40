package com.example.sd40.repository.SanPham;

import com.example.sd40.entity.San_pham.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
@Repository
public interface SanPhamRepository extends JpaRepository<SanPham,Long> {
    @Query("select sp.id, sp.ma,sp.ten,sp.hinhAnhDaiDien,sum(ctsp.soLuong) from SanPham sp join ChiTietSanPhamMauSacHinhAnh ctsphams on sp.id=ctsphams.sanPham.id" +
            " join ChiTietSanPham ctsp on ctsphams.Id = ctsp.chiTietSanPhamMauSacHinhAnh.Id group by sp.id, sp.ma,sp.ten,sp.hinhAnhDaiDien")
    List<Object[]> getAllTongSL();
    @Query("select sp.id, sp.ma,sp.ten,sp.hinhAnhDaiDien from SanPham sp where sp.id not in (select ctsphams.sanPham.id from ChiTietSanPhamMauSacHinhAnh  ctsphams)")
    List<Object[]> getAllSPChuaSL();

    @Transactional
    @Modifying
    @Query("update SanPham set ten=?1,moTa=?2,trangThai=?3,hinhAnhDaiDien=?4,thuongHieu.id=?5,theLoai.id=?6,ngaySua=?8 where id=?7")
    void update(String ten, String moTa,Integer trangThai, String hinhAnh, Long thuongHieu, Long theLoai, Long id, Date ngayTao);

    @Query("select sp from SanPham sp where sp.ten=?1 and sp.thuongHieu.id=?2 and sp.theLoai.id = ?3")
    SanPham findSPCoTonTaiKhong(String ten, Long thuongHien, Long theLoai);

    @Query("select sp from SanPham sp where sp.ten=?1 and sp.id not in (?2)")
    List<SanPham> findByName(String ten, Long idSP);

    SanPham findLastByOrderById();

//    @Query("select sp from SanPham sp order by sp.id desc ")
//    SanPham finSPCuoi();

    SanPham findFirstByOrderByIdDesc();

    @Transactional
    @Modifying
    @Query(value = "insert into san_pham(ten,mo_ta,ngay_tao,ngay_sua,hinh_anh_dai_dien,trang_thai,thuong_hieu_id,the_loai_id) values(?1,?2,getdate(),getdate(),?3,0,?4,?5)",nativeQuery = true)
    void save(String ten,String moTa,String hinhAnh,Long thuongHieu,Long theLoai);

}
