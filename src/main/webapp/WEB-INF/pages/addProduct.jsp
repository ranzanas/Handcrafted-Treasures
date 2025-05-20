<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>
<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/css/addProduct.css" />
</head>
<body>
<section class="add-product-container">
        <h2>Add New Product</h2>
        <form action="${pageContext.request.contextPath}/addProduct" method="POST" enctype="multipart/form-data">
            <div class="form-group">
                <label for="product-name">Product Name *</label>
                <input type="text" id="product-name" name="product-name" required>
            </div>

            <div class="form-group">
                <label for="product-desc">Description *</label>
                <textarea id="product-desc" name="product-desc" required></textarea>
            </div>

            <div class="form-group">
                <label for="product-price">Price *</label>
                <input type="number" id="product-price" name="product-price" required min="1" step="0.01">
            </div>

            <div class="form-group">
                <label for="product-qty">Quantity *</label>
                <input type="number" id="product-qty" name="product-qty" required min="1">
            </div>

            <div class="form-group">
                <label for="product-image">Upload Product Image *</label>
                <input type="file" id="product-image" name="product-image" accept="image/*" required>
            </div>

            <div class="form-group">
                <button type="submit">Add Product</button>
            </div>
        </form>
    </section>
</body>
</html>