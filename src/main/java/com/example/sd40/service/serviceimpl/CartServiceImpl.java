package com.example.sd40.service.serviceimpl;

import com.example.sd40.entity.Cart;
import com.example.sd40.repository.CartRepository;
import com.example.sd40.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository responitory;

    @Override
    public ArrayList<Cart> getAll() {
        return (ArrayList<Cart>) responitory.findAll();
    }

    @Override
    public void save(Cart cart) {
        responitory.saveAndFlush(cart);
    }

    @Override
    public void delete(UUID CartId) {
        responitory.deleteById(CartId);
    }

    @Override
    public void update(UUID CartId, Cart cart) {
        Cart a = getOne(CartId);
        a.setQuantity(cart.getQuantity());
        a.setTotalMoney(cart.getTotalMoney());
        a.setStatus(cart.getStatus());
        responitory.flush();

    }

    @Override
    public Cart getOne(UUID id) {
        return responitory.findById(id).get();
    }
}
