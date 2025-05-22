package com.islington.controller;

import java.io.IOException;

import com.islington.model.UserModel;
import com.islington.service.LoginService;
import com.islington.util.CookieUtil;
import com.islington.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * LoginController is responsible for handling login requests.
 * It interacts with the LoginService to authenticate users and sets session/cookie values.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final LoginService loginService;

    /**
     * Constructor initializes the LoginService.
     */
    public LoginController() {
        this.loginService = new LoginService();
    }

    /**
     * Handles GET requests to show the login page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the user to the login JSP
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    /**
     * Handles POST requests for user authentication.
     * Validates login credentials and redirects based on role (Admin/Customer).
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Extract login form data
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Set user credentials to the model
        UserModel userModel = new UserModel();
        userModel.setUserName(username);
        userModel.setPassword(password);

        // Authenticate user via service
        Boolean loginStatus = loginService.loginUser(userModel);

        if (loginStatus != null && loginStatus) {
            // Fetch userId after successful login
            Integer userId = loginService.getUserIdByUsername(username);

            if (userId != null) {
                // Set session attributes for user identity
                SessionUtil.setAttribute(req, "userId", userId);
                SessionUtil.setAttribute(req, "username", username);

                // Role-based redirection and cookie assignment
                if (username.equals("admin01")) {
                    CookieUtil.addCookie(resp, "role", "Admin", 5 * 30); // 5 * 30 minutes
                    resp.sendRedirect(req.getContextPath() + "/adminDashboard");
                } else {
                    CookieUtil.addCookie(resp, "role", "Customer", 5 * 30);
                    resp.sendRedirect(req.getContextPath() + "/home");
                }
            } else {
                // Should never occur if login is successful, but added for safety
                handleLoginFailure(req, resp, false);
            }

        } else {
            // Handle failed login or server error
            handleLoginFailure(req, resp, loginStatus);
        }
    }

    /**
     * Handles login failure with informative messages.
     *
     * @param req         HTTP request object
     * @param resp        HTTP response object
     * @param loginStatus Boolean indicating login result or null if service error
     */
    private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
            throws ServletException, IOException {
        String errorMessage;
        if (loginStatus == null) {
            errorMessage = "⚠️ Our server is under maintenance. Please try again later!";
        } else {
            errorMessage = "❌ User credential mismatch. Please try again!";
        }

        req.setAttribute("error", errorMessage);
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }
}
