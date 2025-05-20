<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard</title>
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
        <h1>Welcome, Admin</h1>
      </header>

      <section class="cards">
        <div class="card">
          <h3>Total Users</h3>
          <p>${userCount}</p>
        </div>
        <div class="card">
          <h3>Total Orders</h3>
          <p>${orderCount}</p>
        </div>
        <div class="card">
          <h3>Total Products</h3>
          <p>${productCount}</p>

        </div>
        <div class="card">
          <h3>Total Revenue</h3>
          <p>${totalRevenue}</p>
        </div>
      </section>


       
     <form action="${pageContext.request.contextPath}/addProduct" method="get">
    	<button type="submit" class="btn submit">Go to Add Product Page</button>
  	</form> 


      <section class="product-table">
        <h2>Product List</h2>
        <table>
          <thead>
            <tr>
              <th>Image</th>
              <th>Product ID</th>
              <th>Name</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Status</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="product" items="${productList}">
              <tr>
                <td>
                  <img class="product-img"
                       src="${pageContext.request.contextPath}/resources/img/productImg/${product.productImage}"
                       alt="${product.productName}" />
                </td>
                <td>${product.productId}</td>
                <td>${product.productName}</td>
                
                <td>NPR ${product.productPrice}</td>
                <td>${product.productQuantity}</td>
                <td>${product.productStatus}</td>
                <td>
                  <button class="btn edit">Edit</button>
                  <button class="btn delete">Delete</button>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </section>
    </main>
  </div>
</body>
</html>
