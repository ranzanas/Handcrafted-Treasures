<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/home.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" />
</head>
<body>
<jsp:include page="header.jsp" />

<c:if test="${not empty sessionScope.message}">
  <div class="alert">
    ${sessionScope.message}
  </div>
  <c:remove var="message" scope="session" />
</c:if>

<section id="main">
  <h4>Naturally Unique, Authentically Yours</h4>
  <h2>Super value deals</h2>
  <h1>Explore a rich heritage of craftsmanship,</h1>
  <p>
    Home to exquisite handmade items and authentic traditional artifacts!
  </p>
  <form action="${pageContext.request.contextPath}/shop" method="get">
    <button class="btn">Shop Now</button>
  </form>
</section>

<section id="feature" class="section-p1">
  <div class="fe-box">
    <img src="${pageContext.request.contextPath}/resources/img/features/f1.png" alt="Free Shipping" />
    <h6>Free Shipping</h6>
  </div>
  <div class="fe-box">
    <img src="${pageContext.request.contextPath}/resources/img/features/f2.png" alt="" />
    <h6>Online Order</h6>
  </div>
  <div class="fe-box">
    <img src="${pageContext.request.contextPath}/resources/img/features/f3.png" alt="" />
    <h6>Save Money</h6>
  </div>
  <div class="fe-box">
    <img src="${pageContext.request.contextPath}/resources/img/features/f4.png" alt="" />
    <h6>Promotions</h6>
  </div>
  <div class="fe-box">
    <img src="${pageContext.request.contextPath}/resources/img/features/f5.png" alt="Happy Sell" />
    <h6>Happy Sell</h6>
  </div>
  <div class="fe-box">
    <img src="${pageContext.request.contextPath}/resources/img/features/f6.png" alt="" />
    <h6>24/7 Support</h6>
  </div>
</section>

<section id="product1" class="section-p1">
  <h2>Featured Products</h2>
  <p>New Designs, Timeless Hands</p>
  <div class="pro-container">
    <c:forEach var="product" items="${productList}" varStatus="status">
      <c:if test="${status.index < 8}">
        <div class="pro">
          <img class="shirt" src="${pageContext.request.contextPath}/resources/img/productImg/${product.productImage}" alt="" />
          <div class="des">
            <span>${product.productName}</span>
            <h5>${product.productDescription}</h5>
            <c:choose>
              <c:when test="${product.productQuantity == 0}">
                <p style="color: red; font-weight: bold;">Out of Stock</p>
              </c:when>
              <c:otherwise>
                <p style="color: green;">In Stock</p>
              </c:otherwise>
            </c:choose>
            <div class="star">
              <i class="bx bxs-star"></i>
              <i class="bx bxs-star"></i>
              <i class="bx bxs-star"></i>
              <i class="bx bxs-star"></i>
              <i class="bx bxs-star"></i>
            </div>
            <h4>NPR ${product.productPrice}</h4>
          </div>
          <c:if test="${product.productQuantity > 0}">
            <a href="${pageContext.request.contextPath}/cart?productId=${product.productId}">
              <i class="bx bx-cart cart"></i>
            </a>
          </c:if>
        </div>
      </c:if>
    </c:forEach>
  </div>
</section>

<section id="banner" class="section-m1">
  <h4>Your Destination for Original Craftsmanship</h4>
  <h2>
    Artisanal Wonders <span>Revealing Masters of the Craft</span> -
    Redefining Handicrafts
  </h2>
  <form action="${pageContext.request.contextPath}/shop" method="get">
    <button class="normal">Explore More</button>
  </form>
</section>

<section id="product1" class="section-p1">
  <h2>New Arrivals</h2>
  <p>Summer Collection New Handcrafted Design</p>
  <div class="pro-container">
    <c:forEach var="product" items="${productList}" varStatus="status">
      <c:if test="${status.index >= fn:length(productList) - 8}">
        <div class="pro">
          <img class="shirt" src="${pageContext.request.contextPath}/resources/img/productImg/${product.productImage}" alt="" />
          <div class="des">
            <span>${product.productName}</span>
            <h5>${product.productDescription}</h5>
            <c:choose>
              <c:when test="${product.productQuantity == 0}">
                <p style="color: red; font-weight: bold;">Out of Stock</p>
              </c:when>
              <c:otherwise>
                <p style="color: green;">In Stock</p>
              </c:otherwise>
            </c:choose>
            <div class="star">
              <i class="bx bxs-star"></i>
              <i class="bx bxs-star"></i>
              <i class="bx bxs-star"></i>
              <i class="bx bxs-star"></i>
              <i class="bx bxs-star"></i>
            </div>
            <h4>NPR ${product.productPrice}</h4>
          </div>
          <c:if test="${product.productQuantity > 0}">
            <a href="${pageContext.request.contextPath}/cart?productId=${product.productId}">
              <i class="bx bx-cart cart"></i>
            </a>
          </c:if>
        </div>
      </c:if>
    </c:forEach>
  </div>
</section>

<section id="newsletter" class="section-p1 section-m1">
  <div class="newstext">
    <h4>Sign Up For Newsletters</h4>
    <p>
      Get E-mail updates about our latest shop and
      <span>special offers.</span>
    </p>
  </div>
  <div class="form">
    <input type="text" placeholder="Your email address" />
    <button class="normal">Sign Up</button>
  </div>
</section>

<jsp:include page="footer.jsp" />
</body>
</html>
