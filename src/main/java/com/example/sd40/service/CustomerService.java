package com.example.sd40.service;

import com.example.sd40.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CustomerService {
    Page<Customer> findAll(Integer pageNo);
    List<Customer> findAll();
    List<Customer> timKiem(String phone);
    void add(Customer cs);
    void delete(UUID id);
    void update(Customer cs);
    Customer detail(UUID id);
    Customer login(String username, String password);
    Customer getCustomerByName(String username);
    List<Customer> getByUserName(String username);
    List<Customer> checkUserNameUpdate(String tenHT,String tenSua);
}
