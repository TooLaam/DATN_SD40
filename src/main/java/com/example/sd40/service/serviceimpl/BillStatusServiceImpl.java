package com.example.sd40.service.serviceimpl;

import com.example.sd40.entity.Bill;
import com.example.sd40.entity.BillStatus;
import com.example.sd40.repository.BillStatusRepository;
import com.example.sd40.service.BillStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class BillStatusServiceImpl implements BillStatusService {

    @Autowired
    BillStatusRepository billStatusRepository;

    @Override
    public Bill getBillStatus() {
        return null;
    }

    @Override
    public BillStatus findById(UUID id) {
        return billStatusRepository.findById(id).get();
    }

    @Override
    public List<BillStatus> get_all_bill_status() {
        return billStatusRepository.findAll();
    }
}
