<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <style>
        <%@include file="style.css" %>
    </style>
</head>

<div class="container">
    <div class="row">
        <div class="col-6 row">
            <div class="product-content-big-img col-9">
                <img width="100%" src="/assets/img/product/${sp[2]}" alt="" />
            </div>
            <div class="product-content-small-img col-3">
                <c:forEach items="${listHA}" var="sp">
                    <img width="100%" src="/assets/img/product/${sp.hinhAnh}" alt="" />
                </c:forEach>
            </div>
        </div>
        <div class="col-6">

            <c:choose>
                <c:when test="${sp[4]>0}">
                    <c:choose>
                        <c:when test="${sp[7]==sp[6]}">
                            <h5>${sp[1]} <span class="discounted-price" style="color: red">(-${sp[4]}%)</span></h5>
                            <p><span class="text-content">${sp[3]} Đã bán | ${sp[5]} Sản phẩm có sẵn</span></p>
                            <p><span class="text-content">Thương hiệu: ${SP1.thuongHieu.ten}  |  Thể loại: ${SP1.theLoai.ten}</span></p>

                            <span style="text-decoration: line-through" class="priceGiam">$ <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ </span>
                            <p class="price">$ <fmt:formatNumber value="${sp[6]}" pattern="###,###"/>đ </p>


                        </c:when>
                        <c:otherwise>
                            <h5>${sp[1]} <span class="discounted-price" style="color: red">(-${sp[4]}%)</span></h5>
                            <p><span class="text-content">${sp[3]} Đã bán | </span>
                            <span class="text-content" id="hiensl">${sp[5]}  </span>
                                <span class="text-content">Sản phẩm có sẵn</span>
                            </p>
                            <p><span class="text-content">Thương hiệu: ${SP1.thuongHieu.ten}  |  Thể loại: ${SP1.theLoai.ten}</span></p>

                            <span style="text-decoration: line-through" class="priceGiam">$ <fmt:formatNumber value="${sp[9]}" pattern="###,###"/>đ - <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ</span>
                            <p class="price">$ <fmt:formatNumber value="${sp[7]}" pattern="###,###"/>đ - <fmt:formatNumber value="${sp[6]}" pattern="###,###"/>đ </p>

                        </c:otherwise>
                    </c:choose>
                </c:when>

                <c:otherwise>
                    <c:choose>
                        <c:when test="${sp[7]==sp[6]}">
                            <h5>${sp[1]}</h5>
                            <p><span class="text-content">${sp[3]} Đã bán | ${sp[5]} Sản phẩm có sẵn</span></p>
                            <p><span class="text-content">Thương hiệu: ${SP1.thuongHieu.ten}  |  Thể loại: ${SP1.theLoai.ten}</span></p>

                            <span class="price">$ <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ </span>
                        </c:when>
                        <c:otherwise>
                            <h5>${sp[1]}</h5>
                            <p><span class="text-content">${sp[3]} Đã bán | ${sp[5]} Sản phẩm có sẵn</span></p>
                            <p><span class="text-content">Thương hiệu: ${SP1.thuongHieu.ten}  |  Thể loại: ${SP1.theLoai.ten}</span></p>


                            <span class="price" >$ <fmt:formatNumber value="${sp[9]}" pattern="###,###"/>đ - <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ</span>
                        </c:otherwise>
                    </c:choose>


                </c:otherwise>
            </c:choose>

            <p><span style="font-weight: bold;">Color: </span></p>

            <c:forEach items="${listMS}" var="ms">
                <a href="/hienthiKCCus/${sp[0]}/${ms[0]}" class="btn btn-light" style="margin-left: 30px;background-color: gainsboro">
                    <img width="100%" style="width: 20px;height: 20px" src="/assets/img/color/${ms[2]}" alt="Icon" class="icon">
                    <span class="text">${ms[1]}</span>
                </a>
            </c:forEach>
