package com.islington.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.islington.model.OrderModel;
import com.islington.model.UserModel;
import com.islington.service.OrderListService;
import com.islington.service.ProductService;
import com.islington.service.UserListService;

/**
 * Servlet implementation class UserListController
 * Handles the display of all registered users along with feedback,
 * as well as admin dashboard metrics (orders, revenue, product/user count).
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/userList" })
public class UserListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    // Services to fetch users, products, and orders
    private UserListService userService = new UserListService();
    private ProductService productService = new ProductService();
    private final OrderListService orderListService = new OrderListService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles GET requests to load the user list and relevant statistics.
	 * Displays registered users with feedback and admin dashboard metrics.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all users along with their feedback (if any)
        List<UserModel> users = userService.getAllUsersWithFeedback();

        // Fetch all orders with user information for metrics
	 	List<OrderModel> orderList = orderListService.getAllOrdersWithUserInfo();

        // Admin dashboard metrics
        int totalOrders = orderList.size();
        double totalRevenue = orderListService.getTotalRevenue();
        int userCount = userService.getTotalUserCount();
        int productCount = productService.getAllProducts().size();

        // Set all data to be used in the JSP
        request.setAttribute("users", users);
        request.setAttribute("userCount", userCount);
        request.setAttribute("productCount", productCount);
        request.setAttribute("orderList", orderList);
        request.setAttribute("orderCount", totalOrders);
        request.setAttribute("totalRevenue", totalRevenue);

        // Forward to userList.jsp
        request.getRequestDispatcher("WEB-INF/pages/userList.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests the same way as GET.
	 * Useful if filter forms or buttons submit POST requests.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
