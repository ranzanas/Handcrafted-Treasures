<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Form</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/registration.css">

</head>
<body>
	<header>
    <h1>Handcrafted Treasures</h1>
  </header>

  <div class="container">
    <div class="form-container">
      <div class="image-container"></div>

      <!-- Registration Form -->
      <div class="form-box" id="register-form">
        <h2>Create Your Account</h2>
        <form class= "formContainer" method = "post" action = "Registration" enctype="multipart/form-data">
          <div class="input-group">
            <label for="username">Full Name</label>
            <input type="text" id="fullname" name="fullName" required>
          </div>
          <div class="input-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
          </div>
          <div class="input-group">
            <label for="phone">Phone Number</label>
            <input type="tel" id="phone" name="phoneNumber" required>
          </div>
          <div class="input-group">
            <label for="dob">Date of Birth</label>
            <input type="date" id="dob" name="dob" required>
          </div>
          <div class="input-group">
            <label for="address">Address</label>
            <textarea id="address" name="address" required></textarea>
          </div>
          <div class="input-group">
            <label for="gender">Gender</label>
			<input type ="text" name = "gender" required>
          </div>
          <div class="input-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required>
          </div>
          <div class="input-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
          </div>
          <div class="input-group">
            <label for="confirm-password">Confirm Password</label>
            <input type="password" id="confirm-password" name="retypePassword" required>
          </div>
          <button type="submit" class="btn">Register</button>
          <p class="switch-form">Already have an account? <a href="login.html">Login</a></p>
        </form>
      </div>
    </div>
  </div>
</body>
</html>