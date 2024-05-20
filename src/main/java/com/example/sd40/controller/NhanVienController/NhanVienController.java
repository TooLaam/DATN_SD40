package com.example.sd40.controller.NhanVienController;

import com.example.sd40.entity.NhanVien.NhanVien;
import com.example.sd40.service.NhanVien.NhanVienService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

/**
 * @author quynh
 */
@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {

    @Autowired
    NhanVienService nhanVienService;

    @GetMapping("/index")
    public String getAll(Model model, HttpSession session){
        NhanVien nhanVien = (NhanVien) session.getAttribute("nhanVien");
        if (nhanVien == null){
            return "redirect:/admin/login";
        }else {
            if (nhanVien.getChucVu().getId() == 2){
                return "redirect:/admin/login";
            }else {
                model.addAttribute("nhanvien",nhanVien);
                model.addAttribute("listNV",nhanVienService.getAllDesc());
                model.addAttribute("view","/NhanVien/index.jsp");
                return "index";
            }

        }

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<NhanVien> detail(@PathVariable("id")Long id){
        NhanVien nhanVien = nhanVienService.detail(id);
        if (nhanVien != null) {
            return new ResponseEntity<>(nhanVien, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    Date currentDate = new Date(System.currentTimeMillis());

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id")Long id,
                                    @RequestParam("hoTen")String ten,
                                    @RequestParam("ngaySinh")Date ngaySinh,
                                    @RequestParam("sdt")String sdt,
                                    @RequestParam("gioiTinh")Integer gioiTinh,
                                    @RequestParam("diaChi")String diaChi,
                                    @RequestParam("email")String email,
                                    @RequestParam("taiKhoan")String taiKhoan,
                                    @RequestParam("matKhau")String matKhau,
                                    @RequestParam("trangThai")Integer trangThai,
                                    @RequestParam("chucVu")Long chucVu
    ) throws ParseException {
        List<NhanVien> nhanViens = nhanVienService.findByUserNameUpdate(taiKhoan,id);
        if (!nhanViens.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        } else {
            NhanVien nhanVien = nhanVienService.detail(id);

            nhanVien.setHoTen(ten);
            nhanVien.setNgaySinh(ngaySinh);
            nhanVien.setSdt(sdt);
            nhanVien.setGioiTinh(gioiTinh);
            nhanVien.setDiaChi(diaChi);
            nhanVien.setEmail(email);
            nhanVien.setTaiKhoan(taiKhoan);
            nhanVien.setMatKhau(matKhau);
            nhanVien.setTrangThai(trangThai);
            nhanVien.setChucVu(nhanVienService.detailChucVu(chucVu));
            nhanVienService.save(nhanVien);
            return ResponseEntity.ok("ok");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestParam("hoTen")String ten,
            @RequestParam("ngaySinh")Date ngaySinh,
            @RequestParam("sdt")String sdt,
            @RequestParam("gioiTinh")Integer gioiTinh,
            @RequestParam("diaChi")String diaChi,
            @RequestParam("email")String email,
            @RequestParam("taiKhoan")String taiKhoan,
            @RequestParam("matKhau")String matKhau,
            @RequestParam("trangThai")Integer trangThai,
            @RequestParam("chucVu")Long chucVu
    ) throws ParseException {
        Long IDNVCuoi = nhanVienService.IdNvCuoi();
        String maNVMoi = "NV00"+(IDNVCuoi+1);

        List<NhanVien> nhanViens = nhanVienService.findByUserName(taiKhoan);
        if (!nhanViens.isEmpty()) {
            return ResponseEntity.ok("errTrungTen");
        } else {
            NhanVien nhanVien = new NhanVien();

            nhanVien.setMa(maNVMoi);
            nhanVien.setHoTen(ten);
            nhanVien.setNgaySinh(ngaySinh);
            nhanVien.setSdt(sdt);
            nhanVien.setGioiTinh(gioiTinh);
            nhanVien.setDiaChi(diaChi);
            nhanVien.setEmail(email);
            nhanVien.setTaiKhoan(taiKhoan);
            nhanVien.setMatKhau(matKhau);
            nhanVien.setTrangThai(trangThai);
            nhanVien.setChucVu(nhanVienService.detailChucVu(chucVu));
            nhanVienService.save(nhanVien);

            return ResponseEntity.ok("ok");
        }
    }


}
