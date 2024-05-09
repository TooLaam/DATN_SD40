<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <title>Đăng nhập</title>
    <style>
        .logo{
            width: 100%;
        }
        .nav-search{
            margin: 1rem 0;
        }
        .link-text{
            text-decoration: none;
            color: #00575C;
        }
        .link-text:hover{
            text-decoration: underline;
        }
        #help{
            float: right;
            margin: 1rem 0;
        }
        .main{
            background-image: url('/assets/img/posts/s6ip3iygk4_2feda4d717464f84a8946706f215a298.jpg');
            background-repeat: no-repeat;
            background-size: 100%;
        }
        .form{
            margin: 3rem 0 1rem 0;
            padding: 3rem;
            height: 690px;
            background-color: white;
        }
        .btn{
            color: white !important;
            background-color: #00575C !important;
        }
        .btn:hover{
            background-color: #FFB800 !important;
        }
        .login{
            height: 46px;
            font-size: 16px;
            font-weight: 500;
            line-height: 19px;
            letter-spacing: 0em;
            border-radius: 30px !important;
        }
        .text-log{
            margin: 1rem 0 1rem 0;
            font-size: 16px;
            font-weight: 500;
            line-height: 19px;
            letter-spacing: 0em;
            text-align: left;

        }
        .text-log>span{
            font-weight: 400;
            color: #7A7A7A;
        }
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
                        <div class="mb-3">
                            <label for="exampleInputUsername"  class="form-label">Tài khoản</label>
                            <input class="form-control" required id="exampleInputUsername" />
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword" class="form-label">Mật khẩu</label>
                            <input type="password" required class="form-control" id="exampleInputPassword" />
                        </div>
                        <br>
                        <div class="d-grid">
                            <button type="submit" onclick="login()" class="btn login">Đăng nhập</button>
                        </div>
                        <a><img src="/assets/img/SD40logo.png" class="logo" style="margin-top: 6rem;" alt="logo" /></a>
                </div>
            </div>
            <div class="col-1"></div>
        </div>
    </div>
    <br />
</div>
<div class="overlay" id="overlay"></div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function login(){
      var taiKhoan = document.getElementById('exampleInputUsername').value;
      var matKhau = document.getElementById('exampleInputPassword').value;
      if (taiKhoan.trim()===''||matKhau.trim()===''){
          alert("Vui lòng nhập đầy đủ tài khoản và mật khẩu !!!")
          return
      }else {
        $.ajax({
            type: "POST",
            url: "/admin/loginOK",
            data: {
                taiKhoan: taiKhoan,
                matKhau: matKhau
            },
            success: function (response) {
                if (response === "ok"){
                    alert("Đăng nhập thành công !!!")
                    window.location.href = "/admin/index";
                    return;
                }else {
                    alert("Tài khoản hoặc mật khẩu chưa đúng !!!")
                    return;
                }
               }
            ,
            error: function (xhr, status, error) {
                console.error("Error occurred while sending data: " + error);
            }
        });
    }}
</script>
</body>

</html>