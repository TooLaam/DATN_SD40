package com.example.sd40.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name = "Cart")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "Quantity")
    private Integer Quantity;

    @Column(name = "TotalMoney")
    private BigDecimal TotalMoney;

    @Column(name = "Status")
    private Integer Status;

    @ManyToOne
    @JoinColumn(name = "CustomerId", referencedColumnName = "Id", nullable = true)
    private Customer customer;
}
