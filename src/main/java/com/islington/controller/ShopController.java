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
 * Servlet implementation class ShopController
 * Responsible for displaying all available products on the shop page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/shop" })
public class ShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public ShopController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles GET requests to the /shop page.
	 * Retrieves the full list of products from the database and forwards to shop.jsp.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Initialize the product service
        ProductService productService = new ProductService();

        // Retrieve all products from the database
        List<ProductModel> productList = productService.getAllProducts(); 

        // Attach product list to request scope
        request.setAttribute("productList", productList); 

        // Forward the request to the shop page for rendering
		request.getRequestDispatcher("/WEB-INF/pages/shop.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by forwarding them to doGet().
	 * This allows form submissions (if any) to behave the same as a GET.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
