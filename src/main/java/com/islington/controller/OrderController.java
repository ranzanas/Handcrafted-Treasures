package com.islington.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.islington.model.CartModel;
import com.islington.service.CartService;
import com.islington.service.OrderService;

/**
 * Servlet implementation class OrderController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/order" })
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final CartService cartService = new CartService();
    private final OrderService orderService = new OrderService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<CartModel> cartItems = cartService.getCartItemsByUserId(userId);
        if (cartItems.isEmpty()) {
            request.getSession().setAttribute("message", "Your cart is empty.");
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        double totalAmount = 0;
        int totalQuantity = 0;
        for (CartModel item : cartItems) {
            totalAmount += item.getProductPrice() * item.getCartProductQuantity();
            totalQuantity += item.getCartProductQuantity();
        }

        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("orderQuantity", totalQuantity);

		request.getRequestDispatcher("/WEB-INF/pages/order.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String deliveryAddress = request.getParameter("deliveryAddress");
        String paymentMethod = request.getParameter("paymentMethod");
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
        int orderQuantity = Integer.parseInt(request.getParameter("orderQuantity"));

        List<CartModel> cartItems = cartService.getCartItemsByUserId(userId);
        if (cartItems.isEmpty()) {
            request.getSession().setAttribute("message", "Your cart is empty.");
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        int orderId = orderService.createOrder(userId, orderQuantity, deliveryAddress, totalAmount, paymentMethod, cartItems);

        if (orderId > 0) {
            cartService.clearCart(userId);
            request.getSession().setAttribute("message", "✅ Order placed successfully!");
        } else {
            request.getSession().setAttribute("message", "❌ Failed to place order.");
        }

        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
