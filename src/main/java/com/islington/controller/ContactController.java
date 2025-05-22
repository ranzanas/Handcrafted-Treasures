package com.islington.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import com.islington.model.FeedbackModel;
import com.islington.service.FeedbackService;

/**
 * Servlet implementation class ContactController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/contact" })
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Service to handle saving feedback to the database
	private FeedbackService feedbackService = new FeedbackService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles GET requests to load the contact form page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forward user to the contact.jsp page
		request.getRequestDispatcher("/WEB-INF/pages/contact.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests to submit feedback.
	 * Ensures the user is logged in, collects feedback data, and saves it.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get logged-in user ID from session
		Integer userId = (Integer) request.getSession().getAttribute("userId");

        // Redirect to login if user is not authenticated
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Get feedback description from form
        String description = request.getParameter("feedbackDescription");
        LocalDate date = LocalDate.now(); // Set current date

        // Create and populate FeedbackModel
        FeedbackModel feedback = new FeedbackModel();
        feedback.setUserId(userId);
        feedback.setFeedbackDescription(description);
        feedback.setFeedbackDate(date);

        // Save feedback to the database
        feedbackService.saveFeedback(feedback);

        // Set thank you message and reload the contact page
        request.setAttribute("message", "Thank you for your feedback!");
        request.getRequestDispatcher("WEB-INF/pages/contact.jsp").forward(request, response);
	}
}
