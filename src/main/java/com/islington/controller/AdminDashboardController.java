package com.islington.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.islington.model.OrderModel;
import com.islington.model.ProductModel;
import com.islington.service.OrderListService;
import com.islington.service.ProductService;
import com.islington.service.UserListService;

/**
 * Servlet implementation class AdminDashboard
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/adminDashboard" })
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Services used to retrieve data from database
	private ProductService productService = new ProductService();
	private UserListService userService = new UserListService();
	private final OrderListService orderListService = new OrderListService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles GET request to the admin dashboard.
	 * Gathers statistics and data needed for displaying dashboard overview:
	 * - All products
	 * - All orders (with user info)
	 * - Total revenue
	 * - Total user count
	 * - Product count
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Fetch all products
        List<ProductModel> productList = productService.getAllProducts();

		// Fetch all orders with user information
	 	List<OrderModel> orderList = orderListService.getAllOrdersWithUserInfo();

        // Calculate dashboard metrics
        int totalOrders = orderList.size();
        double totalRevenue = orderListService.getTotalRevenue();
        int userCount = userService.getTotalUserCount();

        // Set attributes to be accessed in JSP
        request.setAttribute("orderCount", totalOrders);
        request.setAttribute("totalRevenue", totalRevenue);
        request.setAttribute("userCount", userCount);
        request.setAttribute("productList", productList);
        request.setAttribute("productCount", productList.size());

        // Forward to admin dashboard JSP page
        request.getRequestDispatcher("/WEB-INF/pages/adminDashboard.jsp").forward(request, response);
    }

	/**
	 * Handles POST requests the same way as GET.
	 * This makes form submissions or AJAX calls behave the same as page loads.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
