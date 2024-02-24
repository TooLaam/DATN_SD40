<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>Bill Management</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
            <li class="breadcrumb-item active">Overview</li>
            <li class="breadcrumb-item active">Product</li>
        </ol>
    </nav>
</div>
<!-- End Page Title -->

<section class="section dashboard">
    <div class="row">
        <!-- Left side columns -->
        <div class="col-lg-12">
            <div class="row">
                <!-- Recent Sales -->
                <div class="col-12">
                    <div class="card recent-sales overflow-auto">


                        <div class="card-body">
                            <h5 class="card-title">Bill Statistics
                                <span>| <b>From: ${startDate} To: ${endDate} </b>  </span></h5>
                            <form action="/statisticsResultBill" method="get">
                                <div class="form-row">
                                    <div class="col-md-6 form-group">
                                        <label for="startDate">Start Date:</label>
                                        <input type="date" id="startDate" name="startDate"
                                               class="form-control datepicker">
                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label for="endDate">End Date:</label>
                                        <input type="date" id="endDate" name="endDate" class="form-control datepicker">
                                    </div>
                                </div>
                                <button id="applyDateRange" class="btn btn-primary">Apply</button>
                            </form>


                            <table class="table table-hover datatable">
                                <b>From: ${startDate} To: ${endDate} </b>
                                <thead>
                                <tr>
                                    <th>Receiver Name</th>
                                    <%--                                    <th>Employee</th>--%>
                                    <%--                                    <th>Customer</th>--%>
                                    <th>Customer Phone</th>
                                    <th>Address Delivery</th>
                                    <th>Total Money</th>
                                    <th>Created Date</th>
                                    <th>Payment</th>
                                    <th>Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${statisticsResult}" var="bill">
                                    <tr onclick="goToPage('/bill_detail/index/${bill.id}')">
                                            <%--                                        <td>${bill.id}</td>--%>
                                        <td>${bill.receiverName}</td>
                                            <%--                                        <td>${bill.employee.fullName}</td>--%>
                                            <%--                                        <td>${bill.customer.fullname}</td>--%>
                                        <td>${bill.customerPhone}</td>
                                        <td>${bill.addressDelivery}</td>
                                        <td>${bill.totalMoney}</td>
                                        <td>${bill.createdDate}</td>
                                        <td>${bill.billStatus.name}</td>
                                        <td>${bill.payment.name}</td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                            <b>Total Revenue: ${total}</b>
                        </div>

                    </div>


                </div><!-- End Recent Sales -->

            </div>

        </div><!-- End Left side columns -->

        <!-- Right side columns -->
        <div class="col-lg-12">

            <!-- Recent Activity -->
            <div class="row">
                <!-- Recent Sales -->
                <div class="col-12">
                    <div class="card recent-sales overflow-auto">
                        <div class="card-body">
                            <h5 class="card-title">Product Statistics
                                <span>| <b>From: ${startDateProduct} To: ${endDateProduct} </b> </span></h5>
                            <form action="/statisticsResultProduct" method="get">
                                <div class="form-row">
                                    <div class="col-md-6 form-group">
                                        <label for="startDate">Start Date:</label>
                                        <input type="date" id="startDate" name="startDateProduct"
                                               class="form-control datepicker">
                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label for="endDate">End Date:</label>
                                        <input type="date" id="endDate" name="endDateProduct"
                                               class="form-control datepicker">
                                    </div>
                                </div>
                                <button id="applyDateRange" class="btn btn-primary">Apply</button>
                            </form>

                            <table class="table table-hover datatable">
                                <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Total Sales</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${statisticsResultProduct}" var="pro">
                                    <tr>
                                            <%--                                        <td>${pro.id}</td>--%>
                                        <td>${pro[0]}</td>
                                        <td>${pro[1]}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>

                            </table>
                        </div>

                    </div>
                </div><!-- End Recent Sales -->

            </div>
        </div><!-- End Recent Activity -->


    </div><!-- End Right side columns -->

    </div>

    <script>
        function goToPage(url) {
            window.location.href = url;
        }
    </script>

    <script>
        $(function () {
            // Tạo DatePicker cho ngày bắt đầu
            $("#startDate").datepicker({
                dateFormat: 'yy-mm-dd', // Định dạng ngày
                defaultDate: '-1m',  // Giá trị mặc định (1 tháng trước)
                changeMonth: true,
                changeYear: true
            });

            // Tạo DatePicker cho ngày kết thúc
            $("#endDate").datepicker({
                dateFormat: 'yy-mm-dd', // Định dạng ngày
                defaultDate: new Date(), // Giá trị mặc định (hiện tại)
                changeMonth: true,
                changeYear: true
            });
        });
    </script>

</section>



