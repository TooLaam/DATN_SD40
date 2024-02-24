package com.example.sd40.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CategoryDetail")
public class CategoryDetail {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "Quantity")
    private Integer quanTity;

    @ManyToOne()
    @JoinColumn(
            name = "ProductId",
            referencedColumnName = "Id",
            nullable = true
    )
    private Product product;

    @ManyToOne()
    @JoinColumn(
            name = "CategoryId",
            referencedColumnName = "Id",
            nullable = true
    )
    private Category category;

    public CategoryDetail(Integer Quantity, Product product, Category category) {

        this.quanTity = Quantity;
        this.product = product;
        this.category = category;
    }
}
