package com.example.sd40.service.serviceimpl;

import com.example.sd40.entity.Customer;
import com.example.sd40.repository.CustomerRepository;
import com.example.sd40.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Page<Customer> findAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo,10);
        return customerRepository.findAll(pageable);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> timKiem(String phone) {
        return customerRepository.timKiem(phone);
    }

    @Override
    public void add(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(UUID id) {
        customerRepository.deleteById(id);
    }




    @Override
    public Customer detail(UUID id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void update(Customer cs) {
       customerRepository.save(cs);

    }

    @Override
    public Customer login(String username, String password) {
        return customerRepository.login(username,password);
    }

    @Override
    public Customer getCustomerByName(String username) { return customerRepository.getCustomerByName(username); }

    @Override
    public List<Customer> getByUserName(String username) {
        return customerRepository.getByUserName(username);
    }

    @Override
    public List<Customer> checkUserNameUpdate(String tenHT, String tenSua) {
        return customerRepository.checkUserNameUpdate(tenHT,tenSua);
    }

}
