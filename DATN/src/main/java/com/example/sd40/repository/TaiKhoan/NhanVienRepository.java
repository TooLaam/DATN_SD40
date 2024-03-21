package com.example.sd40.repository.TaiKhoan;

import com.example.sd40.entity.Tai_khoan.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author quynh
 */
@Repository
public interface NhanVienRepository extends JpaRepository<TaiKhoan, Long> {

}
