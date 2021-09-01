package com.restfullProject.restfullProject.ui.model.response;

import java.util.List;

public class UserRest {

	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private List<AddressRest> address ;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<AddressRest> getAddress() {
		return address;
	}

	public void setAddress(List<AddressRest> address) {
		this.address = address;
	}
	
	

}
