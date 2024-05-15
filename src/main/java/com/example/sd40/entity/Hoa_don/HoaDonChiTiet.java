package com.example.sd40.entity.Hoa_don;

import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * @author quynh
 */
@Entity
@Table(name = "hoa_don_chi_tiet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "gia_hien_hanh")
    private BigDecimal giaHienHanh;

    @Column(name = "gia_da_giam")
    private BigDecimal giaDaGiam;

    @ManyToOne
    @JoinColumn(name = "hoa_don_id",referencedColumnName = "id")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "chi_tiet_san_pham_id",referencedColumnName = "id")
    private ChiTietSanPham chiTietSanPham;
}
