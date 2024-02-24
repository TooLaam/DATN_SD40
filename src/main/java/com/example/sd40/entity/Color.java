package com.example.sd40.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "Color")
public class Color {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Image")
    private String image;

    @Column(name = "Status")
    private Integer status;

    @ManyToOne()
    @JoinColumn(
            name = "BrandId",
            referencedColumnName = "Id",
            nullable = true
    )
    private Brand brand;

    public Color( String name, BigDecimal price, String image, Integer status, Brand brand) {

        this.name = name;
        this.price = price;
        this.image = image;
        this.status = status;
        this.brand = brand;
    }
}
