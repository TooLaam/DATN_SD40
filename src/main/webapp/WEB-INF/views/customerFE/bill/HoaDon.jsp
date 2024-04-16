<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<head>
    <link href="/assets/vendor/simple-datatables/style.css" rel="stylesheet">
    <style>
        <%@include file="style4.css" %>
    </style>
</head>

<br>

<div class="containerHD">
    <h1>Quản lý Đơn hàng</h1>
    <div class="tabs">
        <div class="tab active" data-tab="tab1">Chờ xác nhận</div>
        <div class="tab" data-tab="tab2">Xác nhận</div>
        <div class="tab" data-tab="tab3">Chờ giao</div>
        <div class="tab" data-tab="tab4">Đang giao</div>
        <div class="tab" data-tab="tab5">Thành công</div>
        <div class="tab" data-tab="tab6">Hủy</div>
    </div>

    <div class="tab-content" id="tab1">
        <c:choose>
            <c:when test="${empty hoaDonChoXacNhan}">
                <div class="order-item">
                    <div class="text-center" style="position: absolute; left: 50%; transform: translate(-50%, -50%);">
                        Không có đơn hàng nào !!!
                    </div></div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${hoaDonChoXacNhan}" var="choXN">
                    <div class="order-item">
                        <h3>Order ${choXN.maHoaDon}</h3>
                        <div id="productHD${choXN.id}">
                        </div>
                        <p class="statusHD">
                            <span style="color: black;" >Trạng thái: </span>
                            <span style="color: green;">Chờ xác nhận</span>
                        </p>
                        <p class="totalHD">
                            <span style="color: black;" >Thành tiền : </span>
                            <span style="color: rgb(226, 20, 20);"><fmt:formatNumber value=" ${choXN.tongTien}" pattern="###,###"/>đ </span>
                        </p>
                        <p><a type="button" class="btn btn-success" href="/bill/detailHoaDon/${choXN.id}" style="background-color: #00575C;text-align: right;">Chi tiết</a></p>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="tab-content" id="tab2">
        <c:choose>
            <c:when test="${empty hoaDonDaXacNhan}">
                <div class="order-item">
                    <div class="text-center" style="position: absolute; left: 50%; transform: translate(-50%, -50%);">
                        Không có đơn hàng nào !!!
                    </div></div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${hoaDonDaXacNhan}" var="choXN">
                    <div class="order-item">
                        <h3>Order ${choXN.maHoaDon}</h3>
                        <div id="productHD${choXN.id}">
                        </div>
                        <p class="statusHD">
                            <span style="color: black;" >Trạng thái: </span>
                            <span style="color: green;">Hóa đơn đã được xác nhận</span>
                        </p>
                        <p class="totalHD">
                            <span style="color: black;" >Thành tiền : </span>
                            <span style="color: rgb(226, 20, 20);"><fmt:formatNumber value=" ${choXN.tongTien}" pattern="###,###"/>đ </span>
                        </p>
                        <p><a type="button" class="btn btn-success" href="/bill/detailHoaDon/${choXN.id}" style="background-color: #00575C;text-align: right;">Chi tiết</a></p>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="tab-content" id="tab3">
        <c:choose>
            <c:when test="${empty hoaDonChoGiao}">
                <div class="order-item">
                    <div class="text-center" style="position: absolute; left: 50%; transform: translate(-50%, -50%);">
                        Không có đơn hàng nào !!!
                    </div></div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${hoaDonChoGiao}" var="choXN">
                    <div class="order-item">
                        <h3>Order ${choXN.maHoaDon}</h3>
                        <div id="productHD${choXN.id}">
                        </div>
                        <p class="statusHD">
                            <span style="color: black;" >Trạng thái: </span>
                            <span style="color: green;">Chờ giao hàng</span>
                        </p>
                        <p class="totalHD">
                            <span style="color: black;" >Thành tiền : </span>
                            <span style="color: rgb(226, 20, 20);"><fmt:formatNumber value=" ${choXN.tongTien}" pattern="###,###"/>đ </span>
                        </p>
                        <p><a type="button" class="btn btn-success" href="/bill/detailHoaDon/${choXN.id}" style="background-color: #00575C;text-align: right;">Chi tiết</a></p>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="tab-content" id="tab4">
        <c:choose>
            <c:when test="${empty hoaDonDangGiao}">
                <div class="order-item">
                    <div class="text-center" style="position: absolute; left: 50%; transform: translate(-50%, -50%);">
                        Không có đơn hàng nào !!!
                    </div></div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${hoaDonDangGiao}" var="choXN">
                    <div class="order-item">
                        <h3>Order ${choXN.maHoaDon}</h3>
                        <div id="productHD${choXN.id}">
                        </div>
                        <p class="statusHD">
                            <span style="color: black;" >Trạng thái: </span>
                            <span style="color: green;">Đang giao</span>
                        </p>
                        <p class="totalHD">
                            <span style="color: black;" >Thành tiền : </span>
                            <span style="color: rgb(226, 20, 20);"><fmt:formatNumber value=" ${choXN.tongTien}" pattern="###,###"/>đ </span>
                        </p>
                        <p><a type="button" class="btn btn-success" href="/bill/detailHoaDon/${choXN.id}" style="background-color: #00575C;text-align: right;">Chi tiết</a></p>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="tab-content" id="tab5">
        <c:choose>
            <c:when test="${empty hoaDonThanhCong}">
                <div class="order-item">
                    <div class="text-center" style="position: absolute; left: 50%; transform: translate(-50%, -50%);">
                        Không có đơn hàng nào !!!
                    </div></div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${hoaDonThanhCong}" var="choXN">
                    <div class="order-item">
                        <h3>Order ${choXN.maHoaDon}</h3>
                        <div id="productHD${choXN.id}">
                        </div>

                        <p class="statusHD">
                            <span style="color: black;" >Trạng thái: </span>
                            <span style="color: green;">Thành công</span>
                        </p>
                        <p class="totalHD">
                            <span style="color: black;" >Thành tiền : </span>
                            <span style="color: rgb(226, 20, 20);"><fmt:formatNumber value=" ${choXN.tongTien}" pattern="###,###"/>đ </span>
                        </p>
                        <p><a type="button" class="btn btn-success" href="/bill/detailHoaDon/${choXN.id}" style="background-color: #00575C;text-align: right;">Chi tiết</a></p>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="tab-content" id="tab6">
        <c:choose>
            <c:when test="${empty hoaDonHuy}">
                <div class="order-item">
                <div class="text-center" style="position: absolute; left: 50%; transform: translate(-50%, -50%);">
                    Không có đơn hàng nào !!!
                </div></div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${hoaDonHuy}" var="choXN">
                    <div class="order-item">
                        <h3>Order ${choXN.maHoaDon}</h3>
                        <div id="productHD${choXN.id}">
                        </div>
                        <p class="statusHD">
                            <span style="color: black;" >Trạng thái: </span>
                            <span style="color: red;">Hủy</span>
                        </p>
                        <p class="totalHD">
                            <span style="color: black;" >Thành tiền : </span>
                            <span style="color: rgb(226, 20, 20);"><fmt:formatNumber value=" ${choXN.tongTien}" pattern="###,###"/>đ </span>
                        </p>
                        <p><a type="button" class="btn btn-success" href="/bill/detailHoaDon/${choXN.id}" style="background-color: #00575C;text-align: right;">Chi tiết</a></p>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function formatNumber(price) {
        var formattedPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(Math.round(price));

        // Loại bỏ phần thập phân và chữ "đ" phía sau
        formattedPrice = formattedPrice.replace(/(\d+)(?:\.(\d+))? đ/, '$1');

        return formattedPrice;
    }
    document.addEventListener("DOMContentLoaded", function() {
        var defaultTab = document.querySelector('.tab');
        defaultTab.classList.add('active');
        var defaultTabId = defaultTab.getAttribute('data-tab');
        document.getElementById(defaultTabId).classList.add('active');

        var tabs = document.querySelectorAll('.tab');
        tabs.forEach(function(tab) {
            tab.addEventListener('click', function() {
                var tabId = this.getAttribute('data-tab');
                openTab(tabId);
            });
        });
    });

    function openTab(tabId) {
        var tabContents = document.querySelectorAll('.tab-content');
        tabContents.forEach(function(tabContent) {
            tabContent.classList.remove('active');
        });
        document.getElementById(tabId).classList.add('active');

        var tabs = document.querySelectorAll('.tab');
        tabs.forEach(function(tab) {
            tab.classList.remove('active');
        });
        document.querySelector(".tab[data-tab='" + tabId + "']").classList.add("active");
    }

    function hienThiHoaDonChiTiet() {
        <c:forEach items="${allHoaDon}" var="hd">
        $.ajax({
            type: "GET",
            url: "/bill/hienthiHoaDonChiTiet/" +${hd.id},
            success: function (response) {

                $.each(response, function (index, data) {
                    var ten = data[0];
                    var ms = data[1];
                    var kc = data[2];
                    var sl = data[3];
                    var gia = data[4];
                    var giaGiam = data[5];
                    var ha = data[6];

                    // Tạo thẻ <a> với các thông tin và gán sự kiện onclick
                    var linkHTML = '<div style="display: block;">' +
                        ' <div class="productHD">' +
                        ' <img src="/assets/img/product/'+ha+'" alt="Product Image">' +
                        ' <div class="product-infoHD">' +
                        ' <p>Tên: '+ten+'</p>' +
                        ' <span>Kích cỡ: '+kc+'</span> ' +
                        '<span>Màu sắc: '+ms+'</span> ' +
                        '<span>Số lượng: '+sl+'</span> ' +
                        '</div> ' +
                        '<p class="priceHD">' +
                        ' <span style="color: #888;text-decoration: line-through;">'+formatNumber(gia)+'</span> ' +
                        '<span style="color: orangered;">'+formatNumber(giaGiam)+'</span>' +
                        ' </p>' +
                        ' </div>' +
                        ' </div>' +
                        ' <hr>';

                    // Thêm thẻ <a> vào container
                    $('#productHD${hd.id}').append(linkHTML);
                });
            },
            error: function (xhr, status, error) {
                // Xử lý lỗi
                console.error("Error occurred while fetching data: " + error);
            }
        });
        </c:forEach>
    };
    $(document).ready(function() {
        // Gọi hàm hiển thị thông tin hóa đơn chi tiết khi trang web được tải
        hienThiHoaDonChiTiet();
    });
</script>
<script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
        intent="WELCOME"
        chat-title="SD40"
        agent-id="96c8e619-f1d8-425a-a536-0cb7cecdb3b7"
        language-code="vi"
></df-messenger>