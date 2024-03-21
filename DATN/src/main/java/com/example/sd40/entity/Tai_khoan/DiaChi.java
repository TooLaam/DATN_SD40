package com.example.sd40.entity.Tai_khoan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "dia_chi")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiaChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "thanh_pho")
    private String thanhPho;

    @Column(name = "quan_huyen")
    private String quanHuyen;

    @Column(name = "phuong_xa")
    private String phuongXa;

    @Column(name = "dia_chi_cu_the")
    private String diaChiCuThe;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "tai_khoan_id",referencedColumnName = "id")
    private TaiKhoan tkDiaChi;
}
