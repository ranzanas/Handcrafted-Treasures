<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blog</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/blog.css" />
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
	 <section id="page-header" class="blog-header">
            <h2>#readmore</h2>
            <p>Read all case studies about our products!</p>
        </section> 

        <section id="blog">
            <div class="blog-box">
                <div class="blog-img">
                    <img src="${pageContext.request.contextPath}/resources/img/productImg/halfdemonhalfgod.jpg" alt="">
                </div>
                <div class="blog-details">
                    <h4>Tradition, skill, heritage.</h4>
                    <p>Handmaking crafts is a deeply rooted tradition that connects artisans to their cultural heritage. Through their hands-on work, they pass down skills and stories from generation to generation. Every piece crafted carries the essence of history, showcasing unique techniques and materials. By preserving these crafts, artisans ensure that these valuable traditions continue to thrive in a modern world.</p>
                    
                </div>
                <h1>13/01</h1>
            </div>
            <div class="blog-box">
                <div class="blog-img">
                    <img src="${pageContext.request.contextPath}/resources/img/productImg/blueEvilEyeDreamCatcher.jpg" alt="">
                </div>
                <div class="blog-details">
                    <h4>The Art of Crochet and Its Origins</h4>
                    <p>Crochet originated as a practical technique for creating textiles, with early roots in Europe during the 19th century. Over time, it evolved into a creative art form, blending functionality with intricate designs. Today, crochet is a popular craft practiced worldwide, allowing for endless artistic expression. Its rich history continues to inspire contemporary crafters who innovate while honoring tradition.</p>
                    
                </div>
                <h1>13/04</h1>
            </div>
            <div class="blog-box">
                <div class="blog-img">
                    <img src="${pageContext.request.contextPath}/resources/img/productImg/greenAventurine.jpg" alt="">
                </div>
                <div class="blog-details">
                    <h4>Why Handcrafted Jewelry is Timeless</h4>
                    <p>Handcrafted jewelry embodies artistry and individuality, with every piece showcasing the maker's passion and craftsmanship. The process combines tradition and innovation, creating treasures that stand the test of time. Each design is a reflection of the artisan's creativity and dedication. Wearing such jewelry connects us to the story behind it, making it truly one-of-a-kind.</p>
                    
                </div>
                <h1>12/01</h1>
            </div>
            <div class="blog-box">
                <div class="blog-img">
                    <img src="${pageContext.request.contextPath}/resources/img/productImg/luffyHatKeyChain.png" alt="">
                </div>
                <div class="blog-details">
                    <h4>5 DIY Craft Projects to Try This Spring</h4>
                    <p>Embrace the season with easy and stylish DIY craft projects that add a fresh, vibrant touch to your home. These projects are designed to be fun, creative, and perfect for bringing the essence of spring indoors. From floral decorations to colorful accents, each craft brings a burst of joy and personality to your space. Get inspired and let your creativity bloom this spring with these simple, hands-on ideas!</p>
                    
                </div>
                <h1>16/01</h1>
            </div>
            <div class="blog-box">
                <div class="blog-img">
                    <img src="${pageContext.request.contextPath}/resources/img/productImg/flowerPainting.jpg" alt="">
                </div>
                <div class="blog-details">
                    <h4>Top 10 Craft Supplies Every Beginner Needs</h4>
                    <p>Starting with the right tools makes crafting more enjoyable and successful. This list includes essentials like scissors, glue, paint, and brushes to kickstart your creative journey. Each item is chosen to help you explore a variety of crafts, from paper projects to DIY decor. With these supplies, you'll be ready to bring your ideas to life with ease and confidence.</p>
                    
                </div>
                <h1>10/03</h1>
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