package com.example.sd40.service.KhachHang.impl;

import com.example.sd40.entity.Gio_hang.GioHangChiTiet;
import com.example.sd40.entity.Hoa_don.HoaDon;
import com.example.sd40.entity.Hoa_don.HoaDonChiTiet;
import com.example.sd40.entity.KhachHang.DiaChiChiTiet;
import com.example.sd40.entity.KhachHang.Dia_Chi;
import com.example.sd40.entity.KhachHang.KhachHang;
import com.example.sd40.entity.KhachHang.tinhThanhPho;
import com.example.sd40.entity.San_pham.ChiTietSanPham;
import com.example.sd40.repository.HoaDon.HoaDonChiTietRepository;
import com.example.sd40.repository.HoaDon.HoaDonRepository;
import com.example.sd40.repository.KhachHang.*;
import com.example.sd40.service.KhachHang.KhachHangCusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class KhachHangCusImpl implements KhachHangCusService {
    @Autowired
    KhachHangCusRepository khachHangCusRepository;
    @Autowired
    HoaDonRepository hoaDonRepository;
    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;
    @Autowired
    DiaChiRepository diaChiRepository;
    @Autowired
    TinhRepository tinhRepository;
    @Autowired
    DiaChiChiTietRepository diaChiChiTietRepository;
    @Autowired
    KhachHangRepository khachHangRepository;
    @Override
    public KhachHang login(String taiKhoan, String matKhau) {
        return khachHangCusRepository.login(taiKhoan,matKhau);
    }

    @Override
    public List<KhachHang> getAll() {
        return khachHangCusRepository.findAll();
    }

    @Override
    public List<GioHangChiTiet> detailSPGioHang(Long idKH) {
        return khachHangCusRepository.detailSPGioHang(idKH);
    }

    @Override
    public DiaChiChiTiet layDiaChi(Long idKH) {
        return khachHangCusRepository.layDiaChi(idKH);
    }

    @Override
    public List<Object> getVoucherHoaDon() {
        return khachHangCusRepository.getVoucherHoaDon();
    }

    @Override
    public Object layIDTinh() {
        return khachHangCusRepository.layIDTinh();
    }

    @Override
    public void saveHD(Integer trangThai, Date ngayTao, BigDecimal tongTien, Integer phanTramKhuyenMai, Long idVoucher, String ghiChu, BigDecimal tongTienGiam, Long phuongThucThanhToanID, String tenNguoiNhan, String sdtNguoiNhan, String diaChiNguoiNhan, BigDecimal tongTienSanPhamChuaGiam,BigDecimal phiShip,Date ngayCapNhat,String ma) {
        khachHangCusRepository.saveHD(trangThai,ngayTao,tongTien,phanTramKhuyenMai,idVoucher,ghiChu,tongTienGiam,phuongThucThanhToanID,tenNguoiNhan,sdtNguoiNhan,diaChiNguoiNhan,tongTienSanPhamChuaGiam,phiShip,ngayCapNhat,ma);
    }


    @Override
    public Long idHoaDonMoiTao() {
        return khachHangCusRepository.idHoaDonMoiTao();
    }


    @Override
    public void addHDCT(HoaDonChiTiet hoaDonChiTiet) {
        BigDecimal giaDaGiam = hoaDonChiTiet.getChiTietSanPham().getChiTietSanPhamMauSacHinhAnh().getGiaHienHanh().multiply(BigDecimal.valueOf(100-hoaDonChiTiet.getChiTietSanPham().getChiTietSanPhamMauSacHinhAnh().getSanPham().getGiamGIa().getMucGiam()));
        BigDecimal giaGiam = giaDaGiam.divide(BigDecimal.valueOf(100));
        HoaDonChiTiet hoaDonChiTiet1 = new HoaDonChiTiet();
        hoaDonChiTiet1.setHoaDon(hoaDonChiTiet.getHoaDon());
        hoaDonChiTiet1.setSoLuong(hoaDonChiTiet.getSoLuong());
        hoaDonChiTiet1.setChiTietSanPham(hoaDonChiTiet.getChiTietSanPham());
        hoaDonChiTiet1.setTrangThai(hoaDonChiTiet.getTrangThai());
        hoaDonChiTiet1.setGiaHienHanh(hoaDonChiTiet.getChiTietSanPham().getChiTietSanPhamMauSacHinhAnh().getGiaHienHanh());
        hoaDonChiTiet1.setGiaDaGiam(giaGiam);
        hoaDonChiTietRepository.save(hoaDonChiTiet1);
    }

    @Override
    public HoaDon detailHoaDon(Long idHD) {
        return hoaDonRepository.getById(idHD);
    }

    @Override
    public Long idVoucherOk() {
        return khachHangCusRepository.idVoucherOk();
    }

    @Override
    public List<DiaChiChiTiet> danhSachDiaChi(Long idKH) {
        return khachHangCusRepository.danhSachDiaChi(idKH);
    }

    @Override
    public void addDiaChi(Dia_Chi diaChi) {
        diaChiRepository.save(diaChi);
    }

    @Override
    public tinhThanhPho detailTinhThanhPho(Long id) {
        return tinhRepository.getById(id);
    }

    @Override
    public Long idDiaChiMoiNhat() {
        return khachHangCusRepository.idDiaChiMoiNhat();
    }

    @Override
    public void addDiaChiChiTiet(DiaChiChiTiet diaChiChiTiet) {
        diaChiChiTietRepository.save( diaChiChiTiet);
    }

    @Override
    public KhachHang detailKhachHang(Long id) {
        return khachHangRepository.getById(id);
    }

    @Override
    public DiaChiChiTiet detailDiaChiChiTiet(Long id) {
        return diaChiChiTietRepository.getById(id);
    }

    @Override
    public Dia_Chi detailDiaChi(Long id) {
        return diaChiRepository.getById(id);
    }

    @Override
    public List<Object[]> listDiaCHiMoiNhat(Long idKH) {
        return khachHangCusRepository.listDiaCHiMoiNhat(idKH);
    }

    @Override
    public void deleteDCCT(Long id) {
        diaChiChiTietRepository.deleteById(id);
    }

    @Override
    public void updateMacDinhKhac(Long idDCCT,Long idKH) {
        khachHangCusRepository.updateMacDinhKhac(idDCCT, idKH);
    }

    @Override
    public void updateMacDinh(Long idDCCT) {
        khachHangCusRepository.updateMacDinh(idDCCT);
    }

    @Override
    public void deleteDiaChi(Long id) {
        diaChiRepository.deleteById(id);
    }

    @Override
    public void saveHDKhachHang(Integer trangThai, Date ngayTao, BigDecimal tongTien, Integer phanTramKhuyenMai, Long idVoucher, String ghiChu, BigDecimal tongTienGiam, Long phuongThucThanhToanID, String tenNguoiNhan, String sdtNguoiNhan, String diaChiNguoiNhan, BigDecimal tongTienSanPhamChuaGiam, BigDecimal phiShip, Long idKhachHang,Date ngayCapNhat,String ma,Date ngayThanhToan) {
        khachHangCusRepository.saveHDKhachHang(trangThai,ngayTao,tongTien,phanTramKhuyenMai,idVoucher,ghiChu,tongTienGiam,phuongThucThanhToanID,tenNguoiNhan,sdtNguoiNhan,diaChiNguoiNhan,tongTienSanPhamChuaGiam,phiShip,idKhachHang,ngayCapNhat,ma,ngayThanhToan);

    }

    @Override
    public List<HoaDonChiTiet> listHDCT(Long idHD) {
        return khachHangCusRepository.listHDCT(idHD);
    }

    @Override
    public List<HoaDon> listHoaDon(Long idKH, Integer trangThai) {
        return khachHangCusRepository.listHoaDon(idKH,trangThai);
    }

    @Override
    public List<Object[]> listHDCTByHoaDon(Long idHD) {
        return khachHangCusRepository.listHDCTByHoaDon(idHD);
    }


    @Override
    public List<HoaDon> getAllHDByIdKhachHang(Long idkh) {
        return khachHangCusRepository.getAllHDByIdKhachHang(idkh);
    }

    @Override
    public List<HoaDonChiTiet> fidHDCTByHD(Long idhd) {
        return khachHangCusRepository.fidHDCTByHD(idhd);
    }

    @Override
    public List<ChiTietSanPham> fidCTSPByHD(Long idhd) {
        return khachHangCusRepository.fidCTSPByHD(idhd);
    }

    @Override
    public void doiMatKhau(String taiKhoan, String matKhau, Long idKH) {
        khachHangCusRepository.doiMatKhau(taiKhoan,matKhau,idKH);
    }

    @Override
    public List<KhachHang> checkTaiKhoan(Long idkh, String taiKhoan) {
        return khachHangCusRepository.checkTaiKhoan(idkh,taiKhoan);
    }

    @Override
    public void updateKhachHang(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
    }

    @Override
    public List<KhachHang> getAllKhachHang() {
        return khachHangRepository.findAll();
    }

    @Override
    public List<KhachHang> checlTaiKhoanAdd(String taiKhoan) {
        return khachHangCusRepository.checlTaiKhoanAdd(taiKhoan);
    }

    @Override
    public void capNhatSoLuongSPSauKhiXoa(Integer soLuong, Long idctsp) {
        khachHangCusRepository.capNhatSoLuongSPSauKhiXoa(soLuong,idctsp);
    }

    @Override
    public void HuyHoaDon(Long idhd, String lyDo,Date ngayCapNhat) {
        khachHangCusRepository.HuyHoaDon(idhd,lyDo,ngayCapNhat);
    }

    @Override
    public void hoanThanhHoaDon(Long idhd,Date ngayThanhToan,Date ngayCapNhat) {
        khachHangCusRepository.hoanThanhHoaDon(idhd,ngayThanhToan,ngayCapNhat);
    }

    @Override
    public void capNhatSoLuongSPDaBan(Integer soLuong, Long idSP) {
        khachHangCusRepository.capNhatSoLuongSPDaBan(soLuong,idSP);
    }

    @Override
    public List<Object[]> soLuongDaBan(Long idHD) {
        return khachHangCusRepository.soLuongDaBan(idHD);
    }

    @Override
    public Long idKhachHangMoiTao() {
        return khachHangCusRepository.idKhachHangMoiTao();
    }

    @Override
    public String MaHDCuoi() {
        return khachHangCusRepository.MaHDCuoi();
    }

    @Override
    public KhachHang quenMatKhau(String email) {
        return khachHangCusRepository.quenMatKhau(email);
    }

    @Override
    public KhachHang KtraEmail(String email, Long id) {
        return khachHangCusRepository.KtraEmail(email,id);
    }

    @Override
    public void hoanThanhHoaDonVNPAY(Long idhd, Date ngayCapNhat) {
        khachHangCusRepository.hoanThanhHoaDonVNPAY(idhd,ngayCapNhat);
    }


}
