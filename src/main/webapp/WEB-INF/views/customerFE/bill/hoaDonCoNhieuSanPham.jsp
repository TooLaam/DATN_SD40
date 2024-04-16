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

            </div>
            <div style="margin-top: 33px" class="col-md-6">


                <label style="margin-top: 2px"  class="form-label">Địa chỉ chi tiết: </label>
                <div class="input-wrapper" id="dcct">
                    <span id="diaChiChiTietCho" name="diachi">${diachimacdinh.diaChi.moTa} </span>
                    <c:if test="${diachimacdinh!= null}">
                        <span style="margin-left: 10px">,  Tỉnh/Thành phố: </span>
                    </c:if>
                    <span id="tinhCho" style="margin-left: 5px">${diachimacdinh.diaChi.tinhThanhPho.ten}</span>
                    <c:if test="${diachimacdinh.macDinh==0}">
                        <span style="color:red;margin-left: 5px"> [Mặc định]</span>
                    </c:if>
                    <span style="display: none" id="tinh">${diachimacdinh.diaChi.tinhThanhPho.id}</span>
                </div>
                <div id="btnDiaChi">
                    <c:choose>
                        <c:when test="${diachimacdinh != null}">
                            <button class="btn btn-primary" onclick="btnThayDoiDiaChi()"  style="color: white;background-color: #00575C;margin-top: 15px;">Thay đổi địa chỉ</button>
                        </c:when>
                        <c:otherwise>
                            <span> <button class="btn btn-light" onclick="hienThiThem()">Thêm địa chỉ</button></span>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>

    <div class="frame">
        <p style=" padding-top: 5px;background-color: #00575C;border: 1px solid black;border-radius: 7px;color: white">Sản phẩm</p>
        <div class="row">
        <c:forEach items="${listGHCT}" var="ghct">
            <div style="margin-top: 20px" class="productContainer" data-product-id="${ghct.id}">

                <img src="/assets/img/product/${ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.hinhAnh}"  width="320px" height="450px" class="productImage">
                <div class="productInfo">
                    <div class="productName">Tên sản phẩm: ${ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.sanPham.ten}</div>
                    <span style="display: none" id="productID${ghct.id}" class="productID">${ghct.chiTietSanPham.id}</span>
                    <c:choose>
                        <c:when test="${ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam == 0 || ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam == null}">
                            <span class="productPrice">Giá:  <fmt:formatNumber value=" ${ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.giaHienHanh}" pattern="###,###"/>đ    </span>
                        </c:when>
                        <c:otherwise>
                            <span class="productPrice">Giá:  <span style="text-decoration: line-through;color: darkgrey"> <fmt:formatNumber value=" ${ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.giaHienHanh}" pattern="###,###"/>đ    </span>  <span> <fmt:formatNumber value=" ${(ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.giaHienHanh*(100-ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam))/100}" pattern="###,###"/>đ   </span></span>
                        </c:otherwise>
                    </c:choose>

                    <span style="display: none"  class="productPriceT"> ${(ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.giaHienHanh*(100-ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.sanPham.giamGIa.mucGiam))/100}  </span>
                    <span style="display: none" class="giaChuaGiamT"> ${(ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.giaHienHanh)}  </span>
                    <span class="productKichCo">Kích cỡ: ${ghct.chiTietSanPham.kichCo.ten}</span>
                    <span class="productMauSac">Màu sắc: ${ghct.chiTietSanPham.chiTietSanPhamMauSacHinhAnh.mau_sac.ten}</span>
                    <span >Số lượng: </span> <span id="soLuong${ghct.id}" class="productSoLuong">${ghct.soLuong}</span>
                </div>
            </div>
        </c:forEach>
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
                                    <form id="addForm" action="/bill/HienThiHoaDonKhachHangCoNhieuSanPham" method="post">
                                        <input type="text"  style="display: none" name="tongTien" id="tongTien">
                                        <input type="text" style="display: none" value="0" name="phanTramKhuyenMai" id="phamTramKhuyenMai">
                                        <input type="text" style="display: none"  value="${voucher0}" name="idVoucher" id="idVoucher">
                                        <input type="text" style="display: none" name="tongTienGiam" id="tongTienGiam">
                                        <input type="text"  style="display: none" name="tenNguoiNhan" id="tenNguoiNhan">
                                        <input type="text" style="display: none"  name="sdt" id="sdt">
                                        <input type="text" style="display: none" name="diaChiNguoiNhan" id="diaChiNguoiNhan">
                                        <input type="text" style="display: none" name="tinh" id="tinhNhan">
                                        <input type="text" style="display: none" name="tongTienSanPhamChuaGiam" id="tongTienSanPhamChuaGiam">
                                        <input type="text" style="display: none" name="phiShip" id="phiShip">

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
    <!-- Đặt các trường của form ở đây -->
    <!-- Ví dụ: -->
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

