<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>User List</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminDashboard.css" />
  <link href="https://fonts.googleapis.com/css2?family=Spartan:wght@300;400;600;700&display=swap" rel="stylesheet"/>
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
        <h1>Registered Users</h1>
      </header>

      <section class="cards">
        <div class="card"><h3>Total Users</h3><p>${userCount}</p></div>
        <div class="card"><h3>Total Orders</h3><p>${orderCount}</p></div>
        <div class="card"><h3>Total Products</h3><p>${productCount}</p></div>
        <div class="card"><h3>Total Revenue</h3><p>${totalRevenue}</p></div>
      </section>

      <section class="product-table">
        <h2>User List</h2>
        <table>
          <thead>
            <tr>
              <th>User ID</th>
              <th>Full Name</th>
              <th>Username</th>
              <th>Email</th>
              <th>Phone</th>
              <th>Address</th>
              <th>DOB</th>
              <th>Feedback</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="user" items="${users}">
              <tr>
                <td>${user.id}</td>
                <td>${user.fullName}</td>
                <td>${user.userName}</td>
                <td>${user.email}</td>
                <td>${user.number}</td>
                <td>${user.address}</td>
                <td>${user.dob}</td>
                <td>${user.tempFeedback != null ? user.tempFeedback : '-'}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </section>
    </main>
  </div>
</body>
</html>
