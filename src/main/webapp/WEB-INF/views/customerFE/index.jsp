<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="/assets/img/favicons.png" rel="icon">
    <link rel="stylesheet" href="/assets/css/styleFE.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <title>MEOW - Shop Lipstick</title>
</head>

<body>
<div id="freeShip">
    <span>FREE SHIPPING with $20 Purchase </span>
    <a href="#">Details</a>
</div>
<div class="container">
    <ul class="nav justify-content-end">
        <li class="nav-item">
            <a class="nav-link" href="#">Help</a>
        </li>
        <c:choose>
            <c:when test="${CustomerName == null}">
                <li class="nav-item">
                    <a class="nav-link" href="/customer/signup">Sign Up</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/customer/login">Login</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="nav-item">
                    <a class="nav-link" href="/customer/logout">Sign Out</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
    <nav class="nav-search my-2">
        <ul class="navbar mx-auto mb-lg-0 list-unstyled">
            <li class="nav-item">
                <a href="/home"><img src="/assets/img/logo.png" width="200" alt="logo"/>
                </a>
            </li>
            <li class="nav-item">
                <form action="/product/search" method="get" class="input-group" id="nav-search" style="width: 600px;">
                    <input type="text" name="name" class="form-control" placeholder="What can we help you find?"
                           aria-label="Recipient's username"/>
                    <button type="submit" class="btn">SEARCH</button>
                </form>
            </li>
            <li class="nav-item">
                <ul class="list-unstyled" style="display: flex; margin-right: 1rem;">
                    <c:if test="${CustomerName != null}">
                        <li>
                            <a href="/customer/indexcus">
                                <span class="fa fa-user"></span>
                            </a>
                        </li>
                        <li>
                            <a href="/favor/indexcus">
                                <span class="fa fa-heart"></span>
                            </a>
                        </li>
                        <li>
                            <a href="/bill/indexcus">
                                <span class="fa fa-sticky-note"></span>
                            </a>
                        </li>
                    </c:if>
                    <li>
                        <a href="/viewOrderCart">
                            <span class="fa fa-shopping-cart"></span>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </nav>
</div>
<div class="nav-shadow">
    <ul class="nav nav-underline nav-justified container">
        <li class="nav-item">
            <a class="nav-link" href="/home">home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/product/indexcus">lips</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/brand/indexcus">brands</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/blog">blogs</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/aboutus">about us</a>
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
                        <img src="/assets/img/logo.png" class="logo" alt="logo"/>
                        <p id="text_logo">We Have Everything For Lipstick Here!</p>
                    </div>
                    <div class="col-md-12 col-12">
                        <p id="text_subscribe">Subscribe To Our Newsletter</p>
                        <p id="text_blog">New blogs about lipsticks every week!</p>
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
                        <img src="/assets/img/logo_appstore.png" id="logo_appstore"/>
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
                        <span class="text_footer">#meowshop</span>
                        <br/>
                        <span class="text_footer">#themeowshop</span>
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
            Copyright © 2023 The Meow Shop. All rights reserved
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

<!-- Template Main JS File -->
<script src="/assets/js/main.js"></script>

</body>

</html>