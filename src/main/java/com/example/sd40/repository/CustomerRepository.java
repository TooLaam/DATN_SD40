package com.example.sd40.repository;

import com.example.sd40.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,UUID> {
    @Query("SELECT c from Customer c WHERE c.phone=?1")
    List<Customer> timKiem(String phone);

    @Query("SELECT c from Customer c where c.username=?1 and c.password=?2")
    Customer login(String username, String password);

    @Query("SELECT c from Customer c where c.username = ?1")
    Customer getCustomerByName(String username);

    @Query("SELECT c from Customer c where c.username=?1")
    List<Customer> getByUserName(String username);

    @Query("select c from Customer c where c.Id not in (select d.Id from Customer d where d.username =?1) and c.username =?2")
    List<Customer> checkUserNameUpdate(String tenHT,String tenSua);
}
