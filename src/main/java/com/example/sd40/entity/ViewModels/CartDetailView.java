package com.example.sd40.entity.ViewModels;

import com.example.sd40.entity.Cart;
import com.example.sd40.entity.ProductDetail;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CartDetailView {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;
    private String Name;
    private String colorName;
    private Integer Quantity;
    private BigDecimal Price;
    private BigDecimal ProductPrice;
    private String image;
    private UUID ProductId;
    private UUID ProductDetailId;
    private Cart cart;
    private ProductDetail productDetail;

    public CartDetailView(UUID id, String name, String colorName, Integer quantity, BigDecimal price, BigDecimal productPrice, String image, UUID productId, UUID productDetailId, Cart cart, ProductDetail productDetail) {
        Id = id;
        Name = name;
        this.colorName = colorName;
        Quantity = quantity;
        Price = price;
        ProductPrice = productPrice;
        this.image = image;
        ProductId = productId;
        ProductDetailId = productDetailId;
        this.cart = cart;
        this.productDetail = productDetail;
    }

    public CartDetailView() {

    }
}
