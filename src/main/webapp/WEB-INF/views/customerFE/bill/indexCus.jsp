<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<head>
    <style>
        <%@include file="style.css" %>
    </style>
</head>

<div class="container">
    <div class="frame">
        <p>Địa chỉ nhận hàng</p>
        <div class="row">
            <div class="col-md-6">
                <label  class="form-label">Tên người nhận: </label>
                <input required type="text" class="form-control" name="ten">

                <label  class="form-label">Số điện thoại: </label>
                <input required type="text" class="form-control" name="sdt">
            </div>
            <div style="margin-top: 33px" class="col-md-6">
                <label  class="form-label">Tỉnh/Thành phố: </label>
                <input style="width: 400px" type="text" class="search-input" list="provinces" placeholder="Tìm kiếm tỉnh/thành phố">
                <datalist id="provinces">
                    <c:forEach items="${listTinh}" var="tinh">
                        <option value="${tinh.ten}">${tinh.ten}</option>
                    </c:forEach>

                </datalist> <br>

                <label style="margin-top: 35px"  class="form-label">Địa chỉ chi tiết: </label>
                <label  class="form-label">
                    <span>${diachimacdinh[0].diaChi.moTa}</span>
                    <span style="color: red">  [Mặc định]</span>
                </label>
            </div>
        </div>
    </div>

    <div class="frame">
        <p>Sản phẩm</p>
        <div class="row">

<%--            <c:forEach items="${ctsp}" var="ctsp">--%>

<%--                <div class="productContainer" data-product-id="${ctsp.id}" onclick="toggleCheckbox(this)">--%>

<%--                    <img src="/assets/img/product/${ctsp.chiTietSanPhamMauSacHinhAnh.hinhAnh}"  width="320px" height="450px" class="productImage">--%>
<%--                    <div class="productInfo">--%>
<%--                        <span class="productName">Tên sản phẩm: ${ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.ten}</span>--%>
<%--                        <span style="display: none" class="productID">Tên sản phẩm: ${ctsp.id}</span>--%>
<%--                        <span class="productPrice">Giá:--%>
<%--                        <span class="giaChuaGiam"> <fmt:formatNumber value=" ${(ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh)}" pattern="###,###"/>đ </span>--%>
<%--                        <span><fmt:formatNumber value=" ${(ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh*(100-ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam))/100}" pattern="###,###"/>đ  </span>--%>
<%--                    </span>--%>
<%--                        <span style="display: none" class="productPriceT"> ${(ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh*(100-ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam))/100}  </span>--%>
<%--                        <span style="display: none" class="giaChuaGiamT"> ${(ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh)}  </span>--%>
<%--                        <span class="productKichCo">Kích cỡ: ${ctsp.kichCo.ten}</span>--%>
<%--                        <span class="productMauSac">Màu sắc: ${ctsp.chiTietSanPhamMauSacHinhAnh.mau_sac.ten}</span>--%>
<%--                        <span class="productSoLuong">--%>
<%--                        <div class="containerT">--%>
<%--                     <div class="containerT">--%>
<%--                        <button  id="subtractButton" class="buttonTang">-</button>--%>
<%--                        <input readonly type="number" id="inputField" class="inputTang" name="soLuong" value="1">--%>
<%--                        <button  id="addButton" class="buttonTang">+</button>--%>
<%--                    </div>--%>
<%--                    </div>--%>
<%--                       </span>--%>


<%--                    </div>--%>
<%--                    <input type="checkbox" class="productCheckbox">--%>
<%--                    <span class="customCheckbox"></span>--%>

<%--                </div>--%>

<%--            </c:forEach>--%>


        </div>
    </div>
    <div class="frame">
        <p>Tổng tiền</p>

        <div class="table-responsive">
            <div class="checkout">
                <hr/>
                <div class="total-item">
                    <span>
                        <span>Total : </span> <span id="totalSoLuongDisplay">0</span><span> items</span>
                    </span>
                    <span id="totalPriceDisplay" class="after" style="font-weight: bold;color: red"><fmt:formatNumber value="0" pattern="###,###"/>đ</span>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


