<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/about.css" />
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
	
        <section id="page-header" class="about-header">
            <h2>#knowUs</h2>
            <p>Know About Us More!!!</p>
        </section>
	        <section id="about-head" class="section-p1">
            <img src="${pageContext.request.contextPath}/resources/img/about/a6.jpg" alt="">
            <div>
                <h2>Who We Are?</h2>
                <p>At Handcrafted Treasures, we are passionate about celebrating the timeless art of handicrafts. Handicrafts refer to a wide range of traditional, handmade products that are created using various techniques, materials, and tools. These items are often imbued with cultural significance, reflecting the rich heritage and skilled craftsmanship of the artisans who create them.</p>

                <abbr title="">At Handcrafted Treasures, we are committed to sourcing and showcasing the finest examples of handicrafts, ensuring that each piece we offer is a testament to the timeless art of handmade craftsmanship. We believe in the beauty of the handmade process and the unique story that each item tells, and we are excited to share these extraordinary creations with our customers.</abbr>

                <br><br>

                <marquee bgcolor="#ccc" loop="-1" scrollamount="5" width="100%">The beauty of handicrafts lies in the personal touch and the unique stories they carry. From intricate textiles and exquisite ceramics to meticulously carved woodwork and delicately crafted glassware, each item is a reflection of the artisan's creativity, cultural heritage, and dedication to their craft. By supporting and appreciating these handmade treasures, we not only celebrate the rich diversity of human expression but also contribute to the preservation of traditional skills and the livelihoods of artisans around the world.</marquee>
            </div>
        </section>


        <section id="feature" class="section-p1">
            <div class="fe-box">
                <img src="${pageContext.request.contextPath}/resources/img/features/f1.png" alt="">
                <h6>Free Shipping</h6>
            </div>
            <div class="fe-box">
                <img src="${pageContext.request.contextPath}/resources/img/features/f2.png" alt="">
                <h6>Online Order</h6>
            </div>
            <div class="fe-box">
                <img src="${pageContext.request.contextPath}/resources/img/features/f3.png" alt="">
                <h6>Save Money</h6>
            </div>
            <div class="fe-box">
                <img src="${pageContext.request.contextPath}/resources/img/features/f4.png" alt="">
                <h6>Promotions</h6>
            </div>
            <div class="fe-box">
                <img src="${pageContext.request.contextPath}/resources/img/features/f5.png" alt="">
                <h6>Happy Sell</h6>
            </div>
            <div class="fe-box">
                <img src="${pageContext.request.contextPath}/resources/img/features/f6.png" alt="">
                <h6>F24/7 Support</h6>
            </div>
        </section>

        <section id="newsletter" class="section-p1 section-m1">
            <div class="newstext">
                <h4>Sign Up For Newsletters</h4>
                <p>Get E-mail updates about our latest shop and <span>special offers.</span></p>
            </div>
            <div class="form">
                <input type="text" placeholder="Your email address">
                <button class="normal">Sign Up</button>
            </div>
        </section>
	<jsp:include page="footer.jsp" />

</body>
</html>