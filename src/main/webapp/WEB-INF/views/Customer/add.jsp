


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="section dashboard">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <form action="/customer/add" method="post">
                        <nav>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item active"><h1>Create Customer</h1></li>
                                <li style="margin-top: 20px" class="breadcrumb-item"><a href="/customer/index">Home</a></li>
                            </ol>
                        </nav>

                            <div class="row" style="margin-top: 50px;">
                                <div class="col-md-6">

                                    <b><label class="form-label">Full Name:</label></b>
                                    <input type="text" value="${fullnameAdd}" class="form-control"  name="fullname" />
                                    <c:if test="${errName != null}" >
                                        <p style="color: red">${errName}</p>
                                    </c:if>

                                    <b> <label class="form-label">Date Of Birth</label></b>
                                    <input type="date" class="form-control" value="${dateOfBirthAdd}"  name="dateofbirth" />
                                    <c:if test="${errDate != null}" >
                                        <p style="color: red">${errDate}</p>
                                    </c:if>
                                    <c:if test="${errDateAfter != null}" >
                                        <p style="color: red">${errDateAfter}</p>
                                    </c:if>

                                    <b> <label class="form-label">Address</label></b>
                                    <input type="text" class="form-control" value="${addressAdd}"  name="address" />
                                    <c:if test="${errAdd != null}" >
                                        <p style="color: red">${errAdd}</p>
                                    </c:if>

                                    <b><label class="form-label">Phone Number</label></b>
                                    <input type="number" class="form-control" value="${phoneNumberAdd}" name="phone" />
                                    <c:if test="${errPhone != null}" >
                                        <p style="color: red">${errPhone}</p>
                                    </c:if>
                                    <c:if test="${errPhoneErrr != null}" >
                                        <p style="color: red">${errPhoneErrr}</p>
                                    </c:if>


                                </div>

                                <div class="col-md-6">
                                    <b><label class="form-label">Gender</label></b>
                                    <label class="form-label">Male</label>
                                    <input class="form-check-input" type="radio" checked value="1" name="gender" >

                                    <label class="form-label">Female</label>
                                    <input class="form-check-input" type="radio" value="2" name="gender" ><br>

                                    <b><label class="form-label">Email</label></b>
                                    <input type="email" class="form-control" value="${emailAdd}" name="email" />
                                    <c:if test="${errEmail != null}" >
                                        <p style="color: red">${errEmail}</p>
                                    </c:if>

                                    <b><label class="form-label">Username</label></b>
                                    <input type="text" class="form-control" value="${usernameAdd}" name="username" />
                                    <c:if test="${errUser != null}" >
                                        <p style="color: red">${errUser}</p>
                                    </c:if>
                                    <c:if test="${errUserTrung != null}" >
                                        <p style="color: red">${errUserTrung}</p>
                                    </c:if>

                                    <b><label class="form-label">Password</label></b>
                                    <input type="password" class="form-control" value="${passwordAdd}" name="password" />
                                    <c:if test="${errPass != null}" >
                                        <p style="color: red">${errPass}</p>
                                    </c:if>

                                </div>
                            </div>



                        <button type="submit" class="btn btn-primary">ADD</button>
                    </form>

                </div>

            </div>
        </div>
    </div>

</section>











