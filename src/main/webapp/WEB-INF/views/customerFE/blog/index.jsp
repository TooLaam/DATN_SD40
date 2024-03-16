<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
  <style><%@include file="style.css"%></style>
</head>

<br />
    <div class="container">
      <div class="row">
        <div class="col-3">
          <h6 class="filter">Recent Post</h6>
          <ul class="list-unstyled">
            <li>
              <a href="/blogPage1">Latest Black Rouge Lipstick Color Palette 2022 - 2023</a>
            </li>
            <hr>
            <li>
              <a href="/blogPage2">Latest Romand Lipstick Color Palette 2022 - 2023</a>
            </li>
            <hr>
            <li>
              <a href="/blogPage5">Where to Buy Genuine Bbia Lipstick? Which Color Is The Best Seller?</a>
            </li>
          </ul>
          <h6 class="filter">Most liked post</h6>
          <ul class="list-unstyled">
            <li>
              <a href="/blogPage2">Latest Romand Lipstick Color Palette 2022 - 2023</a>
            </li>
            <li>
              <a href="/blogPage3">Latest Bbia Lipstick Color Palette 2022 - 2023</a>
            </li>
            <li>
              <a href="/blogPage4">Latest Merzy Lipstick Color Palette 2022 - 2023</a>
            </li>
          </ul>
        </div>
        <div class="col-9">
          <div class="post">
            <div class="thumnail">
              <a href="/blogPage1">
                <img src="/assets/img/posts/post1.jpeg" alt="" />
                <div class="caption">
                  <span class="title">Latest Black Rouge Lipstick Color Palette 2022 - 2023</span>
                  <span class="content">The Newest, Hottest, Most Complete Black Rouge Lipstick Color Palette 2022 - 2023 [...]</span>
                </div>
              </a>
            </div>
          </div>
          <div class="post">
            <div class="thumnail">
              <a href="/blogPage2">
                <img src="/assets/img/posts/post2.jpeg" alt="" />
                <div class="caption">
                  <span class="title">Latest Romand Lipstick Color Palette 2022 - 2023</span>
                  <span class="content">The most beautiful, hottest, and most complete Romand lipstick color palette today Romand makes [...]</span>
                </div>
              </a>
            </div>
          </div>
          <div class="post">
            <div class="thumnail">
              <a href="/blogPage3">
                <img src="/assets/img/posts/post3.jpeg" alt="" />
                <div class="caption">
                  <span class="title">Latest Bbia Lipstick Color Palette 2022 - 2023</span>
                  <span class="content">Genuine Bbia Lipstick Color Palette, Latest 2022 - 2023 Korean lipstick fan [...]</span>
                </div>
              </a>
            </div>
          </div>
          <div class="post">
            <div class="thumnail">
              <a href="/blogPage4">
                <img src="/assets/img/posts/post4.jpeg" alt="" />
                <div class="caption">
                  <span class="title">Latest Merzy Lipstick Color Palette 2022 - 2023</span>
                  <span class="content">Merzy Lipstick Color Palette Full, Hottest 2022 - 2023 Merzy is a brand [...]</span>
                </div>
              </a>
            </div>
          </div>
          <div class="post">
            <div class="thumnail">
              <a href="/blogPage5">
                <img src="/assets/img/posts/post5.jpeg" alt="" />
                <div class="caption">
                  <span class="title">Where to Buy Genuine Bbia Lipstick? Which Color Is The Best Seller?</span>
                  <span class="content">Where to Buy Genuine and Reputable Bbia Lipstick in Hanoi and Ho Chi Minh City? BBIA is a [...]</span>
                </div>
              </a>
            </div>
          </div>
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