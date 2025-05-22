package com.islington.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.islington.config.DbConfig;
import com.islington.model.UserModel;

/**
 * Service class to manage user profile operations like viewing, updating,
 * and validating user information.
 */
public class UserProfileService {
    private Connection dbConn;

    /**
     * Initializes the database connection using the configuration class.
     */
    public UserProfileService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves user details by their ID.
     *
     * @param userId ID of the user
     * @return UserModel containing user details, or null if not found
     */
    public UserModel getUserDetails(int userId) {
        UserModel user = null;
        String query = """
            SELECT userId, userFullName, user_userName, userAddress, 
                   userDOB, userEmail, userPhone, userImagePath
            FROM users
            WHERE userId = ?
        """;

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

    /**
     * Updates user profile details in the database.
     *
     * @param user UserModel containing updated information
     * @return true if the update was successful, false otherwise
     */
    public boolean updateUser(UserModel user) {
        String sql = """
            UPDATE users
            SET userFullName = ?, userAddress = ?, userDOB = ?, userEmail = ?, 
                userPhone = ?, userImagePath = ?
            WHERE userId = ?
        """;

        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getAddress());
            stmt.setDate(3, java.sql.Date.valueOf(user.getDob()));
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getNumber());
            stmt.setString(6, user.getImagePath());
            stmt.setInt(7, user.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if the given phone number is already used by another user (excluding the current user).
     *
     * @param userId current user's ID
     * @param phone phone number to check
     * @return true if the phone number is used by another user, false otherwise
     */
    public boolean isPhoneUsedByOtherUser(int userId, String phone) {
        String query = "SELECT COUNT(*) FROM users WHERE userPhone = ? AND userId != ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, phone);
            stmt.setInt(2, userId);
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
