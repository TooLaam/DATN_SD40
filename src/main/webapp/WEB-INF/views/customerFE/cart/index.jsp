<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style>
        <%@include file="style.css" %>
    </style>
</head>

<div class="container">
    <br/>
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-borderless datatable">
                    <thead>
                    <tr>
                        <th>Hinh ảnh</th>
                        <th>Tên</th>
                        <th>Kích cỡ</th>
                        <th>Màu sắc</th>
                        <th>số lượng</th>
                        <th>giá</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listCartDetail}" var="cart" varStatus="tt">
                        <tr>
                            <td>
                                <img src="/assets/img/product/${cart.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.hinhAnh}" style="padding-top: 60px" width="320px" height="450px">
                                    </td>
                            <td>${cart.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.sanPham.ten}</td>
                            <td>${cart.chiTietSanPham.kichCo.ten}</td>
                            <td>${cart.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.mau_sac.ten}</td>
                            <td>
                                    ${cart.quantity}</td>
                            <td>${cart.price}</td>
                            <td>
                                <a href="/cart/delete/${cart.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Xoá</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
            </div>
        </div>


        <div class="col-12">
            <div class="table-responsive">
                <div class="checkout">
                    <p>Congratulations! You've got free shipping!</p>
                    <div class="total">
                        <span>Item(s) total</span>
                        <c:choose>
                            <c:when test="${listCartDetail == null}">
                                <span class="after" style="font-weight: bold;color: red">$0</span>
                            </c:when>
                            <c:otherwise>
                                <span class="after" style="font-weight: bold;color: red">$${totalMoney}</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="ship">
                        <div>
                            <span>Shipping</span>
                            <span class="after">$0</span>
                        </div>
                        <div>
                            <span class="location">(To Hanoi)</span>
                            <span class="delivery-fee">$1</span>
                        </div>
                    </div>
                    <hr/>
                    <div class="total-item">
                        <c:choose>
                            <c:when test="${listCartDetail == null}">
                                <span>Total (0 items)</span>
                                <span class="after" style="font-weight: bold;color: red">$0</span>
                            </c:when>
                            <c:otherwise>
                                <span class="after" style="font-weight: bold;color: red">$${totalMoney}</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="d-grid">
                        <a href="/bill/payment" class="btn">Check out</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br/>
</div>

