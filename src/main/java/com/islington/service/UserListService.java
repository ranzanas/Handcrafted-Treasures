package com.islington.service;

import java.sql.*;
import java.util.*;

import com.islington.config.DbConfig;
import com.islington.model.UserModel;

public class UserListService {
    private Connection conn;

    public UserListService() {
        try {
            conn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<UserModel> getAllUsersWithFeedback() {
        List<UserModel> users = new ArrayList<>();
        String sql = "SELECT u.userId, u.userFullName, u.user_userName, u.userEmail, u.userPhone, " +
                "u.userAddress, u.userDOB, u.userRole, f.feedbackDescription " +
                "FROM users u " +
                "LEFT JOIN feedbacks f ON u.userId = f.userId " +
                "WHERE u.userRole != 'Admin' " +
                "ORDER BY u.userId";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UserModel user = new UserModel();
                user.setId(rs.getInt("userId"));
                user.setFullName(rs.getString("userFullName"));
                user.setUserName(rs.getString("user_userName"));
                user.setEmail(rs.getString("userEmail"));
                user.setNumber(rs.getString("userPhone"));
                user.setAddress(rs.getString("userAddress"));
                user.setDob(rs.getDate("userDOB").toLocalDate());
                user.setTempFeedback(rs.getString("feedbackDescription")); // Temp field for display
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public int getTotalUserCount() {
        String sql = "SELECT COUNT(*) FROM users";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
