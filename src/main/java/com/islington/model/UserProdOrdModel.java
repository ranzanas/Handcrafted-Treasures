package com.islington.model;



public class UserProdOrdModel {
	private int userId;
	private String productName;
	private int orderId;
	public UserProdOrdModel() {
	}

	public UserProdOrdModel(int orderId, int userId, String productName) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.productName = productName;

	}
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
}
