package com.example.sd40.service;

import com.example.sd40.entity.ViewModels.WishListView;
import com.example.sd40.entity.WishList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface WishListService {
    void add(WishList wishList);
    void delete(UUID id);
    void update(UUID id, WishList wishList);
    void Like(UUID customerId, UUID productDetailId);
    List<WishList> getAll();
    ArrayList<WishListView> getAllByCustomerId(UUID id);
    Page<WishListView> getAllByCustomerIdWithPagination(UUID id, Pageable pageable);
    WishList getOne(UUID id);
}
