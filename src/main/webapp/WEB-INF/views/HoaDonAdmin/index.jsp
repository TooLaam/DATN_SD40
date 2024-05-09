<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>

    <style>
        <%@include file="style.css" %>
    </style>
</head>

<div class="pagetitle">
    <h1>QUẢN LÝ HÓA ĐƠN</h1>
</div>
<!-- End Page Title -->

<section class="section dashboard">
    <div class="row">
        <div class="col-lg-12">
            <div class="tabs">
                <div class="tab active" data-tab="tab1">Tất cả(${tatCaSize})</div>
                <div class="tab" data-tab="tab2">Chờ xác nhận(${choXacNhanSize})</div>
                <div class="tab" data-tab="tab3">Xác nhận(${xacNhanSize})</div>
                <div class="tab" data-tab="tab4">Chờ giao(${choGiaoHangSize})</div>
                <div class="tab" data-tab="tab5">Đang giao(${dangGiaoHangSize})</div>
                <div class="tab" data-tab="tab6">Thành công(${hoanThanhSize})</div>
                <div class="tab" data-tab="tab7">Hủy(${huySize})</div>
            </div>

            <div class="tab-content" id="tab1">
                <c:choose>
                    <c:when test="${empty tatCa}">
                        <div class="order-item">
                            <div class="text-center" style="position: absolute; left: 60%; transform: translate(-50%, -50%);margin-top: 16px">
                                Không có đơn hàng nào !!!
                            </div></div>
                    </c:when>
                    <c:otherwise>
                        <div class="row">
                            <div class="col-12">
                                <div class="card recent-sales overflow-auto">
                                    <div class="card-body">
                                        <table class="table table-borderless datatable">
                                            <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Mã HD</th>
                                                <th>Ngày tạo</th>
                                                <th>Tổng tiền</th>
                                                <th>Phương thức thanh toán</th>
                                                <th>Tên người nhận</th>
                                                <th>SĐT</th>
                                                <th>Địa chỉ</th>
                                                <th>Trạng thái</th>
                                                <th>Action</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${tatCa}" var="mau" varStatus="tt">
                                                <tr>
                                                    <th scope="row">${tt.index+1}</th>
                                                    <td><strong>${mau.maHoaDon}</strong></td>
                                                    <td><fmt:formatDate value="${mau.ngayTao}" pattern="dd-MM-yyyy"/></td>
                                                    <td><fmt:formatNumber value="${mau.tongTien}" pattern="###,###"/></td>
                                                    <td><p style="color:${mau.phuongThucThanhToan.id==1?'red':'#00ff80'};">${mau.phuongThucThanhToan.ten}</p></td>
                                                    <td>${mau.tenNguoiNhan}</td>
                                                    <td>${mau.sdtNguoiNhan}</td>
                                                    <td>${mau.diaChiNguoiNhan}</td>
                                                    <td>
                                                        <p style="color:   <c:if test="${mau.trangThai == 0}">#7E72B1</c:if>
                                                        <c:if test="${mau.trangThai == 1}">#90A860</c:if>
                                                        <c:if test="${mau.trangThai == 2}">#A074A2</c:if>
                                                        <c:if test="${mau.trangThai == 3}">#E5F172</c:if>
                                                        <c:if test="${mau.trangThai == 4}">#8DC894</c:if>
                                                        <c:if test="${mau.trangThai == 5}">#EE6A6A</c:if>">


                                                        <c:if test="${mau.trangThai == 0}">Chờ xác nhận</c:if>
                                                        <c:if test="${mau.trangThai == 1}">Đã xác nhận</c:if>
                                                        <c:if test="${mau.trangThai == 2}">Chờ giao</c:if>
                                                        <c:if test="${mau.trangThai == 3}">Đang giao</c:if>
                                                        <c:if test="${mau.trangThai == 4}">Thành công</c:if>
                                                        <c:if test="${mau.trangThai == 5}">Hủy</c:if>
                                                        </p>
                                                    </td>
                                                    <td>
                                                        <a href="/billadmin/detailHoaDon/${mau.id}" class="btn btn-success">Chi tiết</a>
                                                        <a onclick="openFormNew(${mau.id})" class="btn btn-success">Lịch sử</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div><!-- End Recent Sales -->
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="tab-content" id="tab2">
                <c:choose>
                    <c:when test="${empty choXacNhan}">
                        <div class="order-item">
                            <div class="text-center" style="position: absolute; left: 60%; transform: translate(-50%, -50%);margin-top: 16px">
                                Không có đơn hàng nào !!!
                            </div></div>
                    </c:when>
                    <c:otherwise>
                        <div class="row">
                            <div class="col-12">
                                <div class="card recent-sales overflow-auto">
                                    <div class="card-body">
                                        <table class="table table-borderless datatable">
                                            <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Mã HD</th>
                                                <th>Ngày tạo</th>
                                                <th>Tổng tiền</th>
                                                <th>Phương thức thanh toán</th>
                                                <th>Tên người nhận</th>
                                                <th>SĐT</th>
                                                <th>Địa chỉ</th>
                                                <th>Trạng thái</th>
                                                <th>Action</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${choXacNhan}" var="mau" varStatus="tt">
                                                <tr>
                                                    <th scope="row">${tt.index+1}</th>
                                                    <td><strong>${mau.maHoaDon}</strong></td>
                                                    <td><fmt:formatDate value="${mau.ngayTao}" pattern="dd-MM-yyyy"/></td>
                                                    <td><fmt:formatNumber value="${mau.tongTien}" pattern="###,###"/></td>
                                                    <td><p style="color:${mau.phuongThucThanhToan.id==1?'red':'#00ff80'};">${mau.phuongThucThanhToan.ten}</p></td>
                                                    <td>${mau.tenNguoiNhan}</td>
                                                    <td>${mau.sdtNguoiNhan}</td>
                                                    <td>${mau.diaChiNguoiNhan}</td>
                                                    <td><p style="color: #7E72B1">Chờ xác nhận</p></td>
                                                    <td>
                                                        <a href="/billadmin/detailHoaDon/${mau.id}" class="btn btn-success">Chi tiết</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div><!-- End Recent Sales -->
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="tab-content" id="tab3">
                <c:choose>
                    <c:when test="${empty xacNhan}">
                        <div class="order-item">
                            <div class="text-center" style="position: absolute; left: 60%; transform: translate(-50%, -50%);margin-top: 16px">
                                Không có đơn hàng nào !!!
                            </div></div>
                    </c:when>
                    <c:otherwise>
                        <div class="row">
                            <div class="col-12">
                                <div class="card recent-sales overflow-auto">
                                    <div class="card-body">
                                        <table class="table table-borderless datatable">
                                            <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Mã HD</th>
                                                <th>Ngày tạo</th>
                                                <th>Tổng tiền</th>
                                                <th>Phương thức thanh toán</th>
                                                <th>Tên người nhận</th>
                                                <th>SĐT</th>
                                                <th>Địa chỉ</th>
                                                <th>Trạng thái</th>
                                                <th>Action</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${xacNhan}" var="mau" varStatus="tt">
                                                <tr>
                                                    <th scope="row">${tt.index+1}</th>
                                                    <td><strong>${mau.maHoaDon}</strong></td>
                                                    <td><fmt:formatDate value="${mau.ngayTao}" pattern="dd-MM-yyyy"/></td>
                                                    <td><fmt:formatNumber value="${mau.tongTien}" pattern="###,###"/></td>
                                                    <td><p style="color:${mau.phuongThucThanhToan.id==1?'red':'#00ff80'};">${mau.phuongThucThanhToan.ten}</p></td>
                                                    <td>${mau.tenNguoiNhan}</td>
                                                    <td>${mau.sdtNguoiNhan}</td>
                                                    <td>${mau.diaChiNguoiNhan}</td>
                                                    <td><p style="color: #90A860">Đã xác nhận</p></td>
                                                    <td>
                                                        <a href="/billadmin/detailHoaDon/${mau.id}" class="btn btn-success">Chi tiết</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div><!-- End Recent Sales -->
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="tab-content" id="tab4">
                <c:choose>
                    <c:when test="${empty choGiaoHang}">
                        <div class="order-item">
                            <div class="text-center" style="position: absolute; left: 60%; transform: translate(-50%, -50%);margin-top: 16px">
                                Không có đơn hàng nào !!!
                            </div></div>
                    </c:when>
                    <c:otherwise>
                        <div class="row">
                            <div class="col-12">
                                <div class="card recent-sales overflow-auto">
                                    <div class="card-body">
                                        <table class="table table-borderless datatable">
                                            <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Mã HD</th>
                                                <th>Ngày tạo</th>
                                                <th>Tổng tiền</th>
                                                <th>Phương thức thanh toán</th>
                                                <th>Tên người nhận</th>
                                                <th>SĐT</th>
                                                <th>Địa chỉ</th>
                                                <th>Trạng thái</th>
                                                <th>Action</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${choGiaoHang}" var="mau" varStatus="tt">
                                                <tr>
                                                    <th scope="row">${tt.index+1}</th>
                                                    <td><strong>${mau.maHoaDon}</strong></td>
                                                    <td><fmt:formatDate value="${mau.ngayTao}" pattern="dd-MM-yyyy"/></td>
                                                    <td><fmt:formatNumber value="${mau.tongTien}" pattern="###,###"/></td>
                                                    <td><p style="color:${mau.phuongThucThanhToan.id==1?'red':'#00ff80'};">${mau.phuongThucThanhToan.ten}</p></td>
                                                    <td>${mau.tenNguoiNhan}</td>
                                                    <td>${mau.sdtNguoiNhan}</td>
                                                    <td>${mau.diaChiNguoiNhan}</td>
                                                    <td><p style="color: #A074A2">Chờ giao hàng</p></td>
                                                    <td>
                                                        <a href="/billadmin/detailHoaDon/${mau.id}" class="btn btn-success">Chi tiết</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div><!-- End Recent Sales -->
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="tab-content" id="tab5">
                <c:choose>
                    <c:when test="${empty dangGiaoHang}">
                        <div class="order-item">
                            <div class="text-center" style="position: absolute; left: 60%; transform: translate(-50%, -50%);margin-top: 16px">
                                Không có đơn hàng nào !!!
                            </div></div>
                    </c:when>
                    <c:otherwise>
                        <div class="row">
                            <div class="col-12">
                                <div class="card recent-sales overflow-auto">
                                    <div class="card-body">
                                        <table class="table table-borderless datatable">
                                            <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Mã HD</th>
                                                <th>Ngày tạo</th>
                                                <th>Tổng tiền</th>
                                                <th>Phương thức thanh toán</th>
                                                <th>Tên người nhận</th>
                                                <th>SĐT</th>
                                                <th>Địa chỉ</th>
                                                <th>Trạng thái</th>
                                                <th>Action</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${dangGiaoHang}" var="mau" varStatus="tt">
                                                <tr>
                                                    <th scope="row">${tt.index+1}</th>
                                                    <td><strong>${mau.maHoaDon}</strong></td>
                                                    <td><fmt:formatDate value="${mau.ngayTao}" pattern="dd-MM-yyyy"/></td>
                                                    <td><fmt:formatNumber value="${mau.tongTien}" pattern="###,###"/></td>
                                                    <td><p style="color:${mau.phuongThucThanhToan.id==1?'red':'#00ff80'};">${mau.phuongThucThanhToan.ten}</p></td>
                                                    <td>${mau.tenNguoiNhan}</td>
                                                    <td>${mau.sdtNguoiNhan}</td>
                                                    <td>${mau.diaChiNguoiNhan}</td>
                                                    <td><p style="color: #E5F172">Đang giao hàng</p></td>
                                                    <td>
                                                        <a href="/billadmin/detailHoaDon/${mau.id}" class="btn btn-success">Chi tiết</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div><!-- End Recent Sales -->
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="tab-content" id="tab6">
                <c:choose>
                    <c:when test="${empty hoanThanh}">
                        <div class="order-item">
                            <div class="text-center" style="position: absolute; left: 60%; transform: translate(-50%, -50%);margin-top: 16px">
                                Không có đơn hàng nào !!!
                            </div></div>
                    </c:when>
                    <c:otherwise>
                        <div class="row">
                            <div class="col-12">
                                <div class="card recent-sales overflow-auto">
                                    <div class="card-body">
                                        <table class="table table-borderless datatable">
                                            <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Mã HD</th>
                                                <th>Ngày tạo</th>
                                                <th>Tổng tiền</th>
                                                <th>Phương thức thanh toán</th>
                                                <th>Tên người nhận</th>
                                                <th>SĐT</th>
                                                <th>Địa chỉ</th>
                                                <th>Trạng thái</th>
                                                <th>Action</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${hoanThanh}" var="mau" varStatus="tt">
                                                <tr>
                                                    <th scope="row">${tt.index+1}</th>
                                                    <td><strong>${mau.maHoaDon}</strong></td>
                                                    <td><fmt:formatDate value="${mau.ngayTao}" pattern="dd-MM-yyyy"/></td>
                                                    <td><fmt:formatNumber value="${mau.tongTien}" pattern="###,###"/></td>
                                                    <td><p style="color:${mau.phuongThucThanhToan.id==1?'red':'#00ff80'};">${mau.phuongThucThanhToan.ten}</p></td>
                                                    <td>${mau.tenNguoiNhan}</td>
                                                    <td>${mau.sdtNguoiNhan}</td>
                                                    <td>${mau.diaChiNguoiNhan}</td>
                                                    <td><p style="color: #8DC894">Thành công</p></td>
                                                    <td>
                                                        <a href="/billadmin/detailHoaDon/${mau.id}" class="btn btn-success">Chi tiết</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div><!-- End Recent Sales -->
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="tab-content" id="tab7">
                <c:choose>
                    <c:when test="${empty huy}">
                        <div class="order-item">
                            <div class="text-center" style="position: absolute; left: 60%; transform: translate(-50%, -50%);margin-top: 16px">
                                Không có đơn hàng nào !!!
                            </div></div>
                    </c:when>
                    <c:otherwise>
                        <div class="row">
                            <div class="col-12">
                                <div class="card recent-sales overflow-auto">
                                    <div class="card-body">
                                        <table class="table table-borderless datatable">
                                            <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Mã HD</th>
                                                <th>Ngày tạo</th>
                                                <th>Tổng tiền</th>
                                                <th>Phương thức thanh toán</th>
                                                <th>Tên người nhận</th>
                                                <th>SĐT</th>
                                                <th>Địa chỉ</th>
                                                <th>Trạng thái</th>
                                                <th>Action</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${huy}" var="mau" varStatus="tt">
                                                <tr>
                                                    <th scope="row">${tt.index+1}</th>
                                                    <td><strong>${mau.maHoaDon}</strong></td>
                                                    <td><fmt:formatDate value="${mau.ngayTao}" pattern="dd-MM-yyyy"/></td>
                                                    <td><fmt:formatNumber value="${mau.tongTien}" pattern="###,###"/></td>
                                                    <td><p style="color:${mau.phuongThucThanhToan.id==1?'red':'#00ff80'};">${mau.phuongThucThanhToan.ten}</p></td>
                                                    <td>${mau.tenNguoiNhan}</td>
                                                    <td>${mau.sdtNguoiNhan}</td>
                                                    <td>${mau.diaChiNguoiNhan}</td>
                                                    <td><p style="color: #EE6A6A">Hủy</p></td>
                                                    <td>
                                                        <a href="/billadmin/detailHoaDon/${mau.id}" class="btn btn-success">Chi tiết</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div><!-- End Recent Sales -->
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>

    </div>
