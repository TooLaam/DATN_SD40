<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<head>
    <link href="/assets/vendor/simple-datatables/style.css" rel="stylesheet">
    <style>
        <%@include file="style.css" %>
    </style>
</head>

<br>
<div class="container">
    <div class="frame">
        <p style=" padding-top: 5px;background-color: #00575C;border: 1px solid black;border-radius: 7px;color: white">Địa chỉ nhận hàng</p>
        <div style="margin-top: 20px" class="row">
            <div class="col-md-6">
                <label  class="form-label">Tên người nhận: </label>
                <div class="input-wrapper">
                    <span> <input style="width: 300px"  required type="text" class="form-control" oninput="layThongTinThanhToan2()" id="ten" name="ten"></span>
                    <span id="tenErr" style="color: red;padding-left: 20px"></span>
                </div>



                <label  class="form-label">Số điện thoại: </label>
                <div class="input-wrapper">
                    <span><input style="width: 300px" oninput="validatePhoneNumber()"  id="sdtNhan" required type="text" class="form-control" name="sdt"></span>
                    <span id="phoneError" style="color: red;padding-left: 20px"></span>
                </div>

                <label  class="form-label">Email: </label>
                <div class="input-wrapper">
                    <span> <input style="width: 300px" oninput="validateEmail()" required type="text" class="form-control" id="email" name="email"></span>
                    <span id="EmailErr" style="color: red;padding-left: 20px"></span>
                </div>
            </div>
            <div style="margin-top: 33px" class="col-md-6">

                <div class="input-wrapper">
                    <label class="form-label">Tỉnh/Thành phố: </label>
                    <input style="width: 300px" type="text" class="search-input" oninput="layThongTinThanhToan2()" list="provinces" id="provinceInput" name="province" data-selected-id="" placeholder="Tìm kiếm tỉnh/thành phố">
                    <span id="errorText" class="error-message">Tỉnh không hợp lệ !</span>
                    <span id="tinhErr" class="error-message"></span>
                </div>

                <datalist id="provinces">
                    <c:forEach items="${listTinh}" var="tinh">
                        <option value="${tinh.ten}" data-id="${tinh.id}">${tinh.ten}</option>
                    </c:forEach>
                </datalist>




                <label style="margin-top: 2px"  class="form-label">Địa chỉ chi tiết: </label>
                <div class="input-wrapper">
                    <span><input style="width: 420px" required type="text" class="form-control" id="diaChiChiTietCho" oninput="layThongTinThanhToan2()" name="diachi"></span>
                    <span id="dcctError" style="color: red;padding-left: 20px"></span>
                </div>

            </div>
        </div>
    </div>

    <div class="frame">
        <p style=" padding-top: 5px;background-color: #00575C;border: 1px solid black;border-radius: 7px;color: white">Sản phẩm</p>
        <div class="row">

            <div style="margin-top: 20px" class="productContainer" data-product-id="${ctsp.id}">

                <img src="/assets/img/product/${ctsp.chiTietSanPhamMauSacHinhAnh.hinhAnh}"  width="320px" height="450px" class="productImage">
                <div class="productInfo">
                    <span class="productName">Tên sản phẩm: ${ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.ten}</span>
                    <span style="display: none" class="productID">Tên sản phẩm: ${ctsp.id}</span>
                    <c:choose>
                        <c:when test="${ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam == 0 || ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam == null}">
                            <span class="productPrice">Giá:  <fmt:formatNumber value=" ${ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh}" pattern="###,###"/>đ    </span>
                        </c:when>
                        <c:otherwise>
                            <span class="productPrice">Giá:  <span style="text-decoration: line-through;color: darkgrey"> <fmt:formatNumber value=" ${ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh}" pattern="###,###"/>đ    </span>  <span> <fmt:formatNumber value=" ${(ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh*(100-ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam))/100}" pattern="###,###"/>đ   </span></span>
                        </c:otherwise>
                    </c:choose>

                    <span id="productPriceT" style="display: none" class="productPriceT"> ${(ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh*(100-ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam))/100}  </span>
                    <span id="giaChuaGiamT" style="display: none" class="giaChuaGiamT"> ${(ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh)}  </span>
                    <span class="productKichCo">Kích cỡ: ${ctsp.kichCo.ten}</span>
                    <span class="productMauSac">Màu sắc: ${ctsp.chiTietSanPhamMauSacHinhAnh.mau_sac.ten}</span>
                    <span class="productSoLuong">
                        <div class="containerT">
                     <div class="containerT">
                        <button  id="subtractButton" class="buttonTang">-</button>
                        <input readonly type="number" id="inputField" class="inputTang" name="soLuong" value="${soLuong}">
                        <button  id="addButton" class="buttonTang">+</button>
                    </div>
                    </div>
                       </span>


                </div>

            </div>


        </div>
    </div>
    <div class="frame">
        <p style=" padding-top: 5px;background-color: #00575C;border: 1px solid black;border-radius: 7px;color: white">Tổng tiền</p>

        <div class="table-responsive">
            <div class="checkout">
                <hr/>
                <div class="total-item">
                    <table class="total-table">
                        <tr>
                            <td rowspan="14" style="width: 450px">Total :<span id="totalSoLuongDisplay">1</span> items
                                <div><h4>Phương thức thanh toán</h4></div>
                                <div class="tab">
                                    <button class="tablinks" id="defaultOpen" onclick="openTab(event, 'Tab1')">Thanh toán khi nhận hàng</button>
                                    <button class="tablinks" onclick="openTab(event, 'Tab2')">VNPAY</button>
                                </div>

                                <div id="Tab1" class="tabcontent">
                                    <form id="addForm" action="/bill/addHoaDon/${ctsp.id}" method="post">
                                        <input type="text" style="display: none" name="tongTien" id="tongTien">
                                        <input type="text" style="display: none" value="0" name="phanTramKhuyenMai" id="phamTramKhuyenMai">
                                        <input type="text" style="display: none" value="${voucher0}" name="idVoucher" id="idVoucher">
                                        <input type="text" style="display: none" name="tongTienGiam" id="tongTienGiam">
                                        <input type="text" style="display: none" name="tenNguoiNhan" id="tenNguoiNhan">
                                        <input type="text" style="display: none" name="sdt" id="sdt">
                                        <input type="text" style="display: none" name="diaChiNguoiNhan" id="diaChiNguoiNhan">
                                        <input type="text" style="display: none" name="diaCHiChiTiet" id="diaCHiChiTiet">
                                        <input type="text" style="display: none" name="tongTienSanPhamChuaGiam" id="tongTienSanPhamChuaGiam">
                                        <input type="text" style="display: none" name="phiShip" id="phiShip">
                                        <input type="text" style="display: none" name="email" id="emailNhan">
                                        <input type="text" style="display: none" name="giaHienHanh" value="${ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh}" >
                                        <input type="text"style="display: none" name="giaDaGiam" value="${(ctsp.chiTietSanPhamMauSacHinhAnh.giaHienHanh*(100-ctsp.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam))/100}" >
                                        <input readonly type="number" id="inputField1" style="display:none;"  class="inputTang" name="soLuong">
                                        <button class="btn btn-primary" id="btnAddHD"  style="color: white;background-color: #00575C;margin-top: 30px;margin-left: 160px">Đặt hàng</button>
                                    </form>
                                </div>

                                <div id="Tab2" class="tabcontent">
                                    <button class="btn btn-primary" style="color: white;background-color: #00575C;margin-top: 30px;margin-left: 160px">Thanh toán</button>
                                </div>
                            </td>
                            <td></td>
                            <td></td>

                        </tr>
                        <tr>
                            <td></td>
                            <td>Tổng tiền sản phẩm : </td>
                            <td><span id="totalPriceDisplay" class="after" style="font-weight: bold;color: red"><fmt:formatNumber value="0" pattern="###,###"/>đ</span></td>
                        </tr>
                        <tr>
                            <td><hr></td>
                            <td>Tổng tiền giảm giá sản phẩm : </td>
                            <td><span id="tongtiengiamSP" class="after" style="font-weight: bold;color: red"><fmt:formatNumber value="0" pattern="###,###"/>đ</span></td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td>Tổng tiền sản phẩm đã giảm giá :</td>
                            <td><span id="tongtienSPDaGiam" class="after" style="font-weight: bold;color: red"><fmt:formatNumber value="0" pattern="###,###"/>đ</span></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>Tổng tiền giảm giá Voucher :
                                <span id="tenVoucher"></span>
                                <span >
                                    <a id="changeLink" style="color: red" href="">[Chọn]</a>
                                </span>

                            </td>
                            <td><span id="soTienGiam" class="after" style="font-weight: bold;color: red"><fmt:formatNumber value="0" pattern="###,###"/>đ</span></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td  style="color: darkgrey;font-size: 12px" id="voucherDetail">
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td id="muaThem" style="color: red;font-size: 12px">
                            </td>
                            <td></td>
                        </tr>


                        <tr>
                            <td></td>
                            <td colspan="2"><hr></td>
                            <td></td>
                        </tr>

                        <tr>
                            <td></td>
                            <td >Tổng tiền sản phẩm  : </td>
                            <td><span id="tongTienSanPham" class="after" style="font-weight: bold;color: red"><fmt:formatNumber value="0" pattern="###,###"/>đ</span></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td >Phí giao hàng : </td>
                            <td ><span id="phiGiaoHang" class="after" style="font-weight: bold;color: red"><fmt:formatNumber value="0" pattern="###,###"/>đ</span></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td style="color: darkgrey;font-size: 12px">*(Với những đơn hàng có tổng tiền sản phẩm từ 1.000.000đ trở lên bạn sẽ được free ship. Đối với đơn hàng ở Hà Nội phí giao hàng sẽ là 30.000đ, các tỉnh khác 40.000đ)
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td id="0tienship" style="color: red;font-size: 12px">
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td colspan="2"><hr></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td >Tổng tiền phải thanh toán : </td>
                            <td><span id="tongTienThanhToan" class="after" style="font-weight: bold;color: red"><fmt:formatNumber value="0" pattern="###,###"/>đ</span></td>
                        </tr>


                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="hiddenForm" >
    <h5 style="color: black">Danh sách voucher bạn có thể sử dụng</h5>
    <span id="closeButton" class="closeButton">&times;</span>
    <div class="scrollable">
        <c:forEach items="${voucher}" var="v">
            <div class="voucher" id="${v[0]}" onclick="selectProduct('${v[0]}','${v[1]}','${v[2]}','${v[3]}','${v[4]}')">
                <div><span class="name"><strong>Voucher: ${v[1]}</strong>   </span> <span class="price" style="color:red;">(-${v[2]}%)</span> </div>
                <div style="color:red;" >Điều kiện: </div>
                <div style="margin-left: 20px">
                    <span class="status">Giảm tối đa: <fmt:formatNumber value="${v[3]}" pattern="###,###"/>đ,  </span>
                    <span class="status">Giá trị tối thiểu của đơn hàng: <fmt:formatNumber value="${v[4]}" pattern="###,###"/>đ </span>
                </div>
            </div>

        </c:forEach>
    </div>
    <button class="btn btn-light" id="submitButton">Chọn</button>
