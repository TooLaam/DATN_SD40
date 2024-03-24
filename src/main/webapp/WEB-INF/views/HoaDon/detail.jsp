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
                                    <th scope="col">Stt</th>
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
                                        document.addEventListener('DOMContentLoaded', function () {
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
                            <div class="row">
                                <c:choose>
                                    <c:when test="${hoaDon.trangThai == 0}">
                                        <a href="/bill/delete/${hoaDon.id}" class="btn btn-success col-3"
                                           style="text-decoration: none;color: white; margin-top: 5px">Hủy</a>
                                    </c:when>
                                    <c:otherwise>
                                        <%-- Nếu trạng thái không phải là 1, hiển thị button --%>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${hoaDon.trangThai < 4}">
                                        <a href="/bill/change/${hoaDon.id}" class="btn btn-success col-3"
                                           style="text-decoration: none;color: white; margin-top: 5px">Thay đổi</a>
                                    </c:when>
                                    <c:otherwise>
                                        <%-- Nếu trạng thái không phải là 1, hiển thị button --%>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>


                    <!-- Modal -->
                    <div class="row">
                        <h4 class="row">Hóa Đơn</h4>
                        <div class="row">
                            <div class="col-6">Mã hóa đơn: ${hoaDon.maHoaDon}</div>
                            <div class="col-6">Tên Khách hàng: ${hoaDon.khachHang.hoTen}</div>
                            <div class="col-6">Số điện thoại: ${hoaDon.khachHang.sdt}</div>
                            <div class="col-6">Email: ${hoaDon.khachHang.email}</div>
                            <div class="col-6">Địa chỉ: ${hoaDon.khachHang.diaChi}</div>
                            <div class="col-6">Phương Thức thanh toán: ${hoaDon.phuongThucThanhToan.ten  }</div>
                            <div class="col-6">Trạng thái thanh
                                toán: ${hoaDon.phuongThucThanhToan.trangThai == 0 ? "trả sau": "Trả trước"  }</div>
                            <div class="col-6">Nghi chú: ${hoaDon.ghiChu}</div>
                            <div class="col-6" id="formattedDate"></div>
                            <script>
                                document.addEventListener('DOMContentLoaded', function () {
                                    var hoaDonNgayThanhToan = ${hoaDon.ngayThanhToan};
                                    var formattedDate = "";
                                    if (hoaDonNgayThanhToan) {
                                        formattedDate = moment(hoaDonNgayThanhToan * 1000).format("DD/MM/YYYY");
                                    }
                                    document.getElementById('formattedDate').textContent = "Ngày nhận dự kiến: " + formattedDate;
                                });
                            </script>
                        </div>
                    </div>
                    <div class="row">
                        <h4 class="row">Sản phẩm</h4>
                        <div class="row">

                        </div>
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
                                            <img src="/assets/img/product/${sanPham.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.hinhAnh}"
                                                 style="padding-top: 60px" width="320px" height="450px">
                                        </td>
                                        <td>${sanPham.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.sanPham.ten}</td>
                                        <td>${sanPham.chiTietSanPham.kichCo.ten}</td>
                                        <td>${sanPham.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.mau_sac.ten}</td>
                                        <td>
                                                ${sanPham.soLuong}</td>
                                        <td>${sanPham.giaDaGiam}</td>
                                        <td>
                                            <a href="/bill/update/${sanPham.id}?quantity=${sanPham.soLuong}" class="btn btn-success"
                                               style="text-decoration: none;color: white; margin-top: 5px">Sửa số lượng</a>
                                            <a href="/bill/delete-bill-detail/${sanPham.id}" class="btn btn-success"
                                               style="text-decoration: none;color: white; margin-top: 5px">Xoá</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>

                            </table>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-7"></div>
                        <div class="col-5">
                            <div class="row">
                                <div class="row">Tổng tiền sản phẩm: ${hoaDon.tongTien + hoaDon.tongTienGiam}</div>
                                <div class="row">Tiền giảm: ${hoaDon.tongTienGiam}</div>
                                <div class="row">Tổng tiền: ${hoaDon.tongTien}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>



</section>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>