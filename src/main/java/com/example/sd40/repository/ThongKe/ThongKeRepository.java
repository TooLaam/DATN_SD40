package com.example.sd40.repository.ThongKe;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.ThongKe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ThongKeRepository extends JpaRepository<HoaDon,Long> {
    @Query(value = "SELECT" +
            " COUNT(hoa_don.id) as ca," +
            " SUM(CASE WHEN trang_thai = 0 THEN 1 ELSE 0 END) AS c1," +
            " (SUM(CASE WHEN trang_thai = 0 THEN 1 ELSE 0 END) * 100.0) / COUNT(hoa_don.id) AS phan_tram_c1," +
            " SUM(CASE WHEN trang_thai = 1 THEN 1 ELSE 0 END) AS c2," +
            " (SUM(CASE WHEN trang_thai = 1 THEN 1 ELSE 0 END) * 100.0) / COUNT(hoa_don.id) AS phan_tram_c2," +
            " SUM(CASE WHEN trang_thai = 2 THEN 1 ELSE 0 END) AS c3," +
            " (SUM(CASE WHEN trang_thai = 2 THEN 1 ELSE 0 END) * 100.0) / COUNT(hoa_don.id) AS phan_tram_c3, " +
            " SUM(CASE WHEN trang_thai = 3 THEN 1 ELSE 0 END) AS c4," +
            " (SUM(CASE WHEN trang_thai = 3 THEN 1 ELSE 0 END) * 100.0) / COUNT(hoa_don.id) AS phan_tram_c4," +
            " SUM(CASE WHEN trang_thai = 4 THEN 1 ELSE 0 END) AS c5," +
            " (SUM(CASE WHEN trang_thai = 4 THEN 1 ELSE 0 END) * 100.0) / COUNT(hoa_don.id) AS phan_tram_c5," +
            " SUM(CASE WHEN trang_thai = 5 THEN 1 ELSE 0 END) AS c6," +
            " (SUM(CASE WHEN trang_thai = 5 THEN 1 ELSE 0 END) * 100.0) / COUNT(hoa_don.id) AS phan_tram_c6" +
            " FROM hoa_don where ngay_thanh_toan >= :ngayBatDau and ngay_thanh_toan<= :ngayKetThuc", nativeQuery = true)
    Object TongDonHangAll(@Param("ngayBatDau") String ngayBatDau, @Param("ngayKetThuc") String ngayKetThuc);


    @Query(value = "select sum(hoa_don_chi_tiet.so_luong), sum(tong_tien) from hoa_don join hoa_don_chi_tiet on hoa_don.id = hoa_don_chi_tiet.hoa_don_id join chi_tiet_san_pham on hoa_don_chi_tiet.chi_tiet_san_pham_id = chi_tiet_san_pham.id where hoa_don.trang_thai=4 and ngay_thanh_toan >= :ngayBatDau and ngay_thanh_toan <= :ngayKetThuc", nativeQuery = true)
    Object DoanhSoVaSanPhamDaBanAll(@Param("ngayBatDau") String ngayBatDau, @Param("ngayKetThuc") String ngayKetThuc);

    @Query("select sum(ct.soLuong) from ChiTietSanPham ct")
    Integer hangTonKho();

    @Query(value = "SELECT" +
            " YEAR(ngay_thanh_toan) AS Nam, " +
            " MONTH(ngay_thanh_toan) AS Thang," +
            " SUM(tong_tien) AS Tong_Doanh_Thu FROM hoa_don" +
            " WHERE ngay_thanh_toan IS NOT NULL and trang_thai = 4 and YEAR(ngay_thanh_toan) = ?1" +
            " GROUP BY YEAR(ngay_thanh_toan), MONTH(ngay_thanh_toan)" +
            " ORDER BY Nam, Thang;",
            nativeQuery = true)
    List<Object[]> getTongDoanhThuByMonthAndYear(String nam);
}
