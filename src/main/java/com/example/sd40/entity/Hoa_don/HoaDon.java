package com.example.sd40.entity.Hoa_don;

import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.NhanVien.NhanVien;
import com.example.sd40.entity.Voucher.Voucher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "hoa_don")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ma")
    private String maHoaDon;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_thanh_toan")
    private Date ngayThanhToan;

    @Column(name = "tong_tien")
    private BigDecimal tongTien;


    @Column(name = "phan_tram_khuyen_mai")
    private Integer phanTramKhuyenMai;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "tong_tien_giam")
    private BigDecimal tongTienGiam;


    @ManyToOne
    @JoinColumn(name = "voucher_id",referencedColumnName = "id")
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_id",referencedColumnName = "id")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id",referencedColumnName = "id")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "phuong_thuc_thanh_toan_id",referencedColumnName = "id")
    private PhuongThucThanhToan phuongThucThanhToan;
}
