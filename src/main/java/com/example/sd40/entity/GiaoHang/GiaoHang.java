package com.example.sd40.entity.GiaoHang;

import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.KhachHang.DiaChiChiTiet;
import com.example.sd40.entity.KhachHang.tinhThanhPho;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "giao_hang")
public class GiaoHang {
    @jakarta.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "ma_giao_hang")
    private String maGiaoHang;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "chi_phi")
    private BigDecimal chiPhi;

    @Column(name = "sdt")
    private String sdt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "hoa_don_id",
            referencedColumnName = "id",
            nullable = true
    )
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "dia_chi_chi_tiet_id",
            referencedColumnName = "id",
            nullable = true
    )
    private DiaChiChiTiet diaChiChiTiet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "trang_thai_giao_hang_id",
            referencedColumnName = "id",
            nullable = true
    )
    private TrangThaiGiaoHang trangThaiGiaoHang;

}
