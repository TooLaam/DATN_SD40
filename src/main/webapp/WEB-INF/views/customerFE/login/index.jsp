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
    <title>Đăng nhập</title>
    <style>
        /* CSS */
        .form-container {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #f9f9f9;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            z-index: 9999;
            max-width: 400px;
            width: 100%;
            display: none;
        }

        .form-title {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input[type="email"] {
            width: calc(100% - 10px); /* Adjust for padding */
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box; /* Ensure padding is included in width */
        }

        .form-group input[type="submit"] {
            width: 100%;
            background-color:  #00575C;
            color: white;
            border: none;
            padding: 15px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        .close-btn {
            position: absolute;
            top: 10px;
            right: 10px;
            cursor: pointer;
            font-size: 20px;
        }
        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5); /* Màu đen mờ với độ trong suốt 50% */
            z-index: 9998; /* Lớp dưới form-container */
            display: none; /* Mặc định ẩn */
        }
    </style>
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
                    <h3>Log in</h3>
                    <div class="text-log">
                        <span>Bạn chưa có tài khoản?</span>
                        <a href="/signup" class="link-text">Đăng ký</a>
                    </div>
                    <form action="/loginOK" method="post">
                        <div class="mb-3">
                            <label for="exampleInputUsername"  class="form-label">Tài khoản</label>
                            <input class="form-control" required id="exampleInputUsername" name="taiKhoan" />
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword" class="form-label">Mật khẩu</label>
                            <input type="password" required class="form-control" id="exampleInputPassword" name="matKhau"/>
                        </div>
                        <c:if test="${erCheckCustomer != null}" >
                            <p style="color: red">${erCheckCustomer}</p>
                        </c:if>
                        <a style="cursor: pointer;" onclick="showNewForm()"><i>Quên mật khẩu?</i></a><br>
                        <br>
                        <div class="d-grid">
                            <button type="submit" class="btn login">Đăng nhập</button>
                        </div>

                        <a href="/home"><img src="/assets/img/SD40logo.png" class="logo" style="margin-top: 6rem;" alt="logo" /></a>
                    </form>
                </div>
            </div>
            <div class="col-1"></div>
        </div>
    </div>
    <br />
</div>
<div class="overlay" id="overlay"></div>
<div class="form-container" id="new-form">
    <!-- Nút đóng -->
    <span class="close-btn" onclick="closeNewForm()">X</span>
    <!-- Các trường và nút của form mới -->
    <h2 class="form-title">Quên mật khẩu</h2>
    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" placeholder="Nhập email mà bạn đã đăng ký !!!">
    </div>
    <div class="form-group">
        <input type="submit" onclick="QuenMatKhau()" value="Gửi">
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    // JavaScript
    function showNewForm() {
        document.getElementById("new-form").style.display = "block";
        document.getElementById("overlay").style.display = "block";
    }

    function closeNewForm() {
        document.getElementById("new-form").style.display = "none";
        document.getElementById("overlay").style.display = "none";
    }
    function QuenMatKhau() {
        var email = document.getElementById('email').value;
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (email.trim() ===''){
            alert("Vui lòng nhập Email !!!")
            return;
        }else {
            if ( emailRegex.test(email) == false) {
                alert('Vui lòng nhập lại email') ;
                return;
            }else {
            var formData = new FormData();
            formData.append('email', email);
            $.ajax({
                type: "POST",
                url: "/quenMatKhau",
                data: formData,
                contentType: false, // Không cần set contentType
                processData: false, // Không cần xử lý dữ liệu
                success: function (response) {
                    if (response === "ok") {
                        alert("Thông tin đã được gửi về Email của bạn !!!");
                        return;
                    } else {
                            alert("Email chưa đăng ký !!!");
                           return;
                    }

                }
            });
        }}
    };
</script>
</body>

</html>