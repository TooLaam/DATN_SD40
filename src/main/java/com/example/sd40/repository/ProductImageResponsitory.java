package com.example.sd40.repository;

import com.example.sd40.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductImageResponsitory extends JpaRepository<ProductImage, UUID> {
        boolean existsByName(String name);
        @Query("SELECT a FROM ProductImage a " +
                "JOIN ProductDetail b ON a.productDetail.id = b.id " +
                "WHERE b.id = ?1")
        List<ProductImage> findByProductDetailId(UUID id);
}
