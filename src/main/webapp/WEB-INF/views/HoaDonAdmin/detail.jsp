<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <link href="/assets/vendor/simple-datatables/style.css" rel="stylesheet">
    <style>
        <%@include file="style1.css" %>
    </style>
</head>


<div class="pagetitle">
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item active"><h1>QUẢN LÝ HÓA ĐƠN</h1></li>
            <li style="margin-top: 7px" class="breadcrumb-item"><a href="/billadmin/index">Home</a></li>
            <li style="margin-top: 7px" class="breadcrumb-item"><a href="/billadmin/detailHoaDon/${HD.id}">Chi tiết hóa đơn</a></li>
        </ol>
    </nav>
</div>
<!-- End Page Title -->

<section class="section dashboard">
    <div class="container3">
        <div class="frame3">
            <c:choose>
                <c:when test="${HD.trangThai==0}"><div class="header3"><p>Đơn hàng đang chờ xác nhận</p></div></c:when>
                <c:when test="${HD.trangThai==1}"><div class="header3"><p>Đơn hàng đã được xác nhận</p></div></c:when>
                <c:when test="${HD.trangThai==2}"><div class="header3"><p>Đơn hàng đang chờ giao</p></div></c:when>
                <c:when test="${HD.trangThai==3}"><div class="header3"><p>Đơn hàng đang giao</p></div></c:when>
                <c:when test="${HD.trangThai==4}"><div class="header3"><p>Đơn hàng hoàn thành</p></div></c:when>
                <c:otherwise><div class="header3" style="background-color: red"><p>Đơn hàng đã hủy</p></div></c:otherwise>
            </c:choose>
            <br>
            <div style="display: flex">
                <span>
                      <h3>Đơn hàng ${HD.maHoaDon}</h3>
                </span>
                <span style="margin-left: 500px">
                     <c:choose>
                         <c:when test="${HD.trangThai==0}"><a class="btn btn-success" onclick="chuyenTrangThaiHoaDon()" style="font-size: 20px;font-weight: bold">Xác nhận đơn hàng</a></c:when>
                         <c:when test="${HD.trangThai==1}"><a class="btn btn-success" onclick="chuyenTrangThaiHoaDon()" style="font-size: 20px;font-weight: bold">Đơn hàng chờ giao</a></c:when>
                         <c:when test="${HD.trangThai==2}"><a class="btn btn-success" onclick="chuyenTrangThaiHoaDon()" style="font-size: 20px;font-weight: bold">Đơn hàng đang giao</a></c:when>
                         <c:when test="${HD.trangThai==3}"><a class="btn btn-success" onclick="chuyenTrangThaiHoaDon()" style="font-size: 20px;font-weight: bold">Hoàn thành đơn hàng</a></c:when>
                     </c:choose>

                </span>
            </div>

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
                                <fmt:formatNumber value="${sp.giaDaGiam}" pattern="###,###"/>đ
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
                <c:choose>
                    <c:when test="${HD.phuongThucThanhToan.id ==1}">
                        <div class="payment-info3">
                            <h4>Thanh toán khi nhận hàng</h4>
                            <p><strong>Tổng tiền sản phẩm:</strong> <fmt:formatNumber value="${HD.tongTienSanPhamChuaGiam}" pattern="###,###"/>đ</p>
                            <p><strong>Tổng giảm giá:</strong> <fmt:formatNumber value="${HD.tongTienGiam}" pattern="###,###"/>đ</p>
                            <p><strong>Phí ship:</strong> <fmt:formatNumber value="${HD.phiShip}" pattern="###,###"/>đ</p>
                            <p><strong>Số tiền thanh toán:</strong> <fmt:formatNumber value="${HD.tongTien}" pattern="###,###"/>đ</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="payment-info3">
                            <h4>Thanh toán VNPAY</h4>
                            <p><strong>Tổng tiền sản phẩm:</strong> <fmt:formatNumber value="${HD.tongTienSanPhamChuaGiam}" pattern="###,###"/>đ</p>
                            <p><strong>Tổng giảm giá:</strong> <fmt:formatNumber value="${HD.tongTienGiam}" pattern="###,###"/>đ</p>
                            <p><strong>Phí ship:</strong> <fmt:formatNumber value="${HD.phiShip}" pattern="###,###"/>đ</p>
                            <p><strong>Số tiền thanh toán:</strong> <fmt:formatNumber value="0" pattern="###,###"/>đ</p>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
            <div>
                    <c:if test="${HD.trangThai == 0 || HD.trangThai == 1||HD.trangThai==2||HD.trangThai==3}">
                        <div class="btnTrangThai">
                            <a id="btnHuy" onclick="btnHuy()">Hủy hóa đơn</a>
                        </div>
                    </c:if>
            </div>
        </div>
    </div>
    <div id="hiddenForm" >
        <h5 style="color: black">Lý do hủy đơn hàng</h5>
        <span onclick="btnDong()" id="closeButton" class="closeButton">&times;</span>
        <div class="voucher">

            <textarea class="long-input" id="lyDo" placeholder="Nhập lý do hủy đơn hàng của bạn ở đây..."></textarea>
        </div>
        <button style="background-color: red" onclick="huyHoaDon()" id="submitButton">Xác nhận hủy đơn hàng</button>
    </div>
</section>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
     function btnHuy() {
        var hiddenForm = document.getElementById('hiddenForm');
        if (hiddenForm.style.display === 'none') {
            hiddenForm.style.display = 'block';
        } else {
            hiddenForm.style.display = 'none';
        }
    };

    function btnDong() {
        document.getElementById('hiddenForm').style.display = 'none';
    };
    function huyHoaDon() {
        var lyDo = document.getElementById('lyDo').value;
        if (lyDo == '') {
            alert("Vui lòng nhập lý do")
            return;
        }
        $.ajax({
            type: "POST",
            url: "/billadmin/huyHoaDon/"+${HD.id},
            data:{
                lyDo: lyDo
            },
            success: function(response) {
                // Xử lý phản hồi từ controller nếu cần
                alert("Hủy đơn hàng thành công !!!")
                window.location.href = "/billadmin/detailHoaDon/"+${HD.id};
            },
            error: function(xhr, status, error) {
                console.error("Đã xảy ra lỗi khi gửi dữ liệu: " + error);
            }
        });
    };
    function chuyenTrangThaiHoaDon() {
       var cf = confirm("Bạn muốn <c:if test="${HD.trangThai==0}">Xác nhận đơn hàng</c:if><c:if test="${HD.trangThai==1}">Chuyển sang chế độ chờ giao</c:if><c:if test="${HD.trangThai==2}">Chuyển sang chế độ đang giao</c:if><c:if test="${HD.trangThai==3}">Hoàn thành đơn hàng</c:if> ???")
        if (cf == true){
        var trangThai = ${HD.trangThai};
        $.ajax({
            type: "POST",
            url: "/billadmin/chuyenTrangThaiHoaDon/"+${HD.id},
            data:{
                trangThai: trangThai
            },
            success: function(response) {
                // Xử lý phản hồi từ controller nếu cần
                alert("<c:if test="${HD.trangThai==0}">Xác nhận đơn hàng</c:if><c:if test="${HD.trangThai==1}">Chuyển sang chế độ chờ giao</c:if><c:if test="${HD.trangThai==2}">Chuyển sang chế độ đang giao</c:if><c:if test="${HD.trangThai==3}">Hoàn thành đơn hàng</c:if> thành công !!!")
                window.location.href = "/billadmin/detailHoaDon/"+${HD.id};
            },
            error: function(xhr, status, error) {
                console.error("Đã xảy ra lỗi khi gửi dữ liệu: " + error);
            }
        });
        } };



</script>