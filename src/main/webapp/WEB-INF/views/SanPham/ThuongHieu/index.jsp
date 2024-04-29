<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                                    <th>Hình ảnh</th>
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
                                        <td><img src="/assets/img/brand/${mau.hinhAnh}" alt="/assets/img/brand/${mau.hinhAnh}"  width="100px" height="100px"></td>
                                        <td><p style="color:${mau.trangThai==0?"chartreuse":"red"}">${mau.trangThai == 0 ? "Còn sử dụng" : "Ngừng sử dụng"}</p></td>
                                        <td><fmt:formatDate value="${mau.ngayTao}" pattern="dd-MM-yyyy"/></td>
                                        <td><fmt:formatDate value="${mau.ngaySua}" pattern="dd-MM-yyyy"/></td>
                                        <td>
                                            <a onclick="detail(${mau.id})" class="btn btn-success">Chi tiết</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
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
                            <button  class="${active == null?"nav-link active":"nav-link"}" id="home-tab" data-bs-toggle="tab"
                                     data-bs-target="#home" type="button" role="tab" aria-controls="home"
                                     aria-selected="true">Chi tiết
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="${active == null?"nav-link":"nav-link active"}"  id="profile-tab" data-bs-toggle="tab"
                                    data-bs-target="#profile" type="button" role="tab" aria-controls="profile"
                                    aria-selected="false">Thêm mới
                            </button>
                        </li>
                    </ul>
                    <%--update--%>
                    <div class="tab-content pt-2" id="myTabContent">
                        <div class="${active == null?"tab-pane fade show active":"tab-pane fade"}" id="home" role="tabpanel"
                             aria-labelledby="home-tab">
                            <div>
                                ID :
                                <input class="form-control" readonly id="idUpdate">
                            </div>

                            <div>
                                Tên :
                                <input required class="form-control" id="tenUpdate">
                            </div>
                            <div>
                                Hình ảnh :
                                <img id="imageUpdate1" src="" height="100px" width="100px">
                                <input type="file" name="image" accept="image/*" class="form-control"
                                       id="imageUpdate">
                            </div>
                            <div>
                                Ngày tạo :
                                <input class="form-control" id="ngayTaoUpdate">
                            </div>

                            <div>
                                Ngày sửa gần nhất :
                                <input class="form-control" id="ngaySuaUpdate">
                            </div>

                            <div id="trangThaiUpdate">
                                Trạng thái :<br>
                                <input type="radio" name="trangThaiUpdate" checked value="0">
                                Còn sử dụng <br>
                                <input type="radio" name="trangThaiUpdate" value="1">
                                Ngừng sử dụng
                            </div>
                            <input type="submit" class="btn btn-primary" onclick="update()" value="Update" style="margin-top: 10px">
                        </div>

                        <%--create--%>
                        <div class="${active == null?"tab-pane fade":"tab-pane fade show active"}" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <div>
                                Tên :
                                <input required class="form-control" id="tenAdd">
                            </div>
                            <div>
                                Hình ảnh :
                                <input  type="file" id="imageAdd" accept="image/*" class="form-control"></div>
                            <div id="trangThaiAdd">
                                Trạng thái :<br>
                                <input type="radio" name="trangThaiAdd" checked value="0"> Còn sử dụng <br>
                                <input type="radio" name="trangThaiAdd" value="1"> Ngừng sử dụng
                            </div>
                            <input type="submit" class="btn btn-primary" onclick="add()" value="Add" style="margin-top: 10px">
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function detail(id){
        $.ajax({
            type: "GET",
            url: "/thuonghieu/detail/"+id,
            success: function (response) {
                $('#tenUpdate').val(response.ten);
                $('#idUpdate').val(response.id);
                $('#ngaySuaUpdate').val(response.ngaySua);
                $('#ngayTaoUpdate').val(response.ngayTao);
                $('#imageUpdate1').attr('src', '/assets/img/brand/' + response.hinhAnh);
                if (response.trangThai == 0) {
                    $('input[name="trangThaiUpdate"][value="0"]').prop('checked', true);
                } else if (response.trangThai == 1) {
                    $('input[name="trangThaiUpdate"][value="1"]').prop('checked', true);
                }

            }});
    }
    function update() {
        var trangThaiValue = $('input[name="trangThaiUpdate"]:checked', '#trangThaiUpdate').val();
        var id = document.getElementById('idUpdate').value;
        var ten = document.getElementById('tenUpdate').value;
        var hinhAnh = document.getElementById('imageUpdate').files[0]; // Sử dụng files[0] để lấy tệp tin đã chọn
        if (id.trim() === '') {
            alert("Vui lòng chọn đối tượng ở danh sách");
            return;
        } else {
            if (ten.trim() === '' || ten.trim() === '' || hinhAnh == null) {
                alert("Vui lòng nhập đầy đủ thông tin");
                return;
            } else {
                var formData = new FormData();
                formData.append('id', id);
                formData.append('ten', ten);
                formData.append('image', hinhAnh);
                formData.append('trangThai', trangThaiValue);

                $.ajax({
                    type: "POST",
                    url: "/thuonghieu/update",
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
                                window.location.href = "/thuonghieu/index";
                            }
                        }

                    }
                });
            }
        }
    }
    function add() {
        var trangThaiValue = $('input[name="trangThaiAdd"]:checked', '#trangThaiAdd').val();
        var ten = document.getElementById('tenAdd').value;
        var hinhAnh = document.getElementById('imageAdd').files[0];
        if (ten.trim() ===''||hinhAnh ==null){
            alert("Vui lòng nhập đầy đủ thông tin")
            return;
        }else {
            var formData = new FormData();
            formData.append('ten', ten);
            formData.append('image', hinhAnh);
            formData.append('trangThai', trangThaiValue);
            $.ajax({
                type: "POST",
                url: "/thuonghieu/add",
                data: formData,
                contentType: false, // Không cần set contentType
                processData: false, // Không cần xử lý dữ liệu
                success: function (response) {
                    if (response === "errTrungTen") {
                        alert("Tên trùng trong danh sách. Vui lòng chọn tên khác !!!");
                        return;
                    } else {
                        var cf = confirm("Bạn muốn thêm mới ???");
                        if (cf == true) {
                            alert("Thêm mới thành công");
                            window.location.href = "/thuonghieu/index";
                        }
                    }

                }
            });
        }
    };
</script>