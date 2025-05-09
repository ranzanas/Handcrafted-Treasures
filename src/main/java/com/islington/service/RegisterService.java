package com.islington.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.islington.config.DbConfig;
import com.islington.model.UserModel;

/**
 * RegisterService handles the registration of new students. It manages database
 * interactions for student registration.
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
	 * Registers a new student in the database.
	 *
	 * @param studentModel the student details to be registered
	 * @return Boolean indicating the success of the operation
	 */
	public Boolean addUser(UserModel userModel) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		/*String programQuery = "SELECT program_id FROM program WHERE name = ?";*/
		String insertQuery = "INSERT INTO users (userFullName, user_userName, userAddress, userDOB, userEmail, userPhone, userPassword, userRole) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


		try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {

			// Fetch program ID
			/*programStmt.setString(1, studentModel.getProgram().getName());
			ResultSet result = programStmt.executeQuery();
			int programId = result.next() ? result.getInt("program_id") : 1;*/

			// Insert user details
            insertStmt.setString(1, userModel.getFullName());
            insertStmt.setString(2, userModel.getUserName());
            insertStmt.setString(3, userModel.getAddress());
            insertStmt.setDate(4, Date.valueOf(userModel.getDob()));
            insertStmt.setString(5, userModel.getEmail());
            insertStmt.setString(6, userModel.getNumber());
            insertStmt.setString(7, userModel.getPassword());
            insertStmt.setString(8, userModel.getRole());

			return insertStmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error during student registration: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}