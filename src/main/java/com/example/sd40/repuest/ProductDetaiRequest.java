package com.example.sd40.repuest;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author quynh
 */

@Getter
@Setter
public class ProductDetaiRequest {
    
    private Long id;
    
    private int quantity;
    
    private BigDecimal price;
    
}
