package com.restfullProject.restfullProject.shared.dto;

public class AddressDto {
	
	private long id;
	private String addressId ;
	private String city;
	private String country;
	private String streatName;
	private String postalCode;
	private String type;
	private UserDto userDetials;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreatName() {
		return streatName;
	}

	public void setStreatName(String streatName) {
		this.streatName = streatName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserDto getUserDetials() {
		return userDetials;
	}

	public void setUserDetials(UserDto userDetials) {
		this.userDetials = userDetials;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	

}
