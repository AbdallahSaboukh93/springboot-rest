package com.restfullProject.restfullProject.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.restfullProject.restfullProject.shared.dto.UserDto;

public interface UserService  extends UserDetailsService{
	UserDto createUser(UserDto user) ;
	
	UserDto findUserByEmail(String email) ;
	
	public UserDto  getUserByUserId(String userId) ;
	
	public UserDto  updateUser(String userId,UserDto user) ;
	
	public void deleteUser(String userId) ;
	
	public boolean resetPassword(String token, String password) ;
}
