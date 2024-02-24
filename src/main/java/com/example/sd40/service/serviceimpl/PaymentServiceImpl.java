package com.example.sd40.service.serviceimpl;

import com.example.sd40.entity.Payment;
import com.example.sd40.repository.PaymentRepository;
import com.example.sd40.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment getOne(UUID uuid) {
        return paymentRepository.findById(uuid).get();
    }
}
