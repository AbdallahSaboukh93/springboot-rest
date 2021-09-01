package com.restfullProject.restfullProject.service.imp;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.restfullProject.restfullProject.io.entities.UserEntity;

@Entity(name = "passord_reset_tokens")
public class PasswordResetTokenEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7305552705712851541L;

	@Id
	@GeneratedValue
	private long id;

	private String token;

	@OneToOne
	@JoinColumn(name = "users_id")
	private UserEntity userDetails;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserEntity getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserEntity userDetails) {
		this.userDetails = userDetails;
	}
	
	
}
