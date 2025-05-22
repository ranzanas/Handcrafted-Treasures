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

/**
 * Servlet implementation class CartController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/cart" })
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles GET requests for cart-related actions:
	 * - View cart
	 * - Add item to cart
	 * - Update quantity in cart
	 * - Remove item from cart
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get userId from session to check login status
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            // Redirect to login if user is not logged in
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        CartService cartService = new CartService();
        String action = request.getParameter("action");

        // Handle item removal from cart
        if ("remove".equals(action)) {
            String cartIdParam = request.getParameter("cartId");
            try {
                int cartId = Integer.parseInt(cartIdParam);
                boolean removed = cartService.removeFromCart(cartId, userId);
                request.getSession().setAttribute("message", removed ? "🗑️ Item removed from cart!" : "❌ Failed to remove item.");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.getSession().setAttribute("message", "❌ Invalid cart item.");
            }
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        // Handle updating cart item quantity
        if ("update".equals(action)) {
            try {
                int cartId = Integer.parseInt(request.getParameter("cartId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                if (quantity > 0) {
                    cartService.updateCartQuantity(cartId, userId, quantity);
                    request.getSession().setAttribute("message", "🛒 Cart updated.");
                } else {
                    cartService.removeFromCart(cartId, userId);
                    request.getSession().setAttribute("message", "🗑️ Item removed from cart.");
                }
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("message", "❌ Invalid input.");
            }
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        // Handle adding a product to the cart
        String productIdParam = request.getParameter("productId");
        if (productIdParam != null) {
            try {
                int productId = Integer.parseInt(productIdParam);
                boolean success = cartService.addToCart(userId, productId);
                request.getSession().setAttribute("message", success ? "✅ Item added to cart!" : "❌ Item failed to add to cart.");
                // Redirect back to the referring page or home if not available
                String referer = request.getHeader("referer");
                response.sendRedirect(referer != null ? referer : request.getContextPath() + "/home");
                return;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
        }

        // Load and display the current user's cart items
        List<CartModel> cartItems = cartService.getCartItemsByUserId(userId);
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
    }

	/**
	 * Redirects POST requests to the GET method for unified handling.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
