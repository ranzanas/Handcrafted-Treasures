<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Order List</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminDashboard.css" />
</head>
<body>
  <div class="container">
    <aside class="sidebar">
      <h2 class="logo">Handcrafted Admin</h2>
      <nav>
        <ul>
          <li><a href="${pageContext.request.contextPath}/adminDashboard">Dashboard</a></li>
          <li><a href="${pageContext.request.contextPath}/orderList">Orders</a></li>
          <li><a href="${pageContext.request.contextPath}/userList">Users</a></li>
          <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
        </ul>
      </nav>
    </aside>

    <main class="main">
      <header class="header">
        <h1>Order List</h1>
      </header>

      <section class="cards">
        <div class="card"><h3>Total Users</h3><p>${userCount}</p></div>
        <div class="card"><h3>Total Orders</h3><p>${orderCount}</p></div>
        <div class="card"><h3>Total Products</h3><p>${productCount}</p></div>
        <div class="card"><h3>Total Revenue</h3><p>NPR ${totalRevenue}</p></div>
      </section>

      <section class="product-table">
        <h2>All Orders</h2>
        <table>
          <thead>
            <tr>
              <th>Order ID</th>
              <th>User</th>
              <th>Product</th>
              <th>Qty</th>
              <th>Amount</th>
              <th>Payment</th>
              <th>Date</th>
              <th>Address</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="order" items="${orderList}">
              <tr>
                <td>${order.orderId}</td>
                <td>${order.userName}</td>
                <td>${order.productName}</td>
                <td>${order.orderQuantity}</td>
                <td> ${order.totalAmount}</td>
                <td>${order.paymentMethod}</td>
                <td>${order.orderDate}</td>
                <td>${order.deliveryAddress}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </section>
    </main>
  </div>
</body>
</html>
