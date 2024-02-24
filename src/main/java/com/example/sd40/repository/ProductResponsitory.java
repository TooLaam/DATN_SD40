package com.example.sd40.repository;


import com.example.sd40.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductResponsitory extends JpaRepository<Product,UUID> {
    @Query("SELECT a FROM Product a " +
            "JOIN Brand b ON a.brand.Id = b.Id " +
            "WHERE UPPER(a.name) LIKE CONCAT('%', UPPER(:name), '%')")
    List<Product> findByProductName(String name);
    @Query("SELECT a FROM Product a " +
            "JOIN Brand b ON a.brand.Id = b.Id " +
            "WHERE b.Id = ?1")
    List<Product> findProductByBrand(UUID id);
}
