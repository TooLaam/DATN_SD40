package com.example.sd40.entity.Tai_khoan;

import com.example.sd40.entity.Gio_hang.GioHang;
import com.example.sd40.entity.Hoa_don.HoaDon;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tai_khoan")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ho_va_ten")
    private String hoVaTen;

    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @Column(name = "gioi_tinh")
    private int gioiTinh;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "ten_tai_khoan")
    private String tenTaiKhoan;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "trang_thaithai")
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vai_tro_id",referencedColumnName = "id")
    private VaiTro vaiTro;

    @JsonIgnore
    @OneToMany(mappedBy = "tkGioHang",cascade = CascadeType.REMOVE)
    private List<GioHang> gioHangList;

    @JsonIgnore
    @OneToMany(mappedBy = "tkDiaChi",cascade = CascadeType.REMOVE)
    private List<DiaChi> diaChiList;

    @JsonIgnore
    @OneToMany(mappedBy = "taiKhoan")
    private List<HoaDon> hoaDonList;
}
