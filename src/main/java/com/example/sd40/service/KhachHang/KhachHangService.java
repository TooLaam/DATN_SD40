package com.example.sd40.service.KhachHang;

import com.example.sd40.entity.KhachHang.KhachHang;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author quynh
 */
@Service
public interface KhachHangService {

    List<KhachHang> getAll();

    void save(KhachHang khachHang);

    KhachHang detail(Long id);

    List<KhachHang> findByUserName(String ten);

    List<KhachHang> findByUserNameUpdate(String ten,Long id);

    Long IdKHCuoi();

    List<KhachHang> findByEmailUpdate(String ten,Long id);

    List<KhachHang> findByEmal(String ten);

}
