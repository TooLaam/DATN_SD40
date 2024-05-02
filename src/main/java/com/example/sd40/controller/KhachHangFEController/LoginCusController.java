package com.example.sd40.controller.KhachHangFEController;

import com.example.sd40.entity.Gio_hang.GioHang;
import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.Mail.MailStructure;
import com.example.sd40.service.GioHang.GioHangService;
import com.example.sd40.service.KhachHang.KhachHangCusService;
import com.example.sd40.service.MailService.MailService;
import jakarta.servlet.http.HttpSession;
//import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class LoginCusController {
    @Autowired
    KhachHangCusService khachHangCusService;
    @Autowired
    GioHangService gioHangService;
    @Autowired
    MailService mailService;



    @GetMapping("/login")
    public String loginCus(Model model,@RequestParam(defaultValue = "0",value = "idsp")Long idsp,@RequestParam(defaultValue = "0",value = "idms")Long idms,HttpSession session){
        session.setAttribute("idsp",idsp);
        session.setAttribute("idms",idms);
        model.addAttribute("view", "/login/index.jsp");
        return "/customerFE/login/index";
    }
    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("view", "/signup/index.jsp");
        return "/customerFE/signup/index";
    }


    @GetMapping("/logout")
    public String logout(Model model, HttpSession session){
//        model.addAttribute("view", "/signup/index.jsp");
        session.removeAttribute("khachHangCus");
        session.removeAttribute("idKhachHang");
        session.removeAttribute("idsp");
        session.removeAttribute("idms");
        session.removeAttribute("gioHangChiTiets");
        session.removeAttribute("listLong");
        return "redirect:/login";
    }


    @PostMapping("/loginOK")
    public String loginOK(@RequestParam("taiKhoan") String username,
                          @RequestParam("matKhau") String password,
                          HttpSession session,
                          Model model) {

        KhachHang checkLogin = khachHangCusService.login(username, password);
        if (checkLogin != null) {
            Long idsp = (Long) session.getAttribute("idsp");
            Long idms = (Long) session.getAttribute("idms");
            if (idms !=0 && idsp !=0){
                session.setAttribute("khachHangCus", checkLogin);
                session.setAttribute("idKhachHang", checkLogin.getId());
                return "redirect:/hienthiKCCus/"+idsp+"/"+idms;
            }
            if (idsp != 0 && idms == 0) {
                session.setAttribute("khachHangCus", checkLogin);
                session.setAttribute("idKhachHang", checkLogin.getId());
                return "redirect:/detailsanphamcus/"+idsp;
            }
            session.setAttribute("khachHangCus", checkLogin);
            session.setAttribute("idKhachHang", checkLogin.getId());
            Long id = (Long) session.getAttribute("idKhachHang");
            System.out.println(id);
            return "redirect:/home";
        } else {
            model.addAttribute("erCheckCustomer", "Tài khoản hoặc mật khẩu chưa chính xác !!!");
            model.addAttribute("view", "/login/index.jsp");
            return "/customerFE/login/index";
        }
    }
    Date currentDate = new Date(System.currentTimeMillis());
    @PostMapping("/addKhachHangCus")
    public String addKhacHangCus(@RequestParam("ten")String ten,
                                 @RequestParam("taiKhoan")String taiKhoan,
                                 @RequestParam("matKhau")String matKhau,
                                 @RequestParam("ngaySinh")String ngaySinh,
                                 @RequestParam("gioiTinh")Integer gioiTinh,
                                 @RequestParam("email")String email,
                                 @RequestParam("sdt")String sdt
    ) throws ParseException {
        KhachHang khachHang = new KhachHang();
        khachHang.setNgaySinh(new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinh));
        khachHang.setSdt(sdt);
        khachHang.setGioiTinh(gioiTinh);
        khachHang.setEmail(email);
        khachHang.setHoTen(ten);
        khachHang.setTaiKhoan(taiKhoan);
        khachHang.setMatKhau(matKhau);
        khachHang.setNgayTao(currentDate);
        khachHang.setTrangThai(0);
        khachHang.setMa("KH"+(khachHangCusService.getAllKhachHang().size()+1));
        khachHangCusService.updateKhachHang(khachHang);


        Long idkh = khachHangCusService.idKhachHangMoiTao();
        GioHang gioHang = new GioHang();
        gioHang.setKhachHang(khachHangCusService.detailKhachHang(idkh));
        gioHang.setGhiChu("Giỏ hàng của "+khachHangCusService.detailKhachHang(idkh).getHoTen());
        gioHang.setNgayTao(currentDate);
        gioHang.setTrangThai(0);
        gioHangService.AddGioHang(gioHang);
        return "redirect:/login";
    }

    @PostMapping("/checkTaiKhoanAdd")
    public ResponseEntity<?> checkTaiKhoanAdd(
            @RequestParam("taiKhoan")String taiKhoan,
            @RequestParam("email")String email
    ) {
        try {
            List<KhachHang> khachHangs = khachHangCusService.checlTaiKhoanAdd(taiKhoan);
            KhachHang khachHang = khachHangCusService.quenMatKhau(email);
            if (!khachHangs.isEmpty()){
                return ResponseEntity.ok("ko");
            }else {
                if (khachHang != null){
                    return ResponseEntity.ok("email");
                }
                else {
                    return ResponseEntity.ok("ok");
                }
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi");
        }
    }

    @PostMapping("/quenMatKhau")
    public ResponseEntity<?> quenMatKhau(
            @RequestParam("email")String email
    ) {
        try {
            KhachHang khachHang = khachHangCusService.quenMatKhau(email);
            if (khachHang != null){
                String messeage = ("Xinn chào "+ khachHang.getMa()
                        + "\nSD40 sport gửi lại thông tin tài khoản của bạn nhé !"
                        + "\n Tên Khách Hàng " +  khachHang.getHoTen()
                        + "\n Ngày sinh : " +  khachHang.getNgaySinh()
                        + "\n Số điện thoại : " +  khachHang.getSdt()

                        + "\n Giới tính : " +  (khachHang.getGioiTinh()==0?"Nam":"Nữ")
                        + "\n Email : " +khachHang.getEmail()
                        + "\n Tài khoản:  " +  khachHang.getTaiKhoan()
                        + "\n Mật khẩu:   " + khachHang.getMatKhau()

                        + "\n Chúc bạn có một trải nghiệp tốt ở SD40 sport"

                );
                MailStructure mailStructure = new MailStructure();
                mailStructure.setMessage(messeage);
                mailStructure.setSubject("Đặt hàng thành công SD40 sport");
                mailStructure.setToEmail(email);
                mailService.sendMail(mailStructure);
                return ResponseEntity.ok("ok");
            }else {
                return ResponseEntity.ok("ko");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi");
        }
    }

}
