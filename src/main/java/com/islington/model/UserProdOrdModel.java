package com.islington.model;

/**
 * Represents a mapping between a user, a product, and an order.
 * Typically used to track which user purchased which product in which order.
 * Useful for many-to-many relationships like user_orders, order_products.
 */
public class UserProdOrdModel {
	private int userId;     // ID of the user who placed the order
	private int productId;  // ID of the product ordered
	private int orderId;    // ID of the order

	/**
	 * Default constructor.
	 */
	public UserProdOrdModel() {
	}

	/**
	 * Parameterized constructor to initialize all fields.
	 */
	public UserProdOrdModel(int orderId, int userId, int productId) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;
	}

	// Getters and Setters

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
