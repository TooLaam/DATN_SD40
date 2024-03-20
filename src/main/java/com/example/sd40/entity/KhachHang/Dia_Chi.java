package com.example.sd40.entity.KhachHang;

import com.example.sd40.entity.San_pham.GiamGIa;
import com.example.sd40.entity.San_pham.TheLoai;
import com.example.sd40.entity.San_pham.ThuongHieu;
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
@Table(name = "dia_chi")
public class Dia_Chi {

    @jakarta.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "trang_thai")
    private Integer trangThai;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "tinh_thanh_pho_id",
            referencedColumnName = "id",
            nullable = true
    )
    private tinhThanhPho tinhThanhPho;


}
