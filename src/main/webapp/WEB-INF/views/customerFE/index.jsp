<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="/assets/img/pngtree-sneakers-png-image_3989154.jpg" rel="icon">
    <link rel="stylesheet" href="/assets/css/styleFE.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <title>SD40 - Giày thể thao</title>
    <style>

        .cart-number {
            position: relative; /* Đặt vị trí tương đối */
            top: -15px; /* Di chuyển lên phía trên */
            background-color: #ff0000; /* Màu nền */
            color: #ffffff; /* Màu chữ */
            font-size: 12px; /* Kích thước font */
            padding: 2px 6px; /* Khoảng cách giữa số và viền */
            border-radius: 50%; /* Định dạng số thành hình tròn */
            text-decoration: none; /* Loại bỏ gạch chân */
        }

        .caption{
            padding: 0.1rem;
        }
        .product {
            display: flex;
            align-items: center;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 100%;
            margin-bottom: 10px;
            text-decoration: none; /* Bỏ gạch chân khi hover */
            color: inherit; /* Kế thừa màu từ phần tử cha */
        }

        .product:hover {
            background-color: #f5f5f5; /* Thay đổi màu nền khi hover */
        }

        .product-image {
            flex: 0 0 auto;
            margin-right: 10px;
        }

        .product-image img {
            max-width: 100px;
            height: auto;
        }

        .product-details {
            display: flex;
            flex-direction: column;
            flex-grow: 1;
        }

        .product-name {
            margin: 0;
            font-size: 16px;
            font-weight: bold;
        }

        .product-brand,
        .product-category {
            margin: 0;
            font-size: 14px;
            color: #888;
        }
        #searchResults {
            margin: 0;
            padding: 0;
            position: absolute;
            top: 100%;
            left: 35%;
            z-index: 1000;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 500px;
            max-height: 700px;
            overflow-y: visible;
        }

        #noResultsMessage {
            font-size: 20px;
            padding: 20px;
            text-align: center;
        }

    </style>
</head>

<body>
<div id="freeShip">
    <span>Miễn phí giao hàng với hóa đơn từ 1.000.000đ</span>
</div>
<div class="container">
    <ul class="nav justify-content-end">
        <%--        <li class="nav-item">--%>
        <%--            <a class="nav-link" href="#">Help</a>--%>
        <%--        </li>--%>
        <c:choose>
            <c:when test="${khachHangCus == null}">
                <li class="nav-item">
                    <a class="nav-link" href="/signup">Đăng ký</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Đăng nhập</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Đăng xuất</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
    <nav class="nav-search my-2">
        <ul class="navbar mx-auto mb-lg-0 list-unstyled">
            <li class="nav-item">
                <a href="/home"><img src="/assets/img/SD40.png" width="200" alt="logo"/>
                </a>
            </li>
            <li class="nav-item">
                <form action="/timKiemSanPhamAll" method="get" class="input-group" id="nav-search" style="width: 600px;">
                    <input type="text" name="key" oninput="seahc()" class="form-control" id="tim" placeholder="Tìm kiếm sản phẩm theo tên, thương hiệu, thể loại"
                           aria-label="Recipient's username"/>

                    <button type="submit" id="btnSearch" class="btn">SEARCH</button>
                </form>
                <ul id="searchResults" class="search-results"></ul>
            </li>
            <li class="nav-item">
                <ul class="list-unstyled" style="display: flex; margin-right: 1rem;">
                    <c:if test="${khachHangCus != null}">
                        <li>
                            <a href="/infoKhachHang">
                                <span class="fa fa-user"></span>
                            </a>
                        </li>

                        <li>
                            <a href="/bill/hienThiHoaDon">
                                <span class="fa fa-sticky-note"></span>
                            </a>
                        </li>
                        <li>
                            <a style="text-decoration: none" href="/hienthigiohangchitiet/${idkh}">
                                <span class="fa fa-shopping-cart"></span>
                                <span id="cartCount" class="cart-number">${slspgh}</span>
                            </a>
                        </li>
                    </c:if>

                </ul>
            </li>
        </ul>
    </nav>
</div>
<div class="nav-shadow">
    <ul class="nav nav-underline nav-justified container">
        <li class="nav-item">
            <a class="nav-link" href="/home">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/sanphamcus">Sản phẩm</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/sanphamcusgiamgia">Giảm giá</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/blog">Blogs</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/aboutus">Thông tin về chúng tôi</a>
        </li>
    </ul>
</div>
<div id="nav-bottom"></div>

<main id="main" class="main">
    <jsp:include page="./${view}"/>
</main><!-- End #main -->

