package com.islington.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.islington.config.DbConfig;
import com.islington.model.UserModel;

/**
 * RegisterService handles the registration of new users (e.g., customers or students).
 * It manages user insertion and validation-related database operations.
 */
public class RegisterService {

	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public RegisterService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Inserts a new user into the database.
	 *
	 * @param userModel the user to register
	 * @return true if registration was successful, null if connection fails or error occurs
	 */
	public Boolean addUser(UserModel userModel) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String insertQuery = "INSERT INTO users (userFullName, user_userName, userAddress, userDOB, userEmail, userPhone, userPassword, userRole, userImagePath) "
		                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {
			insertStmt.setString(1, userModel.getFullName());
			insertStmt.setString(2, userModel.getUserName());
			insertStmt.setString(3, userModel.getAddress());
			insertStmt.setDate(4, Date.valueOf(userModel.getDob()));
			insertStmt.setString(5, userModel.getEmail());
			insertStmt.setString(6, userModel.getNumber());
			insertStmt.setString(7, userModel.getPassword());
			insertStmt.setString(8, userModel.getRole());
			insertStmt.setString(9, userModel.getImagePath());

			return insertStmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error during user registration: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Checks if a phone number is already registered.
	 *
	 * @param phoneNumber the phone number to check
	 * @return true if it exists, false otherwise
	 */
	public boolean isPhoneExists(String phoneNumber) {
		String query = "SELECT COUNT(*) FROM users WHERE userPhone = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, phoneNumber);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Checks if a username is already taken.
	 *
	 * @param username the username to check
	 * @return true if it exists, false otherwise
	 */
	public boolean isUsernameExists(String username) {
		String query = "SELECT COUNT(*) FROM users WHERE user_userName = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
