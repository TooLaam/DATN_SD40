package com.example.demo.repository;

import com.example.demo.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan,Long> {
    Optional<TaiKhoan> findByTenTaiKhoan(String name);

    TaiKhoan findByMatKhau(String code);

}
