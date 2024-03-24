package com.example.sd40.repository.NhanVien;

import com.example.sd40.entity.NhanVien.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author quynh
 */
@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {

}
