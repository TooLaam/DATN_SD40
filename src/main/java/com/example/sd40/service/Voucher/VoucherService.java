package com.example.sd40.service.Voucher;

import com.example.sd40.entity.Voucher.Voucher;
import java.util.List;

public interface VoucherService {
    List<Voucher> getAll();
    void addVoucher(Voucher voucher);
    Voucher detail(Long id);
    List<Voucher> findByNameUpdate(String ten,Long id);
    List<Voucher> findByName(String ten);
    void updateVoucher(Long idVoucher);
}
