<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<head>
    <link href="/assets/vendor/simple-datatables/style.css" rel="stylesheet">
    <style>
        <%@include file="style2.css" %>
    </style>
</head>

<br>
<div class="container1">
    <div class="frame1">
        <div class="header">
            <p>Xác nhận đơn hàng</p>
        </div>
        <div class="row">
            <h3>Bạn đã đặt đơn hàng có mã hóa đơn ${hoaDon.maHoaDon} thành công</h3>
            <div class="productContainer1">
                <img src="/assets/img/product/${ctsp.chiTietSanPhamMauSacHinhAnh.hinhAnh}" width="520px" height="450px" class="productImage1">
                <div class="productInfo1">
                    <h4>Sản phẩm</h4>
                    <span class="productName1">Tên sản phẩm: ${ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.ten}</span>
                    <span class="productPrice1">
                            Giá hiện hành:
                            <fmt:formatNumber value="${ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh}" pattern="###,###"/>đ
                        </span>
                    <span class="productPrice1">
                            Giá đã giảm:
                            <fmt:formatNumber value="${(ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh*(100-ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam))/100}" pattern="###,###"/>đ
                        </span>
                    <span class="productKichCo1">Kích cỡ: ${ctsp.kichCo.ten}</span>
                    <span class="productMauSac1">Màu sắc: ${ctsp.chiTietSanPhamMauSacHinhAnh.mau_sac.ten}</span>
                    <span class="productMauSac1">Số lượng: ${soLuong}</span>
                    <h4>Thông tin người nhận</h4>
                    <div>
                        <span class="infoLabel1">Tên người nhận:</span><span class="infoValue1">${hoaDon.tenNguoiNhan}</span><br>
                        <span class="infoLabel1">Số điện thoại người nhận:</span><span class="infoValue1">${hoaDon.sdtNguoiNhan}</span><br>
                        <span class="infoLabel1">Địa chỉ người nhận:</span><span class="infoValue1">${hoaDon.diaChiNguoiNhan}</span><br>
                    </div>
                    <h4>Thanh toán khi nhận hàng</h4>
                    <div>
                        <span class="infoLabel1">Tổng tiền sản phẩm:</span><span class="infoValue1"><fmt:formatNumber value="${hoaDon.tongTienSanPhamChuaGiam}" pattern="###,###"/>đ</span><br>
                        <span class="infoLabel1">Tổng giảm giá:</span><span class="infoValue1"><fmt:formatNumber value="${hoaDon.tongTienGiam}" pattern="###,###"/>đ</span><br>
                        <span class="infoLabel1">Số tiền thanh toán:</span><span class="infoValue1"><fmt:formatNumber value="${hoaDon.tongTien}" pattern="###,###"/>đ</span><br>
                        <span class="infoLabel1">Phí ship:</span><span class="infoValue1"><fmt:formatNumber value="${hoaDon.phiShip}" pattern="###,###"/>đ</span><br>
                    </div>
                </div>
            </div>
            <div class="confirmationMessage1">Bạn vui lòng chờ để shop xác nhận đơn nhé. Cảm ơn quý khách đã ủng hộ SD40 sport</div>
        </div>
    </div>
</div>
