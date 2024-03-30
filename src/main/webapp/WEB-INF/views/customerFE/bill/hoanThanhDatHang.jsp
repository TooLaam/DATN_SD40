<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<head>
    <link href="/assets/vendor/simple-datatables/style.css" rel="stylesheet">
    <style>
        <%@include file="style.css" %>
    </style>
</head>

<br>
<div class="container">
    <div class="frame">
        <p style=" padding-top: 5px;background-color: #00575C;border: 1px solid black;border-radius: 7px;color: white">Xác nhận đơn hàng</p>
        <div style="margin-top: 20px" class="row">
           <h3>Bạn đã đặt đơn hàng có mã hóa đơn ${hoaDon.maHoaDon} thành công</h3>
            <br>


            <div style="margin-top: 20px" class="productContainer" >

                <img src="/assets/img/product/${ctsp.chiTietSanPhamMauSacHinhAnh.hinhAnh}"  width="320px" height="450px" class="productImage">
                <div class="productInfo">
                    <h4>Sản phẩm</h4>
                    <span class="productName">Tên sản phẩm: ${ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.ten}</span>
                    <c:choose>
                        <c:when test="${ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam == 0 || ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam == null}">
                            <span class="productPrice">Giá:  <fmt:formatNumber value=" ${ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh}" pattern="###,###"/>đ    </span>
                        </c:when>
                        <c:otherwise>
                            <span class="productPrice">Giá:  <span style="text-decoration: line-through;color: darkgrey"> <fmt:formatNumber value=" ${ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh}" pattern="###,###"/>đ    </span>  <span> <fmt:formatNumber value=" ${(ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh*(100-ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam))/100}" pattern="###,###"/>đ   </span></span>
                        </c:otherwise>
                    </c:choose>

                    <span id="productPriceT" style="display: none" class="productPriceT"> ${(ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh*(100-ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam))/100}  </span>
                    <span id="giaChuaGiamT" style="display: none" class="giaChuaGiamT"> ${(ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh)}  </span>
                    <span class="productKichCo">Kích cỡ: ${ctsp.kichCo.ten}</span>
                    <span class="productMauSac">Màu sắc: ${ctsp.chiTietSanPhamMauSacHinhAnh.mau_sac.ten}</span>
                    <span class="productMauSac">Số lượng: ${soLuong}</span>
                    <br>
                    <br>
                    <br>
                    <h4>Thông tin người nhận</h4>
                    <div>
                        <span style="margin-left: 20px"><strong>Tên người nhận: </strong></span><span>${hoaDon.tenNguoiNhan}</span>
                        <span style="margin-left: 20px"><strong>Số điện thoại người nhận: </strong></span><span>${hoaDon.sdtNguoiNhan}</span>
                        <span style="margin-left: 20px"><strong>Địa chỉ người nhận: </strong></span><span>${hoaDon.diaChiNguoiNhan}</span>
                    </div>
                    <br>
                    <br>
                    <br>
                    <h4>Thanh toán khi nhận hàng</h4>
                    <div>
                        <span style="margin-left: 20px"><strong>Tổng tiền sản phẩm: </strong></span><span><fmt:formatNumber value="${hoaDon.tongTienSanPhamChuaGiam}" pattern="###,###"/>đ  </span>
                        <span style="margin-left: 20px"><strong>Tổng giảm giá: </strong></span><span><fmt:formatNumber value="${hoaDon.tongTienGiam}" pattern="###,###"/>đ   </span>
                        <span style="margin-left: 20px"><strong>Số tiền thanh toán: </strong></span><span><fmt:formatNumber value="${hoaDon.tongTien}" pattern="###,###"/>đ   </span>
                        <span style="margin-left: 20px"><strong>Phí ship: </strong></span><span><fmt:formatNumber value="${hoaDon.phiShip}" pattern="###,###"/>đ   </span>
                    </div>


                </div>


            </div>
            <div style="margin-top: 30px;text-align: center">Bạn vui lòng chờ để shop xác nhận đơn nhé. Cảm ơn quý khách đã ủng hộ SD40 sport</div>
        </div>
    </div>
</div>