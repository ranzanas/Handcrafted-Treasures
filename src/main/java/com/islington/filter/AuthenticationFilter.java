package com.islington.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.islington.util.SessionUtil;

/**
 * Servlet Filter implementation class AuthenticationFilter
 * Restricts access to authenticated pages and redirects unauthenticated users to login.
 */
@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
public class AuthenticationFilter extends HttpFilter implements Filter {
	
	private static final String LOGIN = "/login";
	private static final String REGISTER = "/Registration";
	private static final String HOME = "/home";
	private static final String ROOT = "/";
	private static final String ABOUT = "/about";
	private static final String BLOG = "/blog";
	private static final String CART = "/cart";
	private static final String CONTACT = "/contact";
	private static final String SHOP = "/shop";
	private static final String USERPROFILE = "/userProfile";
	private static final String EDITPROFILE = "/editProfile";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Initialization logic if needed
	}

	/**
	 * Filters incoming requests and applies session-based access control.
	 * Allows public pages (home, login, registration, etc.) and static assets.
	 * Redirects unauthenticated users trying to access protected routes.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();

		// Allow public assets like CSS, images, and public pages without login
		if (uri.endsWith(".css") || uri.endsWith(".jpg") || uri.endsWith(".png") ||
		    uri.endsWith(HOME) || uri.endsWith(ROOT) || uri.endsWith(LOGIN) ||
		    uri.endsWith(REGISTER) || uri.endsWith(ABOUT) || uri.endsWith(BLOG)) {
			chain.doFilter(request, response);
			return;
		}

		// Check session for logged-in user
		boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;

		if (!isLoggedIn) {
			// Not logged in → allow access to login/registration only
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + LOGIN);  // redirect to login
			}
		} else {
			// Logged in → restrict access to login/registration page
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
				res.sendRedirect(req.getContextPath() + HOME);  // redirect to homepage
			} else {
				chain.doFilter(request, response);  // allow access
			}
		}
	}

	@Override
	public void destroy() {
		// Cleanup logic if needed
		Filter.super.destroy();
	}
}
