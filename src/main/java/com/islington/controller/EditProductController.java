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

        int productId = Integer.parseInt(request.getParameter("productId"));
        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        double price = Double.parseDouble(request.getParameter("productPrice"));
        int quantity = Integer.parseInt(request.getParameter("productQuantity"));
        String status = quantity > 0 ? "Available" : "Out of Stock";

        String existingImage = request.getParameter("existingImage");
        String imagePath;

        try {
            Part imagePart = request.getPart("productImage");

            if (imagePart != null && imagePart.getSize() > 0) {
                imagePath = imageUtil.getImageNameFromPart(imagePart);
                boolean uploaded = imageUtil.uploadImage(imagePart, "productImg", request);
                if (!uploaded) {
                    handleError(request, response, "Image upload failed. Please try again.");
                    return;
                }
            } else {
                // Use existing image if no new image uploaded
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
                handleSuccess(request, response, "✅ Product updated successfully.");
            } else {
                handleError(request, response, "❌ Failed to update product.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            handleError(request, response, "❌ An unexpected error occurred.");
        }
    }

    private void handleSuccess(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.getSession().setAttribute("message", message);
        response.sendRedirect(request.getContextPath() + "/adminDashboard");
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("/WEB-INF/pages/editProduct.jsp").forward(request, response);
    }
}
