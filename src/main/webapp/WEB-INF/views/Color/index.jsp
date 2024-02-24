<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>Color</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/statisticsResult">Home</a></li>
            <li class="breadcrumb-item active">Overview</li>
            <li class="breadcrumb-item active">Color</li>
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
                            <h5 class="card-title">Color <span>| </span></h5>

                            <table class="table table-borderless datatable">
                                <thead>
                                <tr>

                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Image</th>
                                    <th>Status</th>
                                    <th>Brand</th>
                                    <th>Action</th>

                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listColor}" var="mau">
                                    <tr>

                                        <td>${mau.name}</td>
                                        <td>${mau.price}</td>
                                        <td><img src="/assets/img/color/${mau.image}" width="100px" height="100px"></td>
                                        <td>${mau.status == 0 ? "Stocking" : "Out Of Stock"}</td>
                                        <td>${mau.brand.name}</td>
                                        <td>
                                            <a href="/color/delete/${mau.id}" class="btn btn-danger"
                                               onclick="return confirm('Bạn chắc chắn có muốn xóa??')">Delete</a>
                                            <a href="/color/detail/${mau.id}" class="btn btn-success">Detail</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>

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
                            <form method="post" action="/color/update/${mau.id}" enctype="multipart/form-data">

                                <div>
                                    Ten :
                                    <input class="form-control" name="name" value="${mau.name}">
                                </div>
                                <div>
                                    Gia :
                                    <input class="form-control" name="price" value="${mau.price}">
                                </div>
                                <div>
                                    Image :
                                    <img src="/assets/img/color/${mau.image}" height="100px" width="100px">
                                    <%--                                    <input class="form-control" name="image" type="file" value="${mau.image}">--%>
                                    <input type="file" name="image" accept="image/*" class="form-control"
                                           value="${mau.image}">
                                </div>
                                <div>
                                    Status :<br>
                                    <input type="radio" name="status" value="0" ${ mau.status == "0" ? "checked" : "" }>
                                    Còn Hàng <br>
                                    <input type="radio" name="status" value="1" ${mau.status == "1" ? "checked" : "" }>
                                    Hết hàng
                                </div>
                                <div class="form-group">
                                    Brand :
                                    <select name="brand" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${listBrand}" var="brand">
                                            <option value="${brand.id}">${brand.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <input type="submit" class="btn btn-primary" value="Update" style="margin-top: 10px">
                            </form>
                        </div>

                        <%--create--%>
                        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <form method="post" action="/color/add" enctype="multipart/form-data">
                                <div>
                                    Ten :
                                    <input class="form-control" name="name">
                                </div>
                                <div>
                                    Gia :
                                    <input class="form-control" name="price">
                                </div>
                                <div>
                                    Image :
                                    <input type="file" name="image" accept="image/*" class="form-control"></div>
                                <div>
                                    Status :<br>
                                    <input type="radio" name="status" value="0"> Còn Hàng <br>
                                    <input type="radio" name="status" value="1"> Hết hàng
                                </div>
                                <div class="form-group">
                                    Brand :
                                    <select name="brand" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${listBrand}" var="brand">
                                            <option value="${brand.id}">${brand.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <input type="submit" class="btn btn-primary" value="Add" style="margin-top: 10px">
                            </form>
                        </div>
                        <%--detail--%>
                        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                            <%--                            <form class="row g-3" action="/favor/detail/${spyt.id}" method="get">--%>
                            <%--                                <div class="form-group">--%>
                            <%--                                    ID : ${mau.id}--%>
                            <%--                                </div>--%>
                            <%--                                <div class="form-group">--%>
                            <%--                                    Name : ${mau.name}--%>
                            <%--                                </div>--%>

                            <%--                            </form><!-- End Multi Columns Form -->--%>
                        </div>
                    </div><!-- End Default Tabs -->


                </div>

            </div>
        </div><!-- End Recent Activity -->


    </div><!-- End Right side columns -->

    </div>
</section>



