package com.example.sd40.service;

import com.example.sd40.entity.UsedVoucher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UsedVoucherService {
    void add(UsedVoucher usedVoucher);
    void delete(UUID id);
    void update(UUID id, UsedVoucher usedVoucher);
    List<UsedVoucher> getAll();
    UsedVoucher getUsedVoucherByBillId(UUID id);
    UsedVoucher getOne(UUID id);
}
