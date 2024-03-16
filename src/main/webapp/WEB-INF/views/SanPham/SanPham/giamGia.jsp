
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="section dashboard">
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item active"><h1>Giảm giá cho sản phẩm</h1></li>
            <li style="margin-top: 20px" class="breadcrumb-item"><a href="/sanpham/index">Home</a></li>
            <li style="margin-top: 20px" class="breadcrumb-item"><a href="/ctsp/hienthi/${sp.id}">Chi tiết sản phẩm</a></li>
        </ol>
    </nav>
    <div class="row">
        <!-- Left side columns -->
        <div class="col-lg-12">
            <div class="row">
                <!-- Recent Sales -->
                <div class="col-12">
                    <div class="card recent-sales overflow-auto">


                        <div class="card-body">

                            <form action="/ctsp/updategiamGia/${sp.id}" method="post" enctype="multipart/form-data">
                                <div class="product-info">


                                    <strong  class="form-label">ID sản phẩm</strong>
                                    <input type="text" class="form-control" readonly value="${sp.id}" name="id">

                                    <strong  class="form-label">Mã</strong>
                                    <input type="text" class="form-control" readonly value="${sp.ma}" name="ma">

                                    <strong  class="form-label">Tên</strong>
                                    <input type="text" class="form-control" readonly value="${sp.ten}" name="ten">

                                    <strong  class="form-label">Thương hiệu</strong>
                                    <input type="text" class="form-control" readonly value="${sp.thuongHieu.ten}" name="thuongHieu">

                                    <strong  class="form-label">Thể loại</strong>
                                    <input type="text" class="form-control" readonly value="${sp.theLoai.ten}" name="theLoai">

                                    <strong  class="form-label">Mô tả</strong>
                                    <input type="text" class="form-control" readonly value="${sp.moTa}" name="moTa">

                                    <strong>Chương trình giảm giá: </strong>

                                        <select id="mySelect" onchange="getData()" class="form-select" aria-label="Default select example"name="giamGia" >
                                            <c:if test="${sp.giamGIa != null}">
                                                <option value="${sp.giamGIa.id}" data-ten="${sp.giamGIa.mucGiam}">${sp.giamGIa.ten}</option>
                                            </c:if>
                                            <c:forEach items="${listGG}" var="tk">
                                                <option value="${tk.id}" data-ten="${tk.mucGiam}">${tk.ten}</option>
                                            </c:forEach>
                                        </select>
                                    <br>

                                    <div >
                                        <strong>Mức giảm:     </strong>    <span id="hienTen"></span> <br><br>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Cập nhật</button>

                                </div>
                            </form>

                        </div>

                    </div>


                </div><!-- End Recent Sales -->

            </div>

        </div><!-- End Left side columns -->

        <!-- Right side columns -->

    </div><!-- End Right side columns -->

</section>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        getData();
    });
    function getData() {
        var mySelect = document.getElementById("mySelect");
        var selectedIndex = mySelect.selectedIndex;
        var selectedOption = mySelect.options[selectedIndex];
        var mucGiam = selectedOption.dataset.ten + "%"; // Thêm ký tự % vào mức giảm
        document.getElementById("hienTen").innerHTML = mucGiam;
    }
</script>













