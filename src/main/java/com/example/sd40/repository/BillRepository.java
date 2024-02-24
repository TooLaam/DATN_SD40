package com.example.sd40.repository;


import com.example.sd40.entity.Bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface BillRepository extends JpaRepository<Bill, UUID> {
    
    @Query("SELECT B FROM Bill B WHERE B.CreatedDate >= :startDate AND B.CreatedDate <= :endDate " +
            "AND B.billStatus.Name ='Confirmed'")
    List<Bill> getAllBillsInDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT c from Bill c where c.customer.Id = ?1")
    List<Bill>getAllByCustomerId(UUID cusId);

}
