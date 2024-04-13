<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <meta charset="utf-8">
    <style>
        <%@include file="style.css" %>
        .btn.selected {
            background-color: #b5d199; /* Màu nền mới khi thẻ được chọn */
        }
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
                            <p><span class="text-content">${sp[3]} Đã bán</span></p>
                            <p><span class="text-content">Thương hiệu: ${SP1.thuongHieu.ten}  |  Thể loại: ${SP1.theLoai.ten}</span></p>

                            <div id="giaChung">
                                <span style="text-decoration: line-through" class="priceGiam">$ <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ </span>
                                <p class="price">$ <fmt:formatNumber value="${sp[6]}" pattern="###,###"/>đ </p>
                            </div>

                            <div id="priceHienTaiGoc" style="text-decoration: line-through" class="priceGiam">
                            </div>
                            <div id="priceHienTaiGiam" class="price">
                            </div>



                        </c:when>
                        <c:otherwise>
                            <h5>${sp[1]} <span class="discounted-price" style="color: red">(-${sp[4]}%)</span></h5>
                            <p><span class="text-content">${sp[3]} Đã bán</span>

                            <p><span class="text-content">Thương hiệu: ${SP1.thuongHieu.ten}  |  Thể loại: ${SP1.theLoai.ten}</span></p>

                            <div id="giaChung">
                                <span style="text-decoration: line-through" class="priceGiam">$ <fmt:formatNumber value="${sp[9]}" pattern="###,###"/>đ - <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ</span>
                                <p class="price" >$ <fmt:formatNumber value="${sp[7]}" pattern="###,###"/>đ - <fmt:formatNumber value="${sp[6]}" pattern="###,###"/>đ </p>
                            </div>

                            <div id="priceHienTaiGoc" style="text-decoration: line-through" class="priceGiam">

                            </div>
                            <div id="priceHienTaiGiam" class="price">
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:when>

                <c:otherwise>
                    <c:choose>
                        <c:when test="${sp[7]==sp[6]}">
                            <h5>${sp[1]}</h5>
                            <p><span class="text-content">${sp[3]} Đã bán</span></p>
                            <p><span class="text-content">Thương hiệu: ${SP1.thuongHieu.ten}  |  Thể loại: ${SP1.theLoai.ten}</span></p>
                            <div id="giaChung1">
                                <span class="price">$ <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ </span>
                            </div>

                            <div id="priceHienTaiGiam" class="price">
                            </div>

                        </c:when>
                        <c:otherwise>
                            <h5>${sp[1]}</h5>
                            <p><span class="text-content">${sp[3]} Đã bán</span></p>
                            <p><span class="text-content">Thương hiệu: ${SP1.thuongHieu.ten}  |  Thể loại: ${SP1.theLoai.ten}</span></p>
                            <div id="giaChung1">
                                <span  class="price" >$ <fmt:formatNumber value="${sp[9]}" pattern="###,###"/>đ - <fmt:formatNumber value="${sp[8]}" pattern="###,###"/>đ</span>
                            </div>

                            <div id="priceHienTaiGiam" class="price">

                            </div>
                        </c:otherwise>
                    </c:choose>


                </c:otherwise>
            </c:choose>

            <p><span style="font-weight: bold;">Color: </span></p>

            <c:forEach items="${listMS}" var="ms">
                <a id="mausac${ms[0]}" onclick="getDataForColor(${sp[0]},${ms[0]},${ms[3]},${ms[4]},${ms[5]})" class="btn btn-light ${ms[0]}" style="margin-left: 30px">
                    <img width="100%" style="width: 20px;height: 20px" src="/assets/img/color/${ms[2]}" alt="Icon" class="icon">
                    <span class="text">${ms[1]}</span>
                </a>
            </c:forEach>
