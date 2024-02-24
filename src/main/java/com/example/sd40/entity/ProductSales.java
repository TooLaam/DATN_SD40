package com.example.sd40.entity;

import lombok.*;

import java.math.BigDecimal;

@Data
public class ProductSales {
    private String productName;
    private Integer totalQuantitySold;
    private BigDecimal totalRevenue;

    public ProductSales() {
    }

    public ProductSales(String productName, Integer totalQuantitySold, BigDecimal totalRevenue) {
        this.productName = productName;
        this.totalQuantitySold = totalQuantitySold;
        this.totalRevenue = totalRevenue;
    }

}

