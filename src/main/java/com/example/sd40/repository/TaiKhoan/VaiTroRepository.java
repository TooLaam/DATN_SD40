package com.example.sd40.repository.TaiKhoan;

import com.example.sd40.entity.Tai_khoan.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author quynh
 */
@Repository
public interface VaiTroRepository  extends JpaRepository<VaiTro, Long> {

    Optional<VaiTro> findById(Long id);
}
