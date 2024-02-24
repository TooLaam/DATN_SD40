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
            <li class="breadcrumb-item active">ProductDetail</li>
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
                    <form method="post" action="/product_detail/add" enctype="multipart/form-data">
                        <div class="form-group">
                            Mau :
                            <select name="color" class="form-select"  aria-label="Default select example">
                                <c:forEach items="${listColor}" var="color">
                                    <option value="${color.id}">${color.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            San pham :
                            <select name="product" class="form-select"  aria-label="Default select example">
                                <c:forEach items="${listProduct}" var="product">
                                    <option value="${product.id}">${product.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            Gia nhap:
                            <input class="form-control" name="importPrice" >
                        </div>
                        <div>
                            Gia ban :
                            <input class="form-control" name="price" >
                        </div>
                        <div>
                            So luong :
                            <input class="form-control" name="quantity" >
                        </div>
                        <div>
                            Mo ta :
                            <input class="form-control" name="descripTion" >
                        </div>
<%--                        <div>--%>
<%--                            Trang thai :<br>--%>
<%--                            <input  type="radio" name="status" value="0" > Còn Hàng <br>--%>
<%--                            <input   type="radio" name="status" value="1"> Hết hàng--%>
<%--                        </div>--%>
                        <div class="form-group">
                            Image :
<%--                            <label for="listImages"> <img src="/assets/img/product/${detailSP.listImages.name}" height="45xp" width="45px"></label>--%>
<%--                            <c:forEach items="${listProductImage}" var="listImages">--%>
<%--                                <input type="checkbox" id="listImages" name="listImages" value="${listImages.id}"  multiple>--%>
<%--                                <label for="listImages"> <img src="/assets/img/product/${listImages.name}" height="45xp" width="45px"></label>--%>
<%--                            </c:forEach>--%>
<%--                            <form action="upload" method="post" enctype="multipart/form-data">--%>
                                <input type="file" name="files" multiple="multiple" />
<%--                                <input type="submit" value="Tải lên" />--%>
<%--                            </form>--%>

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



