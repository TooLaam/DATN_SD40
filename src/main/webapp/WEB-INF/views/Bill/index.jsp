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
                            <h5 class="card-title">Bill <span>| </span></h5>

                            <table class="table table-hover datatable">
                                <thead>
                                <tr>
                                    <th>Receiver Name</th>
                                    <%--                                    <td>Employee</td>--%>
                                    <%--                                    <td>Customer</td>--%>
                                    <th>Customer Phone</th>
                                    <th>Address Delivery</th>
                                    <th>Total Money</th>
                                    <th>Created Date</th>
                                    <th>Status</th>
                                    <th>Payment</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listBill}" var="bill">
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
                                        <td>
                                            <a href="/bill_detail/index/${bill.id}" class="btn btn-success">Detail</a>
<%--                                            <a href="/bill/index/${bill.id}" class="btn btn-danger">Detail</a>--%>
                                            <form action="/bill/change_bill_status/${bill.id}" method="post">
                                                <button class=" ${bill.billStatus.id!="159b8bc3-5489-47c0-a115-b94a0cf6286f"?"btn btn-warning":"btn btn-dark"}"
                                                        onclick="return confirm('You want to change this status?')"
                                                    ${bill.billStatus.id=="159b8bc3-5489-47c0-a115-b94a0cf6286f"?"disabled":""}>${bill.billStatus.id=="159b8bc3-5489-47c0-a115-b94a0cf6286f"?"Done":"Change Status"}</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <%--                            <form method="post" enctype="multipart/form-data" action="import">--%>
                            <%--                                Thêm từ file excel: <input class="form-control" name="file" type="file">--%>
                            <%--                                <button>Thêm</button>--%>
                            <%--                            </form>--%>
                        </div>

                    </div>
<%--                    <button class="btn btn-primary"><a href="/kich-thuoc/create"--%>
<%--                                                       style="text-decoration: none;color: white">Add New</a></button>--%>

                </div><!-- End Recent Sales -->

            </div>

        </div><!-- End Left side columns -->

        <!-- Right side columns -->
<%--        <div class="col-lg-4">--%>

<%--            <!-- Recent Activity -->--%>
<%--            <div class="card">--%>

<%--                <div class="card-body">--%>
<%--                    <h5 class="card-title">Sửa <span>| xx</span></h5>--%>
<%--                    <!-- Default Tabs -->--%>
<%--                    <ul class="nav nav-tabs" id="myTab" role="tablist">--%>
<%--                        <li class="nav-item" role="presentation">--%>
<%--                            <button class="nav-link active" id="contact-tab" data-bs-toggle="tab"--%>
<%--                                    data-bs-target="#contact" type="button" role="tab" aria-controls="contact"--%>
<%--                                    aria-selected="false">Detail--%>
<%--                            </button>--%>
<%--                        </li>--%>
<%--                        <li class="nav-item" role="presentation">--%>
<%--                            <button class="nav-link " id="home-tab" data-bs-toggle="tab"--%>
<%--                                    data-bs-target="#home" type="button" role="tab" aria-controls="home"--%>
<%--                                    aria-selected="true">Edit--%>
<%--                            </button>--%>
<%--                        </li>--%>
<%--                        <li class="nav-item" role="presentation">--%>
<%--                            <button class="nav-link" id="profile-tab" data-bs-toggle="tab"--%>
<%--                                    data-bs-target="#profile" type="button" role="tab" aria-controls="profile"--%>
<%--                                    aria-selected="false">Add new--%>
<%--                            </button>--%>
<%--                        </li>--%>
<%--                    </ul>--%>

<%--                    &lt;%&ndash;update&ndash;%&gt;--%>
<%--                    <div class="tab-content pt-2" id="myTabContent">--%>
<%--                        <div class="tab-pane fade" id="home" role="tabpanel"--%>
<%--                             aria-labelledby="home-tab">--%>
<%--                            <form action="/bill/update-bill/${billId}" method="post">--%>
<%--                                <div class="form-group">--%>
<%--                                    Price: <input type="number" name="price" class="form-control"--%>
<%--                                                  value="${billD.price}">--%>
<%--                                </div>--%>


<%--                                <div class="form-group">--%>
<%--                                    Address: <input type="text" name="address" class="form-control"--%>
<%--                                                    value="${billD.address}">--%>
<%--                                </div>--%>

<%--                                <div>--%>
<%--                                    Customer:--%>
<%--                                    <select class="form-select" name="customerId" aria-label="Default select example">--%>
<%--                                        <c:forEach items="${listCustomer}" var="lc">--%>
<%--                                            <option value="${lc.id}" ${lc.id==billD.customer.id?"selected":""}>${lc.fullname}</option>--%>
<%--                                        </c:forEach>--%>
<%--                                    </select>--%>
<%--                                </div>--%>

<%--                                <div>--%>
<%--                                    Employee--%>
<%--                                    <select class="form-select" name="employeeId" aria-label="Default select example">--%>
<%--                                        <c:forEach items="${listEmployee}" var="lem">--%>
<%--                                            <option value="${lem.id}"${lem.id==billD.employee.id?"selected":""}>${lem.fullName}</option>--%>
<%--                                        </c:forEach>--%>
<%--                                    </select>--%>
<%--                                </div>--%>

<%--                                <button class="btn btn-warning">Update</button>--%>
<%--                            </form>--%>
<%--                        </div>--%>

<%--                        &lt;%&ndash;create&ndash;%&gt;--%>
<%--                        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">--%>
<%--                            <form action="/bill/create_bill" method="post">--%>
<%--                                <div class="form-group">--%>
<%--                                    Price: <input type="number" name="price" class="form-control">--%>
<%--                                </div>--%>


<%--                                <div class="form-group">--%>
<%--                                    Address: <input type="text" name="address" class="form-control">--%>
<%--                                </div>--%>

<%--                                <div>--%>
<%--                                    Customer:--%>
<%--                                    <select class="form-select" name="customerId" aria-label="Default select example">--%>
<%--                                        <c:forEach items="${listCustomer}" var="lc">--%>
<%--                                            <option value="${lc.id}">${lc.fullname}</option>--%>
<%--                                        </c:forEach>--%>
<%--                                    </select>--%>
<%--                                </div>--%>

<%--                                <div>--%>
<%--                                    Employee--%>
<%--                                    <select class="form-select" name="employeeId" aria-label="Default select example">--%>
<%--                                        <c:forEach items="${listEmployee}" var="lem">--%>
<%--                                            <option value="${lem.id}">${lem.fullName}</option>--%>
<%--                                        </c:forEach>--%>
<%--                                    </select>--%>
<%--                                </div>--%>

<%--                                <button class="btn btn-success">Add new</button>--%>
<%--                            </form>--%>
<%--                        </div>--%>
<%--                        &lt;%&ndash;detail&ndash;%&gt;--%>
<%--                        <div class="tab-pane fade show active" id="contact" role="tabpanel"--%>
<%--                             aria-labelledby="contact-tab">--%>
<%--                            <form class="row g-3" action="/bill/index/${bill.id}" method="get">--%>
<%--                                <table class="table table-borderless">--%>
<%--                                    <tr>--%>
<%--                                        &lt;%&ndash;                                        <td>Id</td>&ndash;%&gt;--%>
<%--                                        <td>Price</td>--%>
<%--                                        <td>Quantity</td>--%>
<%--                                        <td>Product</td>--%>

<%--                                    </tr>--%>
<%--                                    <c:forEach items="${billDetailD}" var="billDetailD">--%>
<%--                                        <tr>--%>
<%--                                                &lt;%&ndash;                                            <td>${bill.id}</td>&ndash;%&gt;--%>
<%--                                            <td>${billDetailD.price}</td>--%>
<%--                                            <td>${billDetailD.quantity}</td>--%>
<%--                                            <td>${billDetailD.product.name}</td>--%>
<%--                                        </tr>--%>
<%--                                    </c:forEach>--%>
<%--                                </table>--%>
<%--                                <h3>Tổng tiền khách phải trả : <fmt:formatNumber value="${allPrice}" type="currency"--%>
<%--                                                                                 currencyCode="VND"/></h3>--%>
<%--                            </form><!-- End Multi Columns Form -->--%>
<%--                        </div>--%>
<%--                    </div><!-- End Default Tabs -->--%>


<%--                </div>--%>

<%--            </div>--%>
<%--        </div><!-- End Recent Activity -->--%>


    </div><!-- End Right side columns -->

    </div>

    <script>
        function goToPage(url) {
            window.location.href = url;
        }
    </script>
</section>



