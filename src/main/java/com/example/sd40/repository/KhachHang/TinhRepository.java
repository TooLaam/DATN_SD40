package com.example.sd40.repository.KhachHang;

import com.example.sd40.entity.KhachHang.tinhThanhPho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TinhRepository extends JpaRepository<tinhThanhPho,Long> {

    @Query("select t from tinhThanhPho t order by t.ten asc ")
    List<tinhThanhPho> getAllASC();
}
