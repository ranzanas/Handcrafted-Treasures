<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Profile</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/editProfile.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
	<jsp:include page="header.jsp" />
  

    <div class="form-container">
      <h2>Edit Profile</h2>
      <c:if test="${not empty updateError}">
	    <div class="error-message">${updateError}</div>
	</c:if>
      
        <form action="editProfile" method="post" enctype="multipart/form-data">


            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" value="${user.fullName}" required /><br>


            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="${user.address}" required /><br>

            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" value="${user.dob}" required /><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${user.email}" required /><br>

            <label for="number">Phone Number:</label>
            <input type="text" id="number" name="number" value="${user.number}" required /><br>

            <!-- Profile Picture Upload -->
            <div class="profile-picture-container">
                <label for="profilePicture">Profile Picture:</label>
                <input type="file" id="profilePicture" name="image" /><br>
            </div>

            <button type="submit" class = "saveChanges">Save Changes</button>
        </form>

    </div>
    
    	<jsp:include page="footer.jsp" />
	
</body>
</html>