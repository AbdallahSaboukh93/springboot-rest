package com.restfullProject.restfullProject.io.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.restfullProject.restfullProject.shared.dto.UserDto;

@Entity
public class AddressEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9093936692854289339L;

	@Id
	@GeneratedValue
	private long id;

	@Column(length = 30, nullable = false)
	private String addressId;

	@Column(length = 30, nullable = false)
	private String city;

	@Column(length = 30, nullable = false)
	private String country;

	@Column(length = 30, nullable = false)
	private String streatName;

	@Column(length = 30, nullable = false)
	private String postalCode;

	@Column(length = 30, nullable = false)
	private String type;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity userDetials;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
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

	public UserEntity getUserDetials() {
		return userDetials;
	}

	public void setUserDetials(UserEntity userDetials) {
		this.userDetials = userDetials;
	}


}
