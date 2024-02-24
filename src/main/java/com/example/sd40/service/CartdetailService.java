package com.example.sd40.service;


import com.example.sd40.entity.CartDetail;
import com.example.sd40.entity.ViewModels.CartDetailView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public interface CartdetailService {
    ArrayList<CartDetail> getAll();

    ArrayList<CartDetailView> getCartDetailByCustomerId(UUID customerId);

    List<CartDetail> getCartDetailByCusId(UUID customerId);

    CartDetail getOneCartDetail(UUID customerId, UUID productDetailId);

    void save(CartDetail cartdetail);

    void delete(UUID id);

    void deleteAll(UUID id);

    void update(UUID id, CartDetail cartdetail);

    Optional<CartDetail> getOne(UUID id);

    boolean checkExistCartDetail(UUID customerId, UUID productDetailId);
}
