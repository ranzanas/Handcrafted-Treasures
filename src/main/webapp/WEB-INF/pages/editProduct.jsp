<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editProduct.css" />
</head>
<body>
<div class="container">
    <main class="main">
        <h2>Edit Product</h2>
        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/editProduct" method="post" class="add-product-form" enctype="multipart/form-data">
            <!-- Display Product ID as read-only -->
            <label>Product ID:</label>
            <input type="text" value="${product.productId}" readonly />
            <input type="hidden" name="productId" value="${product.productId}" />

            <label>Product Name:</label>
            <input type="text" name="productName" value="${product.productName}" required />

            <label>Description:</label>
            <textarea name="productDescription" rows="3" required>${product.productDescription}</textarea>

            <label>Price:</label>
            <input type="number" name="productPrice" step="0.01" value="${product.productPrice}" required />

            <label>Quantity:</label>
            <input type="number" name="productQuantity" value="${product.productQuantity}" required />

            <!-- Status is shown but auto-calculated -->
            <label>Status:</label>
            <input type="text" value="${product.productQuantity > 0 ? 'Available' : 'Out of Stock'}" readonly />

            <label>Upload New Image:</label>
            <input type="file" name="productImage" value="${product.productImage}" />

            <button class="btn submit" type="submit">Update Product</button>
        </form>
    </main>
</div>
</body>
</html>
