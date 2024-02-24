package com.example.sd40.entity.ViewModels;

import com.example.sd40.entity.Customer;
import com.example.sd40.entity.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class WishListView {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID customerId;
    private UUID productId;
    private UUID productDetailId;
    private BigDecimal price;
    private String image;
    private Customer customer;
    private Product product;
}
