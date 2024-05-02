<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="section dashboard">
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item active"><h1>Thêm/Chỉnh sửa kích thước cho sản phẩm</h1></li>
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

                            <label  class="form-label">ID sản phẩm: </label>
                            <input type="text" class="form-control" readonly value="${sp.id}" name="id">

                            <label  class="form-label">Mã: </label>
                            <input type="text" class="form-control" readonly value="${sp.ma}" name="ma">

                            <label  class="form-label">Tên: </label>
                            <input type="text" class="form-control" readonly value="${sp.ten}" name="ten">

                            <label  class="form-label">Thương hiệu: </label>
                            <input type="text" class="form-control" readonly value="${sp.thuongHieu.ten}" name="thuongHieu">

                            <label  class="form-label">Thể loại: </label>
                            <input type="text" class="form-control" readonly value="${sp.theLoai.ten}" name="theLoai">

                            <label  class="form-label">Mô tả: </label>
                            <input type="text" class="form-control" readonly value="${sp.moTa}" name="moTa">

                            <label  class="form-label">Màu sắc: </label>
                            <a class="btn btn-light" style="margin-left: 30px">
                                <img style="width: 20px;height: 20px" src="/assets/img/color/${ms.hinhAnh}" alt="Icon" class="icon">
                                <span class="text">${ms.ten}</span>
                            </a><br>

                            <label  class="form-label">Kích cỡ hiện có: </label>
                            <c:forEach items="${listKCDangCo}" var="kc1">
                                <a onclick="detailKC(${sp.id},${ms.id},${kc1.kichCo.id})" id="btnKichCo${kc1.kichCo.id}" class="btn btn-primary kichCo" style="margin-left: 30px">${kc1.kichCo.ten} </a>
                            </c:forEach>
                        </div>
                    </div>
                </div><!-- End Recent Sales -->
            </div>
        </div><!-- End Left side columns -->

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
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="profile-tab" data-bs-toggle="tab"
                                    data-bs-target="#profile" type="button" role="tab" aria-controls="profile"
                                    aria-selected="false">Thêm mới
                            </button>
                        </li>
                    </ul>

                    <%--update--%>
                    <div class="tab-content pt-2" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel"
                             aria-labelledby="home-tab">
                                <input  type="text" class="form-control" style="display: none" readonly id="idctsp">

                                <div>
                                    Kích cỡ hiện tại :
                                    <input type="text" class="form-control" readonly  id="kichCo">
                                </div>

                                <div>
                                    Số lượng :
                                    <input type="number" class="form-control"  id="soLuong">
                                </div>

                                <div id="trangThaiUpdate">
                                    Trạng thái : <br>
                                    <input type="radio" name="trangThai" checked value="0">
                                    Còn sử dụng <br>
                                    <input type="radio" name="trangThai" value="1">
                                    Ngừng sử dụng
                                </div>
                                <input type="submit" onclick="updateKC(${sp.id},${ms.id})" class="btn btn-primary" value="Update" style="margin-top: 10px">
                        </div>

                        <%--create--%>
                        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                <div>
                                    Kích thước muốn thêm :
                                    <select  class="form-select" aria-label="Default select example" id="kichCoAdd" >
                                        <c:forEach items="${listKCChuaCo}" var="kc">
                                            <option value="${kc.id}" >
                                                    ${kc.ten}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    Số lượng :
                                    <input type="number" id="soLuongAdd"  class="form-control"></div>

                                <div id="trangThaiAdd">
                                    Trạng thái : <br>
                                    <input type="radio" name="trangThaiAdd" checked value="0">
                                    Còn sử dụng <br>
                                    <input type="radio" name="trangThaiAdd" value="1">
                                    Ngừng sử dụng
                                </div>

                                    <input type="submit" onclick="AddKC(${sp.id},${ms.id})" class="btn btn-primary" value="Add" style="margin-top: 10px">
                        </div>
                        <%--detail--%>
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
    function detailKC(idsp, idms,idkc){

            document.getElementById('idctsp').value='';
            var allButtons = document.querySelectorAll('.kichCo');
            allButtons.forEach(function(button) {
                button.style.backgroundColor = "";
                button.style.color = "black";
            });

            // Đặt màu nền cho nút được click
            document.getElementById('btnKichCo' + idkc).style.backgroundColor = "#00575C";
            document.getElementById('btnKichCo' + idkc).style.color = "white";
            $.ajax({
                type: "GET",
                url: "/ctsp/detailKC/"+idsp+"/"+idms+"/"+idkc,
                success: function (response) {
                    $('#idctsp').val(response.id);
                    $('#kichCo').val(response.kichCo.ten);
                    $('#soLuong').val(response.soLuong);
                    if (response.trangThai == 0) {
                        $('input[name="trangThai"][value="0"]').prop('checked', true);
                    } else if (response.trangThai == 1) {
                        $('input[name="trangThai"][value="1"]').prop('checked', true);
                    }

                }});
    }

    function updateKC(idsp,idms){
        var idctsp = document.getElementById('idctsp').value;
        var soLuong = document.getElementById('soLuong').value;
        var trangThaiValue = $('input[name="trangThai"]:checked', '#trangThaiUpdate').val();
        if (idctsp.trim()===''){
            alert("Vui lòng chọn đối tượng muốn cập nhật trong danh sách")
            return;
        }else {
            if (soLuong.trim()===''){
                alert("Vui lòng nhập đầy đủ thông tin !!!")
                return;
            }else {
                var formData = new FormData();
                formData.append('idctsp', idctsp);
                formData.append('soLuong', soLuong);
                formData.append('trangThai', trangThaiValue);

                $.ajax({
                    type: "POST",
                    url: "/ctsp/updateKC",
                    data: formData,
                    contentType: false, // Không cần set contentType
                    processData: false, // Không cần xử lý dữ liệu
                    success: function (response) {
                        var cf = confirm("Bạn muốn cập nhật ???");
                        if (cf == true) {
                            alert("Cập nhật thành công");
                            window.location.href = "/ctsp/hienthithemkc/"+idsp+"/"+idms;
                        }


                    }
                });
            }
        }
    }

    function AddKC(idsp,idms){
        var soLuong = document.getElementById('soLuongAdd').value;
        var kichCo = document.getElementById('kichCoAdd').value;
        var trangThaiValue = $('input[name="trangThaiAdd"]:checked', '#trangThaiAdd').val();

            if (soLuong.trim()===''){
                alert("Vui lòng nhập đầy đủ thông tin !!!")
                return;
            }else {
                var formData = new FormData();
                formData.append('idkc', kichCo);
                formData.append('idsp', idsp);
                formData.append('idms', idms);
                formData.append('soLuong', soLuong);
                formData.append('trangThai', trangThaiValue);

                $.ajax({
                    type: "POST",
                    url: "/ctsp/addKC",
                    data: formData,
                    contentType: false, // Không cần set contentType
                    processData: false, // Không cần xử lý dữ liệu
                    success: function (response) {
                        var cf = confirm("Bạn muốn thêm mới ???");
                        if (cf == true) {
                            alert("Thêm mới thành công");
                            window.location.href = "/ctsp/hienthithemkc/"+idsp+"/"+idms;
                        }


                    }
                });
            }
    }
</script>
