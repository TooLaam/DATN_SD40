package com.example.sd40.repuest;

import com.example.sd40.entity.San_pham.ChiTietSanPham;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author quynh
 */
@Getter
@Setter
public class HoaDonDetailRequest {

    private Long idBill;

    private int quantity;

    private BigDecimal price;

    private Long idChiTietSanPham;

    private String note;

}
