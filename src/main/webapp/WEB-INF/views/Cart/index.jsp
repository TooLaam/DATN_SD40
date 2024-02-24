<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

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
        <li class="nav-item">
            <a class="nav-link" href="/signup/index.html">Sign Up</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/login/index.html">Login</a>
        </li>
    </ul>
    <nav class="nav-search">
        <div class="row">
            <div class="col-md-2 col-4">
                <a href="/home/index.html"><img src="../images/logo.png" class="logo" alt="logo" />
                </a>
            </div>
            <div class="col-md-1 col-0"></div>
            <div class="col-md-6 col-4">
                <div class="input-group mb-3" id="nav-search">
                    <input type="text" class="form-control" placeholder="What can we help you find?"
                           aria-label="Recipient's username" />
                    <button class="btn" type="button" id="button-addon2">
                        SEARCH
                    </button>
                </div>
            </div>
            <div class="col-md-1 col-0"></div>
            <div class="col-md-2 col-4">
                <ul class="list-unstyled" style="display: flex">
                    <li>
                        <a href="/account/index.html">
                            <span class="fa fa-user"></span>
                        </a>
                    </li>
                    <li>
                        <a href="/wishlist/index.html">
                            <span class="fa fa-heart"></span>
                        </a>
                    </li>
                    <li>
                        <a href="/cart/index.html">
                            <span class="fa fa-shopping-cart"></span>
                        </a>
                    </li>
                    <li>
                        <a href="/bill/index.html">
                            <span class="fa fa-sticky-note"></span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div class="nav-shadow">
    <ul class="nav nav-underline nav-justified container">
        <li class="nav-item">
            <a class="nav-link" href="/home/index.html">home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/product/index.html">lips</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/aboutus/index.html">about us</a>
        </li>
    </ul>
</div>
<div id="nav-bottom"></div>
<div class="main">
    <div class="container">
        <br />
        <div class="row">
            <div class="col-lg-8">
                <div class="row">
                    <div class="col-12">
                        <div class="card recent-sales overflow-auto">


                            <div class="card-body">
                                <h5 class="card-title">FavoriteProduct <span>| </span></h5>

                                <table class="table table-borderless datatable">
                                    <tr>
                                        <th>Id</th>
                                        <th>Customer</th>
                                        <th>Quantity</th>
                                        <th>TotalMoney</th>
                                        <th>Status</th>

                                    </tr>
                                    <c:forEach items="${listCart}" var="cart">
                                        <tr>
                                            <td>${cart.id}</td>
                                            <td>${cart.customer.id}</td>
                                            <td>${cart.Quantity}</td>
                                            <td>${cart.Totalmoney}</td>
                                            <td>${cart.Status}</td>
                                            <td>
                                                <a href="/cart/delete?id=${cart.id}" class="btn btn-danger" onclick="return confirm('Bạn chắc chắn có muốn xóa??')" style="text-decoration: none;color: white"><i class='bx bx-trash'></i></a><br>
                                                <a href="/cart/detail/${cart.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" ><i class='bi bi-arrow-repeat'></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                </form>
                            </div>

                        </div>


                    </div>

                </div>

            </div>

            <div class="col-lg-4">
                <div class="table-responsive">
                    <div class="checkout">
                        <p>Congratulations! You've got free shipping!</p>
                        <div class="total">
                            <span>Item(s) total</span>
                            <span class="after">$450000</span>
                        </div>
                        <div class="ship">
                            <div>
                                <span>Shipping</span>
                                <span class="after">$0</span>
                            </div>
                            <div>
                                <span class="location">(To Vietnam)</span>
                                <span class="delivery-fee">$50000</span>
                            </div>
                        </div>
                        <hr />
                        <div class="total-item">
                            <span>Total (3 items)</span>
                            <span class="after">$450000</span>
                        </div>
                        <div class="d-grid">
                            <button class="btn" type="button">CHECK OUT</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br />
    </div>
</div>
<footer class="bd-footer">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-12">
                <div class="row">
                    <div class="col-md-12 col-12">
                        <img src="../images/logo.png" class="logo" alt="logo" />
                        <p id="text_logo">We Have Everything For Lipstick Here!</p>
                    </div>
                    <div class="col-md-12 col-12">
                        <p id="text_subscribe">Subscribe To Our Newsletter</p>
                        <p id="text_blog">New blogs about lipsticks every week!</p>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="YOUR EMAIL ADDRESS"
                                   aria-label="Recipient's username" aria-describedby="button-addon2" />
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
                        <img src="../images/logo_appstore.png" id="logo_appstore" />
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
                        <ul class="list-unstyled" style="display: flex">
                            <li>
                                <a href="#">
                                    <span class="fa fa-facebook-square"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="fa fa-instagram"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="fa fa-youtube-play"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="fa fa-twitter"></span>
                                </a>
                            </li>
                        </ul>
                        <span class="text_footer">Show us your cat with:</span>
                        <span class="text_footer">#meowshop</span>
                        <br />
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
        <br />
        <hr />
        <div id="Copyright">
            Copyright © 2023 The Meow Shop. All rights reserved
        </div>
    </div>
</footer>
</body>
</html>