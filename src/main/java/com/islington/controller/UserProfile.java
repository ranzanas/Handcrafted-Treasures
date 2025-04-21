package com.islington.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.islington.model.UserModel;
import com.islington.service.UserProfileService;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/userProfile" })
public class UserProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserProfileService userProfileService = new UserProfileService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfile() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the logged-in user's ID from the session (assuming session stores the userId)
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        // If the userId is not in the session, redirect to login page
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Fetch user details using the UserProfileService
        UserModel user = userProfileService.getUserDetails(userId);

        // If user is found, set it as an attribute and forward to the JSP
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/pages/userProfile.jsp").forward(request, response);
        } else {
            // If user is not found, redirect to an error page or show a message
            response.sendRedirect("errorPage.jsp");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        doGet(request, response);
    }
}

