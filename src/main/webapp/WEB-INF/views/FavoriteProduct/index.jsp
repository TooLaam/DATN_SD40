<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>FavoriteProduct</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
            <li class="breadcrumb-item active">Overview</li>
            <li class="breadcrumb-item active">FavoriteProduct</li>
        </ol>
    </nav>
</div>
<!-- End Page Title -->

<section class="section dashboard">
    <div class="row">
        <!-- Left side columns -->
        <div class="col-lg-8">
            <div class="row">
                <!-- Recent Sales -->
                <div class="col-12">
                    <div class="card recent-sales overflow-auto">


                        <div class="card-body">
                            <h5 class="card-title">FavoriteProduct <span>| </span></h5>

                            <table class="table table-borderless datatable">
                                <tr>
                                    <th>ID</th>
                                    <th>Customer</th>
                                    <th>Product</th>
                                    <th>Description</th>
                                    <th>Action</th>

                                </tr>
                                <c:forEach items="${listFavor}" var="spyt">
                                    <tr>
                                        <td>${spyt.id}</td>
                                        <td>${spyt.customer.fullname}</td>
                                        <td>${spyt.product.name}</td>
                                        <td>${spyt.descripTion}</td>
                                        <td>
                                            <a href="/favor/delete?id=${spyt.id}" class="btn btn-danger" onclick="return confirm('Bạn chắc chắn có muốn xóa??')" style="text-decoration: none;color: white"><i class='bx bx-trash'></i></a><br>
                                            <a href="/favor/detail/${spyt.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" ><i class='bi bi-arrow-repeat'></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <%--                            <form method="post" enctype="multipart/form-data" action="import">--%>
                            <%--                                Thêm từ file excel: <input class="form-control" name="file" type="file">--%>
                            <%--                                <button>Thêm</button>--%>
                            <%--                            </form>--%>
                        </div>

                    </div>


                </div><!-- End Recent Sales -->

            </div>

        </div><!-- End Left side columns -->

        <!-- Right side columns -->
        <div class="col-lg-4">

            <!-- Recent Activity -->
            <div class="card">

                <div class="card-body">
                    <h5 class="card-title">Sửa <span>| xx</span></h5>
                    <!-- Default Tabs -->
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active" id="home-tab" data-bs-toggle="tab"
                                    data-bs-target="#home" type="button" role="tab" aria-controls="home"
                                    aria-selected="true">Edit
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="profile-tab" data-bs-toggle="tab"
                                    data-bs-target="#profile" type="button" role="tab" aria-controls="profile"
                                    aria-selected="false">Add new
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="contact-tab" data-bs-toggle="tab"
                                    data-bs-target="#contact" type="button" role="tab" aria-controls="contact"
                                    aria-selected="false">Detail
                            </button>
                        </li>
                    </ul>

                    <%--update--%>
                    <div class="tab-content pt-2" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel"
                             aria-labelledby="home-tab">
                            <form method="post" action="/favor/update/${spyt.id}">
                                <div class="form-group">
                                    Customer :
                                    <select name="customer" class="form-select"  aria-label="Default select example">
                                        <c:forEach items="${listCustomer}" var="customer">
                                            <option value="${customer.id}">${customer.fullname}</option>
                                        </c:forEach>
                                    </select>
                                </div>


                                <div class="form-group">
                                    Product :
                                    <select name="product" class="form-select"  aria-label="Default select example">
                                        <c:forEach items="${listProduct}" var="product">
                                            <option value="${product.id}">${product.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div>
                                    Description :
                                    <input class="form-control" name="descripTion" value="${spyt.descripTion}">
                                </div>
                                <input type="submit" class="btn btn-primary" value="Update"style="margin-top: 10px">
                            </form>
                        </div>

                        <%--create--%>
                        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <form method="post" action="/favor/add">
                                <div class="form-group">
                                    Customer :
                                    <select name="customer" class="form-select"  aria-label="Default select example">
                                        <c:forEach items="${listCustomer}" var="customer">
                                            <option value="${customer.id}">${customer.fullname}</option>
                                        </c:forEach>
                                    </select>
                                </div>


                                <div class="form-group">
                                    Product :
                                    <select name="product" class="form-select"  aria-label="Default select example">
                                        <c:forEach items="${listProduct}" var="product">
                                            <option value="${product.id}">${product.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div>
                                    Description :
                                    <input class="form-control" name="descripTion" >
                                </div>
                                <input type="submit" class="btn btn-primary" value="Add" style="margin-top: 10px">
                            </form>
                        </div>
                        <%--detail--%>
                        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                            <form class="row g-3" action="/favor/detail/${spyt.id}" method="get">
                                <div class="form-group">
                                    Customer : ${spyt.customer.fullname}
                                </div>
                                <div class="form-group">
                                    Product :  ${spyt.product.name}
                                </div>
                                <div class="form-group">
                                    Description  : ${spyt.descripTion}
                                </div>

                            </form><!-- End Multi Columns Form -->
                        </div>
                    </div><!-- End Default Tabs -->


                </div>

            </div>
        </div><!-- End Recent Activity -->


    </div><!-- End Right side columns -->

    </div>
</section>



