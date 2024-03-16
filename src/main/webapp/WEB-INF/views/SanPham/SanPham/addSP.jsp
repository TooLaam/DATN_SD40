
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="section dashboard">
    <div class="row">

        <div class="card">
            <div class="card-body">

                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item active"><h1>Thêm sản phẩm</h1></li>
                        <li style="margin-top: 20px" class="breadcrumb-item"><a href="/sanpham/index">Home</a></li>
<%--                        <li style="margin-top: 20px" class="breadcrumb-item"><a href="/ctsp/hienthi/${sp.id}">Chi tiết sản phẩm</a></li>--%>
                    </ol>
                </nav>
                <%--                    <form action="/sanpham/update" method="post" enctype="multipart/form-data">--%>
                <div class="row" style="margin-top: 50px;">

                    <div class="product-details">
                        <div class="col-md-7">
                            <form action="/ctsp/addSP" method="post" enctype="multipart/form-data" enctype="multipart/form-data">
                                <div class="product-info">

                                    <label  class="form-label">Tên sản phẩm: </label>
                                    <input required type="text" class="form-control" name="ten">

                                    <label  class="form-label">Mô tả: </label>
                                    <input required type="text" class="form-control"  name="moTa">

                                    <p>Thương hiệu:  </p>
                                    <select  class="form-select" aria-label="Default select example"name="thuongHieu" >
                                        <c:forEach items="${listTH}" var="ms">
                                            <option value="${ms.id}" >
                                                ${ms.ten}
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <p>Thể loại: </p>
                                    <select  class="form-select" aria-label="Default select example"name="theLoai" >
                                        <c:forEach items="${listTL}" var="ms">
                                            <option value="${ms.id}" >
                                                    ${ms.ten}
                                            </option>
                                        </c:forEach>
                                    </select>

                                    <div>
                                        Hình ảnh đại diện :
                                        <input required type="file" name="image" accept="image/*" class="form-control"
                                        >
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






