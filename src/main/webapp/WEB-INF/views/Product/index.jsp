<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
        <h1>Product</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/statisticsResult">Home</a></li>
            <li class="breadcrumb-item active">Overview</li>
            <li class="breadcrumb-item active">Product</li>
        </ol>
    </nav>
</div>
<!-- End Page Title -->

<section class="section dashboard">
    <div class="row">
        <div class="col-12">
            <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Product <span>| </span></h5>

                            <table class="table table-borderless datatable">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>AvailableQuantity</th>
                                    <th>Sold</th>
                                    <th>Likes</th>
                                    <th>CreatedDate</th>
                                    <th>Brand</th>
                                    <th>Status</th>
<%--                                    <th>Category</th>--%>
                                    <th>Action</th>

                                </tr>
                                </thead>
                             <tbody>
                             <c:forEach items="${listProduct}" var="sp" >
                                 <tr>

                                     <td>${sp.name}</td>
                                     <td>${sp.availableQuantity}</td>
                                     <td>${sp.sold}</td>
                                     <td>${sp.likes}</td>
                                     <td>${sp.createdDate}</td>
                                     <td>${sp.brand.name}</td>

                                         <%--                                        <td>${sp.brand.name}</td>--%>
                                     <td>${sp.status == 0 ? "Stocking" : "Out Of Stock"}</td>
<%--                                     <td>--%>
<%--                                         <c:forEach items="${sp.list}" var="cate">--%>
<%--                                             <option value="${cate.id}">${cate.category.name}</option>--%>
<%--                                         </c:forEach>--%>
<%--                                     </td>--%>

                                     <td>
                                         <a href="/product/delete/${sp.id}" class="btn btn-danger" onclick="return confirm('Bạn chắc chắn có muốn xóa??')" style="text-decoration: none;color: white">Delete</a>
<%--                                         <a href="/product/detail/${sp.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Detail</a>--%>
                                         <a href="/product/detail/${sp.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Detail</a>
                                     </td>
                                 </tr>
                             </c:forEach>
                             </tbody>

                            </table>
                            <a href="/product/create" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Thêm sản phẩm</a>
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
<%--        <div class="col-lg-4">--%>

<%--            <!-- Recent Activity -->--%>
<%--            <div class="card">--%>

<%--                <div class="card-body">--%>
<%--                    <h5 class="card-title">Sửa <span>| xx</span></h5>--%>
<%--                    <!-- Default Tabs -->--%>
<%--                    <ul class="nav nav-tabs" id="myTab" role="tablist">--%>
<%--                        <li class="nav-item" role="presentation">--%>
<%--                            <button class="nav-link active" id="home-tab" data-bs-toggle="tab"--%>
<%--                                    data-bs-target="#home" type="button" role="tab" aria-controls="home"--%>
<%--                                    aria-selected="true">Edit--%>
<%--                            </button>--%>
<%--                        </li>--%>
<%--                        <li class="nav-item" role="presentation">--%>
<%--                            <button class="nav-link" id="profile-tab" data-bs-toggle="tab"--%>
<%--                                    data-bs-target="#profile" type="button" role="tab" aria-controls="profile"--%>
<%--                                    aria-selected="false">Add new--%>
<%--                            </button>--%>
<%--                        </li>--%>
<%--                        <li class="nav-item" role="presentation">--%>
<%--                            <button class="nav-link" id="contact-tab" data-bs-toggle="tab"--%>
<%--                                    data-bs-target="#contact" type="button" role="tab" aria-controls="contact"--%>
<%--                                    aria-selected="false">Detail--%>
<%--                            </button>--%>
<%--                        </li>--%>
<%--                    </ul>--%>

