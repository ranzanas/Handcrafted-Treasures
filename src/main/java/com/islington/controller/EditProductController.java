package com.islington.controller;

import com.islington.model.ProductModel;
import com.islington.service.ProductService;
import com.islington.util.ImageUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/editProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class EditProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ProductService productService = new ProductService();
    private final ImageUtil imageUtil = new ImageUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        ProductModel product = productService.getProductById(productId);
        request.setAttribute("product", product);
        request.getRequestDispatcher("/WEB-INF/pages/editProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            String name = request.getParameter("productName");
            String description = request.getParameter("productDescription");
            double price = Double.parseDouble(request.getParameter("productPrice"));
            int quantity = Integer.parseInt(request.getParameter("productQuantity"));
            String status = quantity > 0 ? "Available" : "Out of Stock";

            String existingImage = request.getParameter("existingImage");
            String imagePath;

            Part imagePart = request.getPart("productImage");

            if (imagePart != null && imagePart.getSize() > 0) {
                imagePath = imageUtil.getImageNameFromPart(imagePart);
                boolean uploaded = imageUtil.uploadImage(imagePart, "productImg", request);
                if (!uploaded) {
                    handleError(request, response, "Image upload failed. Please try again.", null);
                    return;
                }
            } else {
                imagePath = existingImage;
            }

            ProductModel product = new ProductModel();
            product.setProductId(productId);
            product.setProductName(name);
            product.setProductDescription(description);
            product.setProductPrice(price);
            product.setProductQuantity(quantity);
            product.setProductStatus(status);
            product.setProductImage(imagePath);

            boolean updated = productService.updateProduct(product);

            if (updated) {
                request.getSession().setAttribute("message", "✅ Product details updated successfully.");
                response.sendRedirect(request.getContextPath() + "/adminDashboard");
            } else {
                handleError(request, response, "❌ Failed to update product.", product);
            }
        } catch (Exception e) {
            e.printStackTrace();
            handleError(request, response, "❌ An unexpected error occurred.", null);
        }
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, String message, ProductModel product)
            throws ServletException, IOException {
        request.setAttribute("errorMessage", message);
        if (product != null) {
            request.setAttribute("product", product);
        }
        request.getRequestDispatcher("/WEB-INF/pages/editProduct.jsp").forward(request, response);
    }
}
