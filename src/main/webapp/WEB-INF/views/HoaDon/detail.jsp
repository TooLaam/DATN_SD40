<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<div class="pagetitle">
    <h1>Chi tiết sản phẩm</h1>
    <nav>
        <%--        <ol class="breadcrumb">--%>
        <%--            <li class="breadcrumb-item"><a href="/statisticsResult">Home</a></li>--%>
        <%--            <li class="breadcrumb-item active">Overview</li>--%>
        <%--            <li class="breadcrumb-item active">Product</li>--%>
        <%--        </ol>--%>
    </nav>
</div>
<!-- End Page Title -->

<section class="section dashboard">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <h4 class="row">Lịch sử</h4>
                        <div class="row">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Trạng thái</th>
                                    <th scope="col">Ngày</th>
                                    <th scope="col">Ghi chú</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${lichSus}" var="lichSu" varStatus="tt">
                                    <tr>
                                        <th scope="row">${tt.index + 1}</th>
                                        <td>${lichSu.status == 0 ? "Chờ xác nhận" : lichSu.status == 1 ? "Xác nhận" : lichSu.status == 2 ? "Chờ vận chuyển" : lichSu.status == 3 ? "Vận chuyển" : lichSu.status == 4 ? "Hoàn thành" : "Hủy"}</td>
                                        <td id="formattedDate_${tt.index}"></td>
                                        <td>${lichSu.note}</td>
                                    </tr>
                                    <script>
                                        document.addEventListener('DOMContentLoaded', function() {
                                            var ngayTao = ${lichSu.ngayTao};
                                            var formattedDate = "";
                                            if (ngayTao) {
                                                formattedDate = moment(ngayTao).format("DD/MM/YYYY");
                                            }
                                            document.getElementById('formattedDate_${tt.index}').textContent = formattedDate;
                                        });
                                    </script>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <h4 class="row">Hóa Đơn</h4>
                        <div class="row">
                            <div class="col-6">Mã hóa đơn: ${hoaDon.maHoaDon}</div>
                            <div class="col-6">Tên Khách hàng: ${hoaDon.nguoiNhan}</div>
                            <div class="col-6">Số điện thoại: ${hoaDon.sdtNguoiNhan}</div>
                            <div class="col-6">Email: ${hoaDon.emailNguoiNhan}</div>
                            <div class="col-6">Địa chỉ: ${hoaDon.diaChiNguoiNhan}</div>
                            <div class="col-6">Phương Thức thanh toán: ${hoaDon.phuongThucThanhToan.ten  }</div>
                            <div class="col-6">Trạng thái thanh toán: ${hoaDon.phuongThucThanhToan.trangThai == 0 ? "trả sau": "Trả trước"  }</div>
                            <div class="col-6">Nghi chú: ${hoaDon.ghiChu}</div>
<%--                            <div class="col-6">Ngày nhận dự kiến: ${moment(hoaDon.ngayThanhToan * 1000).format('DD/MM/YYYY')}</div>--%>
                        </div>
                    </div>
                    <div class="row">
                        <h4 class="row">Sản phẩm</h4>
                        <div class="row">
                            <table class="table table-borderless datatable">
                                <thead>
                                <tr>
                                    <th>Hinh ảnh</th>
                                    <th>Tên</th>
                                    <th>Kích cỡ</th>
                                    <th>Màu sắc</th>
                                    <th>số lượng</th>
                                    <th>giá</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${sanPhams}" var="sanPham" varStatus="tt">
                                    <tr>
                                        <td>
                                            <img src="/assets/img/product/${sanPham.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.hinhAnh}" style="padding-top: 60px" width="320px" height="450px">
                                        </td>
                                        <td>${sanPham.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.sanPham.ten}</td>
                                        <td>${sanPham.chiTietSanPham.kichCo.ten}</td>
                                        <td>${sanPham.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.mau_sac.ten}</td>
                                        <td>
                                                ${sanPham.quantity}</td>
                                        <td>${sanPham.price}</td>
                                        <td>
                                            <button type="button" onclick="openUpdate(${cart.id}, ${cart.quantity})" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                                                update
                                            </button>
                                            <a href="/cart/delete/${cart.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Xoá</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    </div>



    </div>

    </div>
</section>

