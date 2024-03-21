package com.example.sd40.repuest;

import lombok.Getter;
import lombok.Setter;

/**
 * @author quynh
 */
@Getter
@Setter
public class KhachHangRequest {

    private Long idNhanVien;

    private String hoVaTen;

    private String ngaySinh;

    private int gioiTinh;

    private String soDienThoai;

    private String email;

    private String diaChi;
}
