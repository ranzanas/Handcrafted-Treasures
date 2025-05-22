package com.islington.controller;

import com.islington.model.ProductModel;
import com.islington.service.ProductService;
import com.islington.util.ImageUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

/**
 * Servlet implementation class AddProductController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/addProduct" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Service class to interact with the product database
    private final ProductService productService = new ProductService();
    // Utility class for handling image-related tasks
    private final ImageUtil imageUtil = new ImageUtil();

    public AddProductController() {
        super();
    }

    /**
     * Handles GET requests to load the Add Product form page.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/addProduct.jsp").forward(request, response);
    }

    /**
     * Handles POST requests for adding a new product to the system.
     * Validates input, checks for duplicate product name, handles image upload, and adds product.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Read form inputs
        String name = request.getParameter("product-name");
        String desc = request.getParameter("product-desc");
        double price = Double.parseDouble(request.getParameter("product-price"));
        int quantity = Integer.parseInt(request.getParameter("product-qty"));
        String status = quantity > 0 ? "In Stock" : "Out of Stock";

        // Input validation
        String errorMessage = null;
        if (name == null || name.trim().isEmpty()) {
            errorMessage = "Product name is required.";
        } else if (productService.productNameExists(name)) {
            errorMessage = "A product with this name already exists.";
        } else if (price <= 0) {
            errorMessage = "Price must be greater than 0.";
        } else if (quantity < 0) {
            errorMessage = "Quantity cannot be negative.";
        }

        // If validation fails, show the error message on the form page
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pages/addProduct.jsp").forward(request, response);
            return;
        }

        try {
            // Handle image upload
            Part imagePart = request.getPart("product-image");
            String imagePath = imageUtil.getImageNameFromPart(imagePart);

            boolean uploaded = imageUtil.uploadImage(imagePart, "productImg", request);

            if (!uploaded) {
                handleError(request, response, "Image upload failed. Please try again.");
                return;
            }

            // Create a new ProductModel object and populate with form data
            ProductModel product = new ProductModel();
            product.setProductName(name);
            product.setProductDescription(desc);
            product.setProductPrice(price);
            product.setProductQuantity(quantity);
            product.setProductStatus(status);
            product.setProductImage(imagePath);

            // Save product to database
            boolean success = productService.addProduct(product);

            if (success) {
                handleSuccess(request, response, "Product added successfully!");
            } else {
                handleError(request, response, "Failed to add product. Please try again.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            handleError(request, response, "An unexpected error occurred. Please try again later.");
        }
    }

    /**
     * Helper method to handle successful product addition.
     */
    private void handleSuccess(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute("success", message);
        request.getRequestDispatcher("/adminDashboard").forward(request, response);
    }

    /**
     * Helper method to handle errors and redirect to the form page with an error message.
     */
    private void handleError(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("/WEB-INF/pages/addProduct.jsp").forward(request, response);
    }
}
