<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>Quản lý khách hàng</h1>
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
                    <h5 class="card-title">Danh sách khách hàng </h5>
                </div>
                <table class="table table-borderless datatable">
                    <thead>
                    <tr>
                        <th>Mã</th>
                        <th>Tên</th>
                        <th>Giới tính</th>
                        <th>Sdt</th>
                        <th>gmail</th>
                        <th>Ngày sinh</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${khachHangs}" var="khachHang" varStatus="tt">
                        <tr>
                            <td>${khachHang.id}</td>
                            <td>${khachHang.hoTen}</td>
                            <td>${khachHang.gioiTinh ==0 ? "Nam" : "Nữ"}</td>
                            <td>${khachHang.sdt}</td>
                            <td>${khachHang.email}</td>
                            <td>${khachHang.ngaySinh}</td>
                            <td>
                                <a href="/customer/view-update/${khachHang.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >chi tiết</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
                <a href="/customer/view-create" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Thêm khách hàng</a>
            </div>


        </div>

    </div>

    </div><!-- End Left side columns -->



    </div><!-- End Right side columns -->

    </div>
</section>