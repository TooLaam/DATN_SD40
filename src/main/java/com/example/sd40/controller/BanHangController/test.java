package com.example.sd40.controller.BanHangController;

import com.example.sd40.service.SanPham.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;

public class test {
    @Autowired
    SanPhamService sanPhamService;

    public static void main(String[] args) {
        test test = new test();
       test.aVoid();
    }
    public void aVoid(){
        System.out.println(sanPhamService.getList());
    }
}
