package com.example.sd40.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "Name")
    private String name;
    @Column(name = "AvailableQuantity")
    private Integer availableQuantity;
    @Column(name = "Sold")
    private Integer sold;
    @Column(name = "Likes")
    private Integer likes;
    @Column(name = "CreatedDate")
    private Date createdDate;
    @Column(name = "Status")
    private Integer status;
    @Column(name = "Description")
    private String descripTion;

    @ManyToOne()
    @JoinColumn(
            name = "BrandId",
            referencedColumnName = "Id",
            nullable = true
    )
    private Brand brand;



    public Product( String name, Integer availableQuantity, Integer sold, Integer likes,  Integer status, String descripTion, Brand brand, List<CategoryDetail> list) {

        this.name = name;
        this.availableQuantity = availableQuantity;
        this.sold = sold;
        this.likes = likes;

        this.status = status;
        this.descripTion = descripTion;
        this.brand = brand;

    }


}