</div>

<div id="loader-overlay" class="loader-overlay" style="display: none;">
    <div class="loader"></div>
</div>
<br/>

<!-- Vendor JS Files -->
<script src="/assets/vendor/apexcharts/apexcharts.min.js"></script>
<script src="/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/assets/vendor/chart.js/chart.umd.js"></script>
<script src="/assets/vendor/echarts/echarts.min.js"></script>
<script src="/assets/vendor/quill/quill.min.js"></script>
<script src="/assets/vendor/simple-datatables/simple-datatables.js"></script>
<script src="/assets/vendor/tinymce/tinymce.min.js"></script>
<script src="/assets/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="/assets/js/main.js">

</script>
<script>
    document.getElementById('btnAddHD').addEventListener('click', function(event) {
        event.preventDefault();

        var tenNguoiNhan = document.getElementById('tenNguoiNhan').value;
        var inputField1 = document.getElementById('inputField1').value;
        var diaCHiChiTiet = document.getElementById('diaCHiChiTiet').value;
        var sdt = document.getElementById('sdt').value;
        var email = document.getElementById('email').value;
        var selectedOption = document.getElementById('provinceInput').value;
        var dataList = document.getElementById('provinces');
        var errorText = document.getElementById('errorText');
        var isValid = false;
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        var phoneRegex = /^(032|033|034|035|036|037|038|039|096|097|098|086|083|084|085|081|082|088|091|094|070|079|077|076|078|090|093|089|056|058|092|059|099)[0-9]{7}$/; // Định dạng số điện thoại ở Việt Nam

        if (inputField1 > ${ctsp.soLuong}){
            alert('Số lượng sản phẩm hiện tại không đủ') ;
            return;
        }
        if (tenNguoiNhan === '') {
            alert('Vui lòng nhập tên người nhận') ;
            return;
        }
        if (diaCHiChiTiet === '') {
            alert('Vui lòng nhập địa chỉ chi tiết') ;
            return;
        }
        if (sdt === '' || phoneRegex.test(sdt) == false) {
            alert('Vui lòng nhập lại số điện thoại') ;
            return;
        }
        if (email === '' || emailRegex.test(email) == false) {
            alert('Vui lòng nhập lại email') ;
            return;
        }

        if (selectedOption.trim() === '') {
            alert('Vui lòng nhập tỉnh/thành phố!') ;
            return; // Kết thúc hàm nếu giá trị rỗng
        }

        // Lặp qua từng option trong datalist
        for (var i = 0; i < dataList.options.length; i++) {
            // Kiểm tra nếu value của option trùng với giá trị đã chọn
            if (dataList.options[i].value === selectedOption) {
                isValid = true;
                break;
            }
        }

        // Hiển thị hoặc ẩn thông báo lỗi
        if (!isValid) {
            alert('Tỉnh không hợp lệ!') ;
            return;
        }
        else {
            document.getElementById('loader-overlay').style.display = 'flex';
            errorText.style.display = 'none';
            document.getElementById('addForm').submit(); // Submit form
        }

    });

    function layThongTinThanhToan2(){
        var ten = document.getElementById('ten').value;
        var provinceInput = document.getElementById('provinceInput').value;
        var diaChiChiTietCho = document.getElementById('diaChiChiTietCho').value;

        var tongTienThanhToan = parseFloat(document.getElementById('tongTienThanhToan').textContent.replace(/[^\d]/g, ''));
        var phiGiaoHang = parseFloat(document.getElementById('phiGiaoHang').textContent.replace(/[^\d]/g, ''));
        var totalPriceDisplay = parseFloat(document.getElementById('totalPriceDisplay').textContent.replace(/[^\d]/g, ''));
        var tongtiengiamSP = parseFloat(document.getElementById('tongtiengiamSP').textContent.replace(/[^\d]/g, ''));
        var soTienGiam = parseFloat(document.getElementById('soTienGiam').textContent.replace(/[^\d]/g, ''));


        var tenNguoiNhan = document.getElementById('tenNguoiNhan');
        var diaChiNguoiNhan = document.getElementById('diaChiNguoiNhan');
        var diaCHiChiTiet = document.getElementById('diaCHiChiTiet');
        var tongTien = document.getElementById('tongTien');
        var tongTienGiam = document.getElementById('tongTienGiam');
        var tongTienSanPhamChuaGiam = document.getElementById('tongTienSanPhamChuaGiam');
        var phiShip = document.getElementById('phiShip');



        tenNguoiNhan.value = ten;

        diaChiNguoiNhan.value= provinceInput;
        diaCHiChiTiet.value = diaChiChiTietCho;
        tongTien.value = tongTienThanhToan;
        tongTienGiam.value = soTienGiam+tongtiengiamSP;
        tongTienSanPhamChuaGiam.value = totalPriceDisplay;
        phiShip.value = phiGiaoHang;


    }


    // Open Tab 1 by default
    document.getElementById("defaultOpen").click();

    function openTab(evt, tabName) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(tabName).style.display = "block";
        evt.currentTarget.className += " active";
    }

    //------------------------------------------------

    function validatePhoneNumber(input) {
        var phoneNumber = document.getElementById('sdtNhan').value;
        var phoneRegex = /^(032|033|034|035|036|037|038|039|096|097|098|086|083|084|085|081|082|088|091|094|070|079|077|076|078|090|093|089|056|058|092|059|099)[0-9]{7}$/; // Định dạng số điện thoại ở Việt Nam

        if (phoneNumber.trim() === '') {
            document.getElementById('phoneError').textContent = ''; // Ẩn thông báo lỗi nếu ô input trống
            document.getElementById("sdt").value = phoneNumber;
        } else if (phoneRegex.test(phoneNumber)) {
            document.getElementById('phoneError').textContent = ''; // Số điện thoại hợp lệ
            document.getElementById("sdt").value = phoneNumber;
        } else {
            document.getElementById('phoneError').textContent = 'Số điện thoại không hợp lệ'; // Số điện thoại không hợp lệ
            document.getElementById("sdt").value = phoneNumber;
        }
    }

    function validateEmail() {

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        var email = document.getElementById('email').value;
        if (email.trim() ===''){
            document.getElementById('EmailErr').textContent = '';
            document.getElementById('emailNhan').value = email;
        }else if (emailRegex.test(email)){
            document.getElementById('EmailErr').textContent = '';
            document.getElementById('emailNhan').value = email;
        }else {
            document.getElementById('EmailErr').textContent = 'Email không hợp lệ';
            document.getElementById('emailNhan').value = email;
        }
    }

    var selectedVoucher = null;
    document.getElementById('changeLink').addEventListener('click', function(event) {
        event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ a

        var hiddenForm = document.getElementById('hiddenForm');
        if (hiddenForm.style.display === 'none') {
            hiddenForm.style.display = 'block';
        } else {
            hiddenForm.style.display = 'none';
        }
    });

    document.getElementById('closeButton').addEventListener('click', function() {
        document.getElementById('hiddenForm').style.display = 'none';
    });

    function selectProduct(id,ten,phamTramGiam,giamToiDa,donToiThieu) {
        // Loại bỏ lớp clicked từ tất cả các sản phẩm
        var vouchers = document.querySelectorAll('.voucher');
        vouchers.forEach(function(voucher) {
            voucher.classList.remove('clicked');
        });
        document.getElementById(id).classList.add('clicked');
        // Thêm lớp clicked vào sản phẩm được click
        selectedVoucher = {
            id: id,
            ten: ten,
            phamTramGiam: phamTramGiam,
            giamToiDa: giamToiDa,
            donToiThieu: donToiThieu
        };

    }

    document.getElementById('submitButton').addEventListener('click', function() {
        var voucherDetail = document.getElementById('voucherDetail');
        var tenVoucher = document.getElementById('tenVoucher');
        var hiddenForm = document.getElementById('hiddenForm');
        var phamTramKhuyenMai = document.getElementById('phamTramKhuyenMai');
        var idVoucher = document.getElementById('idVoucher');
        // Kiểm tra xem đã chọn sản phẩm nào chưa
        if (selectedVoucher !== null) {
            // Thực hiện các hành động với sản phẩm được chọn ở đây

            var htmlvoucherDetail = ' Quý khách sẽ được giảm '+selectedVoucher.phamTramGiam+'% với hóa đơn từ '+formatNumber(parseInt(selectedVoucher.donToiThieu)) +
                ' (Không bao gồm phí giao hàng). Tối đa giảm '+formatNumber(parseInt(selectedVoucher.giamToiDa))+')'
            voucherDetail.innerHTML = htmlvoucherDetail;
            tenVoucher.innerHTML = selectedVoucher.ten;
            hiddenForm.style.display = 'none';
            phamTramKhuyenMai.value = selectedVoucher.phamTramGiam;
            idVoucher.value = selectedVoucher.id
            console.log("Sản phẩm được chọn:", selectedVoucher);
        } else {
            console.log("Chưa chọn sản phẩm nào.");
            alert("Vui lòng chọn voucher !!")
        }
        calculateTotalPrice()

    });


    //---------------------------------------
    function formatNumber(price) {
        var formattedPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(Math.round(price));

        // Loại bỏ phần thập phân và chữ "đ" phía sau
        formattedPrice = formattedPrice.replace(/(\d+)(?:\.(\d+))? đ/, '$1');

        return formattedPrice;
    }




    var errorText = document.getElementById('errorText');
    var dataList = document.getElementById('provinces');
    var selectedOptionId;
    document.getElementById('provinceInput').addEventListener('input', function(event) {
        var selectedOption = event.target.value;
        var valid = false;

        // Kiểm tra nếu giá trị nhập vào không rỗng
        if (selectedOption.trim() !== '') {
            // Lặp qua từng option trong datalist
            for (var i = 0; i < dataList.options.length; i++) {
                // Kiểm tra nếu value của option trùng với giá trị đã chọn
                if (dataList.options[i].value === selectedOption) {
                    valid = true;
                    // Gán giá trị id của option tương ứng cho biến selectedOptionId
                    selectedOptionId = dataList.options[i].getAttribute('data-id');
                    break;
                }
            }
        } else {
            valid = true; // Giá trị rỗng được coi là hợp lệ
            selectedOptionId = null;
        }

        // Hiển thị hoặc ẩn thông báo lỗi
        if (!valid) {
            errorText.style.display = 'block';
            selectedOptionId = null;
        } else {
            errorText.style.display = 'none';
        }

        // Gọi hàm calculateTotalPrice sau khi đã có giá trị cho selectedOptionId
        calculateTotalPrice();
        layThongTinThanhToan2()
    });






    document.addEventListener('DOMContentLoaded', function() {
        var inputField = document.getElementById('inputField');
        var inputField1 = document.getElementById('inputField1');
        var addButton = document.getElementById('addButton');
        var subtractButton = document.getElementById('subtractButton');
        inputField1.value = inputField.value;
        addButton.addEventListener('click', function() {
            inputField.value = parseInt(inputField.value) + 1;
            inputField1.value = inputField.value;
            calculateTotalPrice();
            layThongTinThanhToan2()

        });

        subtractButton.addEventListener('click', function() {
            var currentValue = parseInt(inputField.value);
            var currentValue1 = parseInt(inputField1.value);
            if (currentValue > 1) {
                inputField.value = currentValue - 1;
                inputField1.value = inputField.value;
                calculateTotalPrice();
                layThongTinThanhToan2()

            }
        });
    });



    // Tạo một hàm để tính tổng tiền
    function calculateTotalPrice() {
        // Lấy giá trị của giá sản phẩm
        var idDiaChi = selectedOptionId;
        var productPriceElement = document.getElementById('productPriceT');
        var productPrice = parseFloat(productPriceElement.textContent.replace(/[^\d.-]/g, ''));

        var giaChuaGiammmm = document.getElementById('giaChuaGiamT');
        var giaChuaGiam = parseFloat(giaChuaGiammmm.textContent.replace(/[^\d.-]/g, ''));



        var inputFieldElement = document.getElementById('inputField');
        var quantity = parseInt(inputFieldElement.value);

        var totalPrice = giaChuaGiam * quantity;
        var tienSPdaGiam = productPrice * quantity;
        var tongTienGiamSPp = (giaChuaGiam-productPrice)*quantity;


        var phanTramVoucher;
        var dontoithieu;
        var giamtoida;
        if (selectedVoucher== null){
            phanTramVoucher=0;
            dontoithieu = 0;
            giamtoida= 0;
        }else {
            phanTramVoucher = parseInt(selectedVoucher.phamTramGiam);
            dontoithieu=parseInt(selectedVoucher.donToiThieu);
            giamtoida=parseInt(selectedVoucher.giamToiDa);
        }
        var tiengiam = (tienSPdaGiam*phanTramVoucher)/100;
        var soTienGiam = document.getElementById('soTienGiam');
        var soTienGiamSo = parseFloat(soTienGiam.textContent.replace(/[^\d.-]/g, ''));
        var muaThem = document.getElementById('muaThem');
        var giamVoucher;
        if (tienSPdaGiam<dontoithieu){
            soTienGiam.textContent = '-'+0+'đ';
            muaThem.textContent='*(Bạn cần mua thêm '+ formatNumber(dontoithieu-tienSPdaGiam)+' để được hưởng ưu đãi.)'
            muaThem.style.display="block"
            giamVoucher = 0;
        }else {
            muaThem.style.display="none"
            if (tiengiam>=giamtoida){
                soTienGiam.textContent='-'+formatNumber(giamtoida)
                giamVoucher = giamtoida;
            }else {
                soTienGiam.textContent='-'+formatNumber(tiengiam)
                giamVoucher = tiengiam;
            }
        }
        var tienspsaukhigiamtatca= (productPrice * quantity) - giamVoucher;
        var idHN = ${idHaNoi}

        var phiGiaoHang = document.getElementById('phiGiaoHang');
        var phiship;
        var freetienship = document.getElementById('0tienship');

        if(idDiaChi == null || tienspsaukhigiamtatca >=1000000){
            phiGiaoHang.textContent = formatNumber(0)
            freetienship.style.display="none"
            phiship = 0;
        }else {
            if (parseInt(idDiaChi) === idHN){
                phiGiaoHang.textContent = formatNumber(30000)
                freetienship.style.display="block"
                freetienship.textContent ='*(Bạn cần mua thêm '+ formatNumber(1000000-tienspsaukhigiamtatca)+' để được freeship)'
                phiship = 30000;
            }else {
                phiGiaoHang.textContent = formatNumber(40000);
                freetienship.style.display="block"
                freetienship.textContent ='*(Bạn cần mua thêm '+ formatNumber(1000000-tienspsaukhigiamtatca)+' để được freeship)'
                phiship = 40000;
            }}
        var tongtiensanpham = (productPrice*quantity) - giamVoucher;
        var tongtienphaitra = (productPrice*quantity) - giamVoucher+phiship;
        var totalPriceElement = document.getElementById('totalPriceDisplay');
        var tongtienSPDaGiam = document.getElementById('tongtienSPDaGiam');
        var totalSoLuongDisplay = document.getElementById('totalSoLuongDisplay');
        var tongtiengiamSP = document.getElementById('tongtiengiamSP');
        var tongTienSanPham = document.getElementById('tongTienSanPham');
        tongTienSanPham.textContent = formatNumber(tongtiensanpham);
        var tongTienThanhToan = document.getElementById('tongTienThanhToan');
        tongTienThanhToan.textContent = formatNumber(tongtienphaitra);
        // Hiển thị tổng tiền trên trang
        totalPriceElement.textContent =formatNumber(totalPrice);
        totalSoLuongDisplay.textContent =quantity;
        tongtiengiamSP.textContent = '-'+formatNumber(tongTienGiamSPp);
        tongtienSPDaGiam.textContent = formatNumber(tienSPdaGiam);
        layThongTinThanhToan2()
    }

    // Gọi hàm tính tổng tiền ban đầu
    calculateTotalPrice();
    layThongTinThanhToan2()

</script>
</body>

</html>