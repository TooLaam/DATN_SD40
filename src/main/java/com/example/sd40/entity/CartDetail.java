package com.example.sd40.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "CartDetail")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDetail {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(name = "Quantity")
    private Integer Quantity;

    @Column(name = "Price")
    private BigDecimal Price;

    @ManyToOne
    @JoinColumn(name = "CustomerId",referencedColumnName = "id",nullable = true)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "ProductDetailId",referencedColumnName = "Id",nullable = true)
    private ProductDetail productDetail;

}
