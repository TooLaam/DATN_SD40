package com.example.sd40.repository.KhachHang;

import com.example.sd40.entity.KhachHang.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author quynh
 */
@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
}
