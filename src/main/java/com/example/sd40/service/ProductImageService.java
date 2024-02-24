package com.example.sd40.service;

import com.example.sd40.entity.ProductImage;

import java.util.List;
import java.util.UUID;

public interface ProductImageService {

    void add(ProductImage productImage);
    void delete(UUID id);
    void update(UUID id, ProductImage productImage);
    List<ProductImage> getAll();
    List<ProductImage> getByProductDetailId(UUID id);
    ProductImage getOne(UUID id);
}
