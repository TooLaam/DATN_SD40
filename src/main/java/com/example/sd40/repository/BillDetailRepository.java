package com.example.sd40.repository;


import com.example.sd40.entity.BillDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, UUID> {


    @Query("SELECT BD.productDetail.product.name, SUM(BD.Quantity) AS TotalQuantitySold\n" +
            "FROM BillDetail BD\n" +
            "WHERE BD.bill.CreatedDate >= :startDate AND BD.bill.CreatedDate <= :endDate\n" +
            "GROUP BY BD.productDetail.product.name")
    List<Object[]> getProductSalesData(
            @Param("startDate") java.sql.Date startDate,
            @Param("endDate") java.sql.Date endDate);

    @Query("SELECT c from BillDetail c where c.bill.Id = ?1")
    List<BillDetail> getAllByBillId(UUID id);

    @Query("SELECT a FROM BillDetail a " +
            "JOIN Bill b ON a.bill.Id = b.Id " +
            "JOIN ProductDetail c ON a.productDetail.id = c.id " +
            "WHERE b.Id = ?1")
    List<BillDetail> getBillDetailByBillId(UUID id);

}
