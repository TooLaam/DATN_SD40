package com.example.sd40.repository.KhachHang;

import com.example.sd40.entity.KhachHang.DiaChiChiTiet;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaChiChiTietRepository extends JpaRepository<DiaChiChiTiet,Long> {
}
