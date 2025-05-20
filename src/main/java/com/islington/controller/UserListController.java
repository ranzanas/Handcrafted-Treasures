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
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/userList" })
public class UserListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        List<UserModel> users = userService.getAllUsersWithFeedback();
	 	List<OrderModel> orderList = orderListService.getAllOrdersWithUserInfo();
        int totalOrders = orderList.size();
        double totalRevenue = orderListService.getTotalRevenue();
        int userCount = userService.getTotalUserCount();
        int productCount = productService.getAllProducts().size();


        request.setAttribute("users", users);
        request.setAttribute("userCount", userCount);
        request.setAttribute("productCount", productCount);
        request.setAttribute("orderList", orderList);
        request.setAttribute("orderCount", totalOrders);
        request.setAttribute("totalRevenue", totalRevenue);
        request.getRequestDispatcher("WEB-INF/pages/userList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
