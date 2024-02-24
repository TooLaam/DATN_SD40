package com.example.sd40.service;

import com.example.sd40.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface PaymentService {
    Payment getOne(UUID uuid);
}
