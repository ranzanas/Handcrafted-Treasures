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
 * Handles order processing from cart items.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/order" })
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    // Services to interact with cart and order operations
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
	 * Handles GET request to show order summary page.
	 * Calculates total quantity and amount based on items in user's cart.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get user ID from session
		Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            // Redirect to login if not authenticated
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Fetch cart items for the user
        List<CartModel> cartItems = cartService.getCartItemsByUserId(userId);
        if (cartItems.isEmpty()) {
            // Redirect if cart is empty
            request.getSession().setAttribute("message", "Your cart is empty.");
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        // Calculate total quantity and amount
        double totalAmount = 0;
        int totalQuantity = 0;
        for (CartModel item : cartItems) {
            totalAmount += item.getProductPrice() * item.getCartProductQuantity();
            totalQuantity += item.getCartProductQuantity();
        }

        // Set attributes to show in order.jsp
        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("orderQuantity", totalQuantity);

        // Forward to order confirmation page
		request.getRequestDispatcher("/WEB-INF/pages/order.jsp").forward(request, response);
	}

	/**
	 * Handles POST request to place an order.
	 * Gathers form inputs, creates an order, and clears the cart on success.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get user ID from session
		Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Retrieve form parameters
        String deliveryAddress = request.getParameter("deliveryAddress");
        String paymentMethod = request.getParameter("paymentMethod");
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
        int orderQuantity = Integer.parseInt(request.getParameter("orderQuantity"));

        // Get cart items again to prevent duplicate or invalid orders
        List<CartModel> cartItems = cartService.getCartItemsByUserId(userId);
        if (cartItems.isEmpty()) {
            request.getSession().setAttribute("message", "Your cart is empty.");
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        // Create order and get generated order ID
        int orderId = orderService.createOrder(userId, orderQuantity, deliveryAddress, totalAmount, paymentMethod, cartItems);

        // Handle result and clear cart if successful
        if (orderId > 0) {
            cartService.clearCart(userId);
            request.getSession().setAttribute("message", "✅ Order placed successfully!");
        } else {
            request.getSession().setAttribute("message", "❌ Failed to place order.");
        }

        // Redirect to cart (or could be changed to an order confirmation page)
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
