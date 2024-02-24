package com.example.sd40.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ProductDetail")
public class ProductDetail {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "ImportPrice")
    private BigDecimal importPrice;
    @Column(name = "Price")
    private BigDecimal price;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "CreatedDate")
    private Date createdDate;
    @Column(name = "Status")
    private Integer status;
    @Column(name = "Description")
    private String descripTion;

    @ManyToOne()
    @JoinColumn(
            name = "ProductId",
            referencedColumnName = "Id",
            nullable = true
    )
    private Product product;

    @ManyToOne()
    @JoinColumn(
            name = "ColorId",
            referencedColumnName = "Id",
            nullable = true
    )
    private Color color;

    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> listImages = new ArrayList<>();

    public ProductDetail( BigDecimal importPrice, BigDecimal price, Integer quantity, Date createdDate, Integer status, String descripTion, Product product, Color color, List<ProductImage> listImages) {

        this.importPrice = importPrice;
        this.price = price;
        this.quantity = quantity;
        this.createdDate = createdDate;
        this.status = status;
        this.descripTion = descripTion;
        this.product = product;
        this.color = color;
        this.listImages = listImages;
    }
}
