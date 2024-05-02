
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

                                    <div>
                                        <strong>Màu sắc: </strong> <span>${ms.ten}</span>
                                    </div>
                                    <c:forEach items="${listMS}" var="ms">
                                        <a onclick="getData(${ms[0]},${sp.id})" class="btn btn-light mauSac" id="btnMauSac${ms[0]}" style="margin-left: 30px">
                                            <img style="width: 20px;height: 20px" src="/assets/img/color/${ms[2]}" alt="Icon" class="icon">
                                            <span class="text">${ms[1]}</span>
                                        </a>
                                    </c:forEach><br><br>

                                    <div >
                                        <strong>Giá hiện hành:     </strong> <span id="giaHienHanh"></span>
                                    </div>
                                    <div >
                                        <strong>Giá đã giảm:     </strong> <span id="giaDaGiam"></span>
                                    </div>

                                    <div>
                                        <strong>Size: </strong>   <div id="productsContainer"></div>
                                    </div>

                                    <div >
                                        <strong>Số lượng:     </strong>    <span id="soLuong"></span> <br><br>
                                    </div>

                                    <div>
                                        <strong>Trạng thái:     </strong> <span id="trangThai"> </span><br><br>
                                    </div>
                                    <div style="display:none;" id="idKC"></div>


                                </div>
                            </div>
                            <div class="col-md-5">
                                <img src="/assets/img/product/${sp.hinhAnhDaiDien}" id="img" style="padding-top: 60px" width="320px" height="450px">
                            </div>

                        </div>
                    </div>

                    <a href="/ctsp/hienThiChinhSuaMauSac/${sp.id}"  type="submit" class="btn btn-primary">Thêm/chỉnh sửa màu sắc cho sản phẩm</a>
                    <a href="/ctsp/giamGia/${sp.id}"  type="submit" class="btn btn-primary">Giảm giá sản phẩm</a>
                    <a  onclick="KichCoBtn()"  type="submit" class="btn btn-primary">Thêm/Sửa kích cỡ cho sản phẩm</a>

                </div>

            </div>
        </div>
    </div>

</section>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function formatNumber(price) {
        var formattedPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(Math.round(price));
        formattedPrice = formattedPrice.replace(/(\d+)(?:\.(\d+))? đ/, '$1');
        return formattedPrice;
    }
    function getData(ms,sp) {
        document.getElementById('idKC').textContent='';
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
                url: "/ctsp/hienthiGiaDaGiam/"+sp+"/"+ms,
                success: function (response) {
                    document.getElementById('idKC').textContent = response[0]
                 document.getElementById('giaHienHanh').textContent = formatNumber(response[1]) ;
                 document.getElementById('giaDaGiam').textContent = formatNumber(response[2]) ;
                 $('#img').attr('src', '/assets/img/product/' + response[3]);

                }});

        $.ajax({
            type: "GET",
            url: "/ctsp/hienthiKC/" + sp + "/" + ms,
            success: function (response) {
                $('#productsContainer').empty();
                $.each(response, function (index, data) {
                    var idkc = data[0];
                    var ten = data[1];
                    var soLuong = data[2];
                    var trangThai = data[3];
                    var image = data[4]

                    // Tạo thẻ <a> với các thông tin và gán sự kiện onclick
                    var linkHTML = '<a id="kichco' + idkc + '" data-info="' + idkc + ',' + ten + '" onclick="getDataKC(' + soLuong + ',\'' + idkc + '\',\'' + trangThai + '\')" class="btn btn-light kichco" style="margin-left: 30px">' + ten + '</a>';

                    // Thêm thẻ <a> vào container
                    $('#productsContainer').append(linkHTML);
                });
            },
            error: function (xhr, status, error) {
                // Xử lý lỗi
                console.error("Error occurred while fetching data: " + error);
            }
        });

    }

    function getDataKC(soLuong,id,trangThai){
        var allButtons = document.querySelectorAll('.kichco');
        allButtons.forEach(function(button) {
            button.style.backgroundColor = "";
            button.style.color = "black";
        });

        // Đặt màu nền cho nút được click
        document.getElementById('kichco' + id).style.backgroundColor = "#00575C";
        document.getElementById('kichco' + id).style.color = "white";
        document.getElementById('soLuong').textContent=soLuong;
        if (trangThai ==0){
            document.getElementById('trangThai').textContent='Còn bán';
            document.getElementById('trangThai').style.color='green';
            return;
        }else {
            document.getElementById('trangThai').textContent='Ngừng bán';
            document.getElementById('trangThai').style.color='red';
        }

    }
    function KichCoBtn(){
        var idms = document.getElementById('idKC').innerText;
        console.log(idms)
        if (idms.trim() === ''){
            alert("Bạn vui lòng chọn màu sắc muốn Thêm/Chỉnh sửa kích cỡ !!!")
            return;
        }else {
            window.location.href = "/ctsp/hienthithemkc/${sp.id}/"+idms;
        }

    }
</script>






