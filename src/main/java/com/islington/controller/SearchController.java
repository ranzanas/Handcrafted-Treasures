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
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/search" })
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productService = new ProductService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String keyword = request.getParameter("query");
        if (keyword == null || keyword.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/shop");
            return;
        }

        List<ProductModel> searchResults = productService.searchProducts(keyword);
        request.setAttribute("productList", searchResults);
        request.setAttribute("searchQuery", keyword);

        request.getRequestDispatcher("/WEB-INF/pages/shop.jsp").forward(request, response);
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