<footer class="bd-footer">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-12">
                <div class="row">
                    <div class="col-md-12 col-12">
                        <img src="/assets/img/SD40.png" width="70%" class="logoFE" alt="logo" />
                        <p id="text_logo">Ở đây chúng tôi có bán giày!</p>
                    </div>
                    <div class="col-md-12 col-12">
                        <p id="text_subscribe">Theo dõi ngay!</p>
                        <p id="text_blog">Những câu chuyện về giày thể thao được cập nhật hằng tuần</p>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="YOUR EMAIL ADDRESS"
                                   aria-label="Recipient's username" aria-describedby="button-addon2"/>
                            <button class="btn" type="button" id="button-addon2">
                                SUBSCRIBE
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-1 col-12"></div>
            <div class="col-md-7 col-12">
                <div class="row">
                    <div class="col-md-4 col-6">
                        <p class="text_content_footer">Where's my order?</p>
                        <button class="btn" id="track_order" type="button">
                            TRACK ORDER
                        </button>
                        <p class="text_footer">
                            Please note, it may take longer than usual to fulfill orders
                            due to the impacts of COVID-19.
                        </p>
                    </div>
                    <div class="col-md-4 col-6">
                        <p class="text_content_footer">Shipping</p>
                        <p class="text_footer">About Free Shipping</p>
                        <p class="text_footer">Shipping Information</p>
                    </div>
                    <div class="col-md-4 col-6">
                        <p class="text_content_footer">Shopping App</p>
                        <p class="text_footer">
                            Try our View in Your Room feature, manage registries and save
                            payment info.
                        </p>
                    </div>
                    <div class="col-md-4 col-6">
                        <p class="text_content_footer">Our Company</p>
                        <ul class="list-unstyled">
                            <li class="p-1">
                                <a href="#" class="link_footer">About Us</a>
                            </li>
                            <li class="p-1">
                                <a href="#" class="link_footer">Careers</a>
                            </li>
                            <li class="p-1">
                                <a href="#" class="link_footer">Contact</a>
                            </li>
                            <li class="p-1">
                                <a href="#" class="link_footer">Store locations</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-4 col-6">
                        <p class="text_content_footer">Social Media</p>
                        <ul class="list-unstyled" style="display: flex;">
                            <li>
                                <a href="#" class="nav-search">
                                    <span class="fa fa-facebook-square"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-search">
                                    <span class="fa fa-instagram"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-search">
                                    <span class="fa fa-youtube-play"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-search">
                                    <span class="fa fa-twitter"></span>
                                </a>
                            </li>
                        </ul>
                        <span class="text_footer">Show us your cat with:</span>
                        <span class="text_footer">#sd40sport</span>
                        <br/>
                        <span class="text_footer">#sd40sport</span>
                    </div>
                    <div class="col-md-4 col-6">
                        <p class="text_content_footer">Policies</p>
                        <ul class="list-unstyled">
                            <li class="p-1">
                                <a href="#" class="link_footer">Shipping Policy</a>
                            </li>
                            <li class="p-1">
                                <a href="#" class="link_footer">Refund Policy</a>
                            </li>
                            <li class="p-1">
                                <a href="#" class="link_footer">Privacy Policy</a>
                            </li>
                            <li class="p-1">
                                <a href="#" class="link_footer">Terms of Service</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <br/>
        <hr/>
        <div id="Copyright">
            Copyright © 2023 SD40 Sport. All rights reserved
        </div>
    </div>
</footer>

<!-- Vendor JS Files -->
<script src="/assets/vendor/apexcharts/apexcharts.min.js"></script>
<script src="/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/assets/vendor/chart.js/chart.umd.js"></script>
<script src="/assets/vendor/echarts/echarts.min.js"></script>
<script src="/assets/vendor/quill/quill.min.js"></script>
<script src="/assets/vendor/simple-datatables/simple-datatables.js"></script>
<script src="/assets/vendor/tinymce/tinymce.min.js"></script>
<script src="/assets/vendor/php-email-form/validate.js"></script>
<script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
        intent="WELCOME"
        chat-title="SD40"
        agent-id="96c8e619-f1d8-425a-a536-0cb7cecdb3b7"
        language-code="vi"
></df-messenger>

<!-- Template Main JS File -->
<script src="/assets/js/main.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function seahc(){
        var key = document.getElementById('tim').value;
        if (key ==''){
            $('#searchResults').textContent='';
            $('#searchResults').empty();;
            return;
        }
        $.ajax({
            type: "GET",
            url: "/timKiemSanPham/"+key,
            success: function (data) {
                var resultsList = $('#searchResults');
                resultsList.empty();
                if (data.length === 0) {
                    var linkHTML = '<div id="noResultsMessage">Không tìm thấy sản phẩm...</div>';
                    $('#searchResults').append(linkHTML);
                }else {
                    var slicedData = data.slice(0, 4);
                    slicedData.forEach(function (item) {
                        var ha = item[1];
                        var ten = item[2];
                        var thuongHien = item[3];
                        var theLoai = item[4];
                        var id = item[0];
                        var linkHTML = '<a href="/detailsanphamcus/' + id + '" class="product">' +
                            ' <div class="product-image"> <img src="/assets/img/product/' + ha + '" alt="Product Image"> </div>' +
                            ' <div class="product-details">' +
                            ' <h3 class="product-name">' + ten + '</h3>' +
                            ' <p class="product-brand">' + thuongHien + '</p> ' +
                            '<p class="product-category">' + theLoai + '</p>' +
                            ' </div>' +
                            ' </a>';

                        $('#searchResults').append(linkHTML);
                    });
                }}
            ,
            error: function (xhr, status, error) {
                console.error("Error occurred while sending data: " + error);
            }
        });
    }

    document.getElementById('btnSearch').addEventListener('click', function(event) {
        event.preventDefault();
        var kay = document.getElementById('tim').value;
        if (kay ==''){
            window.location.href = "/sanphamcus";
            return;
        }else {
            document.getElementById('nav-search').submit();
        }
    });

</script>

</body>

</html>