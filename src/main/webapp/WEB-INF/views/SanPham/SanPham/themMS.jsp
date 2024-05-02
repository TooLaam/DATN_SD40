
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="section dashboard">
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item active"><h1>Thêm màu sắc cho sản phẩm</h1></li>
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
                                    <select  class="form-select" aria-label="Default select example"name="mauSac" id="mauSacAdd" >
                                        <c:forEach items="${listMSChuaCo}" var="ms">
                                            <option value="${ms.id}" >
                                                <img style="width: 20px;height: 20px" src="/assets/img/color/${ms.hinhAnh}" alt="Icon" class="icon">
                                                <span class="text">${ms.ten}</span>
                                            </option>
                                        </c:forEach>
                                    </select>

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
            if (giaHienHanh.trim()===''||hinhAnh == null){
                alert("Vui lòng nhập đầy đủ thông tin !!!")
                return;
            }else {
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
                            var cf = confirm("Bạn muốn cập nhật ???");
                            if (cf == true) {
                                alert("Cập nhật thành công");
                                window.location.href = "/ctsp/hienThiChinhSuaMauSac/"+idsp;
                            }


                    }
                });
            }
        }

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
                    var cf = confirm("Bạn muốn thêm dữ liệu???");
                    if (cf == true) {
                        alert("Thêm thành công");
                        window.location.href = "/ctsp/hienthithemkc/"+idsp+"/"+idms;
                    }


                }
            });
        }

    }
</script>












