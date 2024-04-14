package com.example.sd40.entity.Gio_hang;

import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gio_hang_chi_tiet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GioHangChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "TrangThai")
    private Integer trangThai;

    @Column(name = "so_luong")
    private Integer soLuong;

    @ManyToOne
    @JoinColumn(name = "gio_hang_id",referencedColumnName = "id")
    @JsonBackReference
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "chi_tiet_san_pham_id",referencedColumnName = "id")
    @JsonBackReference
    private ChiTietSanPham chiTietSanPham;
}
