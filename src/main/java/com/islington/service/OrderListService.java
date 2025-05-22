package com.islington.service;

import com.islington.config.DbConfig;
import com.islington.model.OrderModel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for handling order-related operations such as
 * retrieving all orders, user-specific orders, and total revenue.
 */
public class OrderListService {
    private Connection conn;

    /**
     * Constructor initializes the database connection.
     */
    public OrderListService() {
        try {
            conn = DbConfig.getDbConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all orders along with user and product info.
     * Useful for admin dashboards to display full order details.
     *
     * @return List of OrderModel objects
     */
    public List<OrderModel> getAllOrdersWithUserInfo() {
        List<OrderModel> orders = new ArrayList<>();

        String sql = """
            SELECT o.orderId, o.orderQuantity, o.orderDate, o.deliveryAddress, o.totalAmount, o.paymentMethod,
                   u.userFullName, p.productName
            FROM orders o
            JOIN userprodord uop ON o.orderId = uop.orderId
            JOIN users u ON u.userId = uop.userId
            JOIN products p ON p.productId = uop.productId
            ORDER BY o.orderId DESC
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                OrderModel order = new OrderModel();
                order.setOrderId(rs.getInt("orderId"));
                order.setOrderQuantity(rs.getInt("orderQuantity"));
                order.setOrderDate(rs.getDate("orderDate").toLocalDate());
                order.setDeliveryAddress(rs.getString("deliveryAddress"));
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setPaymentMethod(rs.getString("paymentMethod"));
                order.setUserName(rs.getString("userFullName"));
                order.setProductName(rs.getString("productName"));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    /**
     * Calculates the total revenue from all orders.
     *
     * @return double value representing total sales revenue
     */
    public double getTotalRevenue() {
        String sql = "SELECT SUM(totalAmount) FROM orders";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Retrieves all orders placed by a specific user.
     * Includes product details for each order.
     *
     * @param userId ID of the user
     * @return List of OrderModel objects
     */
    public List<OrderModel> getOrdersByUserId(int userId) {
        List<OrderModel> orders = new ArrayList<>();
        String sql = """
            SELECT o.orderId, o.orderDate, o.orderQuantity, o.deliveryAddress, o.totalAmount, o.paymentMethod,
                   p.productName, p.productImage
            FROM orders o
            JOIN userprodord uop ON o.orderId = uop.orderId
            JOIN products p ON p.productId = uop.productId
            WHERE uop.userId = ?
            ORDER BY o.orderDate DESC
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderModel order = new OrderModel();
                order.setOrderId(rs.getInt("orderId"));
                order.setOrderDate(rs.getDate("orderDate").toLocalDate());
                order.setOrderQuantity(rs.getInt("orderQuantity"));
                order.setDeliveryAddress(rs.getString("deliveryAddress"));
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setPaymentMethod(rs.getString("paymentMethod"));
                order.setProductName(rs.getString("productName"));
                order.setProductImage(rs.getString("productImage"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}
