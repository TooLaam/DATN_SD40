<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <style><%@include file="style.css"%></style>
</head>

<div class="container">
    <img src="/assets/img/banner/bannerHome.jpg" class="banner" alt="" />
    <div class="row">
        <c:forEach items="${listTH}" var="sp" >
            <div class="col-md-3 col-6">
                <a href="/sanphamcus/thuonghieucus/${sp[0]}" class="link-recommend">
                    <img src="/assets/img/brand/${sp[2]}" class="img-recommend" alt="" />
                    <div class="overlay">${sp[1]}</div>
                </a>
            </div>
        </c:forEach>
    </div>
    <div class="frame">
        <p>New Product</p>
        <div class="row">
            <c:forEach items="${listSPNew}" var="sp">
                <div class="col-md-3 col-6">
                    <div class="thumnail" style="display: flex; flex-direction: column; align-items: center;">
                        <a href="/detailsanphamcus/${sp[0]}">
                            <img src="/assets/img/product/${sp[2]}">
                            <div class="caption">
                                <p>${sp[1]}</p>
                                <c:choose>
                                    <c:when test="${sp[4]>0}">
                                        <c:choose>
                                            <c:when test="${sp[7]==sp[8]}">
                                                <span style="text-decoration: line-through" class="priceGiam">$ <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ </span>
                                                <span class="sold">${sp[3]} sold</span>
                                                <p class="price">$ <fmt:formatNumber value="${sp[6]}" pattern="###,###"/>đ </p>
                                                <span class="discounted-price">-${sp[4]}%</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span style="text-decoration: line-through" class="priceGiam">$ <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ - <fmt:formatNumber value="${sp[7]}" pattern="###,###"/>đ</span>
                                                <span class="sold">${sp[3]} sold</span>
                                                <p class="price">$ <fmt:formatNumber value="${sp[6]}" pattern="###,###"/>đ - <fmt:formatNumber value="${sp[5]}" pattern="###,###"/>đ </p>
                                                <span class="discounted-price">-${sp[4]}%</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>

                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${sp[7]==sp[8]}">

                                                <span class="price">$ <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ </span>
                                                <span class="sold">${sp[3]} sold</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="price" >$ <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ - <fmt:formatNumber value="${sp[7]}" pattern="###,###"/>đ</span>
                                                <span class="sold">${sp[3]} sold</span>
                                            </c:otherwise>
                                        </c:choose>


                                    </c:otherwise>
                                </c:choose>
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
            <c:forEach items="${listSPBest}" var="sp">
                <div class="col-md-3 col-6">
                    <div class="thumnail">
                        <a href="/detailsanphamcus/${sp[0]}">
                            <img src="/assets/img/product/${sp[2]}">
                            <div class="caption">
                                <p>${sp[1]}</p>
                                <c:choose>
                                    <c:when test="${sp[4]>0}">
                                        <c:choose>
                                            <c:when test="${sp[7]==sp[8]}">
                                                <span style="text-decoration: line-through" class="priceGiam">$ <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ </span>
                                                <span class="sold">${sp[3]} sold</span>
                                                <p class="price">$ <fmt:formatNumber value="${sp[6]}" pattern="###,###"/>đ </p>
                                                <span class="discounted-price">-${sp[4]}%</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span style="text-decoration: line-through" class="priceGiam">$ <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ - <fmt:formatNumber value="${sp[7]}" pattern="###,###"/>đ</span>
                                                <span class="sold">${sp[3]} sold</span>
                                                <p class="price">$ <fmt:formatNumber value="${sp[6]}" pattern="###,###"/>đ - <fmt:formatNumber value="${sp[5]}" pattern="###,###"/>đ </p>
                                                <span class="discounted-price">-${sp[4]}%</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>

                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${sp[7]==sp[8]}">

                                                <span class="price">$ <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ </span>
                                                <span class="sold">${sp[3]} sold</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="price" >$ <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ - <fmt:formatNumber value="${sp[7]}" pattern="###,###"/>đ</span>
                                                <span class="sold">${sp[3]} sold</span>
                                            </c:otherwise>
                                        </c:choose>


                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<br />
