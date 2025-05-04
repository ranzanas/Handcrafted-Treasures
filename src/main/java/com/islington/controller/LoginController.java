package com.islington.controller;

import java.io.IOException;

import com.islington.model.UserModel;
import com.islington.service.LoginService;
import com.islington.service.UserProfileService;
import com.islington.util.CookieUtil;
import com.islington.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * LoginController is responsible for handling login requests. It interacts with
 * the LoginService to authenticate users.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final LoginService loginService;


    /**
     * Constructor initializes the LoginService and UserProfileService.
     */
    public LoginController() {
        this.loginService = new LoginService();
      
    }

    /**
     * Handles GET requests to the login page.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    /**
     * Handles POST requests for user login.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserModel userModel = new UserModel();
        userModel.setUserName(username);
        userModel.setPassword(password);

        // Authenticate the user
        Boolean loginStatus = loginService.loginUser(userModel);
        

        if (loginStatus != null && loginStatus) {
        	Integer userId = loginService.getUserIdByUsername(username);
        	if (userId != null) {
        		 SessionUtil.setAttribute(req, "userId", userId);
        		 SessionUtil.setAttribute(req, "username", username);
 

            // Add role to cookies and redirect based on user role
            if (username.equals("admin01")) {
                CookieUtil.addCookie(resp, "role", "Admin", 5 * 30);  // Role-based redirection
                resp.sendRedirect(req.getContextPath() + "/adminDashboard");  // Redirect to admin dashboard
            } else {
                CookieUtil.addCookie(resp, "role", "Customer", 5 * 30);  // Role-based redirection
                resp.sendRedirect(req.getContextPath() + "/home");  
            }
        } 
        	else {
                handleLoginFailure(req, resp, false);
            }
        }
        else {
			handleLoginFailure(req, resp, loginStatus);
        }
    }

    /**
     * Handles login failures by setting attributes and forwarding to the login
     * page.
     *
     * @param req         HttpServletRequest object
     * @param resp        HttpServletResponse object
     * @param loginStatus Boolean indicating the login status
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
            throws ServletException, IOException {
        String errorMessage;
        if (loginStatus == null) {
            errorMessage = "Our server is under maintenance. Please try again later!";
        } else {
            errorMessage = "User credential mismatch. Please try again!";
        }
        req.setAttribute("error", errorMessage);
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }
}
