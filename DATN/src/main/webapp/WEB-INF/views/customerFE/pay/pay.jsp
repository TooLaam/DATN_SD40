<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style>
        <%@include file="style.css" %>
    </style>

</head>

<br/>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h2>Payment Information</h2>
            <%--            <div class="error">--%>
            <%--                <c:if test="${not empty receiverNameError}">--%>
            <%--                    <p class="text-danger">${receiverNameError}</p>--%>
            <%--                </c:if>--%>
            <%--                <c:if test="${not empty customerPhoneError}">--%>
            <%--                    <p class="text-danger">${customerPhoneError}</p>--%>
            <%--                </c:if>--%>
            <%--                <c:if test="${not empty addressDeliveryError}">--%>
            <%--                    <p class="text-danger">${addressDeliveryError}</p>--%>
            <%--                </c:if>--%>
            <%--            </div>--%>
            <form method="post" action="/bill/placeorder">
                <div class="form-group">
                    <label for="receiverName">Receiver Name:</label>
                    <%--<input type="text" class="form-control" value="${inputReceiverName}" id="receiverName"
                           name="receiverName">
                    <c:if test="${not empty receiverNameError}">
                        <div class="text-danger">${receiverNameError}</div>
                    </c:if>--%>
                    <c:choose>
                        <c:when test="${not empty customer}">
                            <input type="text" class="form-control" value="${customer.fullname}" id="receiverName"
                                   name="receiverName">
                            <c:if test="${not empty receiverNameError}">
                                <div class="text-danger">${receiverNameError}</div>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" value="${inputReceiverName}" id="receiverName"
                                   name="receiverName">
                            <c:if test="${not empty receiverNameError}">
                                <div class="text-danger">${receiverNameError}</div>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="form-group">
                    <label for="customerPhone">Customer Phone:</label>
                    <c:choose>
                        <c:when test="${not empty customer}">
                            <input type="text" class="form-control" value="${customer.phone}" id="customerPhone"
                                   name="customerPhone">
                            <c:if test="${not empty customerPhoneError}">
                                <p class="text-danger">${customerPhoneError}</p>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" value="${inputCustomerPhone}" id="customerPhone"
                                   name="customerPhone">
                            <c:if test="${not empty customerPhoneError}">
                                <p class="text-danger">${customerPhoneError}</p>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="form-group">
                    <label for="addressDelivery">Address Delivery:</label>
                    <c:choose>
                        <c:when test="${not empty customer}">
                            <textarea type="text" class="form-control" id="addressDelivery"
                                      name="addressDelivery">${customer.address}</textarea>
                            <c:if test="${not empty addressDeliveryError}">
                                <p class="text-danger">${addressDeliveryError}</p>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                    <textarea class="form-control" id="addressDelivery" data-value="${inputReceiverName}"
                              name="addressDelivery"></textarea>
                            <c:if test="${not empty addressDeliveryError}">
                                <p class="text-danger">${addressDeliveryError}</p>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="form-group">
                    <label for="customerPhone">Discount code or gift card:</label>
                    <input type="text" class="form-control" id="code" name="code">
                    <c:if test="${not empty codeError}">
                        <p class="text-danger">${codeError}</p>
                    </c:if>
                </div>
                <div class="form-group">
                    <div class="py-3">
                        <label for="addressDelivery" class="form-label">Payment:</label>
                        <input class="btn-check" value="2f7fbcf3-3007-4180-a5fe-84d2bcdf171b" type="radio"
                               name="PaymentId" id="flexRadioDefault1">
                        <label class="btn" for="flexRadioDefault1">
                            Ship (COD)
                        </label>
                        <input class="btn-check" value="1f7fbcf3-3007-4180-a5fe-84d2bcdf171b" type="radio"
                               name="PaymentId" id="flexRadioDefault2" checked>
                        <label class="btn" for="flexRadioDefault2">
                            Bank transfer
                        </label>
                    </div>
                </div>
                <button type="submit" class="btn">Place Order</button>
            </form>
        </div>
        <div class="col-md-6">
            <h2>Your shopping cart</h2>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cartDetail}" var="gh">
                    <tr>
                        <td>${gh.productDetail.product.name}</td>
                        <td>${gh.productDetail.price}</td>
                        <td>${gh.quantity}</td>
                        <td>${gh.productDetail.price * gh.quantity}VND</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
            <h4>Total: ${cart.totalMoney}</h4>
        </div>
    </div>
</div>
<br/>