<div id="hiddenForm1" >
    <h5 style="color: black">Danh sách địa chỉ</h5>
    <span id="closeButton1" class="closeButton">&times;</span>
    <!-- Đặt các trường của form ở đây -->
    <!-- Ví dụ: -->
    <div class="scrollable">
        <div  id="listDanhSach">
            <c:forEach items="${listDiaChi}" var="v">

                <div class="voucher" id="${v.id}" onclick="selectDiaChi('${v.id}','${v.diaChi.moTa}','${v.diaChi.tinhThanhPho.id}','${v.diaChi.tinhThanhPho.ten}','${v.macDinh}')">

                    <div class="content">
                        <span  class="name">Địa chỉ: ${v.diaChi.moTa}  </span>
                        <span class="price">  Tỉnh/Thành phố ${v.diaChi.tinhThanhPho.ten}  </span>
                        <c:choose>
                            <c:when test="${v.macDinh == 0}">
                                <span style="color:red;">[Mặc định]</span>
                            </c:when>
                            <c:otherwise>
                            <span>
                                <a onclick="updateMacDinh(${v.id},event)" style="color:#00ff80;cursor: pointer;">[Cài đặt mặc định]</a>
                            </span>
                            </c:otherwise>
                        </c:choose>

                    </div>
                    <div class="productDelete"  onclick="deleteDiaChi(${v.id},event)">
                        <svg width="20px" height="20px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>
                    </div>
                </div>

            </c:forEach>
        </div>
    </div>
    <div class="button-container">
        <span><button class="btn btn-light" id="submitButton1" onclick="layThongTinThanhToan2()" >Chọn</button></span>
        <span> <button class="btn btn-light" id="themDiaChi">Thêm</button></span>
    </div>
</div>


<div id="diaChiMoi" >
    <h5 style="color: black">Địa chỉ mới</h5>
    <span id="closeButton2" class="closeButton">&times;</span>
    <br>
    <br>
    <div>
        <label class="form-label">Tỉnh/Thành phố: </label><span id="errorText" class="error-message">Tỉnh không hợp lệ !</span>
        <input style="width: 100%" type="text" class="search-input" list="provinces" id="provinceInput" name="province" data-selected-id="" placeholder="Tìm kiếm tỉnh/thành phố">
        <datalist id="provinces">
            <!-- Các option được tạo bằng cách lặp qua danh sách `listTinh` từ controller -->
            <c:forEach items="${listTinh}" var="tinh">
                <option value="${tinh.ten}" data-id="${tinh.id}">${tinh.ten}</option>
            </c:forEach>
        </datalist>
    </div>
    <div style="display: none" id="selectedOptionIdDisplay"></div>
    <br>
    <label class="form-label">Địa chỉ chi tiết</label><br>
    <input id="detailDiaChi" type="text" style="width: 100%" class="form-control">
    <br>
    <br>
    <button class="btn btn-light" onclick="addDiaChi()">Thêm mới</button>
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js">

