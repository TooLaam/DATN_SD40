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
@Table(name = "Category")
public class Category {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Status")
    private Integer status;

    public Category( String name, Integer status) {

        this.name = name;
        this.status = status;
    }
}
