package com.islington.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.islington.model.ProductModel;
import com.islington.service.ProductService;

/**
 * HomeController handles requests to the homepage and root URL.
 * It loads the list of products and forwards them to the home view.
 * 
 * @author Ranjana Silwal
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/home", "/" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles GET requests to display the homepage.
	 * Loads all products and forwards the data to home.jsp for rendering.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Initialize the product service
		ProductService productService = new ProductService();

		// Retrieve all products from the database
	    List<ProductModel> productList = productService.getAllProducts();

	    // Set the product list as a request attribute
	    request.setAttribute("productList", productList);

	    // Forward to home.jsp to display the products
		request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by delegating them to the GET handler.
	 * Useful for form submissions that simply reload the page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