<%--            <span id="hienid">aloooo</span>--%>
            <p><span style="font-weight: bold;">Kích cỡ: </span></p>
            <div id="productsContainer"></div>


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
            <span class="text-content" id="hiensl">${sp[5]}  </span>
            <span class="text-content">Sản phẩm có sẵn</span>
            </p>
            <div class="d-grid gap-2">
                <div class="row">
                    <div class="col-10">
                        <form id="addToCartForm">
                            <input style="display: none" id="inputid" name="kichCo" >
                            <input style="display: none"  id="mauSac" name="mauSac" >
                            <input style="display: none" readonly type="number" id="inputField2" class="inputTang" name="soLuong" value="1" required>
                            <c:choose>
                                <c:when test="${idkh==null}">
                                    <a class="btn btn-outline" href="/login?idsp=${SP1.id}&idms=${ms.id}">THÊM VÀO GIỎ HÀNG</a>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" class="btn btn-outline addToCartBtn">THÊM VÀO GIỎ HÀNG</button>
                                </c:otherwise>
                            </c:choose>

                        </form>

                    </div>
                </div>
                        <form id="buyForm"  action="/bill/hienthisanpham/${SP1.id}" method="get">
                            <input style="display: none" id="inputid1" name="kichCo">
                            <input style="display: none" id="mauSac1" name="mauSac">
                            <input style="display: none" id="hiengoc1" name="sl">
                            <input style="display: none" readonly type="number" id="inputField1" class="inputTang" name="soLuong" value="1">
                            <button id="buyButton" class="btn btn-outline"  >MUA NGAY</button>

                        </form>


            </div>
            <div style="display: none" id="idkh">${idkh}</div>
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    <%@include file="logic.js" %>
    $(document).ready(function() {
        $(".addToCartBtn").click(function(e) {
            e.preventDefault();
            var kichC0 =$("#inputid").val();
            var mauSac = $("#mauSac").val();
            var soLuong = $("#inputField1").val();
            var hiensl = $("#hiengoc1").val();
            console.log(soLuong+"--------------")
            console.log(hiensl+"================")
            if (parseInt(soLuong)>parseInt(hiensl)){
                alert("Số lượng sản phẩm không đủ")
                return;
            }
            if (!mauSac || !kichC0){
                alert("Vui lòng chọn màu sắc và kích cỡ !!!")

                return
            }else{
                var formData = {
                    kichCo: kichC0,
                    mauSac: mauSac,
                    soLuong: soLuong
                };

                $.ajax({
                    type: "GET",
                    url: "/hienthiAddHD/${SP1.id}",
                    data: formData,
                    success: function (response) {
                        if (response === "update") {
                            alert("Cập nhật vào giỏ hàng thành công !!!");
                        } else if (response === "success") {
                            var currentQuantity = parseInt($("#cartCount").text());
                            var newQuantity = currentQuantity + 1;
                            $("#cartCount").text(newQuantity);
                            alert("Thêm vào giỏ hàng thành công!!!");
                        }
                    },
                    error: function (xhr, status, error) {
                        alert("Thêm thất bại !!!");
                        console.error("Error occurred while sending data: " + error);
                    }
                });
            }});
    });

    document.getElementById('buyButton').addEventListener('click', function(event) {
        event.preventDefault(); // Ngăn chặn chuyển hướng mặc định\


        var kichCo = document.getElementById('inputid1').value;
        var mauSac = document.getElementById('mauSac1').value;
        var soLuong = $("#inputField1").val();
        var hiensl = $("#hiengoc1").val();
        console.log(soLuong+"--------------")
        console.log(hiensl+"================")


        if (!kichCo || !mauSac) {
            alert("Vui lòng chọn màu săc và kích cỡ!"); // Hiển thị cảnh báo nếu có trường input không được điền đầy đủ
            return; // Dừng việc chuyển hướng nếu có lỗi
        }
        if (parseInt(soLuong)>parseInt(hiensl)){
            alert("Số lượng sản phẩm không đủ")
            return;
        }
        else {
            // Nếu mọi thông tin đã được điền đầy đủ, thực hiện chuyển hướng
            document.getElementById('buyForm').submit();
        }
    });


    var previousSelectedId = null;
    function getDataForColor(idsp, idms,giaGoc,mucGiam,giaGiam) {
        document.getElementById('inputid').value = null;
        document.getElementById('inputid1').value = null;

        var element = document.getElementById('mausac' + idms);

        if (previousSelectedId !== null) {
            var previousSelected = document.getElementById('mausac' + previousSelectedId);
            previousSelected.classList.remove('red-background'); // Xóa class red-background từ nút trước đó
        }

        element.classList.add('red-background'); // Thêm class red-background cho nút hiện tại
        previousSelectedId = idms;


        var giaChung = document.getElementById("giaChung");
        var giaChung1 = document.getElementById("giaChung1");
        var priceHienTaiGiam = document.getElementById("priceHienTaiGiam");
        var priceHienTaiGoc = document.getElementById("priceHienTaiGoc");


        if (mucGiam != null && mucGiam > 0) {
            giaChung.style.display = "none";
            if (giaGoc == giaGiam) {
                priceHienTaiGoc.innerHTML = '$ ' + formatNumber(giaGoc) + 'đ';
                priceHienTaiGiam.innerHTML = '$ ' + formatNumber(giaGiam) + 'đ';
            } else {
                priceHienTaiGoc.innerHTML = '$ ' + formatNumber(giaGoc) + 'đ';
                priceHienTaiGiam.innerHTML = '$ ' + formatNumber(giaGiam) + 'đ';
            }
        } else {
            if (giaGoc == giaGiam) {
                giaChung1.style.display = "none";
                priceHienTaiGiam.innerHTML = '$ ' + formatNumber(giaGoc) + 'đ';
            } else {
                giaChung1.style.display = "none";
                priceHienTaiGiam.innerHTML = '$ ' + formatNumber(giaGoc) + 'đ';
            }
        }

        $.ajax({
            type: "GET",
            url: "/hienthiKCCus/" + idsp + "/" + idms,
            success: function (response) {
                $('#productsContainer').empty();

                // Lặp qua danh sách dữ liệu và tạo thẻ <a> cho mỗi mục
                $.each(response, function (index, data) {
                    var idkc = data[0]; // Giả sử thông tin thứ nhất ở vị trí đầu tiên
                    var ten = data[1]; // Giả sử thông tin thứ hai ở vị trí thứ hai
                    var soLuong = data[2]; // Giả sử thông tin thứ hai ở vị trí thứ hai

                    // Tạo thẻ <a> với các thông tin và gán sự kiện onclick
                    var linkHTML = '<a id="kichco' + idkc + '" data-info="' + idkc + ',' + ten + '" onclick="getData(' + soLuong + ',\'' + idkc + '\',\'' + idms + '\')" class="btn btn-success" style="margin-left: 30px">' + ten + '</a>';

                    // Thêm thẻ <a> vào container
                    $('#productsContainer').append(linkHTML);
                    $('#productsContainer a').on('click', function () {
                        $('#productsContainer a').removeClass('red-background'); // Xóa class red-background từ tất cả các phần tử khác
                        $(this).addClass('red-background'); // Thêm class red-background cho phần tử được click
                    });
                });
            },
            error: function (xhr, status, error) {
                // Xử lý lỗi
                console.error("Error occurred while fetching data: " + error);
            }
        });

    }
</script>
<script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
        intent="WELCOME"
        chat-title="SD40"
        agent-id="96c8e619-f1d8-425a-a536-0cb7cecdb3b7"
        language-code="vi"
></df-messenger>

