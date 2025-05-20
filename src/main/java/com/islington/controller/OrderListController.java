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
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/orderList" })
public class OrderListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 	List<OrderModel> orderList = orderListService.getAllOrdersWithUserInfo();
	        int totalOrders = orderList.size();
	        double totalRevenue = orderListService.getTotalRevenue();
	        int totalUsers = userListService.getTotalUserCount();
	        int totalProducts = productService.getAllProducts().size();

	        request.setAttribute("orderList", orderList);
	        request.setAttribute("orderCount", totalOrders);
	        request.setAttribute("totalRevenue", totalRevenue);
	        request.setAttribute("userCount", totalUsers);
	        request.setAttribute("productCount", totalProducts);

	        request.getRequestDispatcher("/WEB-INF/pages/orderList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
