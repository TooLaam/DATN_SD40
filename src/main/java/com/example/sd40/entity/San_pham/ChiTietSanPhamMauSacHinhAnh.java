package com.example.sd40.entity.San_pham;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "chi_tiet_san_pham_mau_sac")
public class ChiTietSanPhamMauSacHinhAnh {
    @jakarta.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "hinh_anh")
    private String hinhAnh;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "san_pham_id",
            referencedColumnName = "id",
            nullable = true
    )
    private SanPham sanPham;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "mau_sac_id",
            referencedColumnName = "id",
            nullable = true
    )
    private Mau_sac mau_sac;

}