</section>
<form id="subForm">
    <button id="closeBtn" onclick="closeFormNew()" type="button">&times;</button> <!-- Nút đóng -->
    <h2 >Lịch sử thao tác</h2>
    <div class="table-container">
        <table id="dataTable" class="sub-table">
            <thead>
            <tr>
                <th>Mã hóa đơn</th>
                <th>Thao tác</th>
                <th>Mã nhân viên</th>
                <th>Tên nhân viên</th>
                <th>Ngày</th>
            </tr>
            </thead>
            <tbody id="lichSu">
            </tbody>
        </table>
    </div>
</form>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var defaultTab = document.querySelector('.tab');
        defaultTab.classList.add('active');
        var defaultTabId = defaultTab.getAttribute('data-tab');
        document.getElementById(defaultTabId).classList.add('active');

        var tabs = document.querySelectorAll('.tab');
        tabs.forEach(function(tab) {
            tab.addEventListener('click', function() {
                var tabId = this.getAttribute('data-tab');
                openTab(tabId);
            });
        });
    });

    function openTab(tabId) {
        var tabContents = document.querySelectorAll('.tab-content');
        tabContents.forEach(function(tabContent) {
            tabContent.classList.remove('active');
        });
        document.getElementById(tabId).classList.add('active');

        var tabs = document.querySelectorAll('.tab');
        tabs.forEach(function(tab) {
            tab.classList.remove('active');
        });
        document.querySelector(".tab[data-tab='" + tabId + "']").classList.add("active");
    }
    function lichSu(idhd){

    }

    function openFormNew(idhd) {
        document.getElementById("subForm").style.display = "block";
        $('#lichSu').empty();
        $.ajax({
            type: "GET",
            url: "/billadmin/lichSu/" +idhd,
            success: function (response) {

                $.each(response, function (index, data) {
                    var maHD = data.hoaDon.maHoaDon;
                    var thaoTac = data.thaoTac;
                    var maNV = data.nhanVien ? data.nhanVien.ma : "N/A"; // Kiểm tra nếu nhanVien không null
                    var tenNV = data.nhanVien ? data.nhanVien.hoTen : "N/A"; // Kiểm tra nếu nhanVien không null
                    var ngay = data.ngayTao;

                    var linkHTML = ' <tr> <td>'+maHD+'</td>' +
                        ' <td>'+thaoTac+'</td>' +
                        ' <td>'+maNV+'</td>' +
                        ' <td>'+tenNV+'</td>' +
                        ' <td>'+chuyendoingay(ngay)+'</td> </tr>';

                    $('#lichSu').append(linkHTML);

                });
            },
            error: function (xhr, status, error) {
                // Xử lý lỗi
                console.error("Error occurred while fetching data: " + error);
            }
        });

    };

    function closeFormNew() {
        document.getElementById("subForm").style.display = "none";
    };

    function chuyendoingay(dateString) {
        // Chuyển đổi chuỗi thành đối tượng Date
        var dateObject = new Date(dateString);

        // Lấy các giá trị
        var year = dateObject.getFullYear();
        var month = dateObject.getMonth() + 1; // Tháng bắt đầu từ 0 nên cần cộng thêm 1
        var day = dateObject.getDate();
        var hours = dateObject.getHours();
        var minutes = dateObject.getMinutes();

        // Format các giá trị
        month = month < 10 ? '0' + month : month; // Thêm số 0 phía trước nếu tháng nhỏ hơn 10
        day = day < 10 ? '0' + day : day; // Thêm số 0 phía trước nếu ngày nhỏ hơn 10
        hours = hours < 10 ? '0' + hours : hours; // Thêm số 0 phía trước nếu giờ nhỏ hơn 10
        minutes = minutes < 10 ? '0' + minutes : minutes; // Thêm số 0 phía trước nếu phút nhỏ hơn 10


        // Trả về chuỗi định dạng ngày/tháng/năm giờ/phút/giây
        return day + "/" + month + "/" + year + " " + hours + ":" + minutes;
    }
</script>
