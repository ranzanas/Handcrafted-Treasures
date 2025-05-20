package com.islington.service;

import com.islington.config.DbConfig;
import com.islington.model.OrderModel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderListService {
    private Connection conn;

    public OrderListService() {
        try {
            conn = DbConfig.getDbConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

                // TEMP: Store user/product names in description fields
                order.setUserName(rs.getString("userFullName"));
                order.setProductName(rs.getString("productName"));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

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
}

