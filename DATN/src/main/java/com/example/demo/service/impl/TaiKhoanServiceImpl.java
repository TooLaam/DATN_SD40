package com.example.demo.service.impl;

import com.example.demo.entity.TaiKhoan;
import com.example.demo.service.TaiKhoanService;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
    @Override
    public String addUser(TaiKhoan userInfo) {
        return null;
    }

    @Override
    public String updateUser(TaiKhoan userInfo) {
        return null;
    }

    @Override
    public boolean verifyAccount(String verificationPassWord, String resetPass) {
        return false;
    }

    @Override
    public TaiKhoan saveUser(TaiKhoan user, String url) {
        return null;
    }

    @Override
    public TaiKhoan getTaiKhoanByName(String name) {
        return null;
    }
}
