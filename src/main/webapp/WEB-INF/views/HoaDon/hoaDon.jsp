
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>Hóa đơn</h1>
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
                    <h5 class="card-title">Hóa đơn <span>| </span></h5>

                    <div class="">
                        <div class="container mt-5">
                            <!-- Tabs navigation -->
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link active"  data-bs-toggle="tab" data-bs-target="#cho_xac_nhan" type="button" role="tab" aria-controls="cho_xac_nhan" aria-selected="true">Chờ xác nhận</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link"  data-bs-toggle="tab" data-bs-target="#xac_nhan" type="button" role="tab" aria-controls="xac_nhan" aria-selected="false">Xác nhận</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link"  data-bs-toggle="tab" data-bs-target="#cho_van_chuyen" type="button" role="tab" aria-controls="cho_van_chuyen" aria-selected="false">Chờ vận chuyển</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link"  data-bs-toggle="tab" data-bs-target="#van_chuyen" type="button" role="tab" aria-controls="van_chuyen" aria-selected="false">Vận chuyển</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link"  data-bs-toggle="tab" data-bs-target="#hoan_thanh" type="button" role="tab" aria-controls="hoan_thanh" aria-selected="false">Hoàn thành</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link"  data-bs-toggle="tab" data-bs-target="#huy" type="button" role="tab" aria-controls="huy" aria-selected="false">Hủy</button>
                                </li>
                            </ul>

                            <!-- Tabs content -->
                            <div class="tab-content" id="myTabContent">
                                <div class="tab-pane fade show active" id="chơ_xac_nhan" role="tabpanel" aria-labelledby="home-tab">
                                    <h5>Chờ Xác nhận</h5>
                                    <table class="table table-borderless datatable">
                                        <thead>
                                        <tr>
                                            <%--                            <th>STT</th>--%>
                                            <th>Mã</th>
                                            <th>Tên khách hàng</th>
                                            <th>Số điện thoại</th>
                                            <th>Loại hóa đơn</th>
                                            <th>Tổng tiền</th>
                                            <th>ngày tạo</th>
                                            <th>Action</th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${choXacNhans}" var="hoaDon" varStatus="tt">
                                            <tr>
                                                    <%--                                <th scope="row">${tt.index+1}</th>--%>
                                                <td>${hoaDon.maHoaDon}</td>
                                                <td>${hoaDon.nguoiNhan} </td>
                                                <td>${hoaDon.sdtNguoiNhan}</td>
                                                <td>${hoaDon.loaiHoaDon == 1 ? "online" : "tại quầy"}</td>
                                                <td>${hoaDon.tongTien + hoaDon.phiShip }</td>
                                                <td>${hoaDon.ngayTao}</td>
                                                <td>
                                                    <a href="/bill/detail/${hoaDon.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Chi tiết</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>

                                    </table>
                                </div>
                                <div class="tab-pane fade" id="xac_nhan" role="tabpanel" aria-labelledby="profile-tab">
                                    <h5>Xác nhận</h5>
                                    <table class="table table-borderless datatable">
                                        <thead>
                                        <tr>
                                            <%--                            <th>STT</th>--%>
                                            <th>Mã</th>
                                            <th>Tên khách hàng</th>
                                            <th>Số điện thoại</th>
                                            <th>Loại hóa đơn</th>
                                            <th>Tổng tiền</th>
                                            <th>ngày tạo</th>
                                            <th>Action</th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${xacNhans}" var="hoaDon" varStatus="tt">
                                            <tr>
                                                    <%--                                <th scope="row">${tt.index+1}</th>--%>
                                                <td>${hoaDon.maHoaDon}</td>
                                                <td>${hoaDon.nguoiNhan} </td>
                                                <td>${hoaDon.sdtNguoiNhan}</td>
                                                <td>${hoaDon.loaiHoaDon == 1 ? "online" : "tại quầy"}</td>
                                                <td>${hoaDon.tongTien + hoaDon.phiShip }</td>
                                                <td>${hoaDon.ngayTao}</td>
                                                <td>
                                                    <a href="/bill/detail/${hoaDon.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Chi tiết</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>

                                    </table>
                                </div>
                                <div class="tab-pane fade" id="cho_van_chuyen" role="tabpanel" aria-labelledby="contact-tab">
                                    <h5>Chờ vận chuyển</h5>
                                    <table class="table table-borderless datatable">
                                        <thead>
                                        <tr>
                                            <%--                            <th>STT</th>--%>
                                            <th>Mã</th>
                                            <th>Tên khách hàng</th>
                                            <th>Số điện thoại</th>
                                            <th>Loại hóa đơn</th>
                                            <th>Tổng tiền</th>
                                            <th>ngày tạo</th>
                                            <th>Action</th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${choVanChuyens}" var="hoaDon" varStatus="tt">
                                            <tr>
                                                    <%--                                <th scope="row">${tt.index+1}</th>--%>
                                                <td>${hoaDon.maHoaDon}</td>
                                                <td>${hoaDon.nguoiNhan} </td>
                                                <td>${hoaDon.sdtNguoiNhan}</td>
                                                <td>${hoaDon.loaiHoaDon == 1 ? "online" : "tại quầy"}</td>
                                                <td>${hoaDon.tongTien + hoaDon.phiShip }</td>
                                                <td>${hoaDon.ngayTao}</td>
                                                <td>
                                                    <a href="/bill/detail/${hoaDon.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Chi tiết</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>

                                    </table>
                                </div>
                                <div class="tab-pane fade" id="van_chuyen" role="tabpanel" aria-labelledby="contact-tab">
                                    <h5>Vận chuyển</h5>
                                    <table class="table table-borderless datatable">
                                        <thead>
                                        <tr>
                                            <%--                            <th>STT</th>--%>
                                            <th>Mã</th>
                                            <th>Tên khách hàng</th>
                                            <th>Số điện thoại</th>
                                            <th>Loại hóa đơn</th>
                                            <th>Tổng tiền</th>
                                            <th>ngày tạo</th>
                                            <th>Action</th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${VanChuyens}" var="hoaDon" varStatus="tt">
                                            <tr>
                                                    <%--                                <th scope="row">${tt.index+1}</th>--%>
                                                <td>${hoaDon.maHoaDon}</td>
                                                <td>${hoaDon.nguoiNhan} </td>
                                                <td>${hoaDon.sdtNguoiNhan}</td>
                                                <td>${hoaDon.loaiHoaDon == 1 ? "online" : "tại quầy"}</td>
                                                <td>${hoaDon.tongTien + hoaDon.phiShip }</td>
                                                <td>${hoaDon.ngayTao}</td>
                                                <td>
                                                    <a href="/bill/detail/${hoaDon.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Chi tiết</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>

                                    </table>
                                </div>
                                <div class="tab-pane fade" id="hoan_thanh" role="tabpanel" aria-labelledby="contact-tab">
                                    <h5>Hoàn thành</h5>
                                    <table class="table table-borderless datatable">
                                        <thead>
                                        <tr>
                                            <%--                            <th>STT</th>--%>
                                            <th>Mã</th>
                                            <th>Tên khách hàng</th>
                                            <th>Số điện thoại</th>
                                            <th>Loại hóa đơn</th>
                                            <th>Tổng tiền</th>
                                            <th>ngày tạo</th>
                                            <th>Action</th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${hoanThanhs}" var="hoaDon" varStatus="tt">
                                            <tr>
                                                    <%--                                <th scope="row">${tt.index+1}</th>--%>
                                                <td>${hoaDon.maHoaDon}</td>
                                                <td>${hoaDon.nguoiNhan} </td>
                                                <td>${hoaDon.sdtNguoiNhan}</td>
                                                <td>${hoaDon.loaiHoaDon == 1 ? "online" : "tại quầy"}</td>
                                                <td>${hoaDon.tongTien + hoaDon.phiShip }</td>
                                                <td>${hoaDon.ngayTao}</td>
                                                <td>
                                                    <a href="/bill/detail/${hoaDon.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Chi tiết</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>

                                    </table>
                                </div>
                                <div class="tab-pane fade" id="huy" role="tabpanel" aria-labelledby="contact-tab">
                                    <h5>Hủy</h5>
                                    <table class="table table-borderless datatable">
                                        <thead>
                                        <tr>
                                            <%--                            <th>STT</th>--%>
                                            <th>Mã</th>
                                            <th>Tên khách hàng</th>
                                            <th>Số điện thoại</th>
                                            <th>Loại hóa đơn</th>
                                            <th>Tổng tiền</th>
                                            <th>ngày tạo</th>
                                            <th>Action</th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${huys}" var="hoaDon" varStatus="tt">
                                            <tr>
                                                    <%--                                <th scope="row">${tt.index+1}</th>--%>
                                                <td>${hoaDon.maHoaDon}</td>
                                                <td>${hoaDon.nguoiNhan} </td>
                                                <td>${hoaDon.sdtNguoiNhan}</td>
                                                <td>${hoaDon.loaiHoaDon == 1 ? "online" : "tại quầy"}</td>
                                                <td>${hoaDon.tongTien + hoaDon.phiShip }</td>
                                                <td>${hoaDon.ngayTao}</td>
                                                <td>
                                                    <a href="/bill/detail/${hoaDon.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Chi tiết</a>
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


        </div><!-- End Recent Sales -->

    </div>

    </div><!-- End Left side columns -->



    </div><!-- End Right side columns -->

    </div>
</section>


<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>