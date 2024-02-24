
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>Bill Detailx Management</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
            <li class="breadcrumb-item active">Overview</li>
            <li class="breadcrumb-item active">Product</li>
        </ol>
    </nav>
</div>
<!-- End Page Title -->

<section class="section dashboard">
    <div class="row">
        <!-- Left side columns -->
        <div class="col-lg-12">
            <div class="row">
                <!-- Recent Sales -->
                <div class="col-12">
                    <div class="card recent-sales overflow-auto">


                        <div class="card-body">
                            <h5 class="card-title">Bill Detail <span>| </span></h5>

                            <form class="row g-3" action="/bill/index/${bill.id}" method="get">
                                <table class="table table-borderless">
                                    <tr>
                                        <%--                                        <td>Id</td>--%>
                                        <td>Id</td>
                                        <td>Quantity</td>
                                        <td>Product</td>
                                        <td>Price</td>

                                    </tr>
                                    <c:forEach items="${billDetailD}" var="billDetailD">
                                        <tr>
                                                <%--                                            <td>${bill.id}</td>--%>
                                            <td>${billDetailD.id}</td>
                                            <td>${billDetailD.quantity}</td>
                                            <td>${billDetailD.productDetail.product.name}</td>
                                            <td>${billDetailD.productDetail.price}</td>

                                        </tr>
                                    </c:forEach>
                                  <div >
                                        <b>Total: ${total} VND</b>
                                  </div>
                                </table>
<%--                                <h3>Tổng tiền khách phải trả : <fmt:formatNumber value="${allPrice}" type="currency" currencyCode="VND" /></h3>--%>
                            </form><!-- End Multi Columns Form -->
<%--                            <form method="post" enctype="multipart/form-data" action="import">--%>
<%--                                Thêm từ file excel: <input class="form-control" name="file" type="file">--%>
<%--                                <button>Thêm</button>--%>
<%--                            </form>--%>
                        </div>

                    </div>
                    <button class="btn btn-primary"><a href="/kich-thuoc/create"
                                                       style="text-decoration: none;color: white">Add New</a></button>

                </div><!-- End Recent Sales -->

            </div>

        </div><!-- End Left side columns -->

        <!-- Right side columns -->



    </div><!-- End Right side columns -->

    </div>
</section>



