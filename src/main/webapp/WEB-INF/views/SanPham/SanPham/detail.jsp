
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
<%--                        <form action="/sanpham/update" method="post" enctype="multipart/form-data">--%>
                        <div class="row" style="margin-top: 50px;">
                            <div class="col-md-6">
                                <b><label class="form-label">ID:</label></b>
                                <input type="text" class="form-control" id="id" value="${sp.id}" readonly />

                                <b><label class="form-label">Mã:</label></b>
                                <input type="text" readonly class="form-control" value="${sp.ma}"  />

                                <b> <label class="form-label">Tên sản phẩm</label></b>
                                <input type="text" required class="form-control" value="${sp.ten}" id="ten" />

                                <b> <label class="form-label">Mô tả: </label></b>
                                <input required type="text" class="form-control" style="width: 450px;height: 150px;"  value="${sp.moTa}" id="moTa" />

                                <b><label class="form-label">Ngày tạo: </label></b>
                                <label  name="ngayTao">${sp.ngayTao}</label><br>

                                <b><label class="form-label">Ngày sửa gần nhất: </label></b>
                                <label  name="ngaySua">${sp.ngaySua}</label><br>

                            </div>

                            <div class="col-md-6">
                                <div id="trangThaiUpdate">
                                    <b><label class="form-label">Trạng thái: </label></b>
                                    <input class="form-check-input" type="radio" ${sp.trangThai == 0?'checked':''}  value="0" name="trangThai" >
                                    <label class="form-label">Còn sử dụng         </label>

                                    <input class="form-check-input" type="radio" ${sp.trangThai == 1?'checked':''} value="1" name="trangThai" >
                                    <label class="form-label">Ngừng sử dụng</label><br><br>
                                </div>

                                <b><label class="form-label">Hình ảnh đại diện: </label></b>
                                <img src="/assets/img/product/${sp.hinhAnhDaiDien}" height="100px" width="100px">
                                <input required type="file" id="image" accept="image/*" class="form-control">

                                <b><label class="form-label">Thương hiệu</label></b>
                                <select class="form-select" aria-label="Default select example" id="thuongHieu">
                                    <option value="${sp.thuongHieu.id}">${sp.thuongHieu.ten} </option>
                                    <c:forEach items="${listTH}" var="th">
                                        <option value="${th.id}">${th.ten}</option>
                                    </c:forEach>
                                </select>

                                <b><label class="form-label">Thể loại</label></b>
                                <select class="form-select" aria-label="Default select example" id="theLoai">
                                    <option value="${sp.theLoai.id}">${sp.theLoai.ten} </option>
                                    <c:forEach items="${listTL}" var="tl">
                                        <option value="${tl.id}">${tl.ten}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <button type="submit" onclick="update()" class="btn btn-primary">Update</button>


                </div>

            </div>
        </div>
    </div>

</section>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function update() {
        var trangThaiValue = $('input[name="trangThai"]:checked', '#trangThaiUpdate').val();
        var id = document.getElementById('id').value;
        var ten = document.getElementById('ten').value;
        var moTa = document.getElementById('moTa').value;
        var thuongHieu = document.getElementById('thuongHieu').value;
        var theLoai = document.getElementById('theLoai').value;
        var hinhAnh = document.getElementById('image').files[0]; // Sử dụng files[0] để lấy tệp tin đã chọn

            if (ten.trim() === '' || ten.trim() === '' || hinhAnh == null|| moTa.trim()==='') {
                alert("Vui lòng nhập đầy đủ thông tin");
                return;
            } else {
                var formData = new FormData();
                formData.append('id', id);
                formData.append('ten', ten);
                formData.append('image', hinhAnh);
                formData.append('trangThai', trangThaiValue);
                formData.append('moTa', moTa);
                formData.append('thuongHieu', thuongHieu);
                formData.append('theLoai', theLoai);

                $.ajax({
                    type: "POST",
                    url: "/sanpham/update",
                    data: formData,
                    contentType: false, // Không cần set contentType
                    processData: false, // Không cần xử lý dữ liệu
                    success: function (response) {
                        if (response === "errTrungTen") {
                            alert("Tên trùng trong danh sách. Vui lòng chọn tên khác !!!");
                            return;
                        } else {
                            var cf = confirm("Bạn muốn cập nhập ???");
                            if (cf == true) {
                                alert("Cập nhật thành công");
                                window.location.href = "/sanpham/index";
                            }
                        }

                    }
                });
            }

    }
</script>






