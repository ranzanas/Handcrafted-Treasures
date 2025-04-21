package com.islington.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.islington.config.DbConfig;
import com.islington.model.UserModel;

public class UserProfileService {
	  private Connection dbConn;
	  
	    public UserProfileService() {
	        try {
	            dbConn = DbConfig.getDbConnection(); // Adjust according to your DB config class
	        } catch (SQLException | ClassNotFoundException ex) {
	            ex.printStackTrace();
	        }
	    }
	    
	    public UserModel getUserDetails(int userId) {
	        UserModel user = null;
	        String query = "SELECT user_id, userFullName, user_userName, userAddress, userDOB, userEmail, userPhone FROM users WHERE user_userName = ?";
	        
	        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	            stmt.setInt(1, userId);
	            ResultSet result = stmt.executeQuery();

	            if (result.next()) {
	                user = new UserModel();
	                user.setId(result.getInt("user_id"));
	                user.setFullName(result.getString("userFullName"));
	                user.setUserName(result.getString("user_userName"));
	                user.setAddress(result.getString("userAddress"));
	                user.setDob(result.getDate("userDOB").toLocalDate());
	                user.setEmail(result.getString("userEmail"));
	                user.setNumber(result.getString("userPhone"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return user;
	    }
}
