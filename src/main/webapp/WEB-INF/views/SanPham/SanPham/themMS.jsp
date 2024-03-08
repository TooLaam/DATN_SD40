
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="section dashboard">
    <div class="row">

            <div class="card">
                <div class="card-body">

                    <nav>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item active"><h1>Thêm màu sắc cho sản phẩm</h1></li>
                            <li style="margin-top: 20px" class="breadcrumb-item"><a href="/sanpham/index">Home</a></li>
                            <li style="margin-top: 20px" class="breadcrumb-item"><a href="/ctsp/hienthi/${sp.id}">Chi tiết sản phẩm</a></li>
                        </ol>
                    </nav>
                    <%--                    <form action="/sanpham/update" method="post" enctype="multipart/form-data">--%>
                    <div class="row" style="margin-top: 50px;">

                        <div class="product-details">
                            <div class="col-md-7">
                                <form action="/ctsp/updateMS" method="post" enctype="multipart/form-data">
                                    <div class="product-info">


                                        <label  class="form-label">ID sản phẩm</label>
                                        <input type="text" class="form-control" readonly value="${sp.id}" name="id">

                                        <label  class="form-label">Mã</label>
                                        <input type="text" class="form-control" readonly value="${sp.ma}" name="ma">

                                        <label  class="form-label">Tên</label>
                                        <input type="text" class="form-control" readonly value="${sp.ten}" name="ten">

                                        <label  class="form-label">Thương hiệu</label>
                                        <input type="text" class="form-control" readonly value="${sp.thuongHieu.ten}" name="thuongHieu">

                                        <label  class="form-label">Thể loại</label>
                                        <input type="text" class="form-control" readonly value="${sp.theLoai.ten}" name="theLoai">

                                        <label  class="form-label">Mô tả</label>
                                        <input type="text" class="form-control" readonly value="${sp.moTa}" name="moTa">

                                        <p>Màu sắc đang có: </p>
                                        <c:forEach items="${listMSDangCo}" var="ms">
                                            <a class="btn btn-light" style="margin-left: 30px">
                                                <img style="width: 20px;height: 20px" src="/assets/img/color/${ms[2]}" alt="Icon" class="icon">
                                                <span class="text">${ms[1]}</span>
                                            </a>
                                            <%--                                        <a href="/sanpham/detail/${sp[0]}" class="btn btn-success" ></a>--%>
                                        </c:forEach>
                                        <p>Màu sắc muốn thêm: </p>
                                        <select  class="form-select" aria-label="Default select example"name="mauSac" >
                                            <c:forEach items="${listMSChuaCo}" var="ms">
                                                <option value="${ms.id}" >
                                                    <img style="width: 20px;height: 20px" src="/assets/img/color/${ms.hinhAnh}" alt="Icon" class="icon">
                                                    <span class="text">${ms.ten}</span>
                                                </option>
                                            </c:forEach>
                                        </select>

                                        <div>
                                            Hình ảnh :
                                            <input type="file" name="image" accept="image/*" class="form-control"
                                                   >
                                        </div>
                                        <button type="submit" class="btn btn-primary">Thêm</button>

                                    </div>
                                </form>

                            </div>


                        </div>
                    </div>


                </div>

            </div>

    </div>

</section>






