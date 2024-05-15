<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>KHÁCH HÀNG</h1>
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
                                    <th>Mã</th>
                                    <th>Họ tên</th>
                                    <th>Ngày sinh</th>
                                    <th>SĐT</th>
                                    <th>Giới tính</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listKH}" var="mau" varStatus="tt">
                                    <tr>
                                        <th scope="row">${tt.index+1}</th>
                                        <td>${mau.ma}</td>
                                        <td><strong>${mau.hoTen}</strong></td>
                                        <td><fmt:formatDate value="${mau.ngaySinh}" pattern="dd-MM-yyyy"/></td>
                                        <td>${mau.sdt}</td>
                                        <td>${mau.gioiTinh ==0 ?'Nam' : 'Nữ'}</td>
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
                            <div style="display: none">
                                ID :
                                <input class="form-control" style="display: none" readonly id="idUpdate">
                            </div>

                            <div>
                                Mã khách hàng :
                                <input class="form-control" readonly id="maUpdate">
                            </div>

                            <div>
                                Họ Tên :
                                <input class="form-control" id="tenUpdate">
                            </div>

                            <div>
                                Ngày sinh :
                                <input type="date" class="form-control" id="ngaySinhUpdate">
                            </div>

                            <div>
                                Số điện thoại :
                                <input type="number" class="form-control" id="sdtUpdate">
                            </div>

                            <div id="gioiTinhUpdate">
                                Giới tính :<br>
                                <div style="display: flex">
                                    <div style="margin-right: 30px">
                                        <input type="radio" name="gioiTinhUpdate" checked value="0">Nam
                                    </div>
                                    <div>
                                        <input type="radio" name="gioiTinhUpdate" value="1">Nữ
                                    </div>
                                </div>
                            </div>

                            <div>
                                Email :
                                <input type="email" class="form-control" id="emailUpdate">
                            </div>

                            <div>
                                Tài khoản :
                                <input type="text" class="form-control" id="taiKhoanUpdate">
                            </div>

                            <div>
                                Mật khẩu :
                                <input type="password" class="form-control" id="matKhauUpdate">
                            </div>


                            <input type="submit" class="btn btn-primary" onclick="update()" value="Update" style="margin-top: 10px">
                        </div>

                        <%--create--%>
                        <div class="${active == null?"tab-pane fade":"tab-pane fade show active"}" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <div>
                                Họ Tên :
                                <input class="form-control" id="tenAdd">
                            </div>

                            <div>
                                Ngày sinh :
                                <input type="date" class="form-control" id="ngaySinhAdd">
                            </div>

                            <div>
                                Số điện thoại :
                                <input type="number" class="form-control" id="sdtAdd">
                            </div>

                            <div id="gioiTinhAdd">
                                Giới tính :<br>
                                <div style="display: flex">
                                    <div style="margin-right: 30px">
                                        <input type="radio" name="gioiTinhAdd"  checked value="0">Nam
                                    </div>
                                    <div>
                                        <input type="radio" name="gioiTinhAdd" value="1">Nữ
                                    </div>
                                </div>
                            </div>

                            <div>
                                Email :
                                <input type="email" class="form-control" id="emailAdd">
                            </div>

                            <div>
                                Tài khoản :
                                <input type="text" class="form-control" id="taiKhoanAdd">
                            </div>

                            <div>
                                Mật khẩu :
                                <input type="password" class="form-control" id="matKhauAdd">
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
            url: "/khachhang/detail/"+id,
            success: function (response) {
                $('#idUpdate').val(response.id);
                $('#maUpdate').val(response.ma);
                $('#tenUpdate').val(response.hoTen);
                $('#ngaySinhUpdate').val(response.ngaySinh);
                $('#sdtUpdate').val(response.sdt);
                $('#emailUpdate').val(response.email);
                $('#taiKhoanUpdate').val(response.taiKhoan);
                $('#matKhauUpdate').val(response.matKhau);
                if (response.gioiTinh == 0) {
                    $('input[name="gioiTinhUpdate"][value="0"]').prop('checked', true);
                } else if (response.gioiTinh == 1) {
                    $('input[name="gioiTinhUpdate"][value="1"]').prop('checked', true);
                }
            }});
    }
    function update() {
        var gioiTinhValue = $('input[name="gioiTinhUpdate"]:checked', '#gioiTinhUpdate').val();
        var id = document.getElementById('idUpdate').value;
        var hoTen = document.getElementById('tenUpdate').value;
        var ngaySinh = document.getElementById('ngaySinhUpdate').value;
        var sdt = document.getElementById('sdtUpdate').value;
        var email = document.getElementById('emailUpdate').value;
        var taiKhoan = document.getElementById('taiKhoanUpdate').value;
        var matKhau = document.getElementById('matKhauUpdate').value;
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        var phoneRegex = /^(032|033|034|035|036|037|038|039|096|097|098|086|083|084|085|081|082|088|091|094|070|079|077|076|078|090|093|089|056|058|092|059|099)[0-9]{7}$/; // Định dạng số điện thoại ở Việt Nam
        var currentDate = new Date();
        var birthdayDate = new Date(ngaySinh);

        if (id.trim() === '') {
            alert("Vui lòng chọn đối tượng ở danh sách");
            return;
        } else {
            if (hoTen.trim() === '' || ngaySinh.trim() === '' || sdt == null|| email == null|| taiKhoan == null|| matKhau == null) {
                alert("Vui lòng nhập đầy đủ thông tin");
                return;
            } else {
                if (sdt === '' || phoneRegex.test(sdt) == false) {
                    alert('Vui lòng nhập lại số điện thoại') ;
                    return;
                }
                if (email === '' || emailRegex.test(email) == false) {
                    alert('Vui lòng nhập lại email') ;
                    return;
                }
                if (birthdayDate>currentDate){
                    alert("Ngày sinh không được lớn hơn ngày hiện tại !!!")
                    return;
                }else {
                    var cf = confirm("Bạn muốn cập nhập ???");
                    if (cf == true) {
                        var formData = new FormData();
                        formData.append('id', id);
                        formData.append('hoTen', hoTen);
                        formData.append('ngaySinh',ngaySinh);
                        formData.append('sdt',sdt);
                        formData.append('email',email);
                        formData.append('taiKhoan',taiKhoan);
                        formData.append('matKhau',matKhau);
                        formData.append('gioiTinh',gioiTinhValue);
                        $.ajax({
                            type: "POST",
                            url: "/khachhang/update",
                            data: formData,
                            contentType: false, // Không cần set contentType
                            processData: false, // Không cần xử lý dữ liệu
                            success: function (response) {
                                if (response === "errTrungTen") {
                                    alert("Tài khoản trùng trong danh sách. Vui lòng chọn tài khoản khác !!!");
                                    return;
                                }else if (response === "errTrungEmail"){
                                    alert("Email trùng trong danh sách. Vui lòng chọn email khác !!!");
                                    return;
                                } else {
                                    alert("Cập nhật thành công");
                                    window.location.href = "/khachhang/index";
                                }

                            }
                        });
                    }}}
        }
    }
    function add() {
        var gioiTinhValue = $('input[name="gioiTinhAdd"]:checked', '#gioiTinhAdd').val();
        var hoTen = document.getElementById('tenAdd').value;
        var ngaySinh = document.getElementById('ngaySinhAdd').value;
        var sdt = document.getElementById('sdtAdd').value;
        var email = document.getElementById('emailAdd').value;
        var taiKhoan = document.getElementById('taiKhoanAdd').value;
        var matKhau = document.getElementById('matKhauAdd').value;
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        var phoneRegex = /^(032|033|034|035|036|037|038|039|096|097|098|086|083|084|085|081|082|088|091|094|070|079|077|076|078|090|093|089|056|058|092|059|099)[0-9]{7}$/; // Định dạng số điện thoại ở Việt Nam
        var currentDate = new Date();
        var birthdayDate = new Date(ngaySinh);

        if (hoTen.trim() === '' || ngaySinh.trim() === '' || sdt == null|| email == null|| taiKhoan == null|| matKhau == null) {
            alert("Vui lòng nhập đầy đủ thông tin");
            return;
        } else {
            if (sdt === '' || phoneRegex.test(sdt) == false) {
                alert('Vui lòng nhập lại số điện thoại') ;
                return;
            }
            if (email === '' || emailRegex.test(email) == false) {
                alert('Vui lòng nhập lại email') ;
                return;
            } if (birthdayDate>currentDate){
                alert("Ngày sinh không được lớn hơn ngày hiện tại !!!")
                return;
            }else {
                var cf = confirm("Bạn muốn Thêm ???");
                if (cf == true) {
                    var formData = new FormData();
                    formData.append('hoTen', hoTen);
                    formData.append('ngaySinh',ngaySinh);
                    formData.append('sdt',sdt);
                    formData.append('email',email);
                    formData.append('taiKhoan',taiKhoan);
                    formData.append('matKhau',matKhau);
                    formData.append('gioiTinh',gioiTinhValue);

                    $.ajax({
                        type: "POST",
                        url: "/khachhang/add",
                        data: formData,
                        contentType: false, // Không cần set contentType
                        processData: false, // Không cần xử lý dữ liệu
                        success: function (response) {
                            if (response === "errTrungTen") {
                                alert("Tài khoản trùng trong danh sách. Vui lòng chọn tài khoản khác !!!");
                                return;
                            }else if (response === "errTrungEmail"){
                                alert("Email trùng trong danh sách. Vui lòng chọn email khác !!!");
                                return;
                            }
                            else {
                                alert("Thêm thành công");
                                window.location.href = "/khachhang/index";
                            }

                        }
                    });
                }}}

    };

</script>