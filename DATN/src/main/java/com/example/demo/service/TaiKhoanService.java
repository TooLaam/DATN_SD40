package com.example.demo.service;

import com.example.demo.entity.TaiKhoan;

public interface TaiKhoanService {
    public String addUser(TaiKhoan userInfo);

    public String updateUser(TaiKhoan userInfo);
    
    public boolean verifyAccount(String verificationPassWord, String resetPass);

    public TaiKhoan saveUser(TaiKhoan user, String url);

    TaiKhoan getTaiKhoanByName(String name);
}
