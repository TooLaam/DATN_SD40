package com.example.sd40.entity.San_pham;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "chi_tiet_san_pham")
public class ChiTietSanPham {

    @jakarta.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "so_luong")
    private Integer soLuong;


    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "trang_thai")
    private Integer trangThai;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "chi_tiet_san_pham_mau_sac_id",
            referencedColumnName = "id",
            nullable = true
    )
    @JsonBackReference
    private ChiTietSanPhamMauSacHinhAnh chiTietSanPhamMauSacHinhAnh;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kich_co_id",
            referencedColumnName = "id",
            nullable = true
    )
    private KichCo kichCo;

}
