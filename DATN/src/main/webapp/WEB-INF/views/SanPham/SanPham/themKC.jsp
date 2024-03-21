<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="section dashboard">
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item active"><h1>Thêm/Chỉnh sửa kích thước cho sản phẩm</h1></li>
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

                            <label  class="form-label">ID sản phẩm: </label>
                            <input type="text" class="form-control" readonly value="${sp.id}" name="id">

                            <label  class="form-label">Mã: </label>
                            <input type="text" class="form-control" readonly value="${sp.ma}" name="ma">

                            <label  class="form-label">Tên: </label>
                            <input type="text" class="form-control" readonly value="${sp.ten}" name="ten">

                            <label  class="form-label">Thương hiệu: </label>
                            <input type="text" class="form-control" readonly value="${sp.thuongHieu.ten}" name="thuongHieu">

                            <label  class="form-label">Thể loại: </label>
                            <input type="text" class="form-control" readonly value="${sp.theLoai.ten}" name="theLoai">

                            <label  class="form-label">Mô tả: </label>
                            <input type="text" class="form-control" readonly value="${sp.moTa}" name="moTa">

                            <label  class="form-label">Màu sắc: </label>
                            <a class="btn btn-light" style="margin-left: 30px">
                                <img style="width: 20px;height: 20px" src="/assets/img/color/${ms.hinhAnh}" alt="Icon" class="icon">
                                <span class="text">${ms.ten}</span>
                            </a><br>

                            <label  class="form-label">Kích cỡ hiện có: </label>
                            <c:forEach items="${listKCDangCo}" var="kc1">
                                <a href="/ctsp/hienthiupdateKC/${sp.id}/${ms.id}/${kc1.kichCo.id}" class="btn btn-primary" style="margin-left: 30px">${kc1.kichCo.ten} </a>
                            </c:forEach>

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
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="profile-tab" data-bs-toggle="tab"
                                    data-bs-target="#profile" type="button" role="tab" aria-controls="profile"
                                    aria-selected="false">Thêm mới
                            </button>
                        </li>
                    </ul>

                    <%--update--%>
                    <div class="tab-content pt-2" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel"
                             aria-labelledby="home-tab">
                            <form action="/ctsp/updateKC/${sp.id}/${ms.id}" method="post">
                                <input  type="text" class="form-control" style="display: none" readonly value="${ctsp.kichCo.id}" name="idkc">

                                <div>
                                    Kích cỡ hiện tại :
                                    <input type="text" class="form-control" required readonly name="kichCo" value="${ctsp.kichCo.ten}">
                                </div>
                                <c:if test="${err != null}" >
                                    <p style="color: red">${err}</p>
                                </c:if>
                                <div>
                                    Số lượng :
                                    <input type="number" class="form-control" required title="Vui lòng nhập số lượng !" name="soLuong" value="${ctsp.soLuong}">

                                </div>

                                <div>
                                    Trạng thái : <br>
                                    <input type="radio" name="trangThai" checked value="0" ${ ctsp.trangThai == "0" ? "checked" : "" }>
                                    Còn sử dụng <br>
                                    <input type="radio" name="trangThai" value="1" ${ctsp.trangThai == "1" ? "checked" : "" }>
                                    Ngừng sử dụng
                                </div>
                                <input type="submit" class="btn btn-primary" value="Update" style="margin-top: 10px">
                            </form>
                        </div>

                        <%--create--%>
                        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <form method="post" action="/ctsp/addKC/${sp.id}/${ms.id}">
                                <div>
                                    Kích thước muốn thêm :
                                    <select  class="form-select" aria-label="Default select example"name="kichCo" >
                                        <c:forEach items="${listKCChuaCo}" var="kc">
                                            <option value="${kc.id}" >
                                                    ${kc.ten}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    Số lượng :
                                    <input type="number" name="soLuong"  required class="form-control"></div>



                                        Trạng thái :<br>
                                <div>
                                        <input type="radio" name="trangThai" checked value="0"> Còn bán<br>
                                        <input type="radio" name="trangThai" value="1"> Ngừng bán
                                    </div>

                                    <input type="submit" class="btn btn-primary" value="Add" style="margin-top: 10px">
                            </form>
                        </div>
                        <%--detail--%>
                        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                        </div>
                    </div><!-- End Default Tabs -->


                </div>


        </div><!-- End Recent Activity -->
    </div>

    </div><!-- End Right side columns -->

</section>

