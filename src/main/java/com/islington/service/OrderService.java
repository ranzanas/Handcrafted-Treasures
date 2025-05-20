package com.islington.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import com.islington.config.DbConfig;
import com.islington.model.CartModel;



public class OrderService {
    private final Connection dbConn;

    public OrderService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (Exception e) {
            throw new RuntimeException("Database connection failed.", e);
        }
    }

    public int createOrder(int userId, int quantity, String address, double amount, String method, List<CartModel> items) {
        int orderId = -1;
        try {
            dbConn.setAutoCommit(false);  // start transaction

            // Insert into orders
            String orderSQL = "INSERT INTO orders (orderQuantity, orderDate, deliveryAddress, totalAmount, paymentMethod) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = dbConn.prepareStatement(orderSQL, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, quantity);
                ps.setDate(2, Date.valueOf(LocalDate.now()));
                ps.setString(3, address);
                ps.setDouble(4, amount);
                ps.setString(5, method);
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            }

            // Insert into usersordprod
            String userOrderSQL = "INSERT INTO userprodord (userId, productId, orderId) VALUES (?, ?, ?)";
            try (PreparedStatement ps = dbConn.prepareStatement(userOrderSQL)) {
                for (CartModel item : items) {
                    ps.setInt(1, userId);
                    ps.setInt(2, item.getProductId());
                    ps.setInt(3, orderId);
                    ps.addBatch();
                }
                ps.executeBatch();
            }

            dbConn.commit();
        } catch (Exception e) {
            try { dbConn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        }
        return orderId;
    }
}
