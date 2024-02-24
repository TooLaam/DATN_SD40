package com.example.sd40.entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@NoArgsConstructor
@Getter
@Setter
public class Item {

    private UUID idProduct;

    private String productName;

    private Integer quantity;

    private BigDecimal price;

    public Item(UUID idProduct, String productName, Integer quantity, BigDecimal price) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public BigDecimal getTotal() {
        BigDecimal itemTotal = getPrice().multiply(BigDecimal.valueOf(getQuantity()));
        return itemTotal;
    }
}
