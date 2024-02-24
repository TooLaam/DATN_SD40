package com.example.sd40.repository;


import com.example.sd40.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductDetailResponsitory extends JpaRepository<ProductDetail, UUID> {
    @Query("SELECT a FROM ProductDetail a " +
            "JOIN Product b ON a.product.id = b.id " +
            "WHERE b.id = ?1")
    List<ProductDetail> findByProductId(UUID id);
}
