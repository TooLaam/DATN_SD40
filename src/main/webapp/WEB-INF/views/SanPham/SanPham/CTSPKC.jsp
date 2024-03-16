
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="section dashboard">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">

                    <nav>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item active"><h1>Chi tiết sản phẩm</h1></li>
                            <li style="margin-top: 20px" class="breadcrumb-item"><a href="/sanpham/index">Home</a></li>
                            <li style="margin-top: 20px" class="breadcrumb-item"><a href="/ctsp/hienthi/${sp.id}">Chi tiết sản phẩm</a></li>

                        </ol>
                    </nav>
                    <%--                    <form action="/sanpham/update" method="post" enctype="multipart/form-data">--%>
                    <div class="row" style="margin-top: 50px;">

                        <div class="product-details">
                            <div class="col-md-7">
                                <div class="product-info">
                                    <h2>
                                        <strong style="display: inline;">(${sp.ma})${sp.ten} </strong>
                                        <span><c:if test="${sp.giamGIa != null && sp.giamGIa.mucGiam >0}" >
                                            <p style="color: red;display: inline">(- ${sp.giamGIa.mucGiam}%)</p>
                                        </c:if></span>
                                    </h2>
                                    <div>
                                        <strong style="font-weight: bold;">Thương hiệu: </strong> <span>${sp.thuongHieu.ten}</span>  <br><br>
                                    </div>

                                    <div>
                                        <strong style="font-weight: bold;">Thể loại: </strong> <span>${sp.theLoai.ten}</span>  <br><br>
                                    </div>

                                    <div>
                                        <strong style="font-weight: bold;">Mô tả sản phẩm: </strong> <span>${sp.moTa}</span>  <br><br>
                                    </div>




<%--                                    <p style="font-weight: bold;">Màu sắc: </p>--%>
                                    <div>
                                        <strong>Màu sắc: </strong> <span>${ms.ten}</span>
                                    </div>
                                    <c:forEach items="${listMS}" var="ms">
                                        <a href="/ctsp/hienthiKC/${sp.id}/${ms[0]}" class="btn btn-light" style="margin-left: 30px">
                                            <img style="width: 20px;height: 20px" src="/assets/img/color/${ms[2]}" alt="Icon" class="icon">
                                            <span class="text">${ms[1]}</span>
                                        </a>
                                        <%--                                        <a href="/sanpham/detail/${sp[0]}" class="btn btn-success" ></a>--%>
                                    </c:forEach><br><br>

                                    <div >
                                        <strong>Giá hiện hành:     </strong>  ${listHA.giaHienHanh} <br><br>
                                    </div>
                                    <strong style="display: inline;">Giá đã giảm:  </strong>
                                    <span><c:if test="${sp.giamGIa != null && sp.giamGIa.mucGiam >0}" >
                                        <p style="display: inline">${giaDaGiam[2]}</p>
                                    </c:if></span>


                                    <div>
                                        <strong>Size: </strong> <span id="hienten"></span>
                                    </div>
                                    <c:forEach items="${listKC}" var="ms">
                                        <a onclick="getData('${ms[2]}','${ms[3]==0?"Còn bán":"Ngừng bán"}','${ms[1]}')" class="btn btn-success" style="margin-left: 30px" >${ms[1]}</a>
                                    </c:forEach><br><br>
                                    <div >
                                        <strong>Số lượng:     </strong>    <span id="hiensl"> </span> <br><br>
                                    </div>

                                    <div>
                                        <strong>Trạng thái:     </strong> <span id="hientrangThai"> </span><br><br>
                                    </div>


                                </div>
                            </div>
                            <div class="col-md-5">
                                        <img src="/assets/img/product/${listHA.hinhAnh}" style="padding-top: 60px" width="320px" height="450px">


                            </div>

                        </div>
                    </div>

                    <a href="/ctsp/themmausac/${sp.id}"  type="submit" class="btn btn-primary">Thêm/chỉnh sửa màu sắc cho sản phẩm</a>
                    <a href="/ctsp/giamGia/${sp.id}"  type="submit" class="btn btn-primary">Giảm giá sản phẩm</a>
                    <a href="/ctsp/hienthithemkc/${sp.id}/${ms.id}"  type="submit" class="btn btn-primary">Thêm/Sửa kích cỡ cho sản phẩm</a>

                    <%--                    </form>--%>

                </div>

            </div>
        </div>
    </div>

</section>
<script>
    function getData(sl,trangThai,ten) {
        console.log(sl, trangThai, ten);
        document.getElementById("hiensl").textContent  = sl;
        document.getElementById("hientrangThai").textContent  = trangThai;
        document.getElementById("hienten").textContent  = ten;
        }
</script>






