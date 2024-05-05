
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <style>
        .bodyPlus {
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }

        .circle-btn {
            width: 40px;
            height: 40px;
            background-color: #007bff;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            color: white;
            font-size: 18px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .circle-btn:hover {
            background-color: #0056b3;
        }

        .plus {
            position: relative;
            z-index: 1;
            font-weight: bold;
            font-size: 24px; /* Kích thước lớn hơn */
            color: #ffffff; /* Màu trắng đậm */
        }
        .form-container {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #f9f9f9;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            z-index: 9999;
            max-width: 400px;
            width: 100%;
            display: none;
        }

        .form-title {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input[type="email"] {
            width: calc(100% - 10px); /* Adjust for padding */
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box; /* Ensure padding is included in width */
        }

        .form-group input[type="submit"] {
            width: 100%;
            background-color:  #00575C;
            color: white;
            border: none;
            padding: 15px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        .close-btn {
            position: absolute;
            top: 10px;
            right: 10px;
            cursor: pointer;
            font-size: 20px;
        }
        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5); /* Màu đen mờ với độ trong suốt 50% */
            z-index: 9998; /* Lớp dưới form-container */
            display: none; /* Mặc định ẩn */
        }
    </style>
</head>

<section class="section dashboard">
    <div class="row">

        <div class="card">
            <div class="card-body">

                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item active"><h1>Thêm sản phẩm</h1></li>
                        <li style="margin-top: 20px" class="breadcrumb-item"><a href="/sanpham/index">Home</a></li>

                    </ol>
                </nav>
                <div class="row" style="margin-top: 50px;">

                    <div class="product-details">
                        <div class="col-md-7">
                                <div class="product-info">
                                    <label  class="form-label">Tên sản phẩm: </label>
                                    <input  type="text" class="form-control" id="ten">

                                    <label  class="form-label">Mô tả: </label>
                                    <input  type="text" class="form-control"  id="moTa">

                                    <p>Thương hiệu:  </p>
                                    <div style="display: flex">
                                   <span id="thuongHieuNew">
                                    <select  class="form-select" aria-label="Default select example"id="thuongHieu" style="width: 615px;margin-right: 10px" >
                                        <c:forEach items="${listTH}" var="ms">
                                            <option value="${ms.id}" >
                                                ${ms.ten}
                                            </option>
                                        </c:forEach>
                                    </select>
                                   </span>
                                    <span class="bodyPlus">
                                        <button class="circle-btn" onclick="showNewFormThuongHieu()">
                                            <div  class="plus">+</div>
                                        </button>
                                    </span>
                                    </div>


                                    <p>Thể loại: </p>

                                    <div style="display: flex">
                                   <span>
                                    <select  class="form-select" aria-label="Default select example" id="theLoai"  style="width: 615px;margin-right: 10px"  >
                                        <c:forEach items="${listTL}" var="ms">
                                            <option value="${ms.id}" >
                                                    ${ms.ten}
                                            </option>
                                        </c:forEach>
                                    </select>
                                   </span>
                                        <span class="bodyPlus">
                                        <button onclick="showNewFormTheLoai()" class="circle-btn">
                                            <div class="plus">+</div>
                                        </button>
                                    </span>
                                    </div>


                                    <p>Giảm giá sản phẩm: </p>

                                    <div style="display: flex">
                                   <span>
                                   <select  class="form-select" aria-label="Default select example"id="giamGia"
                                            style="width: 615px;margin-right: 10px" >
                                        <c:forEach items="${listGG}" var="ms">
                                            <option value="${ms.id}" >
                                                    ${ms.ten}
                                            </option>
                                        </c:forEach>
                                    </select>
                                   </span>
                                        <span class="bodyPlus">
                                        <button onclick="showNewFormGiamGia()" class="circle-btn">
                                            <div class="plus">+</div>
                                        </button>
                                    </span>
                                    </div>


                                    <div>
                                        Hình ảnh đại diện :
                                        <input  type="file" id="image" accept="image/*" class="form-control"
                                        >
                                    </div>
                                    <button type="submit" onclick="AddSanPham()" class="btn btn-primary">
                                    <span>Tiếp theo</span>
                                    </button>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="overlay" id="overlay"></div>
    <div class="form-container" id="new-formThuongHieu">
        <!-- Nút đóng -->
        <span class="close-btn" onclick="closeNewFormThuongHieu()">X</span>
        <!-- Các trường và nút của form mới -->
        <h2 class="form-title">Thêm Thương hiệu</h2>
        <div>
            Tên :
            <input required class="form-control" id="tenAdd">
        </div><br>
        <div>
            Hình ảnh :
            <input  type="file" id="imageAdd" accept="image/*" class="form-control">
        </div><br>
        <div class="form-group">
            <input type="submit" onclick="addThuongHieu()" value="Thêm">
        </div>
    </div>

    <div class="form-container" id="new-formTheLoai">
        <!-- Nút đóng -->
        <span class="close-btn" onclick="closeNewFormTheLoai()">X</span>
        <!-- Các trường và nút của form mới -->
        <h2 class="form-title">Thêm Thể loại</h2>
        <div>
            Tên :
            <input required class="form-control" id="tenAddTheLoai">
        </div><br>
        <div class="form-group">
            <input type="submit" onclick="addTheLoai()" value="Thêm">
        </div>
    </div>

    <div class="form-container" id="new-formGiamGia">
        <!-- Nút đóng -->
        <span class="close-btn" onclick="closeNewFormGiaGia()">X</span>
        <!-- Các trường và nút của form mới -->
        <h2 class="form-title">Thêm Giảm giá</h2>
        <div>
            Tên :
            <input  class="form-control" id="tenAddGiamGia">
        </div><br>
        <div>
            Mức giảm :
            <input type="number"  class="form-control" id="mucGiamAdd">
        </div><br>
        <div class="form-group">
            <input type="submit" onclick="addGiamGia()" value="Thêm">
        </div>
    </div>
</section>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>

    function showNewFormThuongHieu() {
        document.getElementById("new-formThuongHieu").style.display = "block";
        document.getElementById("overlay").style.display = "block";
    }

    function closeNewFormThuongHieu() {
        document.getElementById("new-formThuongHieu").style.display = "none";
        document.getElementById("overlay").style.display = "none";
    }

    function showNewFormTheLoai() {
        document.getElementById("new-formTheLoai").style.display = "block";
        document.getElementById("overlay").style.display = "block";
    }

    function closeNewFormTheLoai() {
        document.getElementById("new-formTheLoai").style.display = "none";
        document.getElementById("overlay").style.display = "none";
    }

    function showNewFormGiamGia() {
        document.getElementById("new-formGiamGia").style.display = "block";
        document.getElementById("overlay").style.display = "block";
    }

    function closeNewFormGiaGia() {
        document.getElementById("new-formGiamGia").style.display = "none";
        document.getElementById("overlay").style.display = "none";
    }

    function AddSanPham(){
        var ten = document.getElementById('ten').value;
        var moTa = document.getElementById('moTa').value;
        var thuongHieu = document.getElementById('thuongHieu').value;
        var theLoai = document.getElementById('theLoai').value;
        var giamGia = document.getElementById('giamGia').value;
        var image = document.getElementById('image').files[0];

        if (ten.trim() === ''||moTa.trim() === ''|| image == null){
            alert("Vui lòng nhập đầy đủ thông tin !!!")
            return;
        }else {
            var cf = confirm("Bạn muốn thêm dữ liệu???");
            if (cf == true) {
            var formData = new FormData();
            formData.append('ten', ten);
            formData.append('moTa', moTa);
            formData.append('image', image);
            formData.append('theLoai', theLoai);
            formData.append('thuongHieu', thuongHieu);
            formData.append('giamGia', giamGia);

            $.ajax({
                type: "POST",
                url: "/ctsp/addSP",
                data: formData,
                contentType: false, // Không cần set contentType
                processData: false, // Không cần xử lý dữ liệu
                success: function (response) {
                        window.location.href = "/ctsp/hienThiChinhSuaMauSac/"+response.id;
                }
            });
        }}

    }

    function addThuongHieu() {
        var trangThaiValue = '0';
        var ten = document.getElementById('tenAdd').value;
        var hinhAnh = document.getElementById('imageAdd').files[0];
        if (ten.trim() ===''||hinhAnh ==null){
            alert("Vui lòng nhập đầy đủ thông tin")
            return;
        }else {
            var cf = confirm("Bạn muốn thêm mới ???");
            if (cf) {
            var formData = new FormData();
            formData.append('ten', ten);
            formData.append('image', hinhAnh);
            formData.append('trangThai', trangThaiValue);
            $.ajax({
                type: "POST",
                url: "/thuonghieu/add",
                data: formData,
                contentType: false, // Không cần set contentType
                processData: false, // Không cần xử lý dữ liệu
                success: function (response) {
                    if (response === "errTrungTen") {
                        alert("Tên trùng trong danh sách. Vui lòng chọn tên khác !!!");
                        return;
                    } else {
                            alert("Thêm mới thành công");
                            $.ajax({
                                type: "GET",
                                url: "/thuonghieu/listTHNew",
                                success: function (response) {
                                    var selectElement = document.getElementById("thuongHieu");
                                    // Xóa tất cả các option hiện có
                                    while (selectElement.firstChild) {
                                        selectElement.removeChild(selectElement.firstChild);
                                    }
                                    // Thêm các option mới từ response
                                    response.forEach(function (item) {
                                        var option = document.createElement("option");
                                        option.value = item.id;
                                        option.text = item.ten;
                                        selectElement.appendChild(option);
                                    });
                                },
                                error: function (xhr, status, error) {
                                    alert("Đã xảy ra lỗi: " + error);
                                }
                            });
                            closeNewFormThuongHieu();


                    }

                }
            });
        }}
    };

    function addTheLoai() {
        var trangThaiValue = '0';
        var ten = document.getElementById('tenAddTheLoai').value;
        if (ten.trim() ===''){
            alert("Vui lòng nhập đầy đủ thông tin")
            return;
        }else {
            var cf = confirm("Bạn muốn thêm mới ???");
            if (cf) {
            var formData = new FormData();
            formData.append('ten', ten);
            formData.append('trangThai', trangThaiValue);
            $.ajax({
                type: "POST",
                url: "/theloai/add",
                data: formData,
                contentType: false, // Không cần set contentType
                processData: false, // Không cần xử lý dữ liệu
                success: function (response) {
                    if (response === "errTrungTen") {
                        alert("Tên trùng trong danh sách. Vui lòng chọn tên khác !!!");
                        return;
                    } else {

                            alert("Thêm mới thành công");
                            $.ajax({
                                type: "GET",
                                url: "/theloai/listTLNew",
                                success: function (response) {
                                    var selectElement = document.getElementById("theLoai");
                                    // Xóa tất cả các option hiện có
                                    while (selectElement.firstChild) {
                                        selectElement.removeChild(selectElement.firstChild);
                                    }
                                    // Thêm các option mới từ response
                                    response.forEach(function (item) {
                                        var option = document.createElement("option");
                                        option.value = item.id;
                                        option.text = item.ten;
                                        selectElement.appendChild(option);
                                    });
                                },
                                error: function (xhr, status, error) {
                                    alert("Đã xảy ra lỗi: " + error);
                                }
                            });
                            closeNewFormTheLoai();

                        }
                    }


            });
        }}
    };

    function addGiamGia() {
        var trangThaiValue = '0';
        var ten = document.getElementById('tenAddGiamGia').value;
        var mucGiam = document.getElementById('mucGiamAdd').value;
        if (ten.trim() ===''|| mucGiam.trim()===''){
            alert("Vui lòng nhập đầy đủ thông tin")
            return;
        }else {
            var cf = confirm("Bạn muốn thêm mới ???");
            if (cf) {
            var formData = new FormData();
            formData.append('ten', ten);
            formData.append('mucGiam', mucGiam);
            formData.append('trangThai', trangThaiValue);
            $.ajax({
                type: "POST",
                url: "/giamgia/add",
                data: formData,
                contentType: false, // Không cần set contentType
                processData: false, // Không cần xử lý dữ liệu
                success: function (response) {
                    if (response === "errTrungTen") {
                        alert("Tên trùng trong danh sách. Vui lòng chọn tên khác !!!");
                        return;
                    } else {

                            alert("Thêm mới thành công");
                            $.ajax({
                                type: "GET",
                                url: "/giamgia/listGGNew",
                                success: function (response) {
                                    var selectElement = document.getElementById("giamGia");
                                    // Xóa tất cả các option hiện có
                                    while (selectElement.firstChild) {
                                        selectElement.removeChild(selectElement.firstChild);
                                    }
                                    // Thêm các option mới từ response
                                    response.forEach(function (item) {
                                        var option = document.createElement("option");
                                        option.value = item.Id;
                                        option.text = item.ten;
                                        selectElement.appendChild(option);
                                    });
                                },
                                error: function (xhr, status, error) {
                                    alert("Đã xảy ra lỗi: " + error);
                                }
                            });
                            closeNewFormGiaGia();


                    }

                }
            });
        }}
    };
</script>





