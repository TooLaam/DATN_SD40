package com.example.sd40.service;

import com.example.sd40.entity.Product;
import com.example.sd40.entity.ViewModels.ProductView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface ProductServie {

    Product add(Product product);
    void delete(UUID id);
    void update(UUID id, Product product);
    ArrayList<ProductView> getAllProduct();
    Page<ProductView> getAllProductWithPagination(Pageable pageable);
    Page<ProductView> getAllProductByName(String name, Pageable pageable);
    Page<ProductView> getAllProductByBrand(UUID id, Pageable pageable);
    Page<ProductView> getAllProductByPrice(BigDecimal min, BigDecimal max, Pageable pageable);
    List<Product> getAll();
    Product getOne(UUID id);
}
