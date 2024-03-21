package com.example.sd40.repuest;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * @author quynh
 */
@Getter
@Setter
public class NhanVienRequyest {

    private Long idNhanVien;

    private String hoVaTen;

    private String ngaySinh;

    private int gioiTinh;

    private String soDienThoai;

    private String email;

    private String diaChi;

}
