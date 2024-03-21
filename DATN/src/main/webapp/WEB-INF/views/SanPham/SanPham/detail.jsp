
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
                                <li class="breadcrumb-item active"><h1>Thông tin sản phẩm</h1></li>
                                <li style="margin-top: 20px" class="breadcrumb-item"><a href="/sanpham/index">Home</a></li>
                            </ol>
                        </nav>
                        <form action="/sanpham/update" method="post" enctype="multipart/form-data">
                        <div class="row" style="margin-top: 50px;">
                            <div class="col-md-6">
                                <b><label class="form-label">ID:</label></b>
                                <input type="text" class="form-control" name="id" value="${sp.id}" readonly />

                                <b><label class="form-label">Mã:</label></b>
                                <input type="text" readonly class="form-control" value="${sp.ma}" name="ma" />

                                <b> <label class="form-label">Tên sản phẩm</label></b>
                                <input type="text" required class="form-control" value="${sp.ten}" name="ten" />
                                <c:if test="${err != null}" >
                                    <p style="color: red">${err}</p>
                                </c:if>


                                <b> <label class="form-label">Mô tả: </label></b>
                                <input required type="text" class="form-control" style="width: 450px;height: 150px;"  value="${sp.moTa}" name="moTa" />


                                <b><label class="form-label">Ngày tạo: </label></b>
                                <label  name="ngayTao">${sp.ngayTao}</label><br>

                                <b><label class="form-label">Ngày sửa gần nhất: </label></b>
                                <label  name="ngaySua">${sp.ngaySua}</label><br>

                            </div>

                            <div class="col-md-6">
                                <b><label class="form-label">Trạng thái: </label></b>

                                <input class="form-check-input" type="radio" ${sp.trangThai == 0?'checked':''}  value="0" name="trangThai" >
                                <label class="form-label">Còn sử dụng         </label>

                                <input class="form-check-input" type="radio" ${sp.trangThai == 1?'checked':''} value="1" name="trangThai" >
                                <label class="form-label">Ngừng sử dụng</label><br><br>
                                <b><label class="form-label">Hình ảnh đại diện: </label></b>
                                <img src="/assets/img/product/${sp.hinhAnhDaiDien}" height="100px" width="100px">

                                <input required type="file" name="image" accept="image/*" class="form-control">



                                <b><label class="form-label">Thương hiệu</label></b>
                                <select class="form-select" aria-label="Default select example" name="thuongHieu">
                                    <option value="${sp.thuongHieu.id}">${sp.thuongHieu.ten} </option>
                                    <c:forEach items="${listTH}" var="th">
                                        <option value="${th.id}">${th.ten}</option>
                                    </c:forEach>

                                </select>

                                <b><label class="form-label">Thể loại</label></b>
                                <select class="form-select" aria-label="Default select example" name="theLoai">
                                    <option value="${sp.theLoai.id}">${sp.theLoai.ten} </option>
                                    <c:forEach items="${listTL}" var="tl">
                                        <option value="${tl.id}">${tl.ten}</option>
                                    </c:forEach>

                                </select>
                            </div>
                        </div>



                        <button type="submit" class="btn btn-primary">Update</button>
                    </form>

                </div>

            </div>
        </div>
    </div>

</section>






