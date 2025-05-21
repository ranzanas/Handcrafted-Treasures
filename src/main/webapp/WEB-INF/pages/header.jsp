<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="header">
	    <div class="topHeader">
        <ul>
            <c:if test="${empty sessionScope.username}">
                <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                <li><a href="${pageContext.request.contextPath}/Registration">Sign Up</a></li>
              
            </c:if>
        </ul>
      </div>
      <div class="bottomHeader">
	      <h2
	        style="
	          color: #222;
	          font-size: 30px;
	          font-style: italic;
	          font-family: 'Brush Script MT', cursive;
	          letter-spacing: 2px;
	        "
	      >
	        Handcrafted Treasures
	      </h2>
	      <div class="navigationBar">
	        <ul id="navbar">
	          <li><a class="active" href="${pageContext.request.contextPath}/home">Home</a></li>
	          <li><a href="${pageContext.request.contextPath}/shop">Shop</a></li>
	          <li><a href="${pageContext.request.contextPath}/blog">Blog</a></li>
	          <li><a href="${pageContext.request.contextPath}/about">About</a></li>
	          <li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
	        </ul>
	      </div>
	      <c:if test="${not empty sessionScope.username}">
            	<div class="userCart">
                <a href="${pageContext.request.contextPath}/cart">
                    <i class="fa-solid fa-cart-shopping icons"></i>
                </a>
                <div class="user-dropdown">
                    <i class="fa-regular fa-circle-user icons"></i>
                    <div class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/userProfile">Visit Profile</a>
                        <a href="${pageContext.request.contextPath}/LogOutController">Logout</a>
                        <a href="${pageContext.request.contextPath}/orderHistory">Order History</a>
                    </div>
                </div>
            </div>
        </c:if>
      </div>
      
    </header>