<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<head>
    <link href="/assets/vendor/simple-datatables/style.css" rel="stylesheet">
    <style>
        <%@include file="style3.css" %>
    </style>
</head>

<br>
<div class="container3">
    <div class="frame3">
        <c:choose>
            <c:when test="${HD.trangThai==0}"><div class="header3"><p>Đơn hàng đang chờ xác nhận</p></div></c:when>
            <c:when test="${HD.trangThai==1}"><div class="header3"><p>Đơn hàng đã được xác nhận</p></div></c:when>
            <c:when test="${HD.trangThai==2}"><div class="header3"><p>Đơn hàng đang chờ giao</p></div></c:when>
            <c:when test="${HD.trangThai==3}"><div class="header3"><p>Đơn hàng đang giao</p></div></c:when>
            <c:when test="${HD.trangThai==4}"><div class="header3"><p>Đơn hàng hoàn thành</p></div></c:when>
            <c:otherwise><div class="header3"><p>Đơn hàng đã hủy</p></div></c:otherwise>
        </c:choose>
        <br>
        <h3>Đơn hàng ${HD.maHoaDon}</h3>
        <br>
        <div class="conten3">
            <c:forEach var="sp" items="${HDCT}">
                <div class="product-container3">
                    <div class="product-image3">
                        <img src="/assets/img/product/${sp.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.hinhAnh}" alt="Product Image">
                    </div>
                    <div class="product-info3">
                        <h4>${sp.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.sanPham.ten}</h4>
                        <p class="product-price3">
                            <fmt:formatNumber value="${(sp.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.giaHienHanh*(100-sp.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam))/100}" pattern="###,###"/>đ
                        </p>
                        <p>Kích cỡ: ${sp.chiTietSanPham.kichCo.ten}</p>
                        <p>Màu sắc: ${sp.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.mau_sac.ten}</p>
                        <p>Số lượng: ${sp.soLuong}</p>
                    </div>
                </div>
            </c:forEach>
            <div class="recipient-info3">
                <h4>Thông tin người nhận</h4>
                <p><strong>Tên:</strong> ${HD.tenNguoiNhan}</p>
                <p><strong>Số điện thoại:</strong> ${HD.sdtNguoiNhan}</p>
                <p><strong>Địa chỉ:</strong> ${HD.diaChiNguoiNhan}</p>
            </div>
            <div class="payment-info3">
                <h4>Thanh toán khi nhận hàng</h4>
                <p><strong>Tổng tiền sản phẩm:</strong> <fmt:formatNumber value="${HD.tongTienSanPhamChuaGiam}" pattern="###,###"/>đ</p>
                <p><strong>Tổng giảm giá:</strong> <fmt:formatNumber value="${HD.tongTienGiam}" pattern="###,###"/>đ</p>
                <p><strong>Phí ship:</strong> <fmt:formatNumber value="${HD.phiShip}" pattern="###,###"/>đ</p>
                <p><strong>Số tiền thanh toán:</strong> <fmt:formatNumber value="${HD.tongTien}" pattern="###,###"/>đ</p>
            </div>
        </div>
        <c:choose>
            <c:when test="${HD.trangThai==0}">
                <div class="confirmation-message3">Bạn vui lòng chờ để shop xác nhận đơn nhé. Cảm ơn quý khách đã ủng hộ SD40 sport</div>
            </c:when>
            <c:when test="${HD.trangThai==5}">
                <div class="confirmation-message3">Cảm ơn quý khách đã theo dõi SD40 sport</div>
            </c:when>
            <c:otherwise>
                <div class="confirmation-message3">Cảm ơn quý khách đã ủng hộ SD40 sport</div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
