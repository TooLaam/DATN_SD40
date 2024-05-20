
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="pagetitle">
    <h1>VOUCHER</h1>

</div>
<!-- End Page Title -->

<section class="section dashboard">
    <div class="row">
        <!-- Left side columns -->
        <div class="col-lg-8">
            <div class="row">
                <!-- Recent Sales -->
                <div class="col-12">
                    <div class="card recent-sales overflow-auto">


                        <div class="card-body">

                            <table class="table table-borderless datatable">
                                <thead>
                                <tr>

                                    <th>STT</th>
                                    <th>Mã</th>
                                    <th>Tên</th>
                                    <th>Ngày bắt đầu</th>
                                    <th>Ngày kết thúc</th>
                                    <th>Số lượng</th>
                                    <th>Phần trăm giảm</th>
                                    <th>Giảm tối đa</th>
                                    <th>Giá trị đơn tối thiểu</th>
                                    <th>Action</th>

                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listVC}" var="mau" varStatus="tt">
                                    <tr>
                                        <th scope="row">${tt.index+1}</th>
                                        <th >${mau.maVoucher}</th>
                                        <td><strong>${mau.tenVoucher}</strong></td>
                                        <td><fmt:formatDate value="${mau.ngayBatDau}" pattern="dd-MM-yyyy"/></td>
                                        <td><fmt:formatDate value="${mau.ngayKetThuc}" pattern="dd-MM-yyyy"/></td>
                                        <td>${mau.soLuong}</td>
                                        <td>${mau.phanTramGiam}</td>
                                        <td><fmt:formatNumber value="${mau.giamToiDa}" pattern="###,###"/></td>
                                        <td><fmt:formatNumber value="${mau.giaTriDonToiThieu}" pattern="###,###"/></td>

                                        <td>
                                            <a onclick="detail(${mau.id})" class="btn btn-success">Chi tiết</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>

                            </table>
                        </div>

                    </div>
                </div><!-- End Recent Sales -->
            </div>
        </div><!-- End Left side columns -->
        <div class="col-lg-4">
            <div class="card">
                <div class="card-body">
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="${active == null?"nav-link active":"nav-link"}" id="home-tab" data-bs-toggle="tab"
                                    data-bs-target="#home" type="button" role="tab" aria-controls="home"
                                    aria-selected="true">Chi tiết
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="${active == null?"nav-link":"nav-link active"}" id="profile-tab" data-bs-toggle="tab"
                                    data-bs-target="#profile" type="button" role="tab" aria-controls="profile"
                                    aria-selected="false">Thêm mới
                            </button>
                        </li>
                    </ul>
<%--                    id="formUpdate" method="post" action="/voucher/update" enctype="multipart/form-data"--%>
                    <%--update--%>
                    <div class="tab-content pt-2" id="myTabContent">
                        <div class="${active == null?"tab-pane fade show active":"tab-pane fade"}" id="home" role="tabpanel"
                             aria-labelledby="home-tab">
                                <div>
                                    <input class="form-control" style="display: none" id="idUpdate" readonly value="${vc.id}">
                                </div>
                                <div>
                                    Mã :
                                    <input class="form-control" readonly id="maUpdate" value="${vc.maVoucher}">
                                </div>

                                <div>
                                    Tên :
                                    <input class="form-control" id="tenUpdate" value="${vc.tenVoucher}">
                                </div>
                                <div>
                                    Ngày bắt đầu :
                                    <input type="date" class="form-control" id="ngayBDUpdate" value="${vc.ngayBatDau}">
                                </div>

                                <div>
                                    Ngày kết thúc :
                                    <input type="date" class="form-control" id="ngayKTUpdate" value="${vc.ngayKetThuc}">
                                </div>
                                <div>
                                    Số lượng :
                                    <input type="number" class="form-control" id="soLuongUpdate" value="${vc.soLuong}">
                                </div>

                                <div>
                                    Phần trăm giảm :
                                    <input type="number" class="form-control" id="phanTramGiamUpdate" value="${vc.phanTramGiam}">
                                </div>

                                <div>
                                    Giảm tối đa :
                                    <input type="number" class="form-control" id="giamToiDaUpdate" value="${vc.giamToiDa}">
                                </div>

                                <div>
                                    Giá trị đơn tối thiểu :
                                    <input type="number" class="form-control" id="giaTriDonToiThieuUpdate" value="${vc.giaTriDonToiThieu}">
                                </div>

                                <div id="trangThaiUpdate">
                                    Trạng thái :<br>
                                    <input type="radio" name="trangThaiUpdate" checked value="0" ${ vc.trangThai == "0" ? "checked" : "" }>
                                    Còn sử dụng <br>
                                    <input type="radio" name="trangThaiUpdate" value="1" ${vc.trangThai == "1" ? "checked" : "" }>
                                    Ngừng sử dụng
                                </div>
                            <button class="btn btn-primary" id="btnUpdate" onclick="update()" style="margin-top: 10px">Cập nhật</button>
                        </div>

                        <%--create--%>
                        <div class="${active == null?"tab-pane fade":"tab-pane fade show active"}" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                <div>
                                    Tên :
                                    <input class="form-control" required id="tenAdd">
                                </div>

                                <div>
                                    Ngày bắt đầu :
                                    <input type="date" class="form-control" id="ngayBDAdd" required>
                                </div>

                                <div>
                                    Ngày kết thúc :
                                    <input type="date" class="form-control" id="ngayKTAdd" required>
                                </div>

                                <div>
                                    Số lượng :
                                    <input type="number" class="form-control" id="soLuongAdd" required>
                                </div>

                                <div>
                                    Phần trăm giảm :
                                    <input type="number" class="form-control" id="phanTramGiamAdd" required>
                                </div>

                                <div>
                                    Giảm tối đa :
                                    <input type="number" class="form-control" required id="giamToiDaAdd">
                                </div>

                                <div>
                                    Giá trị đơn tối thiểu :
                                    <input type="number" class="form-control" required id="giaTriToiThieuAdd">
                                </div>

                                <div id="trangThaiAdd">
                                    Trạng thái :<br>
                                    <input type="radio" name="trangThaiAdd" checked value="0">
                                    Còn sử dụng <br>
                                    <input type="radio" name="trangThaiAdd" value="1">
                                    Ngừng sử dụng
                                </div>


                                <button class="btn btn-primary" onclick="add()" id="btnAdd" style="margin-top: 10px">Thêm</button>
                            </form>
                        </div>
                        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                        </div>
                    </div><!-- End Default Tabs -->


                </div>

            </div>
        </div><!-- End Recent Activity -->


    </div><!-- End Right side columns -->

    </div>
