<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>contact</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/contact.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
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
		<c:if test="${not empty message}">
		  <div style="padding: 10px; background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; margin-bottom: 15px; text-align: center;">
		    ${message}
		  </div>
		</c:if>
	    <section id="page-header" class="about-header">
      <h2>#let's_talk</h2>
      <p>LEAVE A MESSAGE, We love to hear from you!</p>
    </section>

    <section id="contact-details" class="section-p1">
      <div class="details">
        <span>GET IN TOUCH</span>
        <h2>Visit one of our agency locations or contact us today</h2>
        <h3>Head Office</h3>
        <div>
        <ul>
          <li>
            <i class="bx bx-map-alt"></i>
            <p>New Baneshwor, Kathmandu 44600, Nepal</p>
          </li>
          <li>
            <i class="bx bx-envelope"></i>
            <p>aayanrazzdhital@gmail.com</p>
          </li>
          <li>
            <i class="bx bxs-phone"></i>
            <p>+977 9822797908</p>
          </li>
          <li>
            <i class="bx bx-time-five"></i>
            <p>Sunday to Friday: 9.00am to 5.00pm</p>
          </li>
          </ul>
        </div>
      </div>
      

      
      <div id="form-details">
		<form action="${pageContext.request.contextPath}/contact" method="post">
		  <span>LEAVE A MESSAGE</span>
		  <h2>We love to hear from you</h2>
		  <textarea name="feedbackDescription" cols="30" rows="10" placeholder="Your Message" required></textarea>
		  <button type="submit" class="normal">Send Feedback</button>
		</form>
    </div>
    </section>



    <section id="newsletter" class="section-p1 section-m1">
      <div class="newstext">
        <h4>Sign Up For Newsletters</h4>
        <p>
          Get E-mail updates about our latest shop and
          <span>special offers.</span>
        </p>
      </div>
      <div class="form">
        <input type="text" placeholder="Your email address" />
        <button class="normal">Sign Up</button>
      </div>
    </section>
	<jsp:include page="footer.jsp" />

</body>
</html>