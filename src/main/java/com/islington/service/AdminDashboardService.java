package com.islington.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.islington.config.DbConfig;
import com.islington.model.UserModel;

public class AdminDashboardService {
	private Connection conn;

    public AdminDashboardService() {
        try {
            conn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
	public List<UserModel> getUsersWithFeedback() {
	    List<UserModel> users = new ArrayList<>();
	    String sql = "SELECT u.userId, u.userFullName, u.user_userName, u.userEmail, u.userPhone, " +
	                 "u.userAddress, u.userDOB, f.feedbackDescription " +
	                 "FROM users u " +
	                 "LEFT JOIN feedbacks f ON u.userId = f.userId " +
	                 "ORDER BY u.userId";

	    try (PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            UserModel user = new UserModel();
	            user.setId(rs.getInt("userId"));
	            user.setFullName(rs.getString("userFullName"));
	            user.setUserName(rs.getString("user_userName"));
	            user.setEmail(rs.getString("userEmail"));
	            user.setRole(rs.getString("userPhone"));
	            user.setAddress(rs.getString("userAddress"));
	            user.setDob(rs.getDate("userDOB").toLocalDate());
	            

	            users.add(user);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return users;
	}

}
