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
@Builder
@Table(name = "Brand")
public class Brand {

    @jakarta.persistence.Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Image")
    private String image;

    @Column(name = "Status")
    private Integer staTus;

    public Brand( String name, String image, Integer staTus) {

        this.name = name;
        this.image = image;
        this.staTus = staTus;
    }
}
