package com.islington.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.islington.model.OrderModel;
import com.islington.service.OrderListService;

/**
 * Servlet implementation class OrderHistoryController
 * Handles requests to view the logged-in user's past orders.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/orderHistory" })
public class OrderHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Service layer for retrieving order data
	private final OrderListService orderService = new OrderListService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderHistoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles GET requests to display the user's order history.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the user ID from session
		Integer userId = (Integer) request.getSession().getAttribute("userId");

        // Redirect to login if user is not logged in
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Fetch all orders placed by this user
        List<OrderModel> orderHistory = orderService.getOrdersByUserId(userId);

        // Set orders as a request attribute and forward to JSP
        request.setAttribute("orderHistory", orderHistory);
        request.getRequestDispatcher("/WEB-INF/pages/orderHistory.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by delegating them to the GET handler.
	 * Useful if a form posts to this URL.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
