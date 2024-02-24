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
    <br />
    <div class="container">
        <div class="row">
            <div class="col-6">
                <div class="product-big-img">
                    <img src="/images/product/kingda-sexy.jpg" alt="" />
                </div>
            </div>
            <div class="col-6">
                <h5>MAC Kinda Sexy</h5>
                <p>987 <span class="sold">Sold</span></p>
                <p class="price">$450000</p>
                <div class="row">
                    <div class="col-3">
                        <span class="text-content">Color</span>
                    </div>
                    <div class="col-9">
                        <input type="radio" class="btn-check" name="options" id="Strong" autocomplete="off" checked>
                        <label class="btn options" for="Strong">Strong</label>

                        <input type="radio" class="btn-check" name="options" id="Gifted" autocomplete="off">
                        <label class="btn options" for="Gifted">Gifted</label>

                        <input type="radio" class="btn-check" name="options" id="Fun" autocomplete="off">
                        <label class="btn options" for="Fun">Fun</label>

                        <input type="radio" class="btn-check" name="options" id="Creative" autocomplete="off">
                        <label class="btn options" for="Creative">Creative</label>

                        <input type="radio" class="btn-check" name="options" id="Humble" autocomplete="off">
                        <label class="btn options" for="Humble">Humble</label>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-3">
                        <span class="text-content">Quantity</span>
                    </div>
                    <div class="col-3">
                        <div class="input-group mb-3">
                            <button class="btn btn-outline">-</button>
                            <input type="text" class="form-control" value="1" />
                            <button class="btn btn-outline">+</button>
                        </div>
                    </div>
                    <div class="col-6">
                        <span class="text-content">987 Products Available</span>
                    </div>
                </div>
                <br>
                <div class="d-grid gap-2">
                    <div class="row">
                        <div class="col-10">
                            <button class="btn btn-outline" type="button">
                                ADD TO CART
                            </button>
                        </div>
                        <div class="col-2">
                            <button class="fa fa-heart-o" type="button"></button>
                        </div>
                    </div>
                    <button class="btn" type="button">BUY IT NOW</button>
                </div>
                <br>
                <strong>LIPSTICK WORLD'S COMMITMENT</strong>
                <ul class="list-unstyled">
                    <li>
                        <i class="fa fa-check">
                            GUARANTEED 100% GENUINE QUALITY PRODUCTS
                        </i>
                    </li>
                    <li>
                        <i class="fa fa-check">
                            LATEST DATE
                        </i>
                    </li>
                    <li>
                        <i class="fa fa-check">
                            SERVE UNTIL CUSTOMERS ARE SATISFIED
                        </i>
                    </li>
                </ul>
            </div>
        </div>
        <div class="recently">
            <p>Recently viewed products</p>
            <div class="row">
                <div class="col-md-3 col-6">
                    <div class="thumnail">
                        <a href="/detail/index.html">
                            <img src="../images/product/kingda-sexy.jpg" alt="" />
                            <div class="caption">
                                <p>MAC Kinda Sexy</p>
                                <span class="price">$450000</span>
                                <span class="sold">10 sold</span>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-md-3 col-6">
                    <div class="thumnail">
                        <a href="/detail/index.html">
                            <img src="../images/product/kingda-sexy.jpg" alt="" />
                            <div class="caption">
                                <p>MAC Kinda Sexy</p>
                                <span class="price">$450000</span>
                                <span class="sold">10 sold</span>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-md-3 col-6">
                    <div class="thumnail">
                        <a href="/detail/index.html">
                            <img src="../images/product/kingda-sexy.jpg" alt="" />
                            <div class="caption">
                                <p>MAC Kinda Sexy</p>
                                <span class="price">$450000</span>
                                <span class="sold">10 sold</span>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-md-3 col-6">
                    <div class="thumnail">
                        <a href="/detail/index.html">
                            <img src="../images/product/kingda-sexy.jpg" alt="" />
                            <div class="caption">
                                <p>MAC Kinda Sexy</p>
                                <span class="price">$450000</span>
                                <span class="sold">10 sold</span>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <br />
        <div class="recently">
            <p>You may also like</p>
            <div class="row">
                <div class="col-md-3 col-6">
                    <div class="thumnail">
                        <a href="/detail/index.html">
                            <img src="../images/product/kingda-sexy.jpg" alt="" />
                            <div class="caption">
                                <p>MAC Kinda Sexy</p>
                                <span class="price">$450000</span>
                                <span class="sold">10 sold</span>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-md-3 col-6">
                    <div class="thumnail">
                        <a href="/detail/index.html">
                            <img src="../images/product/kingda-sexy.jpg" alt="" />
                            <div class="caption">
                                <p>MAC Kinda Sexy</p>
                                <span class="price">$450000</span>
                                <span class="sold">10 sold</span>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-md-3 col-6">
                    <div class="thumnail">
                        <a href="/detail/index.html">
                            <img src="../images/product/kingda-sexy.jpg" alt="" />
                            <div class="caption">
                                <p>MAC Kinda Sexy</p>
                                <span class="price">$450000</span>
                                <span class="sold">10 sold</span>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-md-3 col-6">
                    <div class="thumnail">
                        <a href="/detail/index.html">
                            <img src="../images/product/kingda-sexy.jpg" alt="" />
                            <div class="caption">
                                <p>MAC Kinda Sexy</p>
                                <span class="price">$450000</span>
                                <span class="sold">10 sold</span>
                            </div>
                        </a>
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