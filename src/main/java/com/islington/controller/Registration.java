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
import com.islington.service.RegisterService;
import com.islington.util.ImageUtil;
import com.islington.util.PasswordUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/Registration" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class Registration extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Services used for registration and image processing
    private final RegisterService registerService = new RegisterService();
    private final ImageUtil imageUtil = new ImageUtil();

    public Registration() {
        super();
    }

    /**
     * Handles GET request to load the registration form.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
    }

    /**
     * Handles POST request to register a new user.
     * Validates inputs, uploads profile image, encrypts password, and stores user data.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract form inputs
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String birthday = request.getParameter("dob");
        String email = request.getParameter("email");
        String phone = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retypePassword");

        String errorMessage = null;

        // Step 1: Validation
        if (!isValidName(fullName)) {
            errorMessage = "Invalid name format! Please check your full name.";
        } else if (!isValidUsername(username)) {
            errorMessage = "Username must be at least 6 characters long and contain no special characters.";
        } else if (!isValidBirthday(birthday)) {
            errorMessage = "Please enter a valid birthday and user must be above 16 for registration.";
        } else if (registerService.isUsernameExists(username)) {
            errorMessage = "Username already exists. Please choose a different one.";
        } else if (!isValidPhoneNumber(phone)) {
            errorMessage = "Please enter a valid phone number!";
        } else if (registerService.isPhoneExists(phone)) {
            errorMessage = "A user with this phone number already exists.";
        } else if (!isValidPassword(password, retypePassword)) {
            errorMessage = "Please enter a strong password!";
        }

        // Step 2: If validation fails, show error
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
            return;
        }

        try {
            // Step 3: Handle image upload
            Part imagePart = request.getPart("image");
            String imagePath = null;

            if (imagePart != null && imagePart.getSize() > 0) {
                boolean uploaded = imageUtil.uploadImage(imagePart, "people", request);
                if (uploaded) {
                    imagePath = "resources/img/people/" + imageUtil.getImageNameFromPart(imagePart);
                } else {
                    handleError(request, response, "Image upload failed.");
                    return;
                }
            }

            // Step 4: Extract user model and persist
            UserModel userModel = extractUserModel(request, imagePath);
            Boolean isAdded = registerService.addUser(userModel);

            if (isAdded == null) {
                handleError(request, response, "Our server is under maintenance. Please try again later!");
            } else if (isAdded) {
                handleSuccess(request, response, "Your account is successfully created!");
            } else {
                handleError(request, response, "Could not register your account. Please try again later!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            handleError(request, response, "An unexpected error occurred. Please try again later!");
        }
    }

    /**
     * Sends success message and redirects to login page.
     */
    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("success", message);
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    /**
     * Sends error message and reloads registration page.
     */
    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("errorMessage", message);
        req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
    }

    /**
     * Constructs a UserModel from form inputs.
     */
    private UserModel extractUserModel(HttpServletRequest req, String imagePath) throws Exception {
        String fullName = req.getParameter("fullName");
        String username = req.getParameter("username");
        String address = req.getParameter("address");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        String email = req.getParameter("email");
        String number = req.getParameter("phoneNumber");
        String password = req.getParameter("password");

        // Encrypt password using utility
        password = PasswordUtil.encrypt(username, password);

        // Create and return user model
        UserModel user = new UserModel(0, fullName, username, address, dob, email, number, password, imagePath);
        user.setRole("Customer");
        user.setImagePath(imagePath);
        return user;
    }

    // -------------------------------
    // Validation Helper Methods Below
    // -------------------------------

    /**
     * Validates that full name contains no digits or special characters.
     */
    private boolean isValidName(String fullName) {
        return !fullName.matches(".*\\d.*") &&
               !fullName.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
    }

    /**
     * Validates username is at least 6 characters long and has no special characters.
     */
    private boolean isValidUsername(String username) {
        return username.length() > 6 &&
               !username.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
    }

    /**
     * Validates birthday and ensures age is above 16.
     */
    private boolean isValidBirthday(String birthday) {
        try {
            LocalDate birthDate = LocalDate.parse(birthday);
            LocalDate today = LocalDate.now();
            LocalDate minAllowedDob = today.minusYears(16);
            return !birthDate.isAfter(minAllowedDob);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates phone number to start with '+' and have exactly 14 characters (e.g., +977XXXXXXXXXX).
     */
    private boolean isValidPhoneNumber(String phone) {
        return phone.startsWith("+") && phone.length() == 14;
    }

    /**
     * Validates strong password criteria and checks if both password fields match.
     */
    private boolean isValidPassword(String password, String retypePassword) {
        return password.length() > 6 &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*") &&
               password.matches(".*[A-Z].*") &&
               password.equals(retypePassword);
    }
}
