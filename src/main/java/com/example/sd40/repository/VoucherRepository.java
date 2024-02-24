package com.example.sd40.repository;

import com.example.sd40.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, UUID> {
    @Query("SELECT a FROM Voucher a " +
            "WHERE a.code LIKE :code ")
    Voucher findByVoucherCode(String code);
}
