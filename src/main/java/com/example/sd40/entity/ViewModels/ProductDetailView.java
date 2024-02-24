package com.example.sd40.entity.ViewModels;

import com.example.sd40.entity.Color;
import com.example.sd40.entity.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Data
public class ProductDetailView {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private BigDecimal importPrice;
    private BigDecimal price;
    private Integer quantity;
    private Integer availableQuantity;
    private Integer sold;
    private Date createdDate;
    private Integer status;
    private String descripTion;
    private Product product;
    private Color color;

    public ProductDetailView(UUID id, String name, BigDecimal importPrice, BigDecimal price, Integer quantity, Integer availableQuantity, Integer sold, Date createdDate, Integer status, String descripTion, Product product, Color color) {
        this.id = id;
        this.name = name;
        this.importPrice = importPrice;
        this.price = price;
        this.quantity = quantity;
        this.availableQuantity = availableQuantity;
        this.sold = sold;
        this.createdDate = createdDate;
        this.status = status;
        this.descripTion = descripTion;
        this.product = product;
        this.color = color;
    }

    public ProductDetailView() {
    }
}
