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

            // 1. Insert into orders table
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

            // 2. Insert into userprodord table
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

            // 3. Deduct stock from products
            for (CartModel item : items) {
                updateProductStock(item.getProductId(), item.getCartProductQuantity());
            }

            dbConn.commit(); // ✅ Commit transaction if all succeeds
        } catch (Exception e) {
            try {
                dbConn.rollback(); // ❌ Rollback on failure
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return orderId;
    }

    // ✅ Deduct quantity from product stock
    public void updateProductStock(int productId, int quantityOrdered) {
        String query = "UPDATE products SET productQuantity = productQuantity - ? WHERE productId = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, quantityOrdered);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
