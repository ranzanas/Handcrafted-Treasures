package com.islington.model;

/**
 * Represents a product listed in the e-commerce system.
 * Includes basic information like name, description, price, quantity,
 * status (e.g., in stock/out of stock), and image reference.
 */
public class ProductModel {
	private int productId;              // Unique identifier for the product
	private String productName;         // Name of the product
	private String productDescription;  // Description or details of the product
	private double productPrice;        // Price per unit of the product
	private int productQuantity;        // Quantity available in stock
	private String productStatus;       // Stock status (e.g., "In Stock", "Out of Stock")
	private String productImage;        // Path or URL to the product image

	/**
	 * Default constructor.
	 */
	public ProductModel() {
	}

	/**
	 * Parameterized constructor to initialize all product attributes.
	 */
	public ProductModel(int productId, String productName, String productDescription, double productPrice, 
						int productQuantity, String productStatus, String productImage) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.productStatus = productStatus;
		this.productImage = productImage;
	}

	// Getters and Setters

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
}
