package com.example.sd40.service.Voucher.Impl;



import com.example.sd40.entity.Voucher.Voucher;
import com.example.sd40.repository.Voucher.VoucherRepository;
import com.example.sd40.service.Voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherImpl implements VoucherService {
    @Autowired
    VoucherRepository voucherRepository;

    @Override
    public List<Voucher> getAll() {
        return voucherRepository.getAll();
    }

    @Override
    public void addVoucher(Voucher voucher) {
            voucherRepository.save(voucher);
    }

    @Override
    public Voucher detail(Long id) {
        return voucherRepository.getById(id);
    }

    @Override
    public List<Voucher> findByNameUpdate(String ten, Long id) {
        return voucherRepository.findByNameUpdate(ten,id);
    }

    @Override
    public List<Voucher> findByName(String ten) {
        return voucherRepository.findByName(ten);
    }
}
