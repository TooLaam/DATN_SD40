
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="section dashboard">
    <div class="row">

        <div class="card">
            <div class="card-body">
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item active"><h1>Thay đổi số lượng</h1></li>
                        <li style="margin-top: 20px" class="breadcrumb-item"><a href="/bill/detail/${id}">Quay lại</a></li>
                    </ol>
                </nav>
                <div class="row" style="margin-top: 50px;">
                    <div class="product-details">
                        <div class="col-md-7">
                            <form action="/bill/update/${id}" method="post">
                                <div class="product-info">
                                    <div class="mb-3">
                                        <label for="exampleFormControlInput1" class="form-label">Số lượng</label>
                                        <input type="number" class="form-control" id="exampleFormControlInput1" name="quantity" value="${quantity}">
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





