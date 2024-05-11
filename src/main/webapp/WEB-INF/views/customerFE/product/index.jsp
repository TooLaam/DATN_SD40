<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <style>
        <%@include file="style.css" %>
    </style>
</head>

<br/>
<div class="container">
    <div class="content">
        <span class="text-content">Giày thể thao</span>
        <span class="available">(${tongSLSP})</span>
    </div>
    <div class="row">
        <div class="col-3">
            <h6 class="text-filter">Filter</h6>
            <h6 class="filter">Thương hiệu</h6>
            <c:forEach items="${listTH}" var="brand">
                <div class="filter-brand">
                    <a href="/sanphamcus/thuonghieucus/${brand.id}">${brand.ten}</a>
                </div>
            </c:forEach>
            <h6 class="filter">Thể loại</h6>
            <c:forEach items="${listTL}" var="brand">
                <div class="filter-brand">
                    <a href="/sanphamcus/theloaicus/${brand.id}">${brand.ten}</a>
                </div>
            </c:forEach>
            <h6 class="filter">Giá</h6>
            <div class="filter-brand">
                <a href="/sanphamcus/mucGia/0/200000">Dưới 200.000</a>
            </div>
            <div class="filter-brand">
                <a href="/sanphamcus/mucGia/200000/500000">200.000 - 500.000</a>
            </div>
            <div class="filter-brand">
                <a href="/sanphamcus/mucGia/500000/1000000">500.000 - 1.000.000</a>
            </div>
            <div class="filter-brand">
                <a href="/sanphamcus/mucGia/1000000/1000000000">Trên 1.000.000</a>
            </div>
        </div>
        <div class="col-9">
            <div class="row" id="product-list">
                <c:forEach items="${listSP}" var="sp" varStatus="loop">
                    <div class="col-md-4 col-6 product-item" style="display: ${loop.index < 6 ? 'block' : 'none'}">
                        <div class="thumnail">
                            <a href="/detailsanphamcus/${sp[0]}">
                                <img src="/assets/img/product/${sp[2]}">
                                <div class="caption">
                                    <p>${sp[1]}(${sp[5]})</p>
                                    <c:choose>
                                        <c:when test="${sp[4] > 0}">
                                            <c:choose>
                                                <c:when test="${sp[7] == sp[6]}">
                                                    <span style="text-decoration: line-through" class="priceGiam">$ <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ </span>
                                                    <span class="sold">${sp[3]} sold</span>
                                                    <p class="price">$ <fmt:formatNumber value="${sp[6]}" pattern="###,###"/>đ </p>
                                                    <span class="discounted-price">-${sp[4]}%</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span style="text-decoration: line-through" class="priceGiam">$ <fmt:formatNumber value="${sp[9]}" pattern="###,###"/>đ - <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ</span>
                                                    <span class="sold">${sp[3]} sold</span>
                                                    <p class="price">$ <fmt:formatNumber value="${sp[7]}" pattern="###,###"/>đ - <fmt:formatNumber value="${sp[6]}" pattern="###,###"/>đ </p>
                                                    <span class="discounted-price">-${sp[4]}%</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${sp[7] == sp[8]}">
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
            <div class="col-md-12 button-container">
                <button id="load-more" class="button" style="display: ${listSP.size() > 6 ? 'flex' : 'none'}" onclick="loadMore()">Xem Thêm</button>
                <button id="collapse" class="button" style="display: none" onclick="collapseList()">Thu Gọn</button>
            </div>

        </div>
    </div>
</div>

<script>
    var productCount = 6; // Số sản phẩm ban đầu
    var totalProducts = ${listSP.size()}; // Tổng số sản phẩm

    function loadMore() {
        var productList = document.getElementById('product-list');
        // Hiển thị thêm sản phẩm
        for (var i = productCount; i < productCount + 3 && i < totalProducts; i++) {
            productList.children[i].style.display = 'block';
        }
        productCount += 3; // Cập nhật số sản phẩm đã hiển thị
        if (productCount >= totalProducts) {
            document.getElementById('load-more').style.display = 'none'; // Ẩn nút khi đã hiển thị tất cả sản phẩm
        }
        document.getElementById('collapse').style.display = 'block'; // Hiển thị nút Thu Gọn
    }

    function collapseList() {
        var productList = document.getElementById('product-list');
        // Ẩn đi các sản phẩm đã được mở rộng
        for (var i = 6; i < productList.children.length; i++) {
            productList.children[i].style.display = 'none';
        }
        document.getElementById('load-more').style.display = 'block'; // Hiển thị nút Xem Thêm
        document.getElementById('collapse').style.display = 'none'; // Ẩn nút Thu Gọn
        productCount = 6; // Reset số sản phẩm đã hiển thị
    }
</script>
