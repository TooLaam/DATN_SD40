
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
                        <li style="margin-top: 20px" class="breadcrumb-item"><a href="/bill/detail/${id}">Quay lại</a></li>
                    </ol>
                </nav>
                <div class="row mt-20">
                    <div class="row">
                        <table class="table table-borderless datatable">
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
                            <c:forEach items="${sanPhams}" var="sanPham" varStatus="tt">
                                <tr>
                                    <td>
                                        <img src="/assets/img/product/${sanPham.chiTietSanPhamMauSacHinhAnh.hinhAnh}"
                                             style="padding-top: 60px" width="320px" height="450px">
                                    </td>
                                    <td>${sanPham.chiTietSanPhamMauSacHinhAnh.sanPham.ten}</td>
                                    <td>${sanPham.kichCo.ten}</td>
                                    <td>${sanPham.chiTietSanPhamMauSacHinhAnh.mau_sac.ten}</td>
                                    <td>
                                            ${sanPham.chiTietSanPhamMauSacHinhAnh.giaHienHanh}</td>
                                    <td>${sanPham.soLuong}</td>
                                    <td>
                                        <a  class="btn btn-success"  onclick="selected(${sanPham.chiTietSanPhamMauSacHinhAnh.giaHienHanh},`${sanPham.chiTietSanPhamMauSacHinhAnh.sanPham.ten}`,${sanPham.id}, ${sanPham.soLuong} )" style="text-decoration: none;color: white; margin-top: 5px">Chọn</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>

                        </table>
                    </div>
                </div>
                <div class="row" style="margin-top: 50px;">
                    <div class="product-details">
                        <div class="col-md-7">
                            <form action="/bill/create-bill-detail/${id}" method="post">
                                <div class="product-info">
                                    <input type="hidden"  class="form-control"  name="idBill"  value="${id}">
                                    <input type="hidden"  class="form-control"  name="price" id="price"   >
                                    <input type="hidden"  class="form-control"  name="idChiTietSanPham" id="idChiTietSanPham"   >
                                    <div class="mb-3">
                                        <label for="exampleFormControlInput1" class="form-label">Tên sản phẩm</label>
                                        <input type="text" disabled class="form-control" id="name"  >
                                    </div>
                                    <div class="mb-3">
                                        <label for="exampleFormControlInput1" class="form-label">Số lượng</label>
                                        <input type="number" class="form-control" id="exampleFormControlInput1" name="quantity" min="0">
                                    </div>
                                    <label for="exampleFormControlTextarea1" class="form-label">Nhập nội dung </label>
                                    <textarea class="form-control" id="exampleFormControlTextarea1" required name="note" rows="3"></textarea>
                                    <button type="submit" class="btn btn-primary">
                                        <span>Tiếp theo</span>
                                    </button>

                                </div>
                            </form>
                        </div>
                    </div>
                </div>


            </div>

        </div>
    </div>
</section>

<script>
    function selected(giaHienHanh, ten, id, soLuong) {
        console.log(giaHienHanh)
        console.log(id)
        var price = document.querySelector("#price")
        var name = document.querySelector("#name")
        var maxquantity = document.querySelector("#exampleFormControlInput1")
        var idChiTietSanPham =  document.querySelector("#idChiTietSanPham")
        price.value = giaHienHanh
        name.value= ten
        idChiTietSanPham.value = id
        maxquantity.max = soLuong
    }
</script>




