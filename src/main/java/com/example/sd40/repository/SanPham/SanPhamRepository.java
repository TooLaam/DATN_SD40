package com.example.sd40.repository.SanPham;

import com.example.sd40.entity.San_pham.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
@Repository
public interface SanPhamRepository extends JpaRepository<SanPham,Long> {
    @Query("select sp.id, sp.ma,sp.ten,sp.hinhAnhDaiDien,sum(ctsp.soLuong),sp.thuongHieu.ten,sp.theLoai.ten from SanPham sp join ChiTietSanPhamMauSacHinhAnh ctsphams on sp.id=ctsphams.sanPham.id" +
            " join ChiTietSanPham ctsp on ctsphams.Id = ctsp.chiTietSanPhamMauSacHinhAnh.Id group by sp.id, sp.ma,sp.ten,sp.hinhAnhDaiDien,sp.thuongHieu.ten,sp.theLoai.ten")
    List<Object[]> getAllTongSL();
    @Query("select sp.id, sp.ma,sp.ten,sp.hinhAnhDaiDien,sp.thuongHieu.ten,sp.theLoai.ten from SanPham sp where sp.id not in (select ctsphams.sanPham.id from ChiTietSanPhamMauSacHinhAnh  ctsphams)")
    List<Object[]> getAllSPChuaSL();

    @Transactional
    @Modifying
    @Query("update SanPham set ten=?1,moTa=?2,trangThai=?3,hinhAnhDaiDien=?4,thuongHieu.id=?5,theLoai.id=?6,ngaySua=?8,giamGIa.Id = ?9 where id=?7")
    void update(String ten, String moTa,Integer trangThai, String hinhAnh, Long thuongHieu, Long theLoai, Long id, Date ngayTao,Long giamGia);

    @Query("select sp from SanPham sp where sp.ten=?1 and sp.thuongHieu.id=?2 and sp.theLoai.id = ?3")
    SanPham findSPCoTonTaiKhong(String ten, Long thuongHien, Long theLoai);

    @Query("select sp from SanPham sp where sp.ten=?1 and sp.id not in (?2)")
    List<SanPham> findByName(String ten, Long idSP);

    SanPham findFirstByOrderByIdDesc();

    @Query(value = "select top(4) san_pham.id,san_pham.ten,san_pham.hinh_anh_dai_dien,san_pham.so_luong_da_ban,giam_gia.muc_giam,cast( MAX((chi_tiet_san_pham_mau_sac.gia_hien_hanh*(100-giam_gia.muc_giam))/100) as INT),CAST(MIN((chi_tiet_san_pham_mau_sac.gia_hien_hanh*(100-giam_gia.muc_giam))/100) as INT) ,CAST( MAX(chi_tiet_san_pham_mau_sac.gia_hien_hanh) as INT),CAST(MIN(chi_tiet_san_pham_mau_sac.gia_hien_hanh) as INT)  from san_pham join chi_tiet_san_pham_mau_sac on san_pham.id = chi_tiet_san_pham_mau_sac.san_pham_id join giam_gia on giam_gia.id = san_pham.giam_gia_id group by san_pham.hinh_anh_dai_dien,giam_gia.muc_giam, san_pham.id,san_pham.ten,san_pham.so_luong_da_ban,san_pham.ngay_tao  order by san_pham.ngay_tao desc" ,nativeQuery = true)
    List<Object> findAllByOrderNgayTaoDesc();

    @Query(value = "select top(4) san_pham.id,san_pham.ten,san_pham.hinh_anh_dai_dien,san_pham.so_luong_da_ban,giam_gia.muc_giam,cast( MAX((chi_tiet_san_pham_mau_sac.gia_hien_hanh*(100-giam_gia.muc_giam))/100) as INT),CAST(MIN((chi_tiet_san_pham_mau_sac.gia_hien_hanh*(100-giam_gia.muc_giam))/100) as INT) ,CAST( MAX(chi_tiet_san_pham_mau_sac.gia_hien_hanh) as INT),CAST(MIN(chi_tiet_san_pham_mau_sac.gia_hien_hanh) as INT)  from san_pham join chi_tiet_san_pham_mau_sac on san_pham.id = chi_tiet_san_pham_mau_sac.san_pham_id join giam_gia on giam_gia.id = san_pham.giam_gia_id group by san_pham.hinh_anh_dai_dien,giam_gia.muc_giam, san_pham.id,san_pham.ten,san_pham.so_luong_da_ban,san_pham.ngay_tao  order by san_pham.so_luong_da_ban desc" ,nativeQuery = true)
    List<Object> findAllByOrderSLDaBanDesc();

    @Transactional
    @Modifying
    @Query(value = "insert into san_pham(ten,mo_ta,ngay_tao,ngay_sua,hinh_anh_dai_dien,trang_thai,thuong_hieu_id,the_loai_id,giam_gia_id) values(?1,?2,getdate(),getdate(),?3,0,?4,?5,?6)",nativeQuery = true)
    void save(String ten,String moTa,String hinhAnh,Long thuongHieu,Long theLoai,Long giamGia);

    @Query("SELECT s.id,s.hinhAnhDaiDien, s.ten,th.ten,tl.ten FROM SanPham s join ThuongHieu th on th.id = s.thuongHieu.id " +
            " join TheLoai tl on s.theLoai.id = tl.id WHERE s.ten LIKE %:keyword% OR th.ten LIKE %:keyword% OR tl.ten LIKE %:keyword%")
    List<Object[]> searchByTenOrThuongHieuOrTheLoai(@Param("keyword") String keyword);

    @Query("select sp.id,sp.ten,sp.hinhAnhDaiDien,sp.soLuongDaBan,sp.giamGIa.mucGiam,sum(ctsp.soLuong)," +
            " max((ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100),Min((ctsphams.giaHienHanh*(100-sp.giamGIa.mucGiam))/100)," +
            " Max(ctsphams.giaHienHanh),Min(ctsphams.giaHienHanh)" +
            " from ChiTietSanPham ctsp join ChiTietSanPhamMauSacHinhAnh ctsphams on ctsp.chiTietSanPhamMauSacHinhAnh.Id = ctsphams.Id" +
            " join SanPham sp on ctsphams.sanPham.id = sp.id where sp.ten LIKE %:keyword% OR sp.thuongHieu.ten LIKE %:keyword% OR sp.theLoai.ten LIKE %:keyword%" +
            " group by sp.id,sp.ten,sp.hinhAnhDaiDien,sp.soLuongDaBan,sp.giamGIa.mucGiam")
    List<Object> getAllSPBySearch(@Param("keyword") String keyword);



}
