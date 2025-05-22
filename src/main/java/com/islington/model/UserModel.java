package com.islington.model;

import java.time.LocalDate;

/**
 * Represents a user in the system.
 * Includes personal information, login credentials, role, and optional feedback.
 */
public class UserModel {

	private int id;                    // Unique identifier for the user
	private String fullName;           // Full legal name of the user
	private String userName;           // Chosen username for login
	private String address;            // Residential address
	private LocalDate dob;             // Date of birth
	private String email;              // Email address
	private String number;             // Phone number (e.g., +97798XXXXXXXX)
	private String password;           // Encrypted password
	private String role;               // Role (e.g., "Customer", "Admin")
	private String imagePath;          // Path or URL to user's profile picture

	private String tempFeedback;       // Temporary holder for user's feedback (used in admin/user views)

	/**
	 * Default constructor.
	 */
	public UserModel() {
	}

	/**
	 * Constructor with all necessary user fields.
	 */
	public UserModel(int id, String fullName, String userName, String address, LocalDate dob,
					 String email, String number, String password, String imagePath) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.userName = userName;
		this.address = address;
		this.dob = dob;
		this.email = email;
		this.number = number;
		this.password = password;
		this.role = "Customer"; // Default role
		this.imagePath = imagePath;
	}

	// Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getTempFeedback() {
		return tempFeedback;
	}

	public void setTempFeedback(String tempFeedback) {
		this.tempFeedback = tempFeedback;
	}
}