<%--                    &lt;%&ndash;update&ndash;%&gt;--%>
<%--                    <div class="tab-content pt-2" id="myTabContent">--%>
<%--                        <div class="tab-pane fade show active" id="home" role="tabpanel"--%>
<%--                             aria-labelledby="home-tab">--%>
<%--                            <form method="post" action="/product/update/${sp.id}">--%>
<%--                                <div class="form-group">--%>
<%--                                    Brand :--%>
<%--                                    <select name="brand" class="form-select"  aria-label="Default select example">--%>
<%--                                        <c:forEach items="${listBrand}" var="brand">--%>
<%--                                            <option value="${brand.id}">${brand.name}</option>--%>
<%--                                        </c:forEach>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                                <div class="form-group">--%>
<%--                                    Category :--%>
<%--                                    <select name="list" class="form-select"  aria-label="Default select example">--%>
<%--                                        <c:forEach items="${listCategoryDetail}" var="list">--%>
<%--                                            <option value="${list.id}">${list.category.name}</option>--%>
<%--                                        </c:forEach>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                                <div>--%>
<%--                                    Name :--%>
<%--                                    <input class="form-control" name="name" value="${sp.name}">--%>
<%--                                </div>--%>
<%--                                <div>--%>
<%--                                    Description :--%>
<%--                                    <input class="form-control" name="descripTion" value="${sp.descripTion}">--%>
<%--                                </div>--%>
<%--                                <div>--%>
<%--                                    AvailableQuantity :--%>
<%--                                    <input class="form-control" name="availableQuantity" value="${sp.availableQuantity}">--%>
<%--                                </div>--%>
<%--                                <div>--%>
<%--                                    Sold :--%>
<%--                                    <input class="form-control" name="sold" value="${sp.sold}">--%>
<%--                                </div>--%>
<%--                                <div>--%>
<%--                                    Like :--%>
<%--                                    <input class="form-control" name="likes" value="${sp.likes}">--%>
<%--                                </div>--%>
<%--&lt;%&ndash;                                <div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    CreateDate :&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    <input class="form-control" name="createdDate" type="date" value="${sp.createdDate}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--                                <div>--%>
<%--                                    Status :<br>--%>
<%--                                    <input  type="radio" name="status" value="0" ${ sp.status == "0" ? "checked" : "" }> Còn Hàng <br>--%>
<%--                                    <input   type="radio" name="status" value="1" ${ sp.status == "1" ? "checked" : "" }> Hết hàng--%>
<%--                                </div>--%>
<%--                                <input type="submit" class="btn btn-primary" value="Update" style="margin-top: 10px">--%>
<%--                            </form>--%>
<%--                        </div>--%>

<%--                        &lt;%&ndash;create&ndash;%&gt;--%>
<%--                        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">--%>
<%--                            <form method="post" action="/product/add">--%>
<%--                                <div class="form-group">--%>
<%--                                    Brand :--%>
<%--                                    <select name="brand" class="form-select" multiple="multiple"  aria-label="Default select example">--%>
<%--                                        <c:forEach items="${listBrand}" var="brand">--%>
<%--                                            <option value="${brand.id}">${brand.name}</option>--%>
<%--                                        </c:forEach>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                                <div class="form-group">--%>
<%--                                    Category :--%>
<%--                                    <select name="list" class="form-select"  aria-label="Default select example">--%>
<%--                                        <c:forEach items="${listCategoryDetail}" var="list">--%>
<%--                                            <option value="${list.id}">${list.category.name}</option>--%>
<%--                                        </c:forEach>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                                <div>--%>
<%--                                    Name :--%>
<%--                                    <input class="form-control" name="name">--%>
<%--                                </div>--%>
<%--                                <div>--%>
<%--                                    Description :--%>
<%--                                    <input class="form-control" type="text" name="descripTion">--%>
<%--                                </div>--%>
<%--                                <div>--%>
<%--                                    AvailableQuantity :--%>
<%--                                    <input class="form-control" name="availableQuantity">--%>
<%--                                </div>--%>
<%--                                <div>--%>
<%--                                    Sold :--%>
<%--                                    <input class="form-control" name="sold">--%>
<%--                                </div>--%>
<%--                                <div>--%>
<%--                                    Like :--%>
<%--                                    <input class="form-control" name="likes">--%>
<%--                                </div>--%>
<%--                                <div>--%>
<%--                                    CreateDate :--%>
<%--                                    <input class="form-control" name="createdDate" type="date">--%>
<%--                                </div>--%>
<%--                                <div>--%>
<%--                                    Status :<br>--%>
<%--                                    <input  type="radio" name="status" value="0" > Còn Hàng <br>--%>
<%--                                    <input  type="radio" name="status" value="1"> Hết hàng--%>
<%--                                </div>--%>
<%--                                <input type="submit" class="btn btn-primary" value="Add" style="margin-top: 10px">--%>
<%--                            </form>--%>
<%--                        </div>--%>
<%--                        &lt;%&ndash;detail&ndash;%&gt;--%>
<%--                        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">--%>
<%--&lt;%&ndash;                            <form class="row g-3" action="/product/detail/${sp.id}" method="get">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    Color : ${sp.color.name}&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    Category : ${sp.category.name}&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    Name : ${sp.name}&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    DescripTion : ${sp.descripTion}&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    ManufacTurer : ${sp.manufacTurer}&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    AvailableQuantity : ${sp.availableQuantity}&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    Sold : ${sp.sold}&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    Price : ${sp.price}&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    ImportPrice : ${sp.importPrice}&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    Date : ${sp.date}&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    StaTus : ${sp.staTus}&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    IMG : <img src="/assets/img/${sp.imageUrl}" height="100px" width="100px">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </form><!-- End Multi Columns Form -->&ndash;%&gt;--%>
<%--                        </div>--%>
<%--                    </div><!-- End Default Tabs -->--%>


<%--                </div>--%>

<%--            </div>--%>
<%--        </div><!-- End Recent Activity -->--%>


    </div><!-- End Right side columns -->

    </div>
</section>


