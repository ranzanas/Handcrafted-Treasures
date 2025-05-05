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

/**
 * Servlet implementation class EditProfileController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/editProfile" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileController() {
        super();
        // TODO Auto-generated constructor stub
    } 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Integer userId = (Integer) request.getSession().getAttribute("userId");

		    if (userId == null) {
		        response.sendRedirect("login.jsp");
		        return;
		    }

		    // Get form fields
		    String fullName = request.getParameter("fullName");
		    
		    String address = request.getParameter("address");
		    String dob = request.getParameter("dob");
		    String email = request.getParameter("email");
		    String number = request.getParameter("number");

		    // Handle image upload (if selected)
		    Part filePart = request.getPart("image");
		    String imagePath = null;

		    if (filePart != null && filePart.getSize() > 0) {
		    	 ImageUtil imageUtil = new ImageUtil();
		         boolean isUploaded = imageUtil.uploadImage(filePart, getServletContext().getRealPath(""), "profile_images");

		         if (isUploaded) {
		             // Set image path after successful upload
		             imagePath = "resources/images/profile_images/" + imageUtil.getImageNameFromPart(filePart);
		         }
		    }

		    // Populate model
		    UserModel user = new UserModel();
		    user.setId(userId);
		    user.setFullName(fullName);
		    
		    user.setAddress(address);
		    user.setDob(LocalDate.parse(dob));
		    user.setEmail(email);
		    user.setNumber(number);

		    if (imagePath != null) {
		        user.setImagePath(imagePath);
		    }

		    // Call service to update user
		    UserProfileService service = new UserProfileService();
		    boolean updated = service.updateUser(user);

		    if (updated) {
		        response.sendRedirect("userProfile");
		    } else {
		        request.setAttribute("updateError", "Profile update failed.");
		        request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);
		    }
	}

}
