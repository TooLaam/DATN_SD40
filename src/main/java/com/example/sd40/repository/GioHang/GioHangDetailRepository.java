package com.example.sd40.repository.GioHang;

import com.example.sd40.entity.Gio_hang.GioHang;
import com.example.sd40.entity.Gio_hang.GioHangDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author quynh
 */
public interface GioHangDetailRepository extends JpaRepository<GioHangDetail, Long> {

    List<GioHangDetail> findAllByGioHang(GioHang gioHang);

    void  deleteAllByGioHang(GioHang gioHang);
}
