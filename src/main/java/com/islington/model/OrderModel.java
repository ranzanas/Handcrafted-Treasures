package com.islington.model;

import java.time.LocalDate;

/**
 * Represents an order placed by a user.
 * Includes order details, delivery info, payment method,
 * and additional display-related user/product fields.
 */
public class OrderModel {

	private int orderId;                // Unique identifier for the order
	private int orderQuantity;         // Total quantity of products in the order
	private LocalDate orderDate;       // Date the order was placed
	private String deliveryAddress;    // Address where the order should be delivered
	private double totalAmount;        // Total price of the order
	private String paymentMethod;      // Payment method used by the user

	// Additional fields for display purposes
	private String userName;           // Name of the user who placed the order
	private String productName;        // Name of the product (if showing per-item)
	private String productImage;       // Image of the product (for UI display)

	/**
	 * Default constructor.
	 */
	public OrderModel() {
	}

	/**
	 * Constructor to initialize core order fields.
	 */
	public OrderModel(int orderId, int orderQuantity, LocalDate orderDate,
					  String deliveryAddress, double totalAmount, String paymentMethod) {
		super();
		this.orderId = orderId;
		this.orderQuantity = orderQuantity;
		this.orderDate = orderDate;
		this.deliveryAddress = deliveryAddress;
		this.totalAmount = totalAmount;
		this.paymentMethod = paymentMethod;
	}

	// Getters and Setters

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
}
