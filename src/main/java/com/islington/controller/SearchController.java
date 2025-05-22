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
 * Servlet implementation class SearchController
 * Handles search queries submitted by users and displays matching product results.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/search" })
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Service to handle product-related logic
	private ProductService productService = new ProductService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles GET requests to perform a product search.
	 * If the query is valid, it returns matching products to the shop page.
	 * If empty, it redirects to the full shop listing.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the search keyword from the request parameter
        String keyword = request.getParameter("query");

        // If the keyword is missing or empty, redirect to the shop page
        if (keyword == null || keyword.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/shop");
            return;
        }

        // Perform the product search using the service
        List<ProductModel> searchResults = productService.searchProducts(keyword);

        // Attach results and original query to the request
        request.setAttribute("productList", searchResults);
        request.setAttribute("searchQuery", keyword);

        // Forward to the shop page with search results displayed
        request.getRequestDispatcher("/WEB-INF/pages/shop.jsp").forward(request, response);
    }

	/**
	 * Handles POST requests by forwarding them to the GET handler.
	 * This supports search forms submitted via POST.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
