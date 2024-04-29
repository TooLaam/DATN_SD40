<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
    />
    <style><%@include file="thongke.css"%></style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
</head>
<div class="timkiem">
    Ngày bắt đầu: <input type="date" class="start-date" id="ngayBatDau" style="margin-left: 20px;">
    Ngày kết thúc: <input type="date" class="end-date" id="ngayKetThuc" style="margin-left: 20px;">
    <button class="thongke-btn" onclick="thongKe()">Thống kê</button>
</div>
<div class="containerThongKe">
    <div class="box" id="order">
        <div class="content">
            <div class="title">Đơn hàng</div>
            <hr class="hr">
            <div class="info" id="donHang"></div>
        </div>
    </div>
    <div class="box" id="revenue">
        <div class="content">
            <div class="title">Doanh số</div>
            <hr class="hr">
            <div class="info" id="doanhSo"></div>
        </div>

    </div>
    <div class="box" id="sold">
        <div class="content">
            <div class="title">Sản phẩm đã bán</div>
            <hr class="hr">
            <div class="info" id="sanPhamDaBan"></div>
        </div>
    </div>
    <div class="box" id="inventory">
        <div class="content">
            <div class="title">Hàng tồn kho</div>
            <hr class="hr">
            <div class="info" id="hangTonKho">${hangTonKho}</div>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row h-100">
        <div class="col-md-4 d-flex align-items-center justify-content-center">
            <div class="container1 text-center">
                <h4>Đơn hàng Chờ xác nhận(<span id="choXacNhan"></span>) </h4>
                <div class="progress-container">
                    <div class="progress"></div>
                    <div class="circle" id="circleChoXacNhan">
                        <div class="circle1">
                        <h1 id="percentageChoXacNhan"></h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4 d-flex align-items-center justify-content-center">
            <div class="container1 text-center">
                <h4>Đơn hàng đã xác nhận(<span id="xacNhan"></span>)</h4>
                <div class="progress-container">
                    <div class="progress"></div>
                    <div class="circle" id="circleDaXacNhan">
                        <div class="circle1">
                        <h1 id="percentageDaXacNhan"></h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4 d-flex align-items-center justify-content-center">
            <div class="container1 text-center">
                <h4>Đơn hàng Chờ giao(<span id="choGiao"></span>)</h4>
                <div class="progress-container">
                    <div class="progress"></div>
                    <div class="circle" id="circleChoGiao">
                        <div class="circle1">
                        <h1 id="percentageChoGiao"></h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="col-md-4 d-flex align-items-center justify-content-center" style="margin-top: 25px;">
            <div class="container1 text-center">
                <h4>Đơn hàng Đang giao (<span id="dangGiao"></span>)</h4>
                <div class="progress-container">
                    <div class="progress"></div>
                    <div class="circle" id="circleDangGiao">
                        <div class="circle1">
                        <h1 id="percentageDangGiao"></h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4 d-flex align-items-center justify-content-center" style="margin-top: 25px;">
            <div class="container1 text-center">
                <h4>Đơn hàng Thành công(<span id="thanhCong"></span>)</h4>
                <div class="progress-container">
                    <div class="progress"></div>
                    <div class="circle" id="circleThanhCong">
                        <div class="circle1">
                        <h1 id="percentageThanhCong"></h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4 d-flex align-items-center justify-content-center" style="margin-top: 25px;">
            <div class="container1 text-center">
                <h4>Đơn hàng Hủy(<span id="huy"></span>)</h4>
                <div class="progress-container">
                    <div class="progress"></div>
                    <div class="circle" id="circleHuy">
                        <div class="circle1">
                        <h1 id="percentageHuy"></h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<br>
<br>
<div style="margin-left: 10px;">
    <span style="margin-left: 450px;font-size: 24px"><strong>Doanh thu theo tháng</strong> </span>
    <select id="yearSelect" >
        <option value="2020">2020</option>
        <option value="2021">2021</option>
        <option value="2022">2022</option>
        <option value="2023">2023</option>
        <option value="2024" selected>2024</option>
        <option value="2025">2025</option>
        <option value="2026">2026</option>
        <option value="2027">2027</option>
        <option value="2028">2028</option>
        <option value="2029">2029</option>
        <option value="2030">2030</option>
        <option value="2031">2031</option>
        <option value="2032">2032</option>
        <option value="2033">2033</option>
        <option value="2034">2034</option>
        <option value="2035">2035</option>
        <option value="2036">2036</option>
        <option value="2037">2037</option>
        <!-- Thêm các năm khác nếu cần -->
    </select>


