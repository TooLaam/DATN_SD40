package com.example.sd40.repository;


import com.example.sd40.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CartdetailRepository extends JpaRepository<CartDetail, UUID> {

    @Query("SELECT a FROM CartDetail a " +
            "JOIN Cart b ON a.cart.id = b.id " +
            "JOIN ProductDetail c ON a.productDetail.id = c.id " +
            "WHERE b.customer.Id = ?1")
    List<CartDetail> getCartDetailsByCustomerId(UUID customerId);
    @Query("SELECT a FROM CartDetail a " +
            "JOIN Cart b ON a.cart.id = b.id " +
            "JOIN ProductDetail c ON a.productDetail.id = c.id " +
            "WHERE b.customer.Id = :customerId AND c.id = :productDetailId")
    CartDetail getOneCartDetail(UUID customerId, UUID productDetailId);
}
