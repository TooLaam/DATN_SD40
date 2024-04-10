package com.example.sd40.service.KhachHang;

import com.example.sd40.entity.KhachHang.HangThanhVien;
import com.example.sd40.repository.KhachHang.HangThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface HangThanhVienService {
   HangThanhVien detail(Long id);
}
