package com.example.sd40.service.serviceimpl;


import com.example.sd40.entity.ProductImage;
import com.example.sd40.repository.ProductImageResponsitory;
import com.example.sd40.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductImageServiceimpl implements ProductImageService {
    @Autowired
    ProductImageResponsitory responsitory;
    @Override
    public void add(ProductImage productImage) {
        responsitory.saveAndFlush(productImage);
    }

    @Override
    public void delete(UUID id) {
       responsitory.deleteById(id);
    }

    @Override
    public void update(UUID id, ProductImage productImage) {
            ProductImage image = getOne(id);
           image.setName(productImage.getName());
           image.setStaTus(productImage.getStaTus());
           image.setProductDetail(productImage.getProductDetail());
            responsitory.flush();
    }

    @Override
    public List<ProductImage> getAll() {
        return responsitory.findAll();
    }

    @Override
    public List<ProductImage> getByProductDetailId(UUID id) {
        return responsitory.findByProductDetailId(id);
    }

    @Override
    public ProductImage getOne(UUID id) {
        return responsitory.findById(id).get();
    }
}