<%--            <span id="hienid">aloooo</span>--%>
            <p><span style="font-weight: bold;">Kích cỡ: </span></p>

<%--            <input id="inputid" style="display: none" name="kichCo">--%>
            <c:forEach items="${listKC}" var="ms">
                <a onclick="getData('${ms[2]}','${ms[0]}')" class="btn btn-success" style="margin-left: 30px" >${ms[1]}</a>
            </c:forEach><br><br>

            <div class="row mb-3">
                <div style="padding-top: 17px" class="col-3 my-2">
                    <span class="text-content">Số lượng</span>
                </div>
                <div style="margin-top: 20px" class="col-3">
                    <div class="containerT">
                        <button  id="subtractButton" class="buttonTang">-</button>
                        <input readonly type="number" id="inputField" class="inputTang" name="soLuong" value="1">
                        <button  id="addButton" class="buttonTang">+</button>
                    </div>
                </div>
            </div>
            <div class="d-grid gap-2">
                <div class="row">
                    <div class="col-10">
                        <form method="get" action="/hienthiAddHD/${SP1.id}">
                            <input style="display: none" id="inputid" name="kichCo">
                            <input style="display: none" value="${ms.id}" name="mauSac">
                            <input style="display: none" readonly type="number" id="inputField2" class="inputTang" name="soLuong" value="1">
                            <button class="btn btn-outline"  >THÊM VÀO GIỎ HÀNG</button>
                        </form>


                    </div>
                </div>
                <form method="get" action="/hienthiAddHD/${SP1.id}">
                    <input style="display: none" id="inputid1" name="kichCo">
                    <input style="display: none" value="${ms.id}" name="mauSac">
                    <input style="display: none" readonly type="number" id="inputField1" class="inputTang" name="soLuong" value="1">
                    <button class="btn btn-outline"  >MUA NGAY</button>
                </form>

            </div>
            <br>
            <strong>CAM KẾT CỦA SD40 SPORT</strong>
            <ul class="list-unstyled">
                <li>
                    <i class="fa fa-check">
                        ĐẢM BẢO HÀNG CHÍNH HÃNG 100 % CHẤT LƯỢNG
                    </i>
                </li>
                <li>
                    <i class="fa fa-check">
                        GIAO HANG CẤP TỐC
                    </i>
                </li>
                <li>
                    <i class="fa fa-check">
                        PHỤC VỤ ĐẾN KHI KHÁCH HÀNG HÀI LÒNG
                    </i>
                </li>
            </ul>
        </div>
    </div>
    <br/>
</div>

<script>
    <%@include file="logic.js" %>
    document.addEventListener('DOMContentLoaded', function() {
        var inputField = document.getElementById('inputField');
        var inputField2 = document.getElementById('inputField2');
        var inputField1 = document.getElementById('inputField1');
        var addButton = document.getElementById('addButton');
        var subtractButton = document.getElementById('subtractButton');
        addButton.addEventListener('click', function() {
            inputField.value = parseInt(inputField.value) + 1;
            inputField2.value = parseInt(inputField2.value) + 1;
            inputField1.value = parseInt(inputField1.value) + 1;
        });

        subtractButton.addEventListener('click', function() {
            var currentValue = parseInt(inputField.value);
            var currentValue2 = parseInt(inputField2.value);
            var currentValue1 = parseInt(inputField1.value);
            if (currentValue > 1) {
                inputField.value = currentValue - 1;
            }
            if (currentValue2 > 1) {
                inputField2.value = currentValue2 - 1;
            }
            if (currentValue1 > 1) {
                inputField1.value = currentValue1 - 1;
            }
        });
    });

    function getData(sl,id) {
        console.log(sl, id);
        document.getElementById("hiensl").textContent  = sl;
        var input = document.getElementById("inputid");
        var input1 = document.getElementById("inputid1");
        input.value = id;
        input1.value = id;
    }



</script>

