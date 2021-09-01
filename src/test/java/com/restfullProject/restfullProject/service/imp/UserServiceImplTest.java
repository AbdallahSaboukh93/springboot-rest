package com.restfullProject.restfullProject.service.imp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


import com.restfullProject.restfullProject.io.entities.UserEntity;
import com.restfullProject.restfullProject.io.repositories.UserRepository;
import com.restfullProject.restfullProject.service.UserService;
import com.restfullProject.restfullProject.shared.dto.UserDto;

import net.bytebuddy.asm.Advice.This;

class UserServiceImplTest {
	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetUserByUserId() {
	UserEntity userEntity =new UserEntity() ;
	userEntity.setId(1L);
	userEntity.setFirstName("abdallah");
	userEntity.setUserId("sda");
	userEntity.setEncryptedPassword("dsds");
    when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
    UserDto userDto= userService.findUserByEmail("abdallah@abdallah.com");
    assertNotNull(userDto);
    assertEquals("abdallah", userDto.getFirstName());

	}
}
