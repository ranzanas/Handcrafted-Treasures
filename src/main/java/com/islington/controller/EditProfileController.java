package com.islington.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.LocalDate;

import com.islington.model.UserModel;
import com.islington.service.UserProfileService;
import com.islington.util.ImageUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/editProfile" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class EditProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditProfileController() {
        super();
    }

    /**
     * Handles GET request to load the edit profile page with existing user data.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (userId == null) {
            // Redirect to login if user is not authenticated
            response.sendRedirect("login.jsp");
            return;
        }

        // Fetch current user details
        UserProfileService service = new UserProfileService();
        UserModel user = service.getUserDetails(userId);

        // Pass user data to editProfile.jsp
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);
    }

    /**
     * Handles POST request to update user profile with validation and optional image upload.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (userId == null) {
            // User not logged in
            response.sendRedirect("login.jsp");
            return;
        }

        // Fetch form inputs
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String number = request.getParameter("number");

        UserProfileService service = new UserProfileService();
        String errorMessage = null;

        try {
            LocalDate parsedDob = LocalDate.parse(dob);
            LocalDate today = LocalDate.now();
            LocalDate minAllowedDob = today.minusYears(16);

            // Validate date of birth and phone number
            if (parsedDob.isAfter(today)) {
                errorMessage = "Date of birth cannot be in the future.";
            } else if (parsedDob.isAfter(minAllowedDob)) {
                errorMessage = "You must be at least 16 years old.";
            } else if (!isValidPhoneNumber(number)) {
                errorMessage = "Phone number must start with +977 and contain exactly 10 digits after that.";
            } else if (service.isPhoneUsedByOtherUser(userId, number)) {
                errorMessage = "Phone number is already used by another account.";
            }

            // If validation fails, reload form with error
            if (errorMessage != null) {
                request.setAttribute("updateError", errorMessage);
                request.setAttribute("user", service.getUserDetails(userId));
                request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);
                return;
            }

            // Optional image upload
            Part filePart = request.getPart("image");
            String imagePath = null;
            if (filePart != null && filePart.getSize() > 0) {
                ImageUtil imageUtil = new ImageUtil();
                boolean isUploaded = imageUtil.uploadImage(filePart, "people", request);
                if (isUploaded) {
                    imagePath = "resources/img/people/" + imageUtil.getImageNameFromPart(filePart);
                }
            }

            // Create updated user model
            UserModel user = new UserModel();
            user.setId(userId);
            user.setFullName(fullName);
            user.setAddress(address);
            user.setDob(parsedDob);
            user.setEmail(email);
            user.setNumber(number);
            if (imagePath != null) {
                user.setImagePath(imagePath);
            }

            // Update user in database
            boolean updated = service.updateUser(user);

            if (updated) {
                response.sendRedirect("userProfile");
            } else {
                request.setAttribute("updateError", "Profile update failed.");
                request.setAttribute("user", service.getUserDetails(userId));
                request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("updateError", "Invalid input or unexpected error.");
            request.setAttribute("user", service.getUserDetails(userId));
            request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);
        }
    }

    /**
     * Validates Nepali phone number format: +977 followed by exactly 10 digits.
     */
    private boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("\\+977\\d{10}");
    }
}
