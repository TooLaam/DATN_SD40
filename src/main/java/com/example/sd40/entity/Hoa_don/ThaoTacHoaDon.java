package com.example.sd40.entity.Hoa_don;

import com.example.sd40.entity.NhanVien.NhanVien;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "thao_tac_hoa_don")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ThaoTacHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "thao_tac")
    private String thaoTac;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @ManyToOne
    @JoinColumn(name = "hoa_don_id",referencedColumnName = "id")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_id",referencedColumnName = "id")
    private NhanVien nhanVien;
}
