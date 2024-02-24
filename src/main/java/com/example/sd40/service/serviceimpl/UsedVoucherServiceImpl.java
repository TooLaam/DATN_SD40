package com.example.sd40.service.serviceimpl;

import com.example.sd40.entity.UsedVoucher;
import com.example.sd40.repository.UsedVoucherRepository;
import com.example.sd40.service.UsedVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsedVoucherServiceImpl implements UsedVoucherService {
    @Autowired
    UsedVoucherRepository usedVoucherRepository;

    @Override
    public void add(UsedVoucher usedVoucher) {
        usedVoucherRepository.save(usedVoucher);
    }

    @Override
    public void delete(UUID id) {
        usedVoucherRepository.deleteById(id);
    }

    @Override
    public void update(UUID id, UsedVoucher usedVoucher) {
        UsedVoucher usedVoucherUpdate = getOne(id);
        usedVoucherUpdate.setSubTotal(usedVoucher.getSubTotal());
        usedVoucherUpdate.setVoucher(usedVoucher.getVoucher());
        usedVoucherUpdate.setBill(usedVoucher.getBill());
        usedVoucherRepository.saveAndFlush(usedVoucherUpdate);
    }

    @Override
    public List<UsedVoucher> getAll() {
        return usedVoucherRepository.findAll();
    }

    @Override
    public UsedVoucher getUsedVoucherByBillId(UUID id) {
        return usedVoucherRepository.getUsedVoucherByBillId(id);
    }

    @Override
    public UsedVoucher getOne(UUID id) {
        return usedVoucherRepository.findById(id).get();
    }
}
