package com.islington.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.islington.config.DbConfig;
import com.islington.model.ProductModel;

/**
 * Service class responsible for all product-related operations,
 * including CRUD actions and search functionality.
 */
public class ProductService {
    private Connection dbConn;

    /**
     * Initializes the database connection.
     */
    public ProductService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all products from the database.
     * 
     * @return a list of ProductModel objects
     */
    public List<ProductModel> getAllProducts() {
        List<ProductModel> productList = new ArrayList<>();
        String query = "SELECT productId, productName, productDescription, productPrice, productQuantity, productStatus, productImage FROM products";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setProductDescription(rs.getString("productDescription"));
                product.setProductPrice(rs.getDouble("productPrice"));
                product.setProductQuantity(rs.getInt("productQuantity"));
                product.setProductStatus(rs.getString("productStatus"));
                product.setProductImage(rs.getString("productImage"));

                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    /**
     * Searches for products by keyword in name or description.
     * 
     * @param keyword search term entered by the user
     * @return list of matching ProductModel objects
     */
    public List<ProductModel> searchProducts(String keyword) {
        List<ProductModel> results = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE productName LIKE ? OR productDescription LIKE ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
            String searchTerm = "%" + keyword + "%";
            stmt.setString(1, searchTerm);
            stmt.setString(2, searchTerm);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setProductDescription(rs.getString("productDescription"));
                product.setProductPrice(rs.getDouble("productPrice"));
                product.setProductQuantity(rs.getInt("productQuantity"));
                product.setProductStatus(rs.getString("productStatus"));
                product.setProductImage(rs.getString("productImage"));
                results.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    /**
     * Adds a new product to the database.
     * 
     * @param product ProductModel object to be added
     * @return true if successful, false otherwise
     */
    public boolean addProduct(ProductModel product) {
        String query = "INSERT INTO products (productName, productDescription, productPrice, productQuantity, productStatus, productImage) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getProductDescription());
            stmt.setDouble(3, product.getProductPrice());
            stmt.setInt(4, product.getProductQuantity());
            stmt.setString(5, product.getProductStatus());
            stmt.setString(6, product.getProductImage());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a product only if its quantity is zero.
     * 
     * @param productId ID of the product to be deleted
     * @return true if deleted, false otherwise
     */
    public boolean deleteProductIfQuantityZero(int productId) {
        String checkSQL = "SELECT productQuantity FROM products WHERE productId = ?";
        String deleteSQL = "DELETE FROM products WHERE productId = ?";

        try (PreparedStatement checkStmt = dbConn.prepareStatement(checkSQL)) {
            checkStmt.setInt(1, productId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt("productQuantity") == 0) {
                try (PreparedStatement deleteStmt = dbConn.prepareStatement(deleteSQL)) {
                    deleteStmt.setInt(1, productId);
                    return deleteStmt.executeUpdate() > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Updates an existing product's details.
     * 
     * @param product ProductModel with updated values
     * @return true if update was successful, false otherwise
     */
    public boolean updateProduct(ProductModel product) {
        String sql = "UPDATE products SET productName = ?, productDescription = ?, productPrice = ?, productQuantity = ?, productStatus = ?, productImage = ? WHERE productId = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getProductDescription());
            stmt.setDouble(3, product.getProductPrice());
            stmt.setInt(4, product.getProductQuantity());
            stmt.setString(5, product.getProductStatus());
            stmt.setString(6, product.getProductImage());
            stmt.setInt(7, product.getProductId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Checks if a product name already exists in the database.
     * 
     * @param name product name to check
     * @return true if it exists, false otherwise
     */
    public boolean productNameExists(String name) {
        String query = "SELECT COUNT(*) FROM products WHERE productName = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves a product by its ID.
     * 
     * @param productId ID of the product to fetch
     * @return ProductModel object if found, null otherwise
     */
    public ProductModel getProductById(int productId) {
        ProductModel product = null;
        String sql = "SELECT * FROM products WHERE productId = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new ProductModel();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setProductDescription(rs.getString("productDescription"));
                product.setProductPrice(rs.getDouble("productPrice"));
                product.setProductQuantity(rs.getInt("productQuantity"));
                product.setProductStatus(rs.getString("productStatus"));
                product.setProductImage(rs.getString("productImage"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
}