</script >
<script  >


    document.getElementById('btnAddHD').addEventListener('click', function(event) {
        event.preventDefault();

        var tenNguoiNhan = document.getElementById('tenNguoiNhan').value;
        var diaChi = document.getElementById('diaChiChiTietCho').textContent;
        var sdt = document.getElementById('sdt').value;
        var errorText = document.getElementById('errorText');
        var tinhNhan = document.getElementById('tinhNhan');
        var phoneRegex = /^(032|033|034|035|036|037|038|039|096|097|098|086|083|084|085|081|082|088|091|094|070|079|077|076|078|090|093|089|056|058|092|059|099)[0-9]{7}$/; // Định dạng số điện thoại ở Việt Nam

        if (diaChi ===''||tinhNhan ===''){
            alert("Vui lòng chọn địa chỉ")
            return;
        }
        if (tenNguoiNhan === '') {
            alert('Vui lòng nhập tên người nhận') ;
            return;
        }
        if (sdt === '' || phoneRegex.test(sdt) == false) {
            alert('Vui lòng nhập lại số điện thoại') ;
            return;
        }

        else {
            document.getElementById('loader-overlay').style.display = 'flex';
            errorText.style.display = 'none';
            var productIds = [];
            var productElements = document.querySelectorAll('.productContainer');
            productElements.forEach(function(element) {
                var productId = element.getAttribute('data-product-id');
                productIds.push(productId);
            });
            $.ajax({
                type: "POST",
                url: "/bill/addHoaDonKhachHangCoNhieuSanPham",
                data: JSON.stringify(productIds),
                contentType: 'application/json',
                dataType: 'json',
                success: function(response) {
                    // Xử lý phản hồi từ controller nếu cần
                    console.log("Danh sách ID sản phẩm đã được gửi thành công!");
                    // window.location.href = "/bill/HienThiHoaDonKhachHangCoNhieuSanPham";
                    document.getElementById("addForm").submit();
                },
                error: function(xhr, status, error) {
                    console.error("Đã xảy ra lỗi khi gửi dữ liệu: " + error);
                }
            });

        }

    });

    function layThongTinThanhToan2(){
        var ten = document.getElementById('ten').value;
        var diaChiChiTietCho = document.getElementById('diaChiChiTietCho').textContent;
        var tinh = document.getElementById('tinhCho').textContent;
        var tongTienThanhToan = parseFloat(document.getElementById('tongTienThanhToan').textContent.replace(/[^\d]/g, ''));
        var phiGiaoHang = parseFloat(document.getElementById('phiGiaoHang').textContent.replace(/[^\d]/g, ''));
        var totalPriceDisplay = parseFloat(document.getElementById('totalPriceDisplay').textContent.replace(/[^\d]/g, ''));
        var tongtiengiamSP = parseFloat(document.getElementById('tongtiengiamSP').textContent.replace(/[^\d]/g, ''));
        var soTienGiam = parseFloat(document.getElementById('soTienGiam').textContent.replace(/[^\d]/g, ''));


        var tenNguoiNhan = document.getElementById('tenNguoiNhan');
        var diaChiNguoiNhan = document.getElementById('diaChiNguoiNhan');
        var tinhNhan = document.getElementById('tinhNhan')
        var tongTien = document.getElementById('tongTien');
        var tongTienGiam = document.getElementById('tongTienGiam');
        var tongTienSanPhamChuaGiam = document.getElementById('tongTienSanPhamChuaGiam');
        var phiShip = document.getElementById('phiShip');



        tenNguoiNhan.value = ten;

        diaChiNguoiNhan.value = diaChiChiTietCho;
        tinhNhan.value = tinh;
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

    //=====================================================================================================

    //Update mặc định
    function updateMacDinh(idDiaChi,event){
        event.stopPropagation();
        $.ajax({
            type: "POST",
            url: "/bill/updateMacDinh/"+idDiaChi,
            success: function(response) {

                document.getElementById('listDanhSach').innerText = '';

                // Lặp qua danh sách dữ liệu và tạo thẻ <a> cho mỗi mục
                $.each(response, function(index, data) {
                    var iddcct = data[0];
                    var moTa = data[1];
                    var tinh = data[2];
                    var idTinh = data[3];
                    var macDinh = data[4];

                    // Tạo thẻ <a> với các thông tin và gán sự kiện onclick
                    var linkHTML = '<div class="voucher" id="' + iddcct + '' +
                        '" onclick="selectDiaChi(' + iddcct + ',\'' + moTa + '\',' + idTinh + ',\'' + tinh + '\',' + macDinh +
                        ')"> <div> <span class="name">Địa chỉ: ' + moTa + ' </span> <span class="price"> Tỉnh/Thành phố ' + tinh +
                        ' </span>' + (macDinh == 0 ? '<span style="color:red;">[Mặc định]</span>' : '<span> <a onclick="updateMacDinh('+iddcct+',event)" style="color:#00ff80;cursor: pointer;">[Cài đặt mặc định]</a> </span>') +
                        ' </div>  <div class="productDelete" onclick="deleteDiaChi('+iddcct+
                        ',event)"> <svg width="20px" height="20px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg> </div> </div>';

                    // Thêm thẻ <a> vào container
                    $('#listDanhSach').append(linkHTML);


                });
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi
                console.error("Error occurred while fetching data: " + error);
            }
        });
    }


    //Thêm địa chỉ mới
    document.getElementById('themDiaChi').addEventListener('click', function(event) {
        var hiddenForm = document.getElementById('diaChiMoi');
        if (hiddenForm.style.display === 'none') {
            hiddenForm.style.display = 'block';
        } else {
            hiddenForm.style.display = 'none';
        }
    });
    function hienThiThem(){
        var hiddenForm = document.getElementById('diaChiMoi');
        if (hiddenForm.style.display === 'none') {
            hiddenForm.style.display = 'block';
        } else {
            hiddenForm.style.display = 'none';
        }
    };

    document.getElementById('closeButton2').addEventListener('click', function() {
        document.getElementById('diaChiMoi').style.display = 'none';
    });

    function addDiaChi() {
        var selectedOption = document.getElementById('provinceInput').value;
        var diaCHiChiTiet= document.getElementById('detailDiaChi').value;
        var isValid = false;
        if (diaCHiChiTiet.trim() ===''){
            alert("Vui lòng nhập địa chỉ chi tiết")
            return;
        }
        if (selectedOption.trim() === '') {
            alert('Vui lòng nhập tỉnh/thành phố!') ;
            return;
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
        $.ajax({
            type: 'POST',
            url: '/bill/addDiaChiChiTiet',
            data: {
                province:parseInt(document.getElementById('selectedOptionIdDisplay').textContent) , // ID của sản phẩm cần cập nhật
                detailAddress:document.getElementById('detailDiaChi').value
            },
            success: function(response) {
                if (response === "ok"){
                    $.ajax({
                        type: "GET",
                        url: "/bill/layDiaChiMoiNhat",
                        success: function(response) {
                            document.getElementById('listDanhSach').innerText = '';

                            // Lặp qua danh sách dữ liệu và tạo thẻ <a> cho mỗi mục
                            $.each(response, function(index, data) {
                                var iddcct = data[0];
                                var moTa = data[1];
                                var tinh = data[2];
                                var idTinh = data[3];
                                var macDinh = data[4];

                                // Tạo thẻ <a> với các thông tin và gán sự kiện onclick
                                var linkHTML = '<div class="voucher" id="' + iddcct + '' +
                                    '" onclick="selectDiaChi(' + iddcct + ',\'' + moTa + '\',' + idTinh + ',\'' + tinh + '\',' + macDinh +
                                    ')"> <div> <span class="name">Địa chỉ: ' + moTa + ' </span> <span class="price"> Tỉnh/Thành phố ' + tinh +
                                    ' </span>' + (macDinh == 0 ? '<span style="color:red;">[Mặc định]</span>' : '<span> <a onclick="updateMacDinh('+iddcct+',event)" style="color:#00ff80;cursor: pointer;">[Cài đặt mặc định]</a> </span>') +
                                    ' </div>  <div class="productDelete" onclick="deleteDiaChi('+iddcct+
                                    ',event)"> <svg width="20px" height="20px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg> </div> </div>';


                                var linkHTML2='<button class="btn btn-primary" onclick="btnThayDoiDiaChi()"  style="color: white;background-color: #00575C;margin-top: 15px;">Thay đổi địa chỉ</button>'
                                // Thêm thẻ <a> vào container
                                $('#listDanhSach').append(linkHTML);
                                document.getElementById('btnDiaChi').innerHTML='';
                                $('#btnDiaChi').append(linkHTML2);
                                document.getElementById('diaChiMoi').style.display = 'none';
                                document.getElementById('hiddenForm1').style.display = 'block';
                                document.getElementById('loader-overlay').style.display='flex'
                                setTimeout(function() {
                                    document.getElementById('loader-overlay').style.display = 'none';
                                }, 300);
                            });
                        },
                        error: function(xhr, status, error) {
                            // Xử lý lỗi
                            console.error("Error occurred while fetching data: " + error);
                        }
                    });

                }

            },
            error: function(xhr, status, error) {
                // Xử lý lỗi (nếu có)
                console.error('Error updating quantity:', error);
            }
        });
    };

    function deleteDiaChi(addressId,event) {
        // Xóa phần tử HTML trên giao diện

        event.stopPropagation();

        // Gửi yêu cầu xóa đến máy chủ
        $.ajax({
            type: 'DELETE',
            url: '/bill/deleteAddress/' + addressId,
            success: function(response) {
                if (response === "Ko"){
                    alert("Địa chỉ này đang mặc định nên không thể xóa, Vui lòng đổi mặc định trước khi xóa !!!")
                    return;
                }
                else {
                    console.log('Địa chỉ đã được xóa khỏi cơ sở dữ liệu.');
                    var addressElement = document.getElementById(addressId);
                    addressElement.remove();
                    document.getElementById('loader-overlay').style.display='flex'
                    setTimeout(function() {
                        document.getElementById('loader-overlay').style.display = 'none';
                    }, 300);
                }

            },
            error: function(xhr, status, error) {
                console.error('Lỗi xóa địa chỉ:', error);
            }
        });
    }

    //Thay đổi địa chỉ
    var selectedDiaChi= null;
    function btnThayDoiDiaChi() {


        var hiddenForm = document.getElementById('hiddenForm1');
        if (hiddenForm.style.display === 'none') {
            hiddenForm.style.display = 'block';
        } else {
            hiddenForm.style.display = 'none';
        }
    };

    document.getElementById('closeButton1').addEventListener('click', function() {
        document.getElementById('hiddenForm1').style.display = 'none';
    });

    function selectDiaChi(iddcct,moTa,idTinh,tenTinh,macDinh) {
        // Loại bỏ lớp clicked từ tất cả các sản phẩm
        var vouchers = document.querySelectorAll('.voucher');
        vouchers.forEach(function (voucher) {
            voucher.classList.remove('clicked');
        });
        document.getElementById(iddcct).classList.add('clicked');
        // Thêm lớp clicked vào sản phẩm được click
        selectedDiaChi = {
            iddcct: iddcct,
            moTa: moTa,
            idTinh: idTinh,
            tenTinh: tenTinh,
            macDinh:macDinh
        };
    }

    document.getElementById('submitButton1').addEventListener('click', function() {
        var dcct = document.getElementById('dcct');
        var hiddenForm = document.getElementById('hiddenForm1');
        var diaChiNguoiNhan = document.getElementById('diaChiNguoiNhan');

        // Kiểm tra xem đã chọn sản phẩm nào chưa
        if (selectedDiaChi !== null) {
            // Xóa nội dung của phần tử dcct trước khi thêm nội dung mới
            dcct.innerHTML = '';

            // Thực hiện các hành động với sản phẩm được chọn ở đây
            var htmlvoucherDetail = '<span id="diaChiChiTietCho" name="diachi">' + selectedDiaChi.moTa + '</span>' +
                '<span style="margin-left: 10px"> Tỉnh/Thành phố: </span>' +
                '<span id="tinhCho" style="margin-left: 5px">' + selectedDiaChi.tenTinh + '</span>' +
                (selectedDiaChi.macDinh == 0 ? '<span style="color:red;margin-left: 5px"> [Mặc định]</span>' : '') +
                '<span style="display: none" id="tinh">' + selectedDiaChi.idTinh + '</span>';

            diaChiNguoiNhan.value = selectedDiaChi.moTa+', Tỉnh/Thành phố'+selectedDiaChi.tenTinh;
            dcct.innerHTML = htmlvoucherDetail;
            hiddenForm.style.display = 'none';
            document.getElementById('loader-overlay').style.display='flex'
            setTimeout(function() {
                document.getElementById('loader-overlay').style.display = 'none';
            }, 300);
            console.log("Sản phẩm được chọn:", selectedVoucher);
        } else {
            console.log("Chưa chọn sản phẩm nào.");
            alert("Vui lòng chọn địa chỉ !!")
        }
        calculateTotalPrice();
    });


    //--------------------------------------------

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
        layThongTinThanhToan2()
    });


    //---------------------------------------
    function formatNumber(price) {
        var formattedPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(Math.round(price));

        // Loại bỏ phần thập phân và chữ "đ" phía sau
        formattedPrice = formattedPrice.replace(/(\d+)(?:\.(\d+))? đ/, '$1');

        return formattedPrice;
    }



    function calculateTotalPrice() {
        var totalPrice = 0;
        var totalSoLuong = 0;
        var tongGiaChuaGiam = 0 ;
        var tongTienGiam = 0 ;
        var productContainers = document.getElementsByClassName('productContainer');
        for (var i = 0; i < productContainers.length; i++) {
            var productPrice = parseFloat(productContainers[i].querySelector('.productPriceT').textContent.replace(/[^\d.-]/g, ''));
            var giaChuaGiam = parseFloat(productContainers[i].querySelector('.giaChuaGiamT').textContent.replace(/[^\d.-]/g, ''));
            var productQuantity = parseInt(productContainers[i].querySelector('.productSoLuong').textContent);

                totalPrice += productPrice * productQuantity;
                tongGiaChuaGiam += giaChuaGiam * productQuantity;
                tongTienGiam += (giaChuaGiam-productPrice) * productQuantity;
                totalSoLuong ++;

        }
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
        console.log(selectedVoucher)
        var tiengiam = (totalPrice*phanTramVoucher)/100;
        var soTienGiam = document.getElementById('soTienGiam');
        var soTienGiamSo = parseFloat(soTienGiam.textContent.replace(/[^\d.-]/g, ''));
        var muaThem = document.getElementById('muaThem');
        var giamVoucher;
        if (totalPrice<dontoithieu){
            soTienGiam.textContent = '-'+0+'đ';
            muaThem.textContent='*(Bạn cần mua thêm '+ formatNumber(dontoithieu-totalPrice)+' để được hưởng ưu đãi.)'
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
        var tienspsaukhigiamtatca=totalPrice - giamVoucher;
        document.getElementById('tongTienSanPham').textContent = formatNumber(tienspsaukhigiamtatca);

        var phiGiaoHang = document.getElementById('phiGiaoHang');
        var phiship;
        var freetienship = document.getElementById('0tienship');
        var idDiaChi =document.getElementById('tinh').innerText ;
        if(tienspsaukhigiamtatca >=1000000 || idDiaChi == null){
            phiGiaoHang.textContent = formatNumber(0)
            freetienship.style.display="none"
                phiship = 0;
        }else {
            if (parseInt(idDiaChi) === ${idHaNoi}){
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
        var tongTienThanhToan = tienspsaukhigiamtatca+phiship;
        document.getElementById('tongTienThanhToan').textContent = formatNumber(tongTienThanhToan);


        document.getElementById('totalPriceDisplay').textContent = formatNumber(tongGiaChuaGiam);
        document.getElementById('tongtiengiamSP').textContent = formatNumber(tongTienGiam);
        document.getElementById('tongtienSPDaGiam').textContent = formatNumber(totalPrice);


        document.getElementById('totalSoLuongDisplay').textContent = totalSoLuong;
        layThongTinThanhToan2()
    }

    calculateTotalPrice();
    layThongTinThanhToan2()

    var errorText = document.getElementById('errorText');
    var dataList = document.getElementById('provinces');
    var selectedOptionId;
    var idDisplay = document.getElementById('selectedOptionIdDisplay');
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
        idDisplay.textContent = selectedOptionId ? selectedOptionId : "Không có ID";
    });



</script>
<script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
        intent="WELCOME"
        chat-title="SD40"
        agent-id="96c8e619-f1d8-425a-a536-0cb7cecdb3b7"
        language-code="vi"
></df-messenger>
</body>

</html>