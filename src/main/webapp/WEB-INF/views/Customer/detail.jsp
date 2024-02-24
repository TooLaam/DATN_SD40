

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="section dashboard">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <form action="/customer/update" method="post">
                        <nav>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item active"><h1>Info Customer</h1></li>
                                <li style="margin-top: 20px" class="breadcrumb-item"><a href="/customer/index">Home</a></li>
                            </ol>
                        </nav>

                            <div class="row" style="margin-top: 50px;">
                                <div class="col-md-6">
                                    <b><label class="form-label">ID:</label></b>
                                    <input type="text" class="form-control" name="id" value="${cus.id}" readonly />

                                    <b><label class="form-label">Full Name:</label></b>
                                    <input type="text" class="form-control" value="${cus.fullname}" name="fullname" />
                                    <c:if test="${errName != null}" >
                                        <p style="color: red">${errName}</p>
                                    </c:if>

                                    <b> <label class="form-label">Date Of Birth</label></b>
                                    <input type="date" class="form-control" value="${cus.dateofbirth}" name="dateofbirth" />
                                    <c:if test="${errDate != null}" >
                                        <p style="color: red">${errDate}</p>
                                    </c:if>
                                    <c:if test="${errDateAfter != null}" >
                                        <p style="color: red">${errDateAfter}</p>
                                    </c:if>

                                    <b> <label class="form-label">Address</label></b>
                                    <input type="text" class="form-control"  value="${cus.address}" name="address" />
                                    <c:if test="${errAdd != null}" >
                                        <p style="color: red">${errAdd}</p>
                                    </c:if>

                                    <b><label class="form-label">Phone Number</label></b>
                                    <input type="number" class="form-control" value="${cus.phone}" name="phone" />
                                    <c:if test="${errPhone != null}" >
                                        <p style="color: red">${errPhone}</p>
                                    </c:if>
                                    <c:if test="${errPhoneErrr != null}" >
                                        <p style="color: red">${errPhoneErrr}</p>
                                    </c:if>

                                    <b><label class="form-label">Date created: </label></b>
                                    <label  name="datecreated">${cus.datecreated}</label><br>
                                </div>

                                <div class="col-md-6">
                                    <b><label class="form-label">Gender: </label></b>
                                    <label class="form-label">Male</label>
                                    <input class="form-check-input" type="radio" checked value="1" name="gender" >

                                    <label class="form-label">Female</label>
                                    <input class="form-check-input" type="radio" value="2" name="gender" ><br>

                                    <b><label class="form-label">Email</label></b>
                                    <input type="email" class="form-control" value="${cus.email}" name="email" />
                                    <c:if test="${errEmail != null}" >
                                        <p style="color: red">${errEmail}</p>
                                    </c:if>


                                    <b><label class="form-label">Username</label></b>
                                    <input type="text" class="form-control" value="${cus.username}" name="username" />
                                    <c:if test="${errUser != null}" >
                                        <p style="color: red">${errUser}</p>
                                    </c:if>
                                    <c:if test="${errUserTrung != null}" >
                                        <p style="color: red">${errUserTrung}</p>
                                    </c:if>

                                    <b><label class="form-label">Password</label></b>
                                    <input type="password" class="form-control" value="${cus.password}" name="password" />
                                    <c:if test="${errPass != null}" >
                                        <p style="color: red">${errPass}</p>
                                    </c:if>


                                    <b><label class="form-label">Status</label></b>
                                    <select class="form-select" aria-label="Default select example" name="status">
                                        <option value="${cus.status}">${cus.status==1?"ON":"OFF"} </option>
                                        <option value="1">ON</option>
                                        <option value="2">OFF</option>

                                    </select>
                                </div>
                            </div>



                        <button type="submit" class="btn btn-primary">Edit</button>
                    </form>

                </div>

            </div>
        </div>
    </div>

</section>






