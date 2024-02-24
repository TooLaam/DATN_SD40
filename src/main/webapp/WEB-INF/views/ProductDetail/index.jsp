<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>Product</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
            <li class="breadcrumb-item active">Overview</li>
            <li class="breadcrumb-item active">Product</li>
        </ol>
    </nav>
</div>
<!-- End Page Title -->

<section className="section dashboard">
    <div className="row">


        <div className="col-lg-10">


            <div className="card">



                <div class="card-body">
                            <h5 class="card-title">Product <span>| </span></h5>

                            <table class="table table-borderless datatable">
                                <thead>
                                <tr>
                                    <%--                                    <th>ImportPrice</th>--%>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>CreateDate</th>
                                    <th>Status</th>
                                    <th>Product</th>
                                    <th>Color</th>
                                    <th>Image</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                              <tbody>
                              <c:forEach items="${listProductDetail}" var="detailSP">
                                  <tr>

                                          <%--                                        <td>${detailSP.importPrice}</td>--%>
                                      <td>${detailSP.price}</td>
                                      <td>${detailSP.quantity}</td>
                                      <td>${detailSP.createdDate}</td>
                                      <td>${detailSP.status == 0 ? "Còn hàng" : "Hết hàng"}</td>
                                      <td>${detailSP.product.name}</td>
                                <td>
<%--                       <td>${detailSP.color.name}&ndash;%&gt;--%>
                                          <img src="/assets/img/color/${detailSP.color.image}" height="100px" width="100px"></td>
                                      <td>
                                          <c:forEach items="${detailSP.listImages}" var="image" varStatus="loop">
                                              <c:if test="${loop.index == 0}">
                                                  <img src="/assets/img/product/${image.name}" height="100px" width="100px">
                                              </c:if>
                                          </c:forEach>
                                      </td>
                                      <td>
                                          <a href="/product_detail/delete/${detailSP.id}" class="btn btn-danger" onclick="return confirm('Bạn chắc chắn có muốn xóa??')" style="text-decoration: none;color: white">Delete</a>
                                          <a href="/product_detail/detail/${detailSP.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Detail</a>
                                      </td>
                                  </tr>
                              </c:forEach>
                              </tbody>

                            </table>
                            <a href="/product_detail/create" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Thêm sản phẩm</a>
                            <%--                            <form method="post" enctype="multipart/form-data" action="import">--%>
                            <%--                                Thêm từ file excel: <input class="form-control" name="file" type="file">--%>
                            <%--                                <button>Thêm</button>--%>
                            <%--                            </form>--%>
                        </div>

                    </div>


                </div><!-- End Recent Sales -->

            </div>

        </div><!-- End Left side columns -->



    </div>
</section>






