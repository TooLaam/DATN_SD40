package com.example.sd40.service;

import com.example.sd40.entity.Bill;
import com.example.sd40.entity.BillStatus;

import java.util.List;
import java.util.UUID;

public interface BillStatusService {
    Bill getBillStatus();

    BillStatus findById(UUID id);

    List<BillStatus> get_all_bill_status();
}
