package com.example.sd40.service.KhachHang;




import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.repuest.KhachHangRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author quynh
 */
@Service
public interface KhachHangService {

    List<KhachHang> findByKhachHang();

    KhachHang create(KhachHangRequest requyest);

    KhachHang update(KhachHangRequest requyest);

    KhachHang detail(Long id);
}
