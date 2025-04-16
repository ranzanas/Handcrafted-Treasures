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

import com.islington.model.ProgramModel;
import com.islington.model.StudentModel;
import com.islington.service.RegisterService;
import com.islington.util.ImageUtil;
import com.islington.util.PasswordUtil;

/**
 * Servlet implementation class Registration
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Registration" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
			maxFileSize = 1024 * 1024 * 10, // 10MB
			maxRequestSize = 1024 * 1024 * 50) // 50MB
		
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final RegisterService registerService = new RegisterService();
	private final ImageUtil imageUtil = new ImageUtil();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			StudentModel studentModel = extractStudentModel(request);
			Boolean isAdded = registerService.addStudent(studentModel);
			
			if (isAdded == null) {
				handleError(request, response, "Our server is under maintenance. Please try again later!");
			}
			else if(isAdded) {
				if (uploadImage(request)) {
					response.sendRedirect(request.getContextPath() + "/login");
				} else {
					handleError(request, response, "Could not upload the image. Please try again later!");
				}
			}
			else {
				handleError(request, response, "Could not register your account. Please try again later!");
			}
		}
		catch (Exception e) {
			handleError(request, response, "An unexpected error occurred. Please try again later!");
			e.printStackTrace(); // Log the exception
		}
	
	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String username = request.getParameter("username");
	    String birthday = request.getParameter("dob");
	    String email = request.getParameter("email");
	    String phone = request.getParameter("phoneNumber");
	    String password = request.getParameter("password");
	    String retypePassword = request.getParameter("retypePassword");

	    String errorMessage = null;
	    
	 // 1. Name Format Validation

	    if (!isValidName(firstName) || !isValidName(lastName)) {
	    	errorMessage = "Invalid name format! Please check your first and last name";
	    }


	    // 2. Minimum Username Length Requirement and Special Character Validation

	    else if (!isValidUsername(username)) {
	    	errorMessage = "Username must 6 characters long and cannot be special characters";
	    }


	    // 3. Birthday Date Restriction
	    else if (!isValidBirthday(birthday)) {
	    	errorMessage = "Please enter valid birthday!";
	    }


	    // 4. Phone Number Format Requirement

	    else if (!isValidPhoneNumber(phone)) {
	    	errorMessage = "Please enter valid number!";
	    }

	    // 5. Password Complexity Requirement and Matching Passwords

	    else  if (!isValidPassword(password, retypePassword)) {

	    	errorMessage = "Please enter strong password";
	    }
	    
	    if (errorMessage != null) {
	        System.out.println("Error: " + errorMessage);  // Show error in Eclipse console
	        request.setAttribute("errorMessage", errorMessage);
	        request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
	    } else {
	        System.out.println("Registration successful for: " + username); // Success message in console
	        request.setAttribute("successMessage", "Registration successful!");
	    }
	}

	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}

		private boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
			Part image = req.getPart("image");
			return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "student");
		}

		private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
				throws ServletException, IOException {
			req.setAttribute("error", message);
			req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
		}

		private StudentModel extractStudentModel(HttpServletRequest req) throws Exception {
			
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String username = req.getParameter("username");
			LocalDate dob = LocalDate.parse(req.getParameter("dob"));
			String gender = req.getParameter("gender");
			String email = req.getParameter("email");
			String number = req.getParameter("phoneNumber");
			String subject = req.getParameter("subject");

			String password = req.getParameter("password");

			// Assuming password validation is already done in validateRegistrationForm
			
			password = PasswordUtil.encrypt(username, password);

			Part image = req.getPart("image");
			String imageUrl = imageUtil.getImageNameFromPart(image);

			ProgramModel programModel = new ProgramModel(subject);
			return new StudentModel(0, firstName, lastName, username, dob, gender, email, number, password, programModel, imageUrl);
	}

		// Helper methods for validations

	    private boolean isValidName(String name) {

	    // Implement name validation logic
	    return !name.matches(".*\\d.*") && !name.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

	    }
	    
	    private boolean isValidUsername(String username) {

	    // Implement username validation logic

	    return username.length() > 6 && !username.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

	    }
	    
	    private boolean isValidBirthday(String birthday) {
	    	LocalDate birthDate = LocalDate.parse(birthday);
	    	return birthDate.isBefore(LocalDate.now());
	    }


	    private boolean isValidPhoneNumber(String phone) {

	    // Implement phone number validation logic

	    return phone.startsWith("+") && phone.length() == 14;

	    }


	    private boolean isValidPassword(String password, String retypePassword) {

	    // Implement password validation logic

	    return password.length() > 6 && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")

	    && password.matches(".*[A-Z].*") && password.equals(retypePassword);

	    }

}

