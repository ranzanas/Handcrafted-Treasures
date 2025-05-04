<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/adminDashboard.css" />
</head>
<body>
	<div class="sidebar">
    <h2>Dashboard</h2>
    <a href="#">Home</a>
    <a href="#">Orders</a>
    <a href="#">Customers</a>
    <a href="#">Reports</a>
    <a href="#">Settings</a>
  </div>

  <div class="main-content">
    <div class="header">
      <h1>Welcome, Admin</h1>
      <input type="date" />
    </div>

    <div class="cards">
      <div class="card">
        <h3>Total Products</h3>
        <p>120</p>
      </div>
      <div class="card">
        <h3>Orders</h3>
        <p>85</p>
      </div>
      <div class="card">
        <h3>Stock Alerts</h3>
        <p>5</p>
      </div>
      <div class="card">
        <h3>Revenue</h3>
        <p>$15,450</p>
      </div>
    </div>

    <div class="add-product">
      <button>Add Product</button>
    </div>

    <h2 class="section-title">Products</h2>

    <table>
      <thead>
        <tr>
          <th>Product ID</th>
          <th>Product Name</th>
          <th>Stock Status</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>#P1001</td>
          <td>Handmade Vase</td>
          <td class="status in-stock">In Stock</td>
          <td class="action-buttons">
            <button class="edit-btn">Edit</button>
            <button class="delete-btn">Delete</button>
          </td>
        </tr>
        <tr>
          <td>#P1002</td>
          <td>Wooden Sculpture</td>
          <td class="status out-of-stock">Out of Stock</td>
          <td class="action-buttons">
            <button class="edit-btn">Edit</button>
            <button class="delete-btn">Delete</button>
          </td>
        </tr>
        <tr>
          <td>#P1003</td>
          <td>Clay Pot</td>
          <td class="status in-stock">In Stock</td>
          <td class="action-buttons">
            <button class="edit-btn">Edit</button>
            <button class="delete-btn">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</body>
</html>