package com.example.sd40.repository.KhachHang;

import com.example.sd40.entity.KhachHang.HangThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HangThanhVienRepository extends JpaRepository<HangThanhVien,Long> {
}
