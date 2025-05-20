<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Cart</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cart.css" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css" />
  <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" />
</head>
<body>
  <jsp:include page="header.jsp" />

  <c:if test="${not empty sessionScope.message}">
    <div class="alert">${sessionScope.message}</div>
    <c:remove var="message" scope="session" />
  </c:if>

  <section id="cart" class="section-p1">
    <form action="${pageContext.request.contextPath}/cart" method="get">
      <table width="100%">
		  <thead>
		    <tr>
		      <td>Remove</td>
		      <td>Image</td>
		      <td>Product</td>
		      <td>Price</td>
		      <td>Quantity</td>
		      <td>Sub Total</td>
		    </tr>
		  </thead>
		  <tbody>
		    <c:set var="cartTotal" value="0" />
		    <c:forEach var="item" items="${cartItems}">
		      <c:set var="subtotal" value="${item.productPrice * item.cartProductQuantity}" />
		      <c:set var="cartTotal" value="${cartTotal + subtotal}" />
		
		      <tr>
		        <!-- Remove link -->
		        <td>
		          <a href="${pageContext.request.contextPath}/cart?action=remove&cartId=${item.cartId}">
		            <i class='bx bx-x-circle' style="color:red; cursor:pointer;"></i>
		          </a>
		        </td>
		
		        <!-- Product Image -->
		        <td>
		          <img src="${pageContext.request.contextPath}/resources/img/productImg/${item.productImage}" width="70" />
		        </td>
		
		        <!-- Product Name -->
		        <td>${item.productName}</td>
		
		        <!-- Price -->
		        <td>NPR ${item.productPrice}</td>
		
		        <!-- Quantity Update Buttons -->
		        <td>
		          <div style="display: flex; align-items: center; gap: 5px; justify-content:center">
		            <!-- Minus button (only if quantity > 1) -->
		            <c:if test="${item.cartProductQuantity > 1}">
		              <a href="${pageContext.request.contextPath}/cart?action=update&cartId=${item.cartId}&quantity=${item.cartProductQuantity - 1}">
		                <i class='bx bx-minus-circle' style="font-size: 20px; color: #333;"></i>
		              </a>
		            </c:if>
		
		            <!-- Display current quantity -->
		            <input type="text" value="${item.cartProductQuantity}" readonly style="width: 40px; text-align: center;" />
		
		            <!-- Plus button -->
		            <a href="${pageContext.request.contextPath}/cart?action=update&cartId=${item.cartId}&quantity=${item.cartProductQuantity + 1}">
		              <i class='bx bx-plus-circle' style="font-size: 20px; color: #333;"></i>
		            </a>
		          </div>
		        </td>
		
		        <!-- Subtotal -->
		        <td>NPR ${subtotal}</td>
		      </tr>
		    </c:forEach>
		  </tbody>
		</table>

    </form>
  </section>

  <section id="cart-add" class="section-p1">
    <div id="subtotal">
      <h3>Cart Total</h3>
      <table>
        <tr>
          <td>Cart Subtotal</td>
          <td>NPR ${cartTotal}</td>
        </tr>
        <tr>
          <td>Shipping</td>
          <td>Free</td>
        </tr>
        <tr>
          <td><strong>Total</strong></td>
          <td><strong>NPR ${cartTotal}</strong></td>
        </tr>
      </table>
      <form action="${pageContext.request.contextPath}/order" method="get">
  		<button class="normal" type="submit">Place Order</button>
	 </form>
    </div>
  </section>

  <jsp:include page="footer.jsp" />
</body>
</html>
