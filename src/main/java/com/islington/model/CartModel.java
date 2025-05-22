package com.islington.model;

import java.time.LocalDate;

/**
 * Represents an item in a user's shopping cart.
 * Includes both cart-specific data and related product details.
 */
public class CartModel {
	private int cartId;                  // Unique identifier for the cart entry
	private int userId;                  // ID of the user who owns the cart
	private int productId;               // ID of the product added to the cart
	private LocalDate cartCreatedDate;   // Date the item was added to the cart
	private int cartProductQuantity;     // Quantity of the product in the cart

	// Additional product details for display purposes
	private String productName;          // Name of the product
    private String productImage;         // Image URL or path of the product
    private double productPrice;         // Price per unit of the product

    /**
     * Default constructor.
     */
	public CartModel() {
	}

	/**
	 * Parameterized constructor to initialize all fields.
	 */
	public CartModel(int cartId, int userId, int productId, LocalDate cartCreatedDate,
					 int cartProductQuantity, String productName, String productImage, double productPrice) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.productId = productId;
		this.cartCreatedDate = cartCreatedDate;
		this.cartProductQuantity = cartProductQuantity;
		this.productName = productName;
		this.productImage = productImage;
		this.productPrice = productPrice;
	}

	// Getters and Setters
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public LocalDate getCartCreatedDate() {
		return cartCreatedDate;
	}

	public void setCartCreatedDate(LocalDate cartCreatedDate) {
		this.cartCreatedDate = cartCreatedDate;
	}

	public int getCartProductQuantity() {
		return cartProductQuantity;
	}

	public void setCartProductQuantity(int cartProductQuantity) {
		this.cartProductQuantity = cartProductQuantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
}
