<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirm Order</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/order.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
<link
    href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap"
    rel="stylesheet"/>
<link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
<link
    rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"/>
</head>
<body>
	<jsp:include page="header.jsp" />

	<c:if test="${not empty sessionScope.message}">
	  <div class="alert">${sessionScope.message}</div>
	  <c:remove var="message" scope="session" />
	</c:if>	

	<section id="confirm-order">
	  <h2>Confirm Your Order</h2>
	  
	  <form action="${pageContext.request.contextPath}/order" method="post">
	    <div class="form-group">
	      <label>Order Quantity:</label>
	      <input type="text" value="${orderQuantity}" readonly />
	      <input type="hidden" name="orderQuantity" value="${orderQuantity}" />
	    </div>
	
		<div class="form-group">
		  <label>Order Date:</label>
		  <input type="text" value="${today}" readonly />
		  <input type="hidden" name="orderDate" value="${today}" />
		</div>

	
	    <div class="form-group">
	      <label>Total Amount (NPR):</label>
	      <input type="text" value="${totalAmount}" readonly />
	      <input type="hidden" name="totalAmount" value="${totalAmount}" />
	    </div>
	
	    <div class="form-group">
	      <label for="deliveryAddress">Delivery Address:</label>
	      <textarea name="deliveryAddress" id="deliveryAddress" rows="4" required></textarea>
	    </div>
	
	    <div class="form-group">
	      <label for="paymentMethod">Payment Method:</label>
	      <select name="paymentMethod" id="paymentMethod" required>
	        <option value="">-- Select --</option>
	        <option value="Cash on Delivery">Cash on Delivery</option>
	        <option value="Card">Card</option>
	        <option value="Bank Transfer">Bank Transfer</option>
	      </select>
	    </div>
	
	    <button type="submit" class="normal">Confirm Order</button>
	  </form>
	</section>

<jsp:include page="footer.jsp" />
</body>
</html>