package com.example.sd40.entity.Gio_hang;

import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.entity.Tai_khoan.TaiKhoan;
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
@Table(name = "gio_hang_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GioHangDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private int quantity;

    private int price;

    @ManyToOne
    @JoinColumn(name = "id_gio_hang",referencedColumnName = "id")
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "id_product",referencedColumnName = "id")
    private ChiTietSanPham chiTietSanPham;
}
