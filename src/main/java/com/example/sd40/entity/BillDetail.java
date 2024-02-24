package com.example.sd40.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "BillDetail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class BillDetail {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(name = "Quantity")
    private Integer Quantity;

    @Column(name = "Price")
    private BigDecimal Price;

    @ManyToOne
    @JoinColumn(name = "BillId",referencedColumnName = "Id",nullable = true)
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "ProductDetailId",referencedColumnName = "Id",nullable = true)
    private ProductDetail productDetail;

}
