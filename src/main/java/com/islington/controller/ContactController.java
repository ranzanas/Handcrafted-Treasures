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
	private FeedbackService feedbackService = new FeedbackService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/contact.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer userId = (Integer) request.getSession().getAttribute("userId"); // Logged in user

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String description = request.getParameter("feedbackDescription");
        LocalDate date = LocalDate.now();

        FeedbackModel feedback = new FeedbackModel();
        feedback.setUserId(userId);
        feedback.setFeedbackDescription(description);
        feedback.setFeedbackDate(date);

        feedbackService.saveFeedback(feedback);
        request.setAttribute("message", "Thank you for your feedback!");
        request.getRequestDispatcher("WEB-INF/pages/contact.jsp").forward(request, response);
		
	}

}
