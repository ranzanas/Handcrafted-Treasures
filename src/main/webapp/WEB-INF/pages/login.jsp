<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel = "stylesheet" type = "text/css" href = "../css/login.css">
</head>
<body>
	<div class = "mainContainer">
	
		<div class = "photoBox">
			<img src = "../resources/images/loginPhoto.jpg">
		</div>		
		
		<div class = "loginContainer">
			<h1>LOGIN</h1>
			<form>
				<div class = "form-group">
					<label for = "name">Username:</label><br>
					<input type = "text" class = "loginField"><br>
				</div>
				
				<div class = "form-group">
					<label for = "name">Password:</label><br>
					<input type = "text" class = "loginField"><br>
				</div>
				
				<div class="form-group">
                	<button  class="loginButton">Login</button>
            	</div>
			</form>
		</div>
		
	</div>
</body>
</html>