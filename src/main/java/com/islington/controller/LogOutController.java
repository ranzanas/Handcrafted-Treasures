package com.islington.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.islington.util.CookieUtil;
import com.islington.util.SessionUtil;

/**
 * Servlet implementation class LogOutController
 * Handles user logout by clearing session and deleting cookies.
 */
@WebServlet("/LogOutController")
public class LogOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles GET requests for user logout.
	 * Deletes role cookie and invalidates session, then redirects to home page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Remove the role cookie
		CookieUtil.deleteCookie(response, "role");

		// Invalidate the current user session
		SessionUtil.invalidateSession(request);

		// Redirect to homepage after logout
		response.sendRedirect(request.getContextPath() + "/home");
	}

	/**
	 * Handles POST requests for logout.
	 * Performs the same steps as the GET method.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CookieUtil.deleteCookie(response, "role");
		SessionUtil.invalidateSession(request);
		response.sendRedirect(request.getContextPath() + "/home");
	}
}
