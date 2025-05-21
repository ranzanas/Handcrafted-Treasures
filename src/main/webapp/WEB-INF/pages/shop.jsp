<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shop</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/shop.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
<link
     href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
    />
        <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"
    />
</head>
<body>
	<jsp:include page="header.jsp" />
	<c:if test="${not empty sessionScope.message}">
	  	<div class="alert">
	    ${sessionScope.message}
	  </div>
	  <c:remove var="message" scope="session"/>
	</c:if>
	
	<section id="page-header">
            <h2>#From our hands to your heart</h2>
            <p>Handcrafted Beauty Timeless Appeal</p>
            <form action="${pageContext.request.contextPath}/search" method="get" class="search-form">
			  <input type="text" class = "searchbar" name="query" placeholder="Search products..." required />
			  <button type="submit" class = 'search-btn white'>Search</button>
			</form>
            
        </section> 

		<section id="product1" class="section-p1">
		  <div class="pro-container">
		    <c:forEach var="product" items="${productList}">
		      <div class="pro">
		        <img class="shirt" src="${pageContext.request.contextPath}/resources/img/productImg/${product.productImage}" alt="${product.productName}" />
		        <div class="des">
		          <span>${product.productName}</span>
		          <h5>${product.productDescription}</h5>
		          <div class="star">
		            <i class='bx bxs-star'></i>
		            <i class='bx bxs-star'></i>
		            <i class='bx bxs-star'></i>
		            <i class='bx bxs-star'></i>
		            <i class='bx bxs-star'></i>
		          </div>
		          <h4>NPR ${product.productPrice}</h4>
		        </div>
		        <!-- Cart button: make sure your Add to Cart controller accepts ?productId=... -->
		        <a href="${pageContext.request.contextPath}/cart?productId=${product.productId}"><i class='bx bx-cart cart'></i></a>
		      </div>
		    </c:forEach>
		  </div>
		</section>

        <!-- <section id="pagination" class="section-p1">
            <a href="#">1</a>
            <a href="#">2</a>
            <a href="#"><i class='bx bx-right-arrow-alt'></i></a>
        </section>
         -->
        <section id="newsletter" class="section-p1 section-m1">
            <div class="newstext">
                <h4>Sign Up For Newsletters</h4>
                <p>Get E-mail updates about our latest shop and <span>special offers.</span></p>
            </div>
            <div class="form">
                <input type="text" placeholder="Your email address">
                <button class="normal">Sign Up</button>
            </div>
        </section>
        <jsp:include page="footer.jsp" />

</body>
</html>