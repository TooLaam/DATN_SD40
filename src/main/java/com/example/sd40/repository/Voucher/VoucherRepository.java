package com.example.sd40.repository.Voucher;

import com.example.sd40.entity.Voucher.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author quynh
 */
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    @Query("select th from Voucher th where th.tenVoucher =?1")
    List<Voucher> findByName(String ten);
    @Query("select th from Voucher th where th.tenVoucher =?1 and th.id not in (?2)")
    List<Voucher> findByNameUpdate(String ten,Long id);

    @Query("select vc from Voucher vc where vc.id not in (select v.id from Voucher v where vc.phanTramGiam = 0)")
    List<Voucher> getAll();

    @Transactional
    @Modifying
    @Query("update Voucher dc set dc.soLuong=dc.soLuong-?2 where dc.id =?1")
    void updateVoucher(Long idVoucher,Integer soLuong);
}
