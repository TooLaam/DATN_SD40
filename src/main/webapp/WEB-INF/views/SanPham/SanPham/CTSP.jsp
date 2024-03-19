
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="section dashboard">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">

                    <nav>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item active"><h1>Chi tiết sản phẩm</h1></li>
                            <li style="margin-top: 20px" class="breadcrumb-item"><a href="/sanpham/index">Home</a></li>
                            <li style="margin-top: 20px" class="breadcrumb-item"><a href="/ctsp/hienthi/${sp.id}">Chi tiết sản phẩm</a></li>

                        </ol>
                    </nav>
<%--                    <form action="/sanpham/update" method="post" enctype="multipart/form-data">--%>
                        <div class="row" style="margin-top: 50px;">

                            <div class="product-details">
                                <div class="col-md-7">
                                    <div class="product-info">

                                        <h2>
                                            <strong style="display: inline;">(${sp.ma})${sp.ten} </strong>
                                            <span><c:if test="${sp.giamGIa != null && sp.giamGIa.mucGiam >0}" >
                                                <p style="color: red;display: inline">(- ${sp.giamGIa.mucGiam}%)</p>
                                            </c:if></span>
                                        </h2>
                                        <br>
                                        <p>Thương hiệu: ${sp.thuongHieu.ten}</p><br>
                                        <p>Thể loại: ${sp.theLoai.ten}</p><br>
                                        <p>Mô tả sản phẩm: ${sp.moTa}</p>
                                        <p>Màu sắc: </p>
                                        <c:forEach items="${listMS}" var="ms">
                                            <a href="/ctsp/hienthiKC/${sp.id}/${ms[0]}" class="btn btn-light" style="margin-left: 30px">
                                                <img style="width: 20px;height: 20px" src="/assets/img/color/${ms[2]}" alt="Icon" class="icon">
                                                <span class="text">${ms[1]}</span>
                                            </a>
                                            <%--                                        <a href="/sanpham/detail/${sp[0]}" class="btn btn-success" ></a>--%>
                                        </c:forEach>
                                        <p>Giá hiện hành: </p><br>
                                        <p>Size: </p>
                                        <c:forEach items="${listKC}" var="ms">
                                            <a class="btn btn-success" style="margin-left: 30px" >${ms[1]}</a>
                                        </c:forEach>
                                        <p>Số lượng: </p><br>

                                        <p> Trạng thái: </p><br>

                                    </div>
                                </div>
                                <div class="col-md-5">
                                    <div class="product-images">
                                        <div class="image-carousel">
                                            <div class="slide active">
                                                <img src="/assets/img/product/${sp.hinhAnhDaiDien}" style="padding-top: 60px" width="320px" height="450px">
                                            </div>
<%--                                            <div class="slide">--%>
<%--                                                <img src="image2.jpg" alt="Image 2">--%>
<%--                                            </div>--%>
                                        <c:forEach items="${listHA}" var="ha">
                                            <div class="slide">
                                                <img src="/assets/img/product/${ha.hinhAnh}" style="padding-top: 60px" width="320px" height="450px alt="Image 3">
                                            </div>
                                        </c:forEach>

                                        </div>
                                        <div class="controls">
                                            <button class="prev">&#10094;</button>
                                            <button class="next">&#10095;</button>
                                        </div>
                                    </div>
<%--                                        <img src="/assets/img/product/${sp.hinhAnhDaiDien}" style="padding-top: 60px" width="320px" height="450px">--%>
                                </div>

                            </div>
                        </div>



                        <a href="/ctsp/themmausac/${sp.id}"  type="submit" class="btn btn-primary">Thêm/chỉnh sửa màu sắc cho sản phẩm</a>
                        <a href="/ctsp/giamGia/${sp.id}"  type="submit" class="btn btn-primary">Giảm giá sản phẩm</a>
<%--                    </form>--%>

                </div>

            </div>
        </div>
    </div>

</section>






