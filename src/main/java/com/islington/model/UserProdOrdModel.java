package com.islington.model;



public class UserProdOrdModel {
	private int userId;
	private int productId;
	private int orderId;
	public UserProdOrdModel() {
	}

	public UserProdOrdModel(int orderId, int userId, int productId) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;

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
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
}
