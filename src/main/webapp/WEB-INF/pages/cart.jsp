<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add to cart</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/cart.css" />
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
	<section id="cart" class="section-p1">
            <table width="100%">
                <thead>
                    <tr>
                        <td>Remove</td>
                        <td>Image</td>
                        <td>Product</td>
                        <td>Price</td>
                        <td>Quantity</td>
                        <td>Subtotal</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><i class='bx bx-x-circle'></i></td>
                        <td><img src="./img/products/f1.jpg" alt=""></td>
                        <td>Expressive</td>
                        <td>$2499</td>
                        <td><input type="number" value="1"></td>
                        <td>$2499</td>
                    </tr>
                    <tr>
                        <td><i class='bx bx-x-circle'></i></td>
                        <td><img src="./img/products/f2.jpg" alt=""></td>
                        <td>crocheted keychain</td>
                        <td>$2499</td>
                        <td><input type="number" value="1"></td>
                        <td>$2499</td>
                    </tr>
                    <tr>
                        <td><i class='bx bx-x-circle'></i></td>
                        <td><img src="./img/products/f3.jpg" alt=""></td>
                        <td>monstrous, grotesque face</td>
                        <td>$2499</td>
                        <td><input type="number" value="1"></td>
                        <td>$2499</td>
                    </tr>
                </tbody>
            </table>
        </section>

        <section id="cart-add" class="section-p1">
            <div id="coupon">
                <h3>Apply Coupon</h3>
                <div>
                    <input type="text" placeholder="Enter Your Coupon">
                    <button class="normal">Apply</button>
                </div>
            </div>

            <div id="subtotal">
                <h3>Cart Total</h3>
                <table>
                    <tr>
                        <td>Cart Subtotal</td>
                        <td>$7399</td>
                    </tr>
                    <tr>
                        <td>Shipping</td>
                        <td>Free</td>
                    </tr>
                    <tr>
                        <td><strong>Total</strong></td>
                        <td><strong>$7399</strong></td>
                    </tr>
                </table>
                <button class="normal">Proceed to checkout</button>
            </div>
        </section>
	<jsp:include page="footer.jsp" />
</body>
</html>