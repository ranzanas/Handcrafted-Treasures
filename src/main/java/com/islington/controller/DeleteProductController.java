package com.islington.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.islington.service.ProductService;

/**
 * Servlet implementation class DeleteProductController
 */
@WebServlet("/DeleteProductController")
public class DeleteProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Service to handle product-related database operations
	private ProductService productService = new ProductService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles GET request (currently unused).
	 * Could be extended for product deletion confirmation page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * Handles POST request to delete a product.
	 * Only deletes the product if its quantity is zero.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get productId from form parameter
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Attempt to delete product if quantity is zero
        boolean isDeleted = productService.deleteProductIfQuantityZero(productId);

        // Set appropriate message in session
        if (isDeleted) {
            request.getSession().setAttribute("message", "✅ Product deleted.");
        } else {
            request.getSession().setAttribute("message", "❌ Product can't be deleted. It is still in stock.");
        }

        // Redirect back to the admin dashboard
        response.sendRedirect(request.getContextPath() + "/adminDashboard");
	}
}
