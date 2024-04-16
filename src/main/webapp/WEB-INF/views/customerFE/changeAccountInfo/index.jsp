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
        <div class="col-3">
            <div class="content">
                <span class="account-name"> Tài khoản </span>
                <a href="/logout" class="link-text-logout">(Đăng xuất)</a>
            </div>
            <div class="link">
                <a href="/infoKhachHang" style="color:black;" class="link-text">Thông tin tài khoản</a>
            </div>
            <div class="link">
                <a href="/bill/hienThiHoaDon" class="link-text-active">Đơn hàng</a>
            </div>
            <div class="link">
                <a href="/hienThiUpdateKhachHang" class="link-text-active">Chỉnh sửa tài khoản</a>
            </div>
            <div class="link">
                <a style="cursor: pointer;" id="doiMatKhau" class="link-text-active">Đổi mật khẩu</a>
            </div>
        </div>
        <div class="col-9">
            <div class="information">
                <p>Thông tin tài khoản</p>

                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label form-label">Mã</label>
                    <div class="col-sm-10">
                        ${kh.ma}
                    </div>
                </div>

                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label form-label">Họ tên</label>
                    <div class="col-sm-10">
                        ${kh.hoTen}
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label form-label">Ngày sinh</label>
                    <div class="col-sm-10" id="birthday">
                        ${kh.ngaySinh}
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label form-label">Số điện thoại</label>
                    <div class="col-sm-10">
                        ${kh.sdt}
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label form-label">Giới tính</label>
                    <div class="col-sm-10">
                        ${kh.gioiTinh == 1 ?'Nam':'Nữ'}
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label form-label">Email</label>
                    <div class="col-sm-10">
                        ${kh.email}
                    </div>
                </div>
                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label form-label">Điểm tích lũy</label>
                    <div class="col-sm-10">
                        ${kh.diemTichLuy}
                    </div>
                </div>

                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label form-label">Hạng thành viên</label>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${kh.hangThanhVien.id == null}">
                                Thành viên mới
                            </c:when>
                            <c:otherwise>
                                ${kh.hangThanhVien.ten}
                            </c:otherwise>
                        </c:choose>

                    </div>
                    <div style="color: gray">
                        <c:choose>
                            <c:when test="${kh.hangThanhVien.id == 1}">
                                *(Bạn cần tích thêm ${hangBac.dieuKien-kh.diemTichLuy} điểm để lên hạng Bạc)
                            </c:when>
                            <c:when test="${kh.hangThanhVien.id == 2}">
                                *(Bạn cần tích thêm ${hangVang.dieuKien-kh.diemTichLuy} điểm để lên hạng Vàng)
                            </c:when>
                            <c:when test="${kh.hangThanhVien.id == 3}">
                                *(Bạn cần tích thêm ${hangKimCuong.dieuKien-kh.diemTichLuy} điểm để lên hạng Kim Cương)
                            </c:when>
                            <c:when test="${kh.hangThanhVien.id == 4}">

                            </c:when>
                            <c:otherwise>
                                *(Bạn cần tích thêm ${hangDong.dieuKien-kh.diemTichLuy} điểm để lên hạng Đồng)
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <br/>
    <div id="hiddenForm">
        <h3 style="color: black">Đổi tài khoản mật khẩu</h3>
        <span id="closeButton" class="closeButton">&times;</span>
        <div style="display: flex">
            <span><label for="taiKhoanHienTai">Tài khoản hiện tại</label></span>
            <span class="error-message" style="display: none;color: red;margin-left: 20px" id="errTaiKhoan">Tài khoản không đúng</span>
        </div>
        <input type="text" oninput="kiemTraThongTinDoiMatKhau()" id="taiKhoanHienTai" class="form-control">
        <div>
            <label for="taiKhoanMoi">Tài khoản mới</label>
            <input type="text" oninput="kiemTraThongTinDoiMatKhau()" id="taiKhoanMoi" class="form-control" name="taiKhoan">
        </div>
        <div style="display: flex">
            <span><label for="matKhauHienTai">Mật khẩu hiện tại</label></span>
            <span class="error-message" style="display: none;color: red;margin-left: 20px" id="errMatKhau">Mật khẩu không đúng</span>
        </div>
        <input type="password" oninput="kiemTraThongTinDoiMatKhau()" id="matKhauHienTai" class="form-control">
        <div>
            <label for="matKhauMoi">Mật khẩu mới</label>
            <input type="password" oninput="kiemTraThongTinDoiMatKhau()" id="matKhauMoi" class="form-control" name="matKhau">
        </div>
        <div style="display: flex">
            <span><label for="matKhauNhapLai">Nhập lại mật khẩu</label></span>
            <span class="error-message" style="display: none;color: red;margin-left: 20px" id="errMatKhauLai">Mật khẩu không trùng khớp</span>
        </div>
        <input type="password" oninput="kiemTraThongTinDoiMatKhau()" id="matKhauNhapLai" class="form-control">
        <button class="btn btn-light" id="submitButton">Đổi mật khẩu</button>
    </div>

