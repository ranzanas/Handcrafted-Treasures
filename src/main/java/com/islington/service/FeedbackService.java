package com.islington.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.islington.config.DbConfig;
import com.islington.model.FeedbackModel;

/**
 * Service class responsible for handling operations related to user feedback.
 */
public class FeedbackService {
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public FeedbackService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves a feedback entry into the database.
	 *
	 * @param feedback FeedbackModel containing userId, description, and date
	 * @return true if the feedback was saved successfully, false otherwise
	 */
	public boolean saveFeedback(FeedbackModel feedback) {
		String sql = "INSERT INTO feedbacks (userId, feedbackDescription, feedbackDate) VALUES (?, ?, ?)";

		try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
			stmt.setInt(1, feedback.getUserId());
			stmt.setString(2, feedback.getFeedbackDescription());
			stmt.setDate(3, Date.valueOf(feedback.getFeedbackDate()));

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
