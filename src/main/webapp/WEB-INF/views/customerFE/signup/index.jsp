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
                        <form action="/customer/addLogin" method="post">
                            <div class="mb-3">
                                <input class="form-control" id="exampleInputFullName" placeholder="Họ tên" value="${fullnameAdd}" name="fullname" />
                                <c:if test="${errName != null}" >
                                    <p style="color: red">${errName}</p>
                                </c:if>
                            </div>
                            <div class="mb-3">
                                <input class="form-control" id="exampleInputUsername" value="${usernameAdd}" placeholder="Tài khoản" name="username" />
                                <c:if test="${errUser != null}" >
                                    <p style="color: red">${errUser}</p>
                                </c:if>
                                <c:if test="${errUserTrung != null}" >
                                    <p style="color: red">${errUserTrung}</p>
                                </c:if>
                            </div>
                            <div class="mb-3">
                                <input type="password" class="form-control" value="${passwordAdd}" id="exampleInputPassword"
                                    placeholder="Mật khẩu" name="password" />
                                <c:if test="${errPass != null}" >
                                    <p style="color: red">${errPass}</p>
                                </c:if>
                            </div>
                            <div class="mb-3">
                                <input type="number" class="form-control" id="exampleInputPhoneNumber"
                                    placeholder="Số điện thoại" value="${phoneNumberAdd}" name="phone" />
                                <c:if test="${errPhone != null}" >
                                    <p style="color: red">${errPhone}</p>
                                </c:if>
                                <c:if test="${errPhoneErrr != null}" >
                                    <p style="color: red">${errPhoneErrr}</p>
                                </c:if>
                            </div>
                            <div class="mb-3">
                                <input type="email" class="form-control" value="${emailAdd}" id="exampleInputEmail" placeholder="Email" name="email" />
                                <c:if test="${errEmail != null}" >
                                    <p style="color: red">${errEmail}</p>
                                </c:if>
                            </div>
                            <div class="mb-3">
                                <input type="date" class="form-control" id="exampleInputBirthday"
                                    placeholder="Ngày sinh" value="${dateOfBirthAdd}" name="dateofbirth">
                                <c:if test="${errDate != null}" >
                                    <p style="color: red">${errDate}</p>
                                </c:if>
                                <c:if test="${errDateAfter != null}" >
                                    <p style="color: red">${errDateAfter}</p>
                                </c:if>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Giới tính: </label>
                                <input type="radio" class="btn-check" name="gender" value="1" id="male" autocomplete="off"
                                    checked>
                                <label class="btn" for="male">Nam</label>

                                <input type="radio" class="btn-check" value="2" name="gender" id="female" autocomplete="off">
                                <label class="btn" for="female">Nữ</label>
                            </div>

                            <div class="d-grid">
                                <button type="submit" class="btn signup">Đăng ký</button>
                            </div>
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