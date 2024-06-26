package com.example.sd40.entity.San_pham;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "san_pham")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SanPham {
    @jakarta.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "hinh_anh_dai_dien")
    private String hinhAnhDaiDien;

    @Column(name = "so_luong_da_ban")
    private Integer soLuongDaBan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "thuong_hieu_id",
            referencedColumnName = "id",
            nullable = true
    )
    private ThuongHieu thuongHieu;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "the_loai_id",
            referencedColumnName = "id",
            nullable = true
    )
    private TheLoai theLoai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "giam_gia_id",
            referencedColumnName = "id",
            nullable = true
    )
    @JsonBackReference
    private GiamGIa giamGIa;
}
