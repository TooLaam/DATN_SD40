package com.example.sd40.service;

import com.example.sd40.entity.Voucher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface VoucherService {
    void add(Voucher voucher);
    void delete(UUID id);
    void update(UUID id, Voucher voucher);
    List<Voucher> getAll();
    Voucher getByCode(String code);
    Voucher getOne(UUID id);
}
