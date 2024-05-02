
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                                    <select  class="form-select" aria-label="Default select example"id="thuongHieu" >
                                        <c:forEach items="${listTH}" var="ms">
                                            <option value="${ms.id}" >
                                                ${ms.ten}
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <p>Thể loại: </p>
                                    <select  class="form-select" aria-label="Default select example"id="theLoai" >
                                        <c:forEach items="${listTL}" var="ms">
                                            <option value="${ms.id}" >
                                                    ${ms.ten}
                                            </option>
                                        </c:forEach>
                                    </select>

                                    <p>Giảm giá sản phẩm: </p>
                                    <select  class="form-select" aria-label="Default select example"id="giamGia" >
                                        <c:forEach items="${listGG}" var="ms">
                                            <option value="${ms.id}" >
                                                    ${ms.ten}
                                            </option>
                                        </c:forEach>
                                    </select>

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
</section>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
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
                    var cf = confirm("Bạn muốn thêm dữ liệu???");
                    if (cf == true) {
                        window.location.href = "/ctsp/hienThiChinhSuaMauSac/"+response.id;
                    }


                }
            });
        }

    }
</script>





