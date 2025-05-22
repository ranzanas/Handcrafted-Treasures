package com.islington.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.islington.config.DbConfig;
import com.islington.model.CartModel;

/**
 * Handles business logic and database operations related to the shopping cart.
 */
public class CartService {
    private Connection dbConn;

    /**
     * Constructor initializes database connection from DbConfig.
     */
    public CartService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a product to the user's cart with default quantity 1.
     *
     * @param userId    ID of the user
     * @param productId ID of the product to be added
     * @return true if insertion was successful, false otherwise
     */
    public boolean addToCart(int userId, int productId) {
        String insertQuery = "INSERT INTO cart (userId, productId, cartCreatedDate, cartProductQuantity) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = dbConn.prepareStatement(insertQuery)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.setInt(4, 1); // Default quantity

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Removes an item from the cart based on cart ID and user ID.
     */
    public boolean removeFromCart(int cartId, int userId) {
        String deleteQuery = "DELETE FROM cart WHERE cartId = ? AND userId = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(deleteQuery)) {
            stmt.setInt(1, cartId);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates the quantity of a specific cart item.
     */
    public boolean updateCartQuantity(int cartId, int userId, int quantity) {
        String query = "UPDATE cart SET cartProductQuantity = ? WHERE cartId = ? AND userId = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, cartId);
            stmt.setInt(3, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Clears all items from the cart of a specific user.
     */
    public void clearCart(int userId) {
        String sql = "DELETE FROM cart WHERE userId = ?";
        try (PreparedStatement ps = dbConn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all cart items for a given user along with product details.
     *
     * @param userId ID of the user
     * @return List of CartModel objects
     */
    public List<CartModel> getCartItemsByUserId(int userId) {
        List<CartModel> cartItems = new ArrayList<>();

        String query = "SELECT c.*, p.productName, p.productImage, p.productPrice " +
                       "FROM cart c " +
                       "JOIN products p ON c.productId = p.productId " +
                       "WHERE c.userId = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CartModel item = new CartModel();
                item.setCartId(rs.getInt("cartId"));
                item.setUserId(rs.getInt("userId"));
                item.setProductId(rs.getInt("productId"));
                item.setCartCreatedDate(rs.getDate("cartCreatedDate").toLocalDate());
                item.setCartProductQuantity(rs.getInt("cartProductQuantity"));
                item.setProductName(rs.getString("productName"));
                item.setProductImage(rs.getString("productImage"));
                item.setProductPrice(rs.getDouble("productPrice"));
                cartItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }
}
