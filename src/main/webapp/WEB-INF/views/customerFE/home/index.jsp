<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style><%@include file="style.css"%></style>
</head>

<div class="container">
    <img src="/assets/img/banner/banner3.jpg" class="banner" alt="" />
    <div class="row">
        <c:forEach items="${uniqueQuantityProducts}" var="sp">
            <div class="col-md-3 col-6">
                <a href="/product/indexcus/brand/${sp.brand.id}" class="link-recommend">
                    <img src="/assets/img/product/${sp.image}" class="img-recommend" alt="" />
                    <div class="overlay">${sp.brand.name}</div>
                </a>
            </div>
        </c:forEach>
    </div>
    <div class="frame">
        <p>New Product</p>
        <div class="row">
            <c:forEach items="${productSortByCreatedDate}" var="sp">
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
    <div class="frame">
        <p>Best selling</p>
        <div class="row">
            <c:forEach items="${productSortBySold}" var="sp">
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
</div>
<br />
<script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
        intent="WELCOME"
        chat-title="ChatBot"
        agent-id="c9aa1141-ab88-427a-9131-9140328e202b"
        language-code="vi"
></df-messenger>