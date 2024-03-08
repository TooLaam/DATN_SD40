package com.example.sd40.entity.San_pham.ViewModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class TongSLSP {

    private String id;
    private String ma,ten,hinhAnhDaiDien;
    private String soLuongTong;

    public TongSLSP() {
    }

    public TongSLSP(String id, String ma, String ten, String hinhAnhDaiDien, String soLuongTong) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.hinhAnhDaiDien = hinhAnhDaiDien;
        this.soLuongTong = soLuongTong;
    }
}
