<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>

	<c:if test="${param.msg == 'deleted'}">
  		<div class="successMessage">Your profile has been deleted successfully.</div>
	</c:if>
	
  <header>
    <h1>Handcrafted Treasures</h1>
  </header>

  <div class="container">
    <div class="form-box">
      <h2>Login to Your Account</h2>
      
     <c:if test="${not empty error}">
        <div class="error-message" style="color: red; margin-bottom: 10px;">
          ${error}
        </div>
      </c:if>
      <form action="${pageContext.request.contextPath}/login" method="POST">
        <div class="input-group">
          <label for="email">Username</label>
          <input type="text" id="username" name="username" required>
        </div>
        <div class="input-group">
          <label for="password">Password</label>
          <input type="password" id="password" name="password" required>
        </div>
        <button type="submit" class="btn">Login</button>
       <p class="switch-form">Don't have an account? <a href="${pageContext.request.contextPath}/Registration">Register</a></p>

      </form>
    </div>
  </div>
</body>
</html>