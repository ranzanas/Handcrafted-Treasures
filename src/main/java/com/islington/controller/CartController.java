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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        // Get userId from session directly
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login"); // not logged in
            return;
        }
        CartService cartService = new CartService();
        String action = request.getParameter("action");
        
        //Handle remove from cart
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
        
        //Handle update cart
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
        
        //Handle add to cart
        String productIdParam = request.getParameter("productId");

        if (productIdParam != null) {
            try {
                int productId = Integer.parseInt(productIdParam);
                boolean success = cartService.addToCart(userId, productId);
                request.getSession().setAttribute("message", success ? "✅ Item added to cart!" : "❌ Item failed to add to cart.");
                String referer = request.getHeader("referer");
                response.sendRedirect(referer != null ? referer : request.getContextPath() + "/home");
                return;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
        }

        List<CartModel> cartItems = cartService.getCartItemsByUserId(userId);
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
