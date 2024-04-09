
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="section dashboard">
    <div class="row">

        <div class="card">
            <div class="card-body">
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item active"><h1>Thêm nhân viên</h1></li>
                    </ol>
                </nav>
                <div class="row" style="margin-top: 50px;">
                    <div class="product-details">
                        <div class="col-md-7">
                            <form action="/employee/create" method="post">
                                <div class="product-info">
                                    <div class="row">
                                        <div class="">
                                            <label  class="form-label">Tên nhân viên: </label>
                                            <input type="text" class="form-control" name="hoTen" required>
                                        </div>
                                        <div class="">
                                            <label  class="form-label">Ngày Sinh: </label>
                                            <input type="date" class="form-control" name="ngaySinh" required>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="">
                                            <label  class="form-label">Số điện thoại: </label>
                                            <input type="text" class="form-control"  name="sdt" required>
                                        </div>
                                        <div class="">
                                            <label  class="form-label">email: </label>
                                            <input type="email" class="form-control"  name="email" required>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="">
                                            <div class="row">
                                                <div class="row">
                                                    <label  class="form-label">Giới tính: </label>
                                                </div>
                                                <div class="row">
                                                    <div class="form-check col-6">
                                                        <input class="form-check-input"  type="radio" value="0" name="flexRadioDefault" id="flexRadioDefault1">
                                                        <label class="form-check-label" for="flexRadioDefault1">
                                                            Nam
                                                        </label>
                                                    </div>
                                                    <div class="form-check col-6">
                                                        <input class="form-check-input" type="radio"  value="1"  name="flexRadioDefault" id="flexRadioDefault2" checked>
                                                        <label class="form-check-label" for="flexRadioDefault2">
                                                            Nữ
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <%--                                        <div class="col-6">--%>
                                        <%--                                            <label  class="form-label">Địa chỉ: </label>--%>
                                        <%--                                            <textarea type="text" class="form-control"  rows="3"  name="diaChi" required></textarea>--%>
                                        <%--                                        </div>--%>
                                    </div>
                                    <button type="submit" class="btn btn-primary">
                                        <span>Tiếp theo</span>
                                    </button>

                                </div>
                            </form>

                        </div>


                    </div>
                </div>


            </div>

        </div>

    </div>

</section>