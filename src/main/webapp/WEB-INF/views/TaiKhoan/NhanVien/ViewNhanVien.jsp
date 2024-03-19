<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>Quản lý nhân viên</h1>
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
                    <h5 class="card-title">Danh sách nhân viên </h5>
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
                        <th>Trạng thái</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${nhanViens}" var="nhanVien" varStatus="tt">
                        <tr>
                            <td>${nhanVien.id}</td>
                            <td>${nhanVien.hoVaTen}</td>
                            <td>${nhanVien.gioiTinh ==0 ? "Nam" : "Nữ"}</td>
                            <td>${nhanVien.soDienThoai}</td>
                            <td>${nhanVien.email}</td>
                            <td>${nhanVien.ngaySinh}</td>
                            <td>${nhanVien.trangThai == 0 ? "Làm": "Nghỉ"}</td>
                            <td>

                                <a href="/employee/delete/${nhanVien.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Chuyển trạng thái</a>
                                <a href="/employee/view-update/${nhanVien.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Sửa</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
                <a href="/employee/view-create" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Thêm Nhân viên</a>
            </div>


        </div>

    </div>

    </div><!-- End Left side columns -->



    </div><!-- End Right side columns -->

    </div>
</section>
