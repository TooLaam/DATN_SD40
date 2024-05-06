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
            <c:otherwise><div class="header3" style="background-color: red"><p>Đơn hàng đã hủy</p></div></c:otherwise>
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
            <c:choose>
                <c:when test="${HD.phuongThucThanhToan.id == 1}">
                    <c:choose>
                        <c:when test="${HD.trangThai == 0 || HD.trangThai == 1}">
                            <div class="btnTrangThai">
                                <a  id="btnHuy">Hủy hóa đơn</a>
                            </div>

                        </c:when>
                        <c:when test="${HD.trangThai==3}">
                            <div class="btnTrangThai">
                                <a onclick="hoanThanhHoaDon()" id="btnThanhCong">Đã nhận được hàng</a>
                            </div>
                        </c:when>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <c:if test="${HD.trangThai==3}">
                        <div class="btnTrangThai">
                            <a onclick="hoanThanhHoaDonVNPAY()" >Đã nhận được hàng</a>
                        </div>
                    </c:if>
                </c:otherwise>
            </c:choose>

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
<div id="hiddenForm" >
    <h5 style="color: black">Lý do hủy đơn hàng</h5>
    <span id="closeButton" class="closeButton">&times;</span>
    <div class="voucher">

        <textarea class="long-input" id="lyDo" placeholder="Nhập lý do hủy đơn hàng của bạn ở đây..."></textarea>
    </div>
    <button style="background-color: red" onclick="huyHoaDon()" id="submitButton">Xác nhận hủy đơn hàng</button>
</div>
<script>
    document.getElementById('btnHuy').addEventListener('click', function(event) {
        event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ a

        var hiddenForm = document.getElementById('hiddenForm');
        if (hiddenForm.style.display === 'none') {
            hiddenForm.style.display = 'block';
        } else {
            hiddenForm.style.display = 'none';
        }
    });

    document.getElementById('closeButton').addEventListener('click', function() {
        document.getElementById('hiddenForm').style.display = 'none';
    });
    function huyHoaDon() {
        var lyDo = document.getElementById('lyDo').value;
        if (lyDo == '') {
            alert("Vui lòng nhập lý do")
            return;
        }
        $.ajax({
            type: "POST",
            url: "/bill/huyHoaDon/"+${HD.id},
            data:{
                lyDo: lyDo
            },
            success: function(response) {
                // Xử lý phản hồi từ controller nếu cần
                alert("Hủy đơn hàng thành công !!!")
                window.location.href = "/bill/detailHoaDon/"+${HD.id};
            },
            error: function(xhr, status, error) {
                console.error("Đã xảy ra lỗi khi gửi dữ liệu: " + error);
            }
        });
    };

    function hoanThanhHoaDon() {
        var cf = confirm("Bạn chắc chắn đã nhận được hàng ???")
        if (cf == true){
            $.ajax({
                type: "POST",
                url: "/bill/hoanThanhHoaDonCus/"+${HD.id},
                success: function(response) {
                    window.location.href = "/bill/detailHoaDon/"+${HD.id};
                },
                error: function(xhr, status, error) {
                    console.error("Đã xảy ra lỗi khi gửi dữ liệu: " + error);
                }
            });
        }
    };
    function hoanThanhHoaDonVNPAY() {
        var cf = confirm("Bạn chắc chắn đã nhận được hàng ???")
        if (cf == true){
            $.ajax({
                type: "POST",
                url: "/bill/hoanThanhHoaDonCusVNPAY/"+${HD.id},
                success: function(response) {
                    window.location.href = "/bill/detailHoaDon/"+${HD.id};
                },
                error: function(xhr, status, error) {
                    console.error("Đã xảy ra lỗi khi gửi dữ liệu: " + error);
                }
            });
        }
    };
</script>
</body>
</html>
