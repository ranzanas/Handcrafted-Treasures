package com.islington.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.islington.config.DbConfig;
import com.islington.model.ProductModel;

public class ProductService {
    private Connection dbConn;

    public ProductService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
}
