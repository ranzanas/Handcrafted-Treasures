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

    public UserProfile() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (userId == null) {
        	response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UserModel user = userProfileService.getUserDetails(userId);

        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/pages/userProfile.jsp").forward(request, response);
        } else {
            response.sendRedirect("errorPage.jsp");
        }
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (userId == null) {
        	 response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Collect form data
        String fullName = request.getParameter("fullName");
        String userName = request.getParameter("userName");
        String address = request.getParameter("address");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String number = request.getParameter("number");

        // Populate UserModel with updated data
        UserModel user = new UserModel();
        user.setId(userId);
        user.setFullName(fullName);
        user.setUserName(userName);
        user.setAddress(address);
        user.setDob(java.time.LocalDate.parse(dob));
        user.setEmail(email);
        user.setNumber(number);

        boolean isUpdated = userProfileService.updateUser(user);

        if (isUpdated) {
            response.sendRedirect("userProfile"); // Refresh to reflect changes
        } else {
            request.setAttribute("updateError", "Profile update failed.");
            doGet(request, response);
        }
    }
}
