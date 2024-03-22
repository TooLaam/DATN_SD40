package com.example.sd40.service.TaiKhoan;




import com.example.sd40.entity.Tai_khoan.TaiKhoan;
import com.example.sd40.repuest.KhachHangRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author quynh
 */
@Service
public interface KhachHangService {

    List<TaiKhoan> findByKhachHang();

    TaiKhoan create(KhachHangRequest requyest);

    TaiKhoan update(KhachHangRequest requyest);

    TaiKhoan detail(Long id);
}
