
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>THƯƠNG HIỆU</h1>

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

                            <table class="table table-borderless datatable">
                                <thead>
                                <tr>

                                    <th>STT</th>
                                    <th>Tên</th>
                                    <th>Trạng thái</th>
                                    <th>Ngày tạo</th>
                                    <th>Ngày sửa gần nhất</th>
                                    <th>Action</th>

                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listTH}" var="mau" varStatus="tt">
                                    <tr>
                                        <th scope="row">${tt.index+1}</th>
                                        <td><strong>${mau.ten}</strong></td>
                                        <td><p style="color:${mau.trangThai==0?"chartreuse":"red"}">${mau.trangThai == 0 ? "Còn sử dụng" : "Ngừng sử dụng"}</p></td>
                                        <td>${mau.ngayTao}</td>
                                        <td>${mau.ngaySua}</td>
                                        <td>
                                            <a href="/thuonghieu/detail/${mau.id}" class="btn btn-success">Chi tiết</a>
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
                    <!-- Default Tabs -->
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="${active == null?"nav-link active":"nav-link"}" id="home-tab" data-bs-toggle="tab"
                                    data-bs-target="#home" type="button" role="tab" aria-controls="home"
                                    aria-selected="true">Chi tiết
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="${active == null?"nav-link":"nav-link active"}" id="profile-tab" data-bs-toggle="tab"
                                    data-bs-target="#profile" type="button" role="tab" aria-controls="profile"
                                    aria-selected="false">Thêm mới
                            </button>
                        </li>
                    </ul>

                    <%--update--%>
                    <div class="tab-content pt-2" id="myTabContent">
                        <div class="${active == null?"tab-pane fade show active":"tab-pane fade"}" id="home" role="tabpanel"
                             aria-labelledby="home-tab">
                            <form method="post" action="/thuonghieu/update" enctype="multipart/form-data">

                                <div>
                                    ID :
                                    <input class="form-control" readonly name="id" value="${th.id}">
                                </div>
                                <div>
                                    Tên :
                                    <input class="form-control" required name="ten" value="${th.ten}">
                                </div>
                                <c:if test="${errUpdate != null}" >
                                    <p style="color: red">${errUpdate}</p>
                                </c:if>

                                <div>
                                    Ngày tạo :
                                    <input class="form-control" readonly name="ngayTao" value="${th.ngayTao}">
                                </div>

                                <div>
                                    Ngày sửa gần nhất :
                                    <input class="form-control" readonly name="" value="${th.ngaySua}">
                                </div>

                                <div>
                                    Trạng thái :<br>
                                    <input type="radio" name="status" checked value="0" ${ th.trangThai == "0" ? "checked" : "" }>
                                    Còn sử dụng <br>
                                    <input type="radio" name="status" value="1" ${th.trangThai == "1" ? "checked" : "" }>
                                    Ngừng sử dụng
                                </div>
                                <c:if test="${err != null}" >
                                    <p style="color: red">${err}</p>
                                </c:if>
                                <input type="submit" class="btn btn-primary" value="Update" style="margin-top: 10px">
                            </form>
                        </div>

                        <%--create--%>
                        <div class="${active == null?"tab-pane fade":"tab-pane fade show active"}" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <form method="post" action="/thuonghieu/add" enctype="multipart/form-data">
                                <div>
                                    Tên :
                                    <input class="form-control" required name="ten">
                                </div>
                                <div>
                                    Trạng thái :<br>
                                    <input type="radio" name="status" checked value="0"> Còn sử dụng <br>
                                    <input type="radio" name="status" value="1"> Ngừng sử dụng
                                </div>
                                <c:if test="${errName != null}" >
                                    <p style="color: red">${errName}</p>
                                </c:if>

                                <input type="submit" class="btn btn-primary" value="Add" style="margin-top: 10px">
                            </form>
                        </div>
                        <%--detail--%>
                        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                        </div>
                    </div><!-- End Default Tabs -->


                </div>

            </div>
        </div><!-- End Recent Activity -->


    </div><!-- End Right side columns -->

    </div>
</section>







