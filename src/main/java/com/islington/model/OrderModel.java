package com.islington.model;

import java.time.LocalDate;

public class OrderModel {
	private int orderId;
	private int orderQuantity;
	private LocalDate orderDate;
	private String deliveryAddress;
	private double totalAmount;
	private String paymentMethod;
	
	private String userName;
	private String productName;
	private String productImage;
	
	public OrderModel() {
	}

	public OrderModel(int orderId, int orderQuantity,  LocalDate orderDate,
			String deliveryAddress, double totalAmount, String paymentMethod) {
		super();
		this.orderId = orderId;
		this.orderQuantity = orderQuantity;
		this.orderDate = orderDate;
		this.deliveryAddress = deliveryAddress;
		this.totalAmount = totalAmount;
		this.paymentMethod = paymentMethod;

	}
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

	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod the paymentMethod to set
	 */
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
