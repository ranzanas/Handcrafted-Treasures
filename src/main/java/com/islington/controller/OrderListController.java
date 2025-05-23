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
import com.islington.service.ProductService;
import com.islington.service.UserListService;

/**
 * Servlet implementation class OrderListController
 * Responsible for displaying all orders with associated user and product data.
 * Also displays dashboard metrics like total orders, revenue, users, and products.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/orderList" })
public class OrderListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    // Services to access orders, users, and products
    private final OrderListService orderListService = new OrderListService();
    private final UserListService userListService = new UserListService();
    private final ProductService productService = new ProductService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles GET requests to display the order list page.
	 * Loads order data and dashboard statistics for admin view.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Fetch all orders with associated user information
		List<OrderModel> orderList = orderListService.getAllOrdersWithUserInfo();

		// Calculate dashboard statistics
        int totalOrders = orderList.size();
        double totalRevenue = orderListService.getTotalRevenue();
        int totalUsers = userListService.getTotalUserCount();
        int totalProducts = productService.getAllProducts().size();

        // Pass data to JSP
        request.setAttribute("orderList", orderList);
        request.setAttribute("orderCount", totalOrders);
        request.setAttribute("totalRevenue", totalRevenue);
        request.setAttribute("userCount", totalUsers);
        request.setAttribute("productCount", totalProducts);

        // Forward to orderList.jsp page
        request.getRequestDispatcher("/WEB-INF/pages/orderList.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by delegating to the GET handler.
	 * Useful for form submissions or future filters.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
