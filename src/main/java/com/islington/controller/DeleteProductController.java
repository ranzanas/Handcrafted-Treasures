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
	
	private ProductService productService = new ProductService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        int productId = Integer.parseInt(request.getParameter("productId"));
        boolean isDeleted = productService.deleteProductIfQuantityZero(productId);

        if (isDeleted) {
            request.getSession().setAttribute("message", "✅ Product deleted.");
        } else {
            request.getSession().setAttribute("message", "❌ Product can't be deleted. It is still in stock.");
        }

        response.sendRedirect(request.getContextPath() + "/adminDashboard");
	}

}
