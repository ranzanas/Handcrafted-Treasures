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

/**
 * Service class responsible for order placement,
 * inserting order data into the database and updating stock levels.
 */
public class OrderService {
    private final Connection dbConn;

    /**
     * Constructor initializes the database connection.
     */
    public OrderService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (Exception e) {
            throw new RuntimeException("Database connection failed.", e);
        }
    }

    /**
     * Creates a new order, stores order data, links user & products, and updates product stock.
     * 
     * @param userId    ID of the user placing the order
     * @param quantity  Total quantity of products in the order
     * @param address   Delivery address
     * @param amount    Total amount to be paid
     * @param method    Payment method (e.g., Cash, Card)
     * @param items     List of cart items included in the order
     * @return the generated order ID if successful, -1 otherwise
     */
    public int createOrder(int userId, int quantity, String address, double amount, String method, List<CartModel> items) {
        int orderId = -1;

        try {
            dbConn.setAutoCommit(false); // Begin transaction

            // Step 1: Insert order details into `orders` table
            String orderSQL = "INSERT INTO orders (orderQuantity, orderDate, deliveryAddress, totalAmount, paymentMethod) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = dbConn.prepareStatement(orderSQL, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, quantity);
                ps.setDate(2, Date.valueOf(LocalDate.now()));
                ps.setString(3, address);
                ps.setDouble(4, amount);
                ps.setString(5, method);
                ps.executeUpdate();

                // Retrieve generated orderId
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            }

            // Step 2: Link user to each product in `userprodord` table
            String userOrderSQL = "INSERT INTO userprodord (userId, productId, orderId) VALUES (?, ?, ?)";
            try (PreparedStatement ps = dbConn.prepareStatement(userOrderSQL)) {
                for (CartModel item : items) {
                    ps.setInt(1, userId);
                    ps.setInt(2, item.getProductId());
                    ps.setInt(3, orderId);
                    ps.addBatch();
                }
                ps.executeBatch(); // Execute all inserts in one go
            }

            // Step 3: Deduct stock from each product
            for (CartModel item : items) {
                updateProductStock(item.getProductId(), item.getCartProductQuantity());
            }

            dbConn.commit(); // ✅ Commit transaction on success
        } catch (Exception e) {
            try {
                dbConn.rollback(); // ❌ Rollback transaction on failure
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

        return orderId;
    }

    /**
     * Updates product quantity in stock after purchase.
     *
     * @param productId        ID of the purchased product
     * @param quantityOrdered  Quantity purchased
     */
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
