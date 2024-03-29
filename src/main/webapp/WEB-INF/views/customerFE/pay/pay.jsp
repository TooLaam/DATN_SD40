<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style>
        <%@include file="style.css" %>
    </style>

</head>

<br/>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h2>Payment Information</h2>
            <form method="post" action="/bill/placeorder">
                <div class="form-group">
                    <label for="receiverName">Họ và tên:</label>
                    <input type="text" class="form-control"  id="receiverName"
                           name="nguoiNhan" required>
                </div>
                <div class="form-group">
                    <label for="receiverName">Số điện thoại</label>
                    <input type="number" class="form-control"
                           name="sdtNguoiNhan" required>
                </div>
                <div class="form-group">
                    <label for="receiverName">Thành phố</label>
                    <select class="form-select" aria-label="Default select example" name="thanhPho" id="Province">
                        <option selected>Open this select menu</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="receiverName">Quận huyện</label>
                    <select class="form-select" aria-label="Default select example" name="quanHuyen" id="District">
                        <option selected>Open this select menu</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="receiverName">Phường xã</label>
                    <select class="form-select" aria-label="Default select example" name="phuongXa" id="Wards">
                        <option selected>Open this select menu</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="receiverName">Địa chỉ cụ thể</label>
                   <input type="text" class="form-control" placeholder="ngõ 54"
                    name="diaChiNguoiNhan" required>
                </div>
                <div class="form-group">
                    <label for="receiverName">Email</label>
                    <input type="email" class="form-control" placeholder="ngõ 54"
                           name="emailNguoiNhan" required>
                </div>
                <div class="form-group">
                    <label for="receiverName">Ghi chú:</label>
                    <textarea class="form-control" name="ghiChu" id="exampleFormControlTextarea1" rows="3"></textarea>
                </div>
                <div class="form-group">
                    <label for="receiverName">Phương thức thanh toán</label>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="maPhuongThuc" value="Vnpay" id="flexRadioDefault1">
                        <label class="form-check-label" for="flexRadioDefault1">
                            Vnpay
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="maPhuongThuc"  value="Trả sau" id="flexRadioDefault2" checked>
                        <label class="form-check-label" for="flexRadioDefault2">
                            Trả sau
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="receiverName" id="phiShip1">Phí ship:</label></br>
                    <label for="receiverName" id="ngayNhan1">Ngày nhận:</label>
                    <input type="hidden" class="form-control" id="phiShip"  value=""
                           name="phiShip">
                    <input type="hidden" class="form-control"   id="ngayNhan" value=""
                           name="ngayNhan">
                </div>
                <input type="hidden" class="form-control"  value="${totalMoney + (totalMoney > 1000000 ? 0: 30)}"
                       name="tongTien"  id="totalBill1">
                <h4>Tổng đơn hàng: ${totalMoney}</h4>
                <h4 id="totalBill">Tổng hoá đơn: ${totalMoney }</h4>

                <button type="submit" class="btn">Thanh toán</button>
            </form>
        </div>
        <div class="col-md-6">
            <h2>Your shopping cart</h2>
            <table class="table table-striped">
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
</div>
<br/>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<script>
    getAllProvince()

    function getAllProvince() {

        const selectElementProvince = document.getElementById("Province");
        const options = [];
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: 'https://online-gateway.ghn.vn/shiip/public-api/master-data/province',
            dataType: 'json',
            headers: {
                "token": "d73043b1-2777-11ee-b394-8ac29577e80e",
            },
            success: function (responseData) {
                const option = document.createElement("option");
                option.value = ""
                option.text = "Open this select menu"
                options.push(option);
                responseData.data.map(item => {
                    const option = document.createElement("option");
                    option.value = item.ProvinceID + "," + item.ProvinceName;
                    option.text = item.ProvinceName
                    options.push(option);
                })
                selectElementProvince.options.length = 0;

                for (const option of options) {
                    selectElementProvince.appendChild(option);
                }
            },
            error: function (e) {
            }
        });

    }

    document.getElementById("Province").addEventListener("change", getAllDistrict);

    function getAllDistrict() {
        const selectElementDistrict = document.getElementById("District");
        var data = document.getElementById("Province").value
        const codeProvince = document.getElementById("Province").value.split(",")[0];
        const options = [];
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: 'https://online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=' + codeProvince,
            dataType: 'json',
            headers: {
                "token": "d73043b1-2777-11ee-b394-8ac29577e80e",
            },
            success: function (responseData) {
                const option = document.createElement("option");
                option.value = ""
                option.text = "Open this select menu"
                options.push(option);
                responseData.data.map(item => {
                    const option = document.createElement("option");
                    option.value = item.DistrictID + "," + item.DistrictName;
                    option.text = item.DistrictName
                    options.push(option);
                })
                selectElementDistrict.options.length = 0;

                for (const option of options) {
                    selectElementDistrict.appendChild(option);
                }
            },
            error: function (e) {
            }
        });

    }

    document.getElementById("District").addEventListener("change", getAllWards);

    function getAllWards() {
        const selectElementWards = document.getElementById("Wards");
        const codeDistrict = document.getElementById("District").value.split(",")[0];
        const options = [];
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: 'https://online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=' + codeDistrict,
            dataType: 'json',
            headers: {
                "token": "d73043b1-2777-11ee-b394-8ac29577e80e",
            },
            success: function (responseData) {
                const option = document.createElement("option");
                option.value = ""
                option.text = "Open this select menu"
                options.push(option);
                responseData.data.map(item => {
                    const option = document.createElement("option");
                    option.value = item.WardCode + "," + item.WardName;
                    option.text = item.WardName
                    options.push(option);
                })
                selectElementWards.options.length = 0;

                for (const option of options) {
                    selectElementWards.appendChild(option);
                }
            },
            error: function (e) {
            }
        });

    }

    document.getElementById("Wards").addEventListener("change", changWard);
    function changWard() {
        var wardCode = document.getElementById("Wards").value.split(",")[0];
        var district = document.getElementById("District").value.split(",")[0];
        var province = document.getElementById("Province").value.split(",")[0];


        selectAddress(district,wardCode )
    }
    function selectAddress( to_district_id, to_ward_code) {
        fetchAllDayShip(to_district_id, to_ward_code)
        fetchAllMoneyShip(to_district_id, to_ward_code)
    }

    function fetchAllDayShip(to_district_id, to_ward_code) {
        console.log(to_district_id + '    ' + to_ward_code)
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: 'https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/leadtime?from_district_id=1485&from_ward_code=1A0607&to_district_id=' + to_district_id + '&to_ward_code=' + to_ward_code + '&service_id=53320',
            dataType: 'json',
            headers: {
                "token": "d73043b1-2777-11ee-b394-8ac29577e80e",
                "shop_id": "4374133",
            },
            success: function (responseData) {
                var input1 = document.getElementById("ngayNhan");
                var lable = document.getElementById("ngayNhan1");
                lable.textContent = "Ngày nhận: " + moment(responseData.data.leadtime * 1000).format('DD/MM/YYYY');;
                input1.value = moment(responseData.data.leadtime * 1000).format('DD/MM/YYYY');
            },
            error: function (e) {
                console.log(e)
            }
        });
    }
    var moneyShip = 0;
    function fetchAllMoneyShip(to_district_id, to_ward_code) {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: 'https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee?service_type_id=2&insurance_value=&coupon&from_district_id=3440&height=15&length=15&weight=1000&width=15&to_district_id='+to_district_id+'&to_ward_code='+to_ward_code,
            dataType: 'json',
            headers: {
                "token": "d73043b1-2777-11ee-b394-8ac29577e80e",
                "shop_id": "4374133",
            },
            success: function (responseData) {
                var input1 = document.getElementById("phiShip");
                var viewtotalBill = document.getElementById("totalBill");
                var totalBill1 = document.getElementById("totalBill1");
                var lable = document.getElementById("phiShip1");
                lable.textContent = "Phí ship: "+ responseData.data.total;
                input1.value =  responseData.data.total;
                viewtotalBill.textContent = "Tổng hoá đơn: "+ (responseData.data.total + ${totalMoney});
                totalBill1.value =  responseData.data.total + ${totalMoney};
            },
            error: function (e) {
                console.log(e)
            }
        });
    }

</script>