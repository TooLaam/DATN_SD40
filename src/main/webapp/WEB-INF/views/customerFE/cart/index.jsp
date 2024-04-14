<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<head>
    <style>
        <%@include file="style.css" %>
    </style>
</head>

<div class="container" style="background-color: white">
    <div class="frame">
        <p>Giỏ hàng</p>
    <br/>
    <div class="row">

            <c:choose>
                <c:when test="${empty listghct}">
                    <div class="text-center" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);">
                        Hiện giỏ hàng của bạn đang trống
                    </div>
                </c:when>
                <c:otherwise>
                <div class="col-12">
                    <c:forEach items="${listghct}" var="ghct" varStatus="tt">
                        <div class="productContainer" data-product-id="${ghct.id}" onclick="toggleCheckbox(this)">
                            <img src="/assets/img/product/${ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.hinhAnh}"  width="320px" height="450px" class="productImage">
                            <div class="productInfo">
                                <span class="productName">Tên sản phẩm: ${ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.sanPham.ten}</span>
                                <span style="display: none" class="productID">Tên sản phẩm: ${ghct.id}</span>
                                <c:choose>
                                    <c:when test="${ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam == 0 || ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam == null}">
                                        <span class="productPrice">Giá:  <fmt:formatNumber value=" ${ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.giaHienHanh}" pattern="###,###"/>đ    </span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="productPrice">Giá:  <span style="text-decoration: line-through;color: darkgrey"> <fmt:formatNumber value=" ${ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.giaHienHanh}" pattern="###,###"/>đ    </span>  <span> <fmt:formatNumber value=" ${(ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.giaHienHanh*(100-ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam))/100}" pattern="###,###"/>đ   </span></span>
                                    </c:otherwise>
                                </c:choose>
                                <span style="display: none" class="productPriceT"> ${(ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.giaHienHanh*(100-ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam))/100}  </span>
                                <span class="productKichCo">Kích cỡ: ${ghct.chiTietSanPham.kichCo.ten}</span>
                                <span class="productMauSac">Màu sắc: ${ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.mau_sac.ten}</span>

                                <span class="productSoLuong">
                                    <div class="containerT">
                                        <button onclick="giamSoLuong(${ghct.id})"  id="subtractButton${ghct.id}" class="buttonTang${ghct.id} buttonTangT">-</button>
                                        <input readonly type="number" id="inputField${ghct.id}" class="inputTang" name="soLuong" value="${ghct.soLuong}">
                                        <button onclick="tangSoLuong(${ghct.id})"  id="addButton${ghct.id}" class="buttonTang${ghct.id} buttonTangT">+</button>
                                    </div>
                                </span>
                            </div>
                            <input type="checkbox" class="productCheckbox">
                            <span class="customCheckbox"></span>
                            <div class="productDelete" onclick="deleteProduct(${ghct.id})">
                                <svg width="20px" height="20px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                    <div class="col-12">
                        <div class="table-responsive">
                        <div class="checkout">
                            <p>Tạm tính tổng tiền</p>
                            <hr/>
                            <div class="total-item">
                                <span>
                                    <span>Total : </span> <span id="totalSoLuongDisplay">0</span><span> items</span>
                                      </span>
                                <span id="totalPriceDisplay" class="after" style="font-weight: bold;color: red"><fmt:formatNumber value="0" pattern="###,###"/>đ</span>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary" id="btnSumit" style="color: white;background-color: #00575C;margin-top: 30px;width: 100%">Đặt hàng</button>
                </div>

                </c:otherwise>
            </c:choose>
    </div>
        <br/>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
    function formatNumber(number) {
        return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
<c:forEach items="${listghct}" var="ghct" varStatus="tt">

    function toggleCheckbox(container) {
        var checkbox = container.querySelector('.productCheckbox');
        var isCheckboxEvent = container.classList.contains('checkboxEvent'); // Kiểm tra xem sự kiện click có phát sinh từ checkbox không
        if (!isCheckboxEvent) { // Chỉ thực hiện khi không phải từ checkbox
            checkbox.checked = !checkbox.checked;
            if (checkbox.checked) {
                container.style.backgroundColor = 'rgb(195, 195, 195)'; /* Thay đổi màu nền của sản phẩm khi được chọn */
            } else {
                container.style.backgroundColor = ''; /* Đặt lại màu nền khi không được chọn */
            }
            handleCheckboxChange();
        }
    }

    document.addEventListener('DOMContentLoaded', function() {
        var inputField = document.getElementById('inputField${ghct.id}');
        var addButton = document.getElementById('addButton${ghct.id}');
        var subtractButton = document.getElementById('subtractButton${ghct.id}');
        addButton.addEventListener('click', function() {
            inputField.value = parseInt(inputField.value) + 1;
            var container = this.parentElement.parentElement; // Lấy container chứa nút đã được click
            var checkbox = container.querySelector('.productCheckbox');
            if (checkbox.checked) {
                container.style.backgroundColor = 'rgb(195, 195, 195)'; // Thay đổi màu nền của sản phẩm khi tăng số lượng
            }
        });

        subtractButton.addEventListener('click', function() {
            var currentValue = parseInt(inputField.value);
            if (currentValue > 1) {
                inputField.value = currentValue - 1;
                var container = this.parentElement.parentElement; // Lấy container chứa nút đã được click
                var checkbox = container.querySelector('.productCheckbox');
                if (checkbox.checked) {
                    container.style.backgroundColor = 'rgb(195, 195, 195)'; // Thay đổi màu nền của sản phẩm khi giảm số lượng
                }
            }
        });

        // Ngăn chặn sự kiện click trên checkbox khi nhấp vào các nút tăng giảm số lượng
        var quantityButtons = document.querySelectorAll('.buttonTang${ghct.id}');
        quantityButtons.forEach(function(button) {
            button.addEventListener('click', function(event) {
                event.stopPropagation(); // Ngăn chặn sự kiện click từ các nút tăng giảm số lượng
            });
        });
    });
</c:forEach>

function giamSoLuong(ghctId) {
    var inputField = document.getElementById('inputField' + ghctId);
    var currentValue = parseInt(inputField.value);

    console.log(ghctId)
    console.log(currentValue)
    console.log(inputField)
    // Gửi yêu cầu cập nhật số lượng sản phẩm bằng AJAX
    $.ajax({
        type: 'POST', // Phương thức HTTP
        url: '/updatesoluong', // URL của endpoint để cập nhật số lượng
        data: {
            idghct: ghctId, // ID của sản phẩm cần cập nhật
            soluong: currentValue -1 // Số lượng mới
        },
        success: function(response) {
            if (response === "ko"){
                alert("Số lượng không đủ")
                return;
            }
            else if (response ==="ok") {
                // Xử lý phản hồi từ server (nếu cần)
                console.log('Quantity updated successfully!');
            }
        },
        error: function(xhr, status, error) {
            // Xử lý lỗi (nếu có)
            console.error('Error updating quantity:', error);
        }
    });
    handleCheckboxChange()
}

function tangSoLuong(ghctId) {
    var inputField = document.getElementById('inputField' + ghctId);
    var currentValue = parseInt(inputField.value);

    console.log(ghctId)
    console.log(currentValue)
    console.log(inputField)
    // Gửi yêu cầu cập nhật số lượng sản phẩm bằng AJAX
    $.ajax({
        type: 'POST', // Phương thức HTTP
        url: '/updatesoluong', // URL của endpoint để cập nhật số lượng
        data: {
            idghct: ghctId, // ID của sản phẩm cần cập nhật
            soluong: currentValue +1 // Số lượng mới
        },
        success: function(response) {
            if (response === "ko"){
                alert("Số lượng không đủ")
                    return;
            }else if (response ==="ok") {
                // Xử lý phản hồi từ server (nếu cần)
                console.log('Quantity updated successfully!');
            }
        },
        error: function(xhr, status, error) {
            // Xử lý lỗi (nếu có)
            console.error('Error updating quantity:', error);
        }
    });
    handleCheckboxChange()
}


function handleCheckboxChange() {
    var checkedProductIds = [];
    var totalPrice = 0;
    var totalSoLuong = 0;
    var productContainers = document.getElementsByClassName('productContainer');
    for (var i = 0; i < productContainers.length; i++) {
        var checkbox = productContainers[i].querySelector('.productCheckbox');
        var productId = productContainers[i].querySelector('.productID').textContent.trim(); // Lấy ID của sản phẩm
        var productPrice = parseFloat(productContainers[i].querySelector('.productPriceT').textContent.replace(/[^\d.-]/g, '')); // Lấy giá của sản phẩm
        var productQuantity = parseInt(productContainers[i].querySelector('.inputTang').value); // Lấy số lượng của sản phẩm

        if (checkbox.checked) {
            // Nếu checkbox được chọn, thêm ID của sản phẩm vào mảng
            checkedProductIds.push(productId);
            totalPrice += productPrice * productQuantity;
            totalSoLuong ++;
        }
    }
    var totalPriceDisplay = document.getElementById('totalPriceDisplay');
    totalPriceDisplay.textContent = formatNumber(totalPrice)+"đ"
    var totalSoLuongDisplay = document.getElementById('totalSoLuongDisplay');
    totalSoLuongDisplay.textContent = totalSoLuong;
    // In ra danh sách các ID sản phẩm được chọn (đây chỉ là ví dụ)
    console.log("Các sản phẩm được chọn: " + checkedProductIds.join(', '));
    console.log("Tổng số lượng * giá của các sản phẩm được chọn là: " + totalPrice);
}

// Gán sự kiện change cho tất cả các ô checkbox của sản phẩm
var checkboxes = document.querySelectorAll('.productCheckbox');
checkboxes.forEach(function(checkbox) {
    checkbox.addEventListener('change', handleCheckboxChange);
});

    // Function to handle delete button click event
    function deleteProduct(productId) {
        var cf= confirm("Bạn muốn xóa sản phẩm khỏi giỏ hàng ?");

        if(cf == true) {
            var productElements = document.querySelectorAll('.productContainer');
            productElements.forEach(function (productElement) {
                var dataProductId = productElement.getAttribute('data-product-id');
                if (dataProductId && dataProductId === productId.toString()) {
                    productElement.parentNode.removeChild(productElement);
                    console.log('Product removed from UI successfully');
                    var currentQuantity = parseInt($("#cartCount").text());
                    var newQuantity = currentQuantity -1;
                    $("#cartCount").text(newQuantity);
                    $.ajax({
                        type: 'POST',
                        url: '/deleteGHCT',
                        data: {productId: productId},
                        success: function (response) {
                            console.log('Product deleted from database successfully');
                        },
                        error: function (xhr, status, error) {
                            console.error('Error deleting product from database:', error);
                        }
                    });
                }
            });
        } }


    document.getElementById('btnSumit').addEventListener('click', function(event) {
        var selectedProductIds = [];
        var checkboxes = document.querySelectorAll('.productCheckbox');
        var checked = false; // Biến kiểm tra có sản phẩm nào được chọn không
        checkboxes.forEach(function(checkbox) {
            if (checkbox.checked) {
                var productId = checkbox.closest('.productContainer').getAttribute('data-product-id');
                selectedProductIds.push(productId);
                checked = true;
            }
        });
        if (!checked) {
            alert("Vui lòng chọn sản phẩm")
            return;
        }
        // Gán danh sách ID vào trường ẩn trước khi gửi form
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/bill/datHangTuGioHang",
            data: JSON.stringify(selectedProductIds),
            dataType: 'json',
            success: function(response) {
                // Xử lý phản hồi từ controller nếu cần
                console.log("Danh sách ID sản phẩm đã được gửi thành công!");
                window.location.href = "/bill/hienThiSanPhamHoaDon";
            },
            error: function(xhr, status, error) {
                console.error("Đã xảy ra lỗi khi gửi dữ liệu: " + error);
            }
        });
    });

</script>
<script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
        intent="WELCOME"
        chat-title="SD40"
        agent-id="96c8e619-f1d8-425a-a536-0cb7cecdb3b7"
        language-code="vi"
></df-messenger>
