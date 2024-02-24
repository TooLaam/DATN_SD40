package com.example.sd40.repository;


import com.example.sd40.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WishListRespository extends JpaRepository<WishList, UUID> {
    @Query("SELECT a FROM WishList a " +
            "JOIN Customer b ON a.customer.Id = b.Id " +
            "JOIN Product c ON a.product.id = c.id " +
            "WHERE b.Id = :id")
    List<WishList> findByCustomerId(UUID id);
    @Query("SELECT a FROM WishList a " +
            "JOIN Customer b ON a.customer.Id = b.Id " +
            "JOIN Product c ON a.product.id = c.id " +
            "WHERE a.customer.Id = ?1 AND a.product.id = ?2")
    Optional<WishList> getWishListByCustomerLike(UUID customerId, UUID productId);
}
