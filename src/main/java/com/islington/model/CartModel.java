package com.islington.model;

import java.time.LocalDate;

public class CartModel {
	private int cartId;
	private int userId;
	private int productId;
	private LocalDate cartCreatedDate;
	private int cartProductQuantity;
	
	private String productName;
    private String productImage;
    private double productPrice;
	public CartModel() {
	}

	public CartModel(int cartId, int userId, int productId,   LocalDate cartCreatedDate,
			int cartProductQuantity,String productName, String productImage, double productPrice) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.setProductId(productId);
		this.cartCreatedDate = cartCreatedDate;
		this.cartProductQuantity = cartProductQuantity;
		this.productName= productName;
		this.productImage = productImage;
		this.productPrice = productPrice;
	}

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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}
