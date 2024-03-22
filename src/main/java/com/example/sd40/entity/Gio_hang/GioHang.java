package com.example.sd40.entity.Gio_hang;

import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.Tai_khoan.TaiKhoan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "gio_hang")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_gio_hang")
    private String maGioHang;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "nguoi_so_huu",referencedColumnName = "id")
    private TaiKhoan tkGioHang;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id",referencedColumnName = "id")
    private KhachHang khachHang;
}