</section>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>



    function detail(id){
        $.ajax({
            type: "GET",
            url: "/voucher/detail/"+id,
            success: function (response) {
                $('#tenUpdate').val(response.tenVoucher);
                $('#idUpdate').val(response.id);
                $('#maUpdate').val(response.maVoucher);
                $('#ngayBDUpdate').val(response.ngayBatDau);
                $('#ngayKTUpdate').val(response.ngayKetThuc);
                $('#soLuongUpdate').val(response.soLuong);
                $('#phanTramGiamUpdate').val(response.phanTramGiam);
                $('#giamToiDaUpdate').val(response.giamToiDa);
                $('#giaTriDonToiThieuUpdate').val(response.giaTriDonToiThieu);
                // Gán giá trị cho input radio trạng thái
                if (response.trangThai == 0) {
                    $('input[name="trangThaiUpdate"][value="0"]').prop('checked', true);
                } else if (response.trangThai == 1) {
                    $('input[name="trangThaiUpdate"][value="1"]').prop('checked', true);
                }

            }});
    }

    function update() {

        var ngayBD = document.getElementById('ngayBDUpdate').value;
        var ngayKT = document.getElementById('ngayKTUpdate').value;
        var trangThaiValue = $('input[name="trangThaiUpdate"]:checked', '#trangThaiUpdate').val();
        var id = document.getElementById('idUpdate').value;
        var ten = document.getElementById('tenUpdate').value;
        var soLuong = document.getElementById('soLuongUpdate').value;
        var phanTramGiam = document.getElementById('phanTramGiamUpdate').value;
        var giamToiDa = document.getElementById('giamToiDaUpdate').value;
        var giaTriDonToiThieu = document.getElementById('giaTriDonToiThieuUpdate').value;
        var ngayBatDau = new Date(ngayBD);
        var ngayKetThuc = new Date(ngayKT);
        if (id.trim() ===''){
            alert("Vui lòng chọn đối tượng ở danh sách")
            return;
        }else{
            if (ten.trim() ===''||soLuong.trim() ===''||phanTramGiam.trim() ===''||giamToiDa.trim() ===''||giaTriDonToiThieu.trim() ===''||ngayBD.trim() ===''||ngayKT.trim() ===''){
                alert("Vui lòng nhập đầy đủ thông tin")
                return;
            }else {
                if (ngayBatDau>ngayKetThuc){
                    alert("Ngày bắt đầu không được lớn hơn ngày kết thúc")
                    return;
                }
                if(parseInt(soLuong)<0||parseInt(soLuong)==0){
                    alert("Số lượng phải lớn hơn 0 !!!")
                    return;
                }
                if(parseInt(phanTramGiam)<0||parseInt(phanTramGiam)==0||parseInt(phanTramGiam)>100){
                    alert("Phần trăm giảm phải lớn hơn 0 và nhỏ hơn 100 !!!")
                    return;
                }
                if(parseInt(giamToiDa)<0){
                    alert("Giá trị giảm tối đa phải lớn hơn hoặc bằng 0 !!!")
                    return;
                }
                if(parseInt(giaTriDonToiThieu)<0){
                    alert("Giá trị đơn hàng tối thiểu phải lớn hơn hoặc bằng 0 !!!")
                    return;
                }
                else {
                        $.ajax({
                            type: "POST",
                            url: "/voucher/update",
                            data: {
                                id: id,
                                ten: ten,
                                soLuong: soLuong,
                                ngayBatDau: ngayBD,
                                ngayKetThuc: ngayKT,
                                phanTramGiam: phanTramGiam,
                                giamToiDa: giamToiDa,
                                giaTriDonToiThieu: giaTriDonToiThieu,
                                trangThai: trangThaiValue
                            },
                            success: function (response) {
                                if (response === "errTrungTen") {
                                    alert("Tên trùng trong danh sách. Vui lòng chọn tên khác !!!")
                                    return;
                                } else {
                                    var cf = confirm("Bạn muốn cập nhập ???")
                                    if (cf == true) {
                                        alert("Cập nhật thành công")
                                        window.location.href = "/voucher/index";
                                    }
                                }

                            }
                        });
                     }
            }
        }
       };

    function add() {

        var ngayBD = document.getElementById('ngayBDAdd').value;
        var ngayKT = document.getElementById('ngayKTAdd').value;
        var trangThaiValue = $('input[name="trangThaiAdd"]:checked', '#trangThaiAdd').val();
        var ten = document.getElementById('tenAdd').value;
        var soLuong = document.getElementById('soLuongAdd').value;
        var phanTramGiam = document.getElementById('phanTramGiamAdd').value;
        var giamToiDa = document.getElementById('giamToiDaAdd').value;
        var giaTriDonToiThieu = document.getElementById('giaTriToiThieuAdd').value;
        var ngayBatDau = new Date(ngayBD);
        var ngayKetThuc = new Date(ngayKT);
            if (ten.trim() ===''||soLuong.trim() ===''||phanTramGiam.trim() ===''||giamToiDa.trim() ===''||giaTriDonToiThieu.trim() ===''||ngayBD.trim() ===''||ngayKT.trim() ===''){
                alert("Vui lòng nhập đầy đủ thông tin")
                return;
            }else {
                if (ngayBatDau>ngayKetThuc){
                    alert("Ngày bắt đầu không được lớn hơn ngày kết thúc")
                    return;
                }
                if(parseInt(soLuong)<0||parseInt(soLuong)==0){
                    alert("Số lượng phải lớn hơn 0 !!!")
                    return;
                }
                if(parseInt(phanTramGiam)<0||parseInt(phanTramGiam)==0||parseInt(phanTramGiam)>100){
                    alert("Phần trăm giảm phải lớn hơn 0 và nhỏ hơn 100 !!!")
                    return;
                }
                if(parseInt(giamToiDa)<0){
                    alert("Giá trị giảm tối đa phải lớn hơn hoặc bằng 0 !!!")
                    return;
                }
                if(parseInt(giaTriDonToiThieu)<0){
                    alert("Giá trị đơn hàng tối thiểu phải lớn hơn hoặc bằng 0 !!!")
                    return;
                }
                else {
                    var cf = confirm("Bạn muốn thêm mới ???")
                    if (cf == true) {
                    $.ajax({
                        type: "POST",
                        url: "/voucher/add",
                        data: {
                            ten: ten,
                            soLuong: soLuong,
                            ngayBatDau: ngayBD,
                            ngayKetThuc: ngayKT,
                            phanTramGiam: phanTramGiam,
                            giamToiDa: giamToiDa,
                            giaTriDonToiThieu: giaTriDonToiThieu,
                            trangThai: trangThaiValue
                        },
                        success: function (response) {
                            if (response === "errTrungTen") {
                                alert("Tên trùng trong danh sách. Vui lòng chọn tên khác !!!")
                                return;
                            } else {

                                    alert("Thêm thành công")
                                    window.location.href = "/voucher/index";
                                }
                            }
                    });
                }
            }}
    };
</script>