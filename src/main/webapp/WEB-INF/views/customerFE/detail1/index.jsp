<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style>
        <%@include file="style.css" %>
    </style>
</head>

<br/>
<div class="container">
    <div class="row">
        <div class="col-6 row">
            <div class="product-content-big-img col-9">
                <img src="/assets/img/product/${image.name}" alt="" />
            </div>
            <div class="product-content-small-img col-3">
                <c:forEach items="${listImage}" var="sp">
                    <img src="/assets/img/product/${sp.name}" alt="" />
                </c:forEach>
            </div>
        </div>
        <div class="col-6">
            <h5>${productDetail.name}</h5>
            <p>${productDetail.sold} <span class="sold">Sold</span></p>
            <p class="price">$${productDetail.price}</p>
            <div class="product-content-product-color">
                <p><span style="font-weight: bold;">Color: </span><span class="product-content-product-color-name">${productDetail.color.name}</span></p>
                <ul class="list-inline">
                    <c:forEach items="${listColor}" var="color">
                        <c:choose>
                            <c:when test="${color.productDetailId == productDetail.id}">
                                <li class="list-inline-item">
                                    <a href="/product_detail/indexcus/${color.productDetailId}" class="product-content-product-color-img-active">
                                        <img src="/assets/img/color/${color.image}" alt="" value="${color.id}" />
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="list-inline-item">
                                    <a href="/product_detail/indexcus/${color.productDetailId}" class="product-content-product-color-img">
                                        <img src="/assets/img/color/${color.image}" alt="" value="${color.id}" />
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </div>
            <div class="row mb-3">
                <div class="col-3 my-2">
                    <span class="text-content">Quantity</span>
                </div>
                <div class="col-3">
                    <div class="input-group mb-3">
                        <a href="/product_detail/reduce/${productDetail.id}" class="btn btn-outline">-</a>
                        <input disabled type="text" class="form-control" value="${productDetail.quantity}"/>
                        <a href="/product_detail/increase/${productDetail.id}" class="btn btn-outline">+</a>
                    </div>
                </div>
                <div class="col-6 my-2">
                    <span class="text-content">${productDetail.availableQuantity} Products Available</span>
                </div>
            </div>
            <div class="d-grid gap-2">
                <div class="row">
                    <div class="col-10">
                        <a class="btn btn-outline" href="/cart/add/${productDetail.id}">
                            ADD TO CART
                        </a>
                    </div>
                    <c:if test="${CustomerName != null}">
                        <form class="col-2" action="/favor/like/${productDetail.id}" method="post">
                            <button type="submit" class="fa fa-heart-o"></button>
                        </form>
                    </c:if>
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
            <c:forEach items="${listProduct}" var="sp">
                <div class="col-md-3 col-6">
                    <div class="thumnail">
                        <a href="/product_detail/indexcus/${sp.productDetailId}">
                            <img src="/assets/img/product/${sp.image}">
                            <div class="caption">
                                <p>${sp.name}</p>
                                <span class="price">$${sp.price}</span>
                                <span class="sold">${sp.sold} sold</span>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <br/>
    <div class="recently">
        <p>You may also like</p>
        <div class="row">
            <c:forEach items="${listProduct}" var="sp">
                <div class="col-md-3 col-6">
                    <div class="thumnail">
                        <a href="/product_detail/indexcus/${sp.productDetailId}">
                            <img src="/assets/img/product/${sp.image}">
                            <div class="caption">
                                <p>${sp.name}</p>
                                <span class="price">$${sp.price}</span>
                                <span class="sold">${sp.sold} sold</span>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <br/>
</div>

<script>
    <%@include file="logic.js" %>
</script>