
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
                                            <a href="/voucher/detail/${mau.id}" class="btn btn-success">Chi tiết</a>
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

                    <%--update--%>
                    <div class="tab-content pt-2" id="myTabContent">
                        <div class="${active == null?"tab-pane fade show active":"tab-pane fade"}" id="home" role="tabpanel"
                             aria-labelledby="home-tab">
                            <form id="formUpdate" method="post" action="/voucher/update" enctype="multipart/form-data">

                                <div>
                                    <input class="form-control" style="display: none" readonly name="id" value="${vc.id}">
                                </div>
                                <div>
                                    Mã :
                                    <input class="form-control" readonly name="ma" value="${vc.maVoucher}">
                                </div>

                                <div>
                                    Tên :
                                    <input class="form-control" required name="ten" value="${vc.tenVoucher}">
                                </div>
                                <c:if test="${errUpdate != null}" >
                                    <p style="color: red">${errUpdate}</p>
                                </c:if>
                                <div>
                                    Ngày bắt đầu :
                                    <input type="date" class="form-control" id="ngayBDUpdate" required name="ngayBatDau">
                                </div>

                                <div>
                                    Ngày sửa gần nhất :
                                    <input type="date" class="form-control" id="ngayKTUpdate" required name="ngayKetThuc">
                                </div>
                                <div>
                                    Số lượng :
                                    <input type="number" class="form-control" required name="soLuong" value="${vc.soLuong}">
                                </div>

                                <div>
                                    Phần trăm giảm :
                                    <input type="number" class="form-control" required name="phanTramGiam" value="${vc.phanTramGiam}">
                                </div>

                                <div>
                                    Giảm tối đa :
                                    <input type="number" class="form-control" required name="giamToiDa" value="<fmt:formatNumber value="${vc.giamToiDa}" pattern="###,###"/>">
                                </div>

                                <div>
                                    Giá trị đơn tối thiểu :
                                    <input type="number" class="form-control" required name="giaTriDonToiThieu" value="<fmt:formatNumber value="${vc.giaTriDonToiThieu}" pattern="###,###"/>">
                                </div>

                                <div>
                                    Trạng thái :<br>
                                    <input type="radio" name="trangThai" checked value="0" ${ vc.trangThai == "0" ? "checked" : "" }>
                                    Còn sử dụng <br>
                                    <input type="radio" name="trangThai" value="1" ${vc.trangThai == "1" ? "checked" : "" }>
                                    Ngừng sử dụng
                                </div>
                                <c:if test="${err != null}" >
                                    <p style="color: red">${err}</p>
                                </c:if>
                                <c:if test="${errNgay != null}" >
                                    <p style="color: red">${errNgay}</p>
                                </c:if>
                                <button class="btn btn-primary" id="btnUpdate" onclick="ktra(event)" style="margin-top: 10px">Cập nhật</button>
                            </form>
                        </div>

                        <%--create--%>
                        <div class="${active == null?"tab-pane fade":"tab-pane fade show active"}" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <form id="formAdd" method="post" action="/voucher/add" enctype="multipart/form-data">
                                <div>
                                    Tên :
                                    <input class="form-control" required name="ten">
                                </div>

                                <div>
                                    Ngày bắt đầu :
                                    <input type="date" class="form-control" id="ngayBDAdd" required name="ngayBatDau">
                                </div>

                                <div>
                                    Ngày sửa gần nhất :
                                    <input type="date" class="form-control" id="ngayKTAdd" required name="ngayKetThuc">
                                </div>

                                <div>
                                    Số lượng :
                                    <input type="number" class="form-control" required name="soLuong">
                                </div>

                                <div>
                                    Phần trăm giảm :
                                    <input type="number" class="form-control" required name="phanTramGiam">
                                </div>

                                <div>
                                    Giảm tối đa :
                                    <input type="number" class="form-control" required name="giamToiDa">
                                </div>

                                <div>
                                    Giá trị đơn tối thiểu :
                                    <input type="number" class="form-control" required name="giaTriDonToiThieu">
                                </div>

                                <div>
                                    Trạng thái :<br>
                                    <input type="radio" name="trangThai" checked value="0">
                                    Còn sử dụng <br>
                                    <input type="radio" name="trangThai" value="1">
                                    Ngừng sử dụng
                                </div>
                                <c:if test="${errName != null}" >
                                    <p style="color: red">${errName}</p>
                                </c:if>
                                <c:if test="${errNgay != null}" >
                                    <p style="color: red">${errNgay}</p>
                                </c:if>

                                <button class="btn btn-primary" onclick="ktraADD(event)" id="btnAdd" style="margin-top: 10px">Thêm</button>
                            </form>
                        </div>
                        <%--detail--%>
                        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                        </div>
                    </div><!-- End Default Tabs -->


                </div>

            </div>
        </div><!-- End Recent Activity -->


    </div><!-- End Right side columns -->

    </div>
</section>
<script>
    function formatDate(inputDate) {
        // Chỉ lấy phần ngày tháng từ chuỗi
        var dateParts = inputDate.split(" ")[0].split("-");
        return dateParts[0] + "-" + dateParts[1] + "-" + dateParts[2]
    }
    document.querySelector('input[name="ngayBatDau"]').value = formatDate('${vc.ngayBatDau}');
    document.querySelector('input[name="ngayKetThuc"]').value = formatDate('${vc.ngayKetThuc}');

    function ktra(e) {
        e.preventDefault();
        var ngayBD = document.getElementById('ngayBDUpdate').value;
        var ngayKT = document.getElementById('ngayKTUpdate').value;

        var ngayBatDau = new Date(ngayBD);
        var ngayKetThuc = new Date(ngayKT);

       if (ngayBatDau>ngayKetThuc){
                alert("Ngày bắt đầu không được lớn hơn ngày kết thúc")
                return;
            }
            else {
             document.getElementById('formUpdate').submit();
        }};

    function ktraADD(e) {
        e.preventDefault();
        var ngayBD = document.getElementById('ngayBDAdd').value;
        var ngayKT = document.getElementById('ngayKTAdd').value;

        var ngayBatDau = new Date(ngayBD);
        var ngayKetThuc = new Date(ngayKT);

        if (ngayBatDau>ngayKetThuc){
            alert("Ngày bắt đầu không được lớn hơn ngày kết thúc")
            return;
        }
        else {
            document.getElementById('formAdd').submit();
        }};
</script>







