<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
  <style><%@include file="style.css"%></style>
</head>

<br />
    <div class="container">
      <div class="row">
        <div class="col-3">
          <h6 class="filter">Bài đăng gần đây</h6>
          <ul class="list-unstyled">
            <li>
              <a href="/blogPage1">Sneaker News: Khám Phá Sâu Sắc Thế Giới Sneaker</a>
            </li>
            <hr>
            <li>
              <a href="/blogPage2">Highsnobiety: Nền Tảng Đa Dạng Về Thời Trang Và Sneaker</a>
            </li>
            <hr>
            <li>
              <a href="/blogPage3">Hypebeast: Trải Nghiệm Đẳng Cấp Với Thế Giới Sneaker</a>
            </li>
          </ul>
          <h6 class="filter">Bài đăng được yêu thích nhất</h6>
          <ul class="list-unstyled">
            <li>
              <a href="/blogPage4">Complex Sneakers: Khám Phá Sự Đa Dạng Của Thế Giới Sneaker</a>
            </li>
            <li>
              <a href="/blogPage5">Sneaker Freaker: Khám Phá Đẳng Cấp Trong Thế Giới Sneaker</a>
            </li>
            <li>
              <a href="/blogPage1">Sneaker News: Khám Phá Sâu Sắc Thế Giới Sneaker</a>
            </li>
          </ul>
        </div>
        <div class="col-9">
          <div class="content">
            <h3 class="title">Complex Sneakers: Khám Phá Sự Đa Dạng Của Thế Giới Sneaker</h3>
            <img src="/assets/img/posts/th (30).jpg" width="100%" alt="" />

            <p>Complex Sneakers là một phần của Complex Media, một nền tảng truyền thông về thời trang và văn hóa đường phố. Với một sự kết hợp độc đáo giữa tin tức, đánh giá sản phẩm và phân tích sâu sắc, Complex Sneakers là một điểm đến không thể bỏ qua cho những ai yêu thích giày thể thao.</p>

            <h3>Tin Tức & Sự Kiện</h3>
            <img src="/assets/img/posts/th (20).jpg" width="100%" alt="" />
            <p><a href="#">Sneaker News cung cấp thông tin liên tục về các sự kiện, triển lãm,
              và phát hành mới nhất trong ngành công nghiệp sneaker.
              Từ những thông tin chính thức về các bản hợp tác đặc biệt đến những hình ảnh đầu tiên về các mẫu giày sắp ra mắt,
              bạn sẽ luôn cập nhật với những thông tin nóng hổi từ Sneaker News.</a> </p>
            <img src="/assets/img/posts/th (21).jpg" width="100%" alt="" />
            <h4>Đánh Giá Sản Phẩm</h4>
            <p><strong><a href="#">Để giúp bạn đưa ra quyết định mua hàng thông minh, Sneaker News cung cấp những bài đánh giá chất lượng và chi tiết về các mẫu giày mới nhất trên thị trường. Từ việc kiểm tra chất lượng đến đánh giá về thoải mái và hiệu suất, mỗi bài đánh giá của Sneaker News đều giúp bạn hiểu rõ hơn về sản phẩm trước khi quyết định mua.</a></strong></p>
            <img src="/assets/img/posts/th (22).jpg" width="100%" alt="" />
            <h4>Phân Tích Sâu Sắc</h4>
            <p><strong><a href="#">Bên cạnh việc cung cấp thông tin và đánh giá, Sneaker News cũng là nơi để bạn tìm hiểu sâu hơn về lịch sử và những câu chuyện đằng sau từng đôi giày. Từ nguồn gốc của các biểu tượng sneaker đến những câu chuyện về những bản hợp tác đặc biệt, phần phân tích của Sneaker News là nguồn thông tin hữu ích cho cả người mới bắt đầu và người hâm mộ nhiệt huyết của thế giới sneaker.
            </a></strong></p>
            <img src="/assets/img/posts/th (23).jpg" width="100%" alt="" />
            <p><strong><a href="#">Với một sự kết hợp độc đáo của tin tức, đánh giá, và phân tích sâu sắc, Sneaker News không chỉ là một blog thông tin, mà còn là một cộng đồng cho những ai đam mê văn hóa sneaker.</a></strong></p>
            <img src="/assets/img/posts/th (24).jpg" width="100%" alt="" />
          </div>
          <br>
        </div>
      </div>
    </div>
<script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
        intent="WELCOME"
        chat-title="ChatBot"
        agent-id="c9aa1141-ab88-427a-9131-9140328e202b"
        language-code="vi"
></df-messenger>