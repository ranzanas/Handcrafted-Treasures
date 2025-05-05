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
	        String query = "SELECT userId, userFullName, user_userName, userAddress, userDOB, userEmail, userPhone, userImagePath FROM users WHERE userId = ?";
	        
	        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	            stmt.setInt(1, userId);
	            ResultSet result = stmt.executeQuery();

	            if (result.next()) {
	                user = new UserModel();
	                user.setId(result.getInt("userId"));
	                user.setFullName(result.getString("userFullName"));
	                user.setUserName(result.getString("user_userName"));
	                user.setAddress(result.getString("userAddress"));
	                user.setDob(result.getDate("userDOB").toLocalDate());
	                user.setEmail(result.getString("userEmail"));
	                user.setNumber(result.getString("userPhone"));
	                user.setImagePath(result.getString("userImagePath"));

	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return user;
	    }
	    
	    public boolean updateUser(UserModel user) {
	    	
	        String sql = "UPDATE users SET userFullName = ?, user_userName = ?, userAddress = ?, userDOB = ?, userEmail = ?, userPhone = ? , userImagePath = ? WHERE userId = ?";
	        
	        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
	        	stmt.setString(1, user.getFullName());
	        	stmt.setString(2, user.getUserName());
	        	stmt.setString(3, user.getAddress());
	        	stmt.setDate(4, java.sql.Date.valueOf(user.getDob()));
	        	stmt.setString(5, user.getEmail());
	        	stmt.setString(6, user.getNumber());
	        	stmt.setString(7, user.getImagePath());  
	        	stmt.setInt(8, user.getId());   


	            int rowsAffected = stmt.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

}
