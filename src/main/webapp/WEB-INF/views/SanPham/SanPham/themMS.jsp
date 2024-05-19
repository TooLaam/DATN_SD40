
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
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item active"><h1>Thêm/Chỉnh sửa màu sắc cho sản phẩm</h1></li>
            <li style="margin-top: 20px" class="breadcrumb-item"><a href="/sanpham/index">Home</a></li>
            <li style="margin-top: 20px" class="breadcrumb-item"><a href="/ctsp/hienthi/${sp.id}">Chi tiết sản phẩm</a></li>
        </ol>
    </nav>
    <div class="row">
        <!-- Left side columns -->
        <div class="col-lg-8">
            <div class="row">
                <!-- Recent Sales -->
                <div class="col-12">
                    <div class="card recent-sales overflow-auto">
                        <div class="card-body">
                                <div class="product-info">
                                    <label  class="form-label">ID sản phẩm</label>
                                    <input type="text" class="form-control" readonly value="${sp.id}" id="idsp">

                                    <label  class="form-label">Mã</label>
                                    <input type="text" class="form-control" readonly value="${sp.ma}" name="ma">

                                    <label  class="form-label">Tên</label>
                                    <input type="text" class="form-control" readonly value="${sp.ten}" name="ten">

                                    <label  class="form-label">Thương hiệu</label>
                                    <input type="text" class="form-control" readonly value="${sp.thuongHieu.ten}" name="thuongHieu">

                                    <label  class="form-label">Thể loại</label>
                                    <input type="text" class="form-control" readonly value="${sp.theLoai.ten}" name="theLoai">

                                    <label  class="form-label">Mô tả</label>
                                    <input type="text" class="form-control" readonly value="${sp.moTa}" name="moTa">

                                    <p>Màu sắc đang có: </p>
                                    <c:forEach items="${listMSDangCo}" var="ms">
                                        <a onclick="detailMS(${sp.id},${ms[0]})"  class="btn btn-light mauSac" id="btnMauSac${ms[0]}" style="margin-left: 30px">
                                            <img style="width: 20px;height: 20px" src="/assets/img/color/${ms[2]}" alt="Icon" class="icon">
                                            <span class="text">${ms[1]}</span>
                                        </a>

                                    </c:forEach>
                                    <p>Màu sắc muốn thêm: </p>
                                    <div style="display: flex">
                                    <span>
                                    <select  class="form-select" aria-label="Default select example"name="mauSac" id="mauSacAdd" style="width: 630px;margin-right: 10px"  >
                                        <c:forEach items="${listMSChuaCo}" var="ms">
                                            <option value="${ms.id}" >
                                                <span class="text">${ms.ten}</span>
                                            </option>
                                        </c:forEach>
                                    </select>
                                    </span>
                                    <span class="bodyPlus">
                                        <button onclick="showNewFormMauSac()" class="circle-btn">
                                            <div class="plus">+</div>
                                        </button>
                                    </span>
                                </div>

                                    <label  class="form-label">Giá hiện hành</label>
                                    <input type="number" class="form-control"  id="giaHienHanhAdd">


                                    <div>
                                        Hình ảnh :
                                        <input  type="file" name="image" accept="image/*" id="imageAdd" class="form-control"
                                        >
                                    </div>
                                    <button type="submit" onclick="AddMauSac()" class="btn btn-primary">Thêm</button>

                                </div>
                        </div>

                    </div>


                </div><!-- End Recent Sales -->

            </div>

        </div><!-- End Left side columns -->

        <!-- Right side columns -->
        <div class="col-lg-4">

            <!-- Recent Activity -->
            <div class="card">

                <div class="card-body">
                    <!-- Default Tabs -->
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active" id="home-tab" data-bs-toggle="tab"
                                    data-bs-target="#home" type="button" role="tab" aria-controls="home"
                                    aria-selected="true">Chi tiết
                            </button>
                        </li>
                    </ul>

                    <%--update--%>
                    <div class="tab-content pt-2" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel"
                             aria-labelledby="home-tab">
                                <input  type="text" class="form-control" style="display: none" readonly  id="idms">

                                <label  class="form-label">Màu sắc muốn cập nhật</label>
                                <input  type="text" class="form-control" readonly id="ten" >

                                <label  class="form-label">Giá hiện hành</label>
                                <input type="number" class="form-control" id="giaHienHanh" >

                                <div id="trangThaiUpdate">
                                    <label  class="form-label">Trạng thái</label><br>
                                    <input type="radio" name="trangThai" value="0">
                                    Còn sử dụng <br>
                                    <input type="radio" name="trangThai" value="1" >
                                    Ngừng sử dụng
                                </div>

                            <div>
                                Hình ảnh :
                                <img id="imageUpdate1" src="" height="100px" width="100px">
                                <input type="file" name="image" accept="image/*" class="form-control"
                                       id="imageUpdate">
                            </div>

                                <button class="btn btn-primary" onclick="capNhatMS(${sp.id})">Cập nhật</button>
                        </div>

                        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                        </div>
                    </div><!-- End Default Tabs -->


                </div>


            </div><!-- End Recent Activity -->
        </div>

    </div><!-- End Right side columns -->

    <div class="overlay" id="overlay"></div>
    <div class="form-container" id="new-formMauSac">
        <!-- Nút đóng -->
        <span class="close-btn" onclick="closeNewFormMauSac()">X</span>
        <!-- Các trường và nút của form mới -->
        <h2 class="form-title">Thêm Màu sắc</h2>
        <div>
            Tên :
            <input required class="form-control" id="tenAdd">
        </div><br>
        <div>
            Hình ảnh :
            <input  type="file" id="imageAddMauSac" accept="image/*" class="form-control">
        </div><br>
        <div class="form-group">
            <input type="submit" onclick="addms()" value="Thêm">
        </div>
    </div>
</section>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function detailMS(sp,ms){
        document.getElementById('idms').value='';
        var allButtons = document.querySelectorAll('.mauSac');
        allButtons.forEach(function(button) {
            button.style.backgroundColor = "";
            button.style.color = "black";
        });

        // Đặt màu nền cho nút được click
        document.getElementById('btnMauSac' + ms).style.backgroundColor = "#00575C";
        document.getElementById('btnMauSac' + ms).style.color = "white";
        $.ajax({
            type: "GET",
            url: "/ctsp/detailMS/"+sp+"/"+ms,
            success: function (response) {
                $('#ten').val(response.mau_sac.ten);
                $('#idms').val(response.mau_sac.id);
                $('#giaHienHanh').val(response.giaHienHanh);
                $('#imageUpdate1').attr('src', '/assets/img/product/' + response.hinhAnh);
                if (response.trangThai == 0) {
                    $('input[name="trangThai"][value="0"]').prop('checked', true);
                } else if (response.trangThai == 1) {
                    $('input[name="trangThai"][value="1"]').prop('checked', true);
                }

            }});
    }

    function capNhatMS(idsp){
        var idms = document.getElementById('idms').value;
        var giaHienHanh = document.getElementById('giaHienHanh').value;
        var trangThaiValue = $('input[name="trangThai"]:checked', '#trangThaiUpdate').val();
        var hinhAnh = document.getElementById('imageUpdate').files[0];
        if (idms.trim() ===''){
            alert("Vui lòng chọn màu sắc muốn chỉnh sửa ở bên danh sách !!!")
            return;
        }else {
            if (giaHienHanh.trim() === '' || hinhAnh == null) {
                alert("Vui lòng nhập đầy đủ thông tin !!!")
                return;
            } else {
                if (parseFloat(giaHienHanh) < 0) {
                    alert("Giá hiện hành phải lớn hơn hoặc bằng 0 !!!")
                    return;
                } else {
                    var cf = confirm("Bạn muốn cập nhật ???");
                    if (cf == true) {
                    var formData = new FormData();
                    formData.append('idms', idms);
                    formData.append('giaHienHanh', giaHienHanh);
                    formData.append('image', hinhAnh);
                    formData.append('trangThai', trangThaiValue);
                    formData.append('idsp', idsp);

                    $.ajax({
                        type: "POST",
                        url: "/ctsp/updateMS",
                        data: formData,
                        contentType: false, // Không cần set contentType
                        processData: false, // Không cần xử lý dữ liệu
                        success: function (response) {
                                alert("Cập nhật thành công");
                                window.location.href = "/ctsp/hienThiChinhSuaMauSac/" + idsp;
                            }
                    });
                }
            }
        }}
    }

    function AddMauSac(){
        var idsp = document.getElementById('idsp').value;
        var idms = document.getElementById('mauSacAdd').value;
        var giaHienHanh = document.getElementById('giaHienHanhAdd').value;
        var image = document.getElementById('imageAdd').files[0];

        if (giaHienHanh.trim() === ''|| image == null){
            alert("Vui lòng nhập đầy đủ thông tin !!!")
            return;
        }else {
            if (parseFloat(giaHienHanh) < 0) {
                alert("Giá hiện hành phải lớn hơn hoặc bằng 0 !!!")
                return;
            } else {
                var cf = confirm("Bạn muốn thêm dữ liệu???");
                if (cf == true) {
                var formData = new FormData();
                formData.append('idms', idms);
                formData.append('giaHienHanh', giaHienHanh);
                formData.append('image', image);
                formData.append('idsp', idsp);

                $.ajax({
                    type: "POST",
                    url: "/ctsp/addMS",
                    data: formData,
                    contentType: false, // Không cần set contentType
                    processData: false, // Không cần xử lý dữ liệu
                    success: function (response) {
                            alert("Thêm thành công");
                            window.location.href = "/ctsp/hienthithemkc/" + idsp + "/" + idms;
                    }
                });
            }
        }}
    }
    function showNewFormMauSac() {
        document.getElementById("new-formMauSac").style.display = "block";
        document.getElementById("overlay").style.display = "block";
    }

    function closeNewFormMauSac() {
        document.getElementById("new-formMauSac").style.display = "none";
        document.getElementById("overlay").style.display = "none";
    }

    function addms() {
        var trangThaiValue = '0';
        var ten = document.getElementById('tenAdd').value;
        var hinhAnh = document.getElementById('imageAddMauSac').files[0];
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
                    url: "/mausac/add",
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
                                url: "/mausac/listMSNew",
                                success: function (response) {
                                    var selectElement = document.getElementById("mauSacAdd");
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
                            closeNewFormMauSac();


                        }

                    }
                });
            }}
    };
</script>












