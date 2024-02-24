package com.example.sd40.service;

import com.example.sd40.entity.ProductDetail;
import com.example.sd40.entity.ViewModels.ProductDetailView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface ProductDetailService {
    void add(ProductDetail productDetail);
    void delete(UUID id);
    void update(UUID id, ProductDetail productDetail);
    ArrayList<ProductDetailView> getAllProductDetail();
    ProductDetailView getProductDetailById(UUID id);
    List<ProductDetail> getAll();
    ProductDetail getOne(UUID id);

}
