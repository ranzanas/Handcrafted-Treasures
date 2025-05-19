package com.islington.model;

import java.time.LocalDate;

public class UserModel {
	private int id;
	private String fullName;
	private String userName;
	private String address;
	private LocalDate dob;
	private String email;
	private String number;
	private String password;
	private String role;
	private String imagePath;
	public UserModel() {
	}

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
		this.role = "Customer";
		this.imagePath = imagePath;

	}

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
}
