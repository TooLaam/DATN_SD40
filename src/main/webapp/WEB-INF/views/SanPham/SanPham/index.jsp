<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>SẢN PHẨM</h1>
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
                    <h5 class="card-title">Sản phẩm <span>| </span></h5>

                    <table class="table table-borderless datatable">
                        <thead>
                        <tr>
<%--                            <th>STT</th>--%>
                            <th>Mã</th>
                            <th>Tên</th>
                            <th>Hình ảnh đại diện</th>
                            <th>Số lượng tổng</th>
                            <th>Action</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listTong}" var="sp" varStatus="tt">
                            <tr>
<%--                                <th scope="row">${tt.index+1}</th>--%>
                                <td>${sp[1]}</td>
                                <td><strong>${sp[2]}</strong> </td>
                                <td><img src="/assets/img/product/${sp[3]}" width="100px" height="100px"></td>
                                <td><div style="color: ${sp[4]==0?"red":"black"}">${sp[4]==0?"Hết hàng":sp[4]}</div> </td>

                                <td>

                                    <a href="/sanpham/detail/${sp[0]}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Sửa</a>
                                    <a href="/ctsp/hienthi/${sp[0]}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Chi tiết</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:forEach items="${listSPChuaSL}" var="sp" varStatus="tt">
                            <tr>
<%--                                <th scope="row">${index+1}</th>--%>
                                <td>${sp[1]}</td>
                                <td><strong>${sp[2]}</strong> </td>
                                <td><img src="/assets/img/product/${sp[3]}" width="100px" height="100px"></td>
                                <td style="color: red" >Chưa có sản phẩm</td>

                                <td>

                                    <a href="/sanpham/detail/${sp[0]}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Sửa</a>
                                    <a href="/ctsp/hienthi/${sp[0]}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Chi tiết</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                    <a href="/ctsp/hienThiaddSP" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Thêm sản phẩm</a>
                    <%--                            <form method="post" enctype="multipart/form-data" action="import">--%>
                    <%--                                Thêm từ file excel: <input class="form-control" name="file" type="file">--%>
                    <%--                                <button>Thêm</button>--%>
                    <%--                            </form>--%>
                </div>

            </div>


        </div><!-- End Recent Sales -->

    </div>

    </div><!-- End Left side columns -->



    </div><!-- End Right side columns -->

    </div>
</section>


