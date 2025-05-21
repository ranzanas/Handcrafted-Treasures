<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>My Orders</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
<jsp:include page="header.jsp" />

<section id="cart" class="section-p1" style = "height: 500px;">
  <h2 style="text-align:center; margin: 50px;">My Order History</h2>
	<div class= "tableSection">
		  <table width="100%">
		    <thead>
		      <tr>
		        <td>Image</td>
		        <td>Product</td>
		        <td>Quantity</td>
		        <td>Total</td>
		        <td>Payment</td>
		        <td>Date</td>
		        <td>Address</td>
		      </tr>
		    </thead>
		    <tbody>
		      <c:forEach var="order" items="${orderHistory}">
		        <tr>
		          <td><img src="${pageContext.request.contextPath}/resources/img/productImg/${order.productImage}" width="70" /></td>
		          <td>${order.productName}</td>
		          <td>${order.orderQuantity}</td>
		          <td>NPR ${order.totalAmount}</td>
		          <td>${order.paymentMethod}</td>
		          <td>${order.orderDate}</td>
		          <td>${order.deliveryAddress}</td>
		        </tr>
		      </c:forEach>
		    </tbody>
		</table>
	</div>
</section>

<jsp:include page="footer.jsp" />
</body>
</html>
