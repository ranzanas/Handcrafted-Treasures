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
 * Handles displaying and updating the logged-in user's profile.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/userProfile" })
public class UserProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Service class for retrieving and updating user details
    private UserProfileService userProfileService = new UserProfileService();

    public UserProfile() {
        super();
    }

    /**
     * Handles GET request to load the user's profile page.
     * Retrieves the user's data from the database using the session userId.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get user ID from session
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        // Redirect to login if not logged in
        if (userId == null) {
        	response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Fetch user details
        UserModel user = userProfileService.getUserDetails(userId);

        if (user != null) {
            // Set user details as request attribute and forward to JSP
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/pages/userProfile.jsp").forward(request, response);
        } else {
            // In case of error fetching user
            response.sendRedirect("errorPage.jsp");
        }
    }

    /**
     * Handles POST request to update user profile information.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer userId = (Integer) request.getSession().getAttribute("userId");

        // Redirect to login if session expired
        if (userId == null) {
        	 response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Collect updated form data from request
        String fullName = request.getParameter("fullName");
        String userName = request.getParameter("userName");
        String address = request.getParameter("address");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String number = request.getParameter("number");

        // Populate UserModel with the new data
        UserModel user = new UserModel();
        user.setId(userId);
        user.setFullName(fullName);
        user.setUserName(userName);
        user.setAddress(address);
        user.setDob(java.time.LocalDate.parse(dob));
        user.setEmail(email);
        user.setNumber(number);

        // Update the user in the database
        boolean isUpdated = userProfileService.updateUser(user);

        if (isUpdated) {
            // Refresh the profile page to show updated info
            response.sendRedirect("userProfile");
        } else {
            // If update fails, show error and reload profile page
            request.setAttribute("updateError", "Profile update failed.");
            doGet(request, response);
        }
    }
}
