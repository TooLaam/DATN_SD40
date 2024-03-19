package com.example.sd40.repuest;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * @author quynh
 */
@Getter
@Setter
public class HoaDonKhachHangResquest {

    private BigDecimal phiShip;

    private BigDecimal tienGiam;

    private BigDecimal tongTien;

    private BigDecimal tongTienKhiGiam;

    private String ghiChu;

    private String nguoiNhan;

    private String sdtNguoiNhan;

    private String thanhPho;

    private String quanHuyen;

    private String phuongXa;

    private String diaChiNguoiNhan;

    private String emailNguoiNhan;

    private Date ngayNhan;

    private String maPhuongThuc;

    private Integer trangThai;

    private Long maVoucher;

    private String idUser;

    private BigDecimal price;
    
    private BigDecimal priceThanhToan;
    
    private List<ProductDetaiRequest> productDetaiRequestList;
    
}
