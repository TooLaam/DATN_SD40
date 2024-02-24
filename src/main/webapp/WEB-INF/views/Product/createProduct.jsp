<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>Product</h1>
    <nav>
        <ol class="breadcrumb">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
            <li class="breadcrumb-item active">Overview</li>
            <li class="breadcrumb-item active">Product</li>
        </ol>
    </nav>
</div>
<!-- End Page Title -->

<section className="section dashboard">
    <div className="row">


        <div className="col-lg-10">


            <div className="card">

                <div className="card-body">
                    <h5 className="card-title">ADD<span>| xx</span></h5>

                    <form method="post" action="/product/add">
                        <div class="form-group">
                            Brand :
                            <select name="brand" class="form-select"  aria-label="Default select example"> //multiple="multiple"
                                <c:forEach items="${listBrand}" var="brand">
                                    <option value="${brand.id}">${brand.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            Category :
                            <select name="category" class="form-select"  aria-label="Default select example">
                                <c:forEach items="${listCategory}" var="ca">
                                    <option value="${ca.id}">${ca.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            Name :
                            <input class="form-control" name="name">
                        </div>
                        <div>
                            Description :
                            <input class="form-control" type="text" name="descripTion">
                        </div>
                        <div>
                            AvailableQuantity :
                            <input class="form-control" name="availableQuantity">
                        </div>
                        <div>
                            Sold :
                            <input class="form-control" name="sold">
                        </div>
                        <div>
                            Like :
                            <input class="form-control" name="likes">
                        </div>
                        <div>
                            Status :<br>
                            <input  type="radio" name="status" value="0" > Còn Hàng <br>
                            <input  type="radio" name="status" value="1"> Hết hàng
                        </div>
                        <input type="submit" class="btn btn-primary" value="Add" style="margin-top: 10px">
                    </form>



                </div>

            </div>
        </div>


    </div>
        </div>
    </div>
</section>