</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function formatDate(inputDate) {
        // Chỉ lấy phần ngày tháng từ chuỗi
        var parts = inputDate.split(' ')[0].split('-');
        return parts[2] + '/' + parts[1] + '/' + parts[0];
    }
    document.getElementById("birthday").textContent = formatDate("${kh.ngaySinh}");

    document.getElementById('doiMatKhau').addEventListener('click', function(event) {
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

    function kiemTraThongTinDoiMatKhau() {
        var taiKhoanHienTai = document.getElementById('taiKhoanHienTai').value;
        var taiKhoanMoi = document.getElementById('taiKhoanMoi').value;
        var matKhauHienTai = document.getElementById('matKhauHienTai').value;
        var matKhauMoi = document.getElementById('matKhauMoi').value;
        var matKhauNhapLai = document.getElementById('matKhauNhapLai').value

        if (taiKhoanHienTai !== "${kh.taiKhoan}" && taiKhoanHienTai !== "") {
            document.getElementById('errTaiKhoan').style.display = 'block';
            return false;
        } else {
            document.getElementById('errTaiKhoan').style.display = 'none';
        }

        // Kiểm tra mật khẩu hiện tại
        if (matKhauHienTai !== "${kh.matKhau}" && matKhauHienTai !== "") {
            document.getElementById('errMatKhau').style.display = 'block';
            return false;
        } else {
            document.getElementById('errMatKhau').style.display = 'none';
        }

        // Kiểm tra mật khẩu nhập lại
        if (matKhauMoi !== matKhauNhapLai && matKhauNhapLai !== "") {
            document.getElementById('errMatKhauLai').style.display = 'block';
            return false;
        } else {
            document.getElementById('errMatKhauLai').style.display = 'none';
        }

        // Nếu tất cả điều kiện đều hợp lệ, trả về true
        return true;
    }



    $("#submitButton").click(function(e) {
        e.preventDefault();
        var taiKhoanHienTai = document.getElementById('taiKhoanHienTai').value;
        var taiKhoanMoi = document.getElementById('taiKhoanMoi').value;
        var matKhauHienTai = document.getElementById('matKhauHienTai').value;
        var matKhauMoi = document.getElementById('matKhauMoi').value;
        var matKhauNhapLai = document.getElementById('matKhauNhapLai').value

        if (taiKhoanHienTai == ''||taiKhoanMoi ==''||matKhauHienTai==''||matKhauMoi==''||matKhauNhapLai==''){
            alert("Vui lòng nhập đầy đủ thông tin")
            return;
        }else {

            if (taiKhoanHienTai !== "${kh.taiKhoan}") {
                alert("Vui lòng nhập lại tài khoản hiện tại")
                return;
            }else if (matKhauHienTai !== "${kh.matKhau}"){
                alert("Vui lòng nhập lại mật khẩu hiện tại")
                return;
            }else if (matKhauMoi !== matKhauNhapLai){
                alert("Mật khẩu chưa trùng khớp !!!")
                return;
            }
            else {
                var formData = {
                    matKhau: document.getElementById('matKhauMoi').value,
                    taiKhoan: document.getElementById('taiKhoanMoi').value
                };
                $.ajax({
                    type: "POST",
                    url: "/doiMatKhau",
                    data: formData,
                    success: function (response) {
                        if (response ==='ok'){
                            alert("Đổi mật khẩu thành công !!!")
                            window.location.href = "/infoKhachHang";
                        }else {
                            alert("Tài khoản đã trùng với của khách hàng khác, Vui lòng chọn tài khoản khác !!!")
                        }

                    },
                    error: function (xhr, status, error) {
                        alert("thất bại !!!");
                        console.error("Error occurred while sending data: " + error);
                    }
                });
            }
        }
    });
</script>