<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style><%@include file="style.css"%></style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <title>Đăng ký</title>
</head>

<body>
<div class="container">
    <nav class="nav-search">
        <div class="row">
            <div class="col-md-2 col-4">
                <a href="/home"><img src="/assets/img/SD40.png" class="logo" alt="logo" />
                </a>
            </div>

        </div>
    </nav>
</div>
<div id="nav-bottom"></div>
<div class="main">
    <div class="container">
        <div class="row">
            <div class="col-6"></div>
            <div class="col-5">
                <div class="form">
                    <h3>Đăng ký</h3>
                    <div class="text-log">
                        <span>Bạn đã có tài khoản ?</span>
                        <a href="/login" class="link-text">Đăng nhập</a>
                    </div>
                    <form id="AddKhachHang" action="/addKhachHangCus" method="post">
                        <div class="mb-3">
                            <input class="form-control" id="exampleInputFullName" placeholder="Họ tên" name="ten" />
                        </div>
                        <div class="mb-3">
                            <input class="form-control" id="exampleInputUsername" placeholder="Tài khoản" name="taiKhoan" />
                        </div>
                        <div class="mb-3">
                            <input type="password" class="form-control"  id="exampleInputPassword"
                                   placeholder="Mật khẩu" name="matKhau" />
                        </div>
                        <div class="mb-3">
                            <input type="text" oninput="layThongTinUpdate()" class="form-control" id="exampleInputPhoneNumber"
                                   placeholder="Số điện thoại" name="sdt" />
                            <span class="error-message" style="display: none;color: red;margin-left: 20px" id="errSdt">Số điện thoại không hợp lệ</span>
                        </div>
                        <div class="mb-3">
                            <input type="email" oninput="layThongTinUpdate()" class="form-control" id="exampleInputEmail" placeholder="Email" name="email" />
                            <span class="error-message" style="display: none;color: red;margin-left: 20px" id="errEmail">Email không hợp lệ</span>
                        </div>
                        <div class="mb-3">
                            <input type="date" class="form-control" id="exampleInputBirthday"
                                   placeholder="Ngày sinh" name="ngaySinh">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Giới tính: </label>
                            <input type="radio" class="radio-input" name="gioiTinh" value="1"  checked id="male" autocomplete="off">
                            <label class="radio-label" for="male">Nam</label>

                            <input type="radio" class="radio-input" value="0" name="gioiTinh" id="female" autocomplete="off">
                            <label class="radio-label" for="female">Nữ</label>
                        </div>

                        <div class="d-grid">
                            <button id="capNhat" type="submit" class="btn signup">Đăng ký</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-1"></div>
        </div>
    </div>
    <br />
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function layThongTinUpdate (){

        var email = document.getElementById('exampleInputEmail').value;
        var sdt = document.getElementById('exampleInputPhoneNumber').value;
        var phoneRegex = /^(032|033|034|035|036|037|038|039|096|097|098|086|083|084|085|081|082|088|091|094|070|079|077|076|078|090|093|089|056|058|092|059|099)[0-9]{7}$/; // Định dạng số điện thoại ở Việt Nam
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (phoneRegex.test(sdt)||sdt == ''){
            document.getElementById('errSdt').style.display='none';
            return;
        }else {
            document.getElementById('errSdt').style.display='block';
        }

        if (emailRegex.test(email) || email == '') {
            document.getElementById('errEmail').style.display='none';
            return;
        }
        else {
            document.getElementById('errEmail').style.display='block';
        }

    }

    $("#capNhat").click(function(e) {
        e.preventDefault();
        var ten = document.getElementById('exampleInputFullName').value;
        var ngaySinh = document.getElementById('exampleInputBirthday').value;
        var email = document.getElementById('exampleInputEmail').value;
        var sdt = document.getElementById('exampleInputPhoneNumber').value;
        var taiKhoan = document.getElementById('exampleInputUsername').value;
        var matKhau = document.getElementById('exampleInputPassword').value;
        var phoneRegex = /^(032|033|034|035|036|037|038|039|096|097|098|086|083|084|085|081|082|088|091|094|070|079|077|076|078|090|093|089|056|058|092|059|099)[0-9]{7}$/; // Định dạng số điện thoại ở Việt Nam
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        var currentDate = new Date();
        var birthdayDate = new Date(ngaySinh);
        if (ten ==''|| ngaySinh == ''||email==''||sdt==''||taiKhoan==''||matKhau==''){
            alert("Vui lòng nhập đầu đủ thông tin !!")
            return;
        }else {
            if (!phoneRegex.test(sdt)){
                alert("Số điện thoại không hợp lệ !!!")
                return;
            }else if (!emailRegex.test(email)){
                alert("Email không hợp lệ !!!")
                return;
            }else if (birthdayDate>currentDate){
                alert("Ngày sinh không được lớn hơn ngày hiện tại !!!")
                return;
            }
            else {
                var formData = {
                    taiKhoan: taiKhoan
                };
                $.ajax({
                    type: "POST",
                    url: "/checkTaiKhoanAdd",
                    data: formData,
                    success: function (response) {
                        if (response ==='ok'){
                            alert("Thêm thành công !!!")
                            document.getElementById('AddKhachHang').submit();
                        }else {
                            alert("Tài khoản đã trùng, Vui lòng chọn tài khoản khác !!!")
                        }
                    },
                    error: function (xhr, status, error) {
                        alert("thất bại !!!");
                        console.error("Error occurred while sending data: " + error);
                    }
                });
            }
        }});
</script>
</body>

</html>