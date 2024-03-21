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
                                <button type="button" onclick="openUpdate(${cart.id}, ${cart.quantity})" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                                    update
                                </button>
                                <a href="/cart/delete/${cart.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Xoá</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <form action="/cart/update" method="post">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="id" class="form-control" placeholder="ngõ 54"
                               name="id" >
                        <input type="number" id="quantity" class="form-control" placeholder="ngõ 54"
                               name="quantity" min="1">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
                </form>
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
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
    function openUpdate(id, price) {
        var input1 = document.getElementById("id");
        var input2 = document.getElementById("quantity");
        input1.value = id;
        input2.value = price;
    }
</script>