</div>
<canvas id="myChart"></canvas>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    function formatNumber(price) {
        var formattedPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(Math.round(price));

        // // Loại bỏ phần thập phân và chữ "đ" phía sau
        // formattedPrice = formattedPrice.replace(/(\d+)(?:\.(\d+))? đ/, '$1');

        return price.toLocaleString('vi-VN');
    }
    function formatThapPhan(value) {
        if (Number.isInteger(value)) {
            // Nếu là số nguyên, giữ nguyên giá trị
            return value;
        } else {
            // Nếu là số thập phân, chỉ giữ lại một số sau dấu thập phân
            return value.toFixed(1);
        }
    }
    function chuyenDoiNgay(dateString) {
        // Tạo một đối tượng Date từ chuỗi đầu vào
        var date = new Date(dateString);

        // Lấy ngày, tháng và năm từ đối tượng Date
        var day = date.getDate();
        var month = date.getMonth() + 1; // Lưu ý: Tháng bắt đầu từ 0, nên cần cộng thêm 1
        var year = date.getFullYear();

        // Định dạng lại thành chuỗi YYYY-MM-DD
        var formattedDate = year + '-' + month.toString().padStart(2, '0') + '-' + day.toString().padStart(2, '0');

        return formattedDate;
    }

    document.addEventListener("DOMContentLoaded", function() {
        LayGiaTriCuaBieuDo(2024);
        loadConSo("1999-12-12","2999-12-12");

    });
    function loadConSo(ngayBD,ngayKT){
        $.ajax({
            type: "GET",
            url: "/thongke/thongKeTongDonHangTheoNgay/"+ngayBD+"/"+ngayKT,
            success: function(response) {
                if (response[0] === 0){
                    document.getElementById('donHang').textContent="0"
                    document.getElementById('percentageChoXacNhan').textContent="0%";
                    document.getElementById('choXacNhan').textContent="0";
                    document.getElementById('percentageDaXacNhan').textContent="0%";
                    document.getElementById('xacNhan').textContent="0";
                    document.getElementById('percentageChoGiao').textContent="0%";
                    document.getElementById('choGiao').textContent="0";
                    document.getElementById('percentageDangGiao').textContent="0%";
                    document.getElementById('dangGiao').textContent="0";
                    document.getElementById('percentageThanhCong').textContent="0%";
                    document.getElementById('thanhCong').textContent="0";
                    document.getElementById('percentageHuy').textContent="0%";
                    document.getElementById('huy').textContent="0";
                }
                else {document.getElementById('donHang').textContent=response[0];
                    document.getElementById('percentageChoXacNhan').textContent=formatThapPhan(response[2]) +"%";
                    document.getElementById('choXacNhan').textContent=response[1];
                    document.getElementById('percentageDaXacNhan').textContent=formatThapPhan(response[4]) +"%";
                    document.getElementById('xacNhan').textContent=response[3];
                    document.getElementById('percentageChoGiao').textContent=formatThapPhan(response[6])+"%";
                    document.getElementById('choGiao').textContent=response[5];
                    document.getElementById('percentageDangGiao').textContent=formatThapPhan(response[8])+"%";
                    document.getElementById('dangGiao').textContent=response[7];
                    document.getElementById('percentageThanhCong').textContent=formatThapPhan(response[10])+"%";
                    document.getElementById('thanhCong').textContent=response[9];
                    document.getElementById('percentageHuy').textContent=formatThapPhan(response[12])+"%";
                    document.getElementById('huy').textContent=response[11];
                }
                load()
                $.ajax({
                    type: "GET",
                    url: "/thongke/thongKeSanPhamDaBanAllTheoNgay/"+ngayBD+"/"+ngayKT,
                    success: function(response) {
                        console.log(response)
                        if (response ===''){
                            document.getElementById('sanPhamDaBan').textContent="0"
                        }else {
                            document.getElementById('sanPhamDaBan').textContent=response
                        }}
                });

                $.ajax({
                    type: "GET",
                    url: "/thongke/thongKeDoanhThuTheoNgay/"+ngayBD+"/"+ngayKT,
                    success: function(response) {
                        console.log(response)
                        if (response ===''){
                            document.getElementById('doanhSo').textContent="$0"
                        }else {
                            document.getElementById('doanhSo').textContent='$'+formatNumber(response);
                        }}
                });


            }
        });
    }
    document.getElementById("yearSelect").addEventListener("change", function() {
        var selectedYear = this.value;
        LayGiaTriCuaBieuDo(selectedYear);
    });

    var myChart = null;

    function LayGiaTriCuaBieuDo(selectedYear) {
        $.ajax({
            type: "GET",
            url: "/thongke/bieuDo/" + selectedYear,
            success: function(response) {
                renderChart(response);
            }
        });
    }

    function renderChart(doanhThuData) {
        if (myChart !== null && typeof myChart.destroy === 'function') {
            myChart.destroy();
        }

        var xValues = [];
        var yValues = [];
        var barColors = ["brown"];
        var doanhThuMap = new Map();

        doanhThuData.forEach(item => {
            var thang = item[1];
            var doanhThu = item[2];
            doanhThuMap.set(thang, doanhThu);
        });

        for (var thang = 1; thang <= 12; thang++) {
            var doanhThu = doanhThuMap.get(thang);
            if (doanhThu === undefined) {
                doanhThu = 0;
            }
            xValues.push("Tháng " + thang);
            yValues.push(doanhThu);
        }

        var canvas = document.getElementById("myChart");
        // canvas.style.height = "100px"; // Đặt lại chiều cao của canvas

        myChart = new Chart(canvas.getContext('2d'), {
            type: "bar",
            data: {
                labels: xValues,
                datasets: [{
                    backgroundColor: barColors,
                    data: yValues
                }]
            },
            options: {
                legend: { display: false },
                title: {
                    display: true,
                    text: "Tính doanh thu theo từng tháng"
                },
                maintainAspectRatio: true, // Không giữ nguyên tỉ lệ khung hình
                responsive: true
            }
        });
    }

    function load() {
        const circleDangGiao = document.getElementById("circleDangGiao");
        const circleHuy = document.getElementById("circleHuy");
        const circleThanhCong = document.getElementById("circleThanhCong");
        const circleDaXacNhan = document.getElementById("circleDaXacNhan");
        const circleChoXacNhan = document.getElementById("circleChoXacNhan");
        const circleChoGiao = document.getElementById("circleChoGiao");
        const percentageDangGiao = parseFloat(
            document.getElementById("percentageDangGiao").innerText
        );
        const percentageHuy = parseFloat(
            document.getElementById("percentageHuy").innerText
        );
        const percentageThanhCong = parseFloat(
            document.getElementById("percentageThanhCong").innerText
        );
        const percentageChoXacNhan = parseFloat(
            document.getElementById("percentageChoXacNhan").innerText
        );
        const percentageDaXacNhan = parseFloat(
            document.getElementById("percentageDaXacNhan").innerText
        );
        const percentageChoGiao = parseFloat(
            document.getElementById("percentageChoGiao").innerText
        );
        const startTime = performance.now();
        const animationDuration = 500; // milliseconds

        function animateCircle(timestamp) {
            const elapsed = timestamp - startTime;
            const progress = Math.min(1, elapsed / animationDuration);
            const currentPercentageDangGiao = progress * percentageDangGiao;
            const currentPercentageHuy = progress * percentageHuy;
            const currentPercentageThanhCong = progress * percentageThanhCong;
            const currentPercentageChoGiao = progress * percentageChoGiao;
            const currentPercentageChoXacNhan = progress * percentageChoXacNhan;
            const currentPercentageDaXacNhan = progress * percentageDaXacNhan;
            circleDangGiao.style.background = `conic-gradient(#E5F172 0%, #E5F172 `+currentPercentageDangGiao+`%, transparent `+currentPercentageDangGiao+`%, transparent 100%)`;
            circleHuy.style.background = `conic-gradient(#EE6A6A 0%, #EE6A6A `+currentPercentageHuy+`%, transparent `+currentPercentageHuy+`%, transparent 100%)`;
            circleThanhCong.style.background = `conic-gradient(#8DC894 0%, #8DC894 `+currentPercentageThanhCong+`%, transparent `+currentPercentageThanhCong+`%, transparent 100%)`;
            circleDaXacNhan.style.background = `conic-gradient(#90A860 0%, #90A860 `+currentPercentageDaXacNhan+`%, transparent `+currentPercentageDaXacNhan+`%, transparent 100%)`;
            circleChoXacNhan.style.background = `conic-gradient(#7E72B1 0%, #7E72B1 `+currentPercentageChoXacNhan+`%, transparent `+currentPercentageChoXacNhan+`%, transparent 100%)`;
            circleChoGiao.style.background = `conic-gradient(#A074A2 0%, #A074A2 `+currentPercentageChoGiao+`%, transparent `+currentPercentageChoGiao+`%, transparent 100%)`;
            if (progress < 1) {
                requestAnimationFrame(animateCircle);
            }
        }

        requestAnimationFrame(animateCircle);
    }
    load();

    function thongKe(){
       var ngayBatDau = document.getElementById("ngayBatDau").value;
       var ngayKetThuc = document.getElementById("ngayKetThuc").value;
       var ngayBD = new Date(ngayBatDau);
       var ngayKT = new Date(ngayKetThuc);
       var ngayBDChuyenDoi = chuyenDoiNgay(ngayBD);
       var ngayKTChuyenDoi = chuyenDoiNgay(ngayKT)
       if (ngayBatDau.trim()===''||ngayKetThuc.trim()===''){
          loadConSo("1999-12-12","2999-12-12");
           return;
       }else {
           if (ngayBD>ngayKT){
               alert("Ngày bắt đầu không được lớn hơn ngày kết thúc")
               return;
           }else {
              loadConSo(ngayBDChuyenDoi,ngayKTChuyenDoi)

           }
       }
    }


</script>


