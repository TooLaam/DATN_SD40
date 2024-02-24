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

                    <form method="post" action="/product/update/${sp.id}">
                        <div class="form-group">
                            Brand :
                            <select name="brand" class="form-select"  aria-label="Default select example">
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
                            <input class="form-control" name="name" value="${sp.name}">
                        </div>
                        <div>
                            Description :
                            <input class="form-control" name="descripTion" value="${sp.descripTion}">
                        </div>
                        <div>
                            AvailableQuantity :
                            <input class="form-control" name="availableQuantity" value="${sp.availableQuantity}">
                        </div>
                        <div>
                            Sold :
                            <input class="form-control" name="sold" value="${sp.sold}">
                        </div>
                        <div>
                            Like :
                            <input class="form-control" name="likes" value="${sp.likes}">
                        </div>
                        <%--                                <div>--%>
                        <%--                                    CreateDate :--%>
                        <%--                                    <input class="form-control" name="createdDate" type="date" value="${sp.createdDate}">--%>
                        <%--                                </div>--%>
                        <div>
                            Status :<br>
                            <input  type="radio" name="status" value="0" ${ sp.status == "0" ? "checked" : "" }> Còn Hàng <br>
                            <input   type="radio" name="status" value="1" ${ sp.status == "1" ? "checked" : "" }> Hết hàng
                        </div>
                        <input type="submit" class="btn btn-primary" value="Update" style="margin-top: 10px">
                    </form>


                </div>

            </div>
        </div>


    </div>
    </div>
    </div>
</section>



