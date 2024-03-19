
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="section dashboard">
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item active"><h1>Thêm màu sắc cho sản phẩm</h1></li>
            <li style="margin-top: 20px" class="breadcrumb-item"><a href="/sanpham/index">Home</a></li>
            <li style="margin-top: 20px" class="breadcrumb-item"><a href="/ctsp/hienthi/${sp.id}">Chi tiết sản phẩm</a></li>
        </ol>
    </nav>
    <div class="row">
        <!-- Left side columns -->
        <div class="col-lg-8">
            <div class="row">
                <!-- Recent Sales -->
                <div class="col-12">
                    <div class="card recent-sales overflow-auto">


                        <div class="card-body">

                            <form action="/ctsp/addMS" method="post" enctype="multipart/form-data">
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
                                        <a href="/ctsp/updateMS/${sp.id}/${ms[0]}" class="btn btn-light" style="margin-left: 30px">
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

                                    <label  class="form-label">Giá hiện hành</label>
                                    <input type="number" required class="form-control"  name="giaHienHanh">


                                    <div>
                                        Hình ảnh :
                                        <input  type="file" name="image" accept="image/*" required class="form-control"
                                        >
                                    </div>
                                    <button type="submit" class="btn btn-primary">Thêm</button>

                                </div>
                            </form>

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
                    <!-- Default Tabs -->
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active" id="home-tab" data-bs-toggle="tab"
                                    data-bs-target="#home" type="button" role="tab" aria-controls="home"
                                    aria-selected="true">Chi tiết
                            </button>
                        </li>
                    </ul>

                    <%--update--%>
                    <div class="tab-content pt-2" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel"
                             aria-labelledby="home-tab">
                            <form action="/ctsp/updateHA/${sp.id}" method="post" enctype="multipart/form-data">
                                <input  type="text" class="form-control" style="display: none" readonly value="${listHA.mau_sac.id}" name="idms">

                                <label  class="form-label">Màu sắc muốn cập nhật</label>
                                <input  type="text" class="form-control" required readonly value="${listHA.mau_sac.ten}" name="">
                                <c:if test="${err != null}" >
                                    <p style="color: red">${err}</p>
                                </c:if>
                                <label  class="form-label">Giá hiện hành</label>
                                <input type="number" class="form-control" required value="${listHA.giaHienHanh}" name="giaHienHanh">
                                <img src="/assets/img/product/${listHA.hinhAnh}" style="padding-top: 60px" width="100px" height="150px">
                                <input required type="file" name="image" accept="image/*" class="form-control"
                                       value="${listHA.hinhAnh}">
                                <button class="btn btn-primary">Cập nhật</button>
                            </form>
                        </div>

                        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                        </div>
                    </div><!-- End Default Tabs -->


                </div>


            </div><!-- End Recent Activity -->
        </div>

    </div><!-- End Right side columns -->

</section>













