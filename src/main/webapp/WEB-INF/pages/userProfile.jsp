<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/userProfile.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
	    <link
      href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
    />
        <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"
    />

</head>
<body>
	<jsp:include page="header.jsp" />
	    <section class="mainSection">
      <div class="profilePhotoSection">
          <c:choose>
    <c:when test="${not empty user.imagePath}">
      <img src="${pageContext.request.contextPath}/${user.imagePath}" 
           alt="Profile Photo" class="profileImage" />
    </c:when>
    <c:otherwise>
      <i class="fa-regular fa-circle-user profileIcon"></i>
    </c:otherwise>
  </c:choose>
      </div>
      <div class="infoSection">
        <h1 class="myProfileTitle">My Profile</h1>
        <div class="personalInfo">
          <h3 class="userName">${user.fullName}</h3>
          <br />
          <div class="userInfo">
            <span><i class="fa-solid fa-address-card"></i></span>
            <span>Username:</span>
            <span>${user.userName}</span>
          </div>
          <div class="userInfo">
            <span><i class="fa-solid fa-calendar-days"></i></span>
            <span>Date of Birth:</span>
            <span>${user.dob}</span>
          </div>
          <div class="userInfo">
            <span><i class="fa-solid fa-location-dot"></i></span>
            <span>Address:</span>
            <span>${user.address}</span>
          </div>
          <div class="userInfo">
            <span><i class="fa-solid fa-envelope"></i></span>
            <span>Email:</span>
            <span>${user.email}</span>
          </div>
          <div class="userInfo">
            <span><i class="fa-solid fa-phone"></i></span>
            <span>Phone</span>
            <span>${user.number}</span>
          </div>
        </div>
        <div class="buttonClass">
          <a href ="${pageContext.request.contextPath}/editProfile"><button class="editButton">Edit Profile</button></a>
          <button class="deleteButton">Delete Profile</button>
        </div>
      </div>
    </section>
	<jsp:include page="footer.jsp" />
</body>
</html>