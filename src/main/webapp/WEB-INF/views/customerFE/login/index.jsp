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
                                <p style="color: black">${erCheckCustomer}</p>
                            </c:if>
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
</body>

</html>