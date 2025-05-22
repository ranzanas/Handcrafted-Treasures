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

    private final RegisterService registerService = new RegisterService();
    private final ImageUtil imageUtil = new ImageUtil();

    public Registration() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String birthday = request.getParameter("dob");
        String email = request.getParameter("email");
        String phone = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retypePassword");

        String errorMessage = null;

        // Validation
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

        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
            return;
        }

        try {
            // Upload image first
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

            // Create user model with uploaded image path
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
            handleError(request, response, "An unexpected error occurred. Please try again later!");
            e.printStackTrace();
        }
    }

    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("success", message);
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("errorMessage", message);
        req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
    }

    private UserModel extractUserModel(HttpServletRequest req, String imagePath) throws Exception {
        String fullName = req.getParameter("fullName");
        String username = req.getParameter("username");
        String address = req.getParameter("address");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        String email = req.getParameter("email");
        String number = req.getParameter("phoneNumber");
        String password = req.getParameter("password");

        password = PasswordUtil.encrypt(username, password);

        UserModel user = new UserModel(0, fullName, username, address, dob, email, number, password, imagePath);
        user.setRole("Customer");
        user.setImagePath(imagePath);
        return user;
    }

    // Validation methods
    private boolean isValidName(String fullName) {
        return !fullName.matches(".*\\d.*") &&
               !fullName.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
    }

    private boolean isValidUsername(String username) {
        return username.length() > 6 &&
               !username.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
    }

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

    private boolean isValidPhoneNumber(String phone) {
        return phone.startsWith("+") && phone.length() == 14;
    }

    private boolean isValidPassword(String password, String retypePassword) {
        return password.length() > 6 &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*") &&
               password.matches(".*[A-Z].*") &&
               password.equals(retypePassword);
    }
}
