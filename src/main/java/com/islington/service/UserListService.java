package com.islington.service;

import java.sql.*;
import java.util.*;

import com.islington.config.DbConfig;
import com.islington.model.UserModel;

/**
 * Service class responsible for retrieving user data for admin purposes,
 * especially those users who are not admins themselves.
 */
public class UserListService {
    private Connection conn;

    /**
     * Constructor initializes the database connection.
     */
    public UserListService() {
        try {
            conn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all non-admin users from the database along with their feedback (if any).
     *
     * @return a list of UserModel objects including optional feedback description
     */
    public List<UserModel> getAllUsersWithFeedback() {
        List<UserModel> users = new ArrayList<>();

        String sql = """
            SELECT u.userId, u.userFullName, u.user_userName, u.userEmail, u.userPhone,
                   u.userAddress, u.userDOB, u.userRole, f.feedbackDescription
            FROM users u
            LEFT JOIN feedbacks f ON u.userId = f.userId
            WHERE u.userRole != 'Admin'
            ORDER BY u.userId
        """;

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

                // Store feedback if available
                String feedback = rs.getString("feedbackDescription");
                if (feedback != null) {
                    user.setTempFeedback(feedback);
                }

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Retrieves the total number of users in the system, including Admins.
     *
     * @return count of all users in the users table
     */
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
