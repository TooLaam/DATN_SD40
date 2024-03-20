package com.example.sd40.repuest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author quynh
 */
@Getter
@Setter
@AllArgsConstructor
public class GioHangDetailRequest {

    private Long idProduct;

    private int price;

    private int quantity;

}
