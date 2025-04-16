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
	<div class = "mainContainer">
		<h1>Registration Form</h1>
		<form class= "formContainer" method = "post" action = "Registration" enctype="multipart/form-data">
			<div class = inputRow>
				<div class = "leftContainer">
					<div class = "form-group">
						<label for = "name">First Name:</label><br>
						<input type = "text" class = "loginField" name = "firstName"><br>
					</div>
					
					<div class = "form-group">
						<label for = "name">Username:</label><br>
						<input type = "text" class = "loginField" name = "username" ><br>
						
					</div>
					
					<div class = "form-group">
						<label for = "name">Gender:</label><br>
						<input type = "text" class = "loginField" name = "gender"><br>
					</div>
					
					<div class = "form-group">
						<label for = "name">Phone Number:</label><br>
						<input type = "text" class = "loginField" name = "phoneNumber"><br>
					</div>
					
					<div class = "form-group">
						<label for = "name">Password:</label><br>
						<input type = "text" class = "loginField" name = "password"><br>
					</div>
				</div>
				
				
				<div class = "rightContainer">
					<div class = "form-group">
						<label for = "name">Last Name:</label><br>
						<input type = "text" class = "loginField" name = "lastName"><br>
					</div>
					
					<div class = "form-group">
						<label for = "name">Birthday:</label><br>
						<input type = "date" class = "loginField" name = "dob"><br>
					</div>
					
					<div class = "form-group">
						<label for = "name">Email:</label><br>
						<input type = "text" class = "loginField" name = "email"><br>
					</div>
					
					<div class = "form-group">
						<label for = "name">Subject:</label><br>
						<select class = 'form-control' name = 'subject'>
							<option value ='Computing'>Computing</option>
							<option value ='AI'>AI</option>
							<option value ='Cyber Security'>Cyber Security</option>
							<option value ='Multiedia'>Multimedia</option>
						</select>
					</div>
					
					<div class = "form-group">
						<label for = "name">Retype Password:</label><br>
						<input type = "text" class = "loginField" name = "retypePassword"><br>
					</div>
				</div>
			</div>
			
	<div class="form-group">
		<label>Upload Image:</label><br><br>
		<input type="file" name="image"><br>
	</div>
				
				<div class = "btn-group">
					<button class = "submitButton">Submit</button>
				</div>
		</form>
	</div>
</body>
</html>