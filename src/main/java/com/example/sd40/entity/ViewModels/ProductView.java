package com.example.sd40.entity.ViewModels;

import com.example.sd40.entity.Brand;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Data
public class ProductView {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private Integer availableQuantity;
    private BigDecimal price;
    private Integer sold;
    private Integer likes;
    private String image;
    private Date createdDate;
    private Integer status;
    private String descripTion;
    private UUID productDetailId;
    private Brand brand;

    public ProductView(UUID id, String name, Integer availableQuantity, BigDecimal price, Integer sold, Integer likes, String image, Date createdDate, Integer status, String descripTion, UUID productDetailId, Brand brand) {
        this.id = id;
        this.name = name;
        this.availableQuantity = availableQuantity;
        this.price = price;
        this.sold = sold;
        this.likes = likes;
        this.image = image;
        this.createdDate = createdDate;
        this.status = status;
        this.descripTion = descripTion;
        this.productDetailId = productDetailId;
        this.brand = brand;
    }

    public ProductView() {
    }
}
