package com.example.sd40.entity.Hoa_don;

import com.example.sd40.entity.San_pham.ChiTietSanPham;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @author quynh
 */
@Entity
@Table(name = "hoa_don_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    private int quantity;
    
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "hoa_don",referencedColumnName = "id")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "product_detail",referencedColumnName = "id")
    private ChiTietSanPham chiTietSanPham;
}
