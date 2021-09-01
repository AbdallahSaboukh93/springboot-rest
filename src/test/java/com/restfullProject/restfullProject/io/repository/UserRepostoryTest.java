package com.restfullProject.restfullProject.io.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.restfullProject.restfullProject.io.entities.AddressEntity;
import com.restfullProject.restfullProject.io.entities.UserEntity;
import com.restfullProject.restfullProject.io.repositories.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepostoryTest {
	
	@Autowired
	UserRepository userReposit;

	@BeforeEach
	void setUp() throws Exception {
		createRecrods();
	}

	@Test
	void testGetVerfiedUser() {
//		Pageable pageableRequest = PageRequest.of(1, 1);
//		Page<UserEntity> page = userReposit.findAllUsersWithConfirmedEmailAddress(pageableRequest);
//		assertNotNull(page);
//		
//        List<UserEntity> userEntities = page.getContent();
//        assertNotNull(userEntities);
//        assertTrue(userEntities.size() == 1);
	}

	private void createRecrods() {
		// Prepare User Entity
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName("Sergey");
		userEntity.setLastName("Kargopolov");
		userEntity.setUserId("1a2b3c");
		userEntity.setEncryptedPassword("xxx");
		userEntity.setEmail("test@test.com");
		userEntity.setEmailVerficationStatus(true);

		// Prepare User Addresses
		AddressEntity addressEntity = new AddressEntity();
		userEntity.setId(1L);

		addressEntity.setType("shipping");
		addressEntity.setAddressId("ahgyt74hfy");
		addressEntity.setCity("Vancouver");
		addressEntity.setCountry("Canada");
		addressEntity.setPostalCode("ABCCDA");
		addressEntity.setStreatName("123 Street Address");

		List<AddressEntity> addresses = new ArrayList<>();
		addresses.add(addressEntity);

		userEntity.setAddress(addresses);

		userReposit.save(userEntity);

		// Prepare User Entity
		UserEntity userEntity2 = new UserEntity();
		userEntity2.setFirstName("Sergey");
		userEntity2.setLastName("Kargopolov");
		userEntity2.setUserId("1a2b3cddddd");
		userEntity2.setEncryptedPassword("xxx");
		userEntity2.setEmail("test@test.com");
		userEntity2.setEmailVerficationStatus(true);

		// Prepare User Addresses
		AddressEntity addressEntity2 = new AddressEntity();
		addressEntity2.setType("shipping");
		addressEntity2.setAddressId("ahgyt74hfywwww");
		addressEntity2.setCity("Vancouver");
		addressEntity2.setCountry("Canada");
		addressEntity2.setPostalCode("ABCCDA");
		addressEntity2.setStreatName("123 Street Address");

		List<AddressEntity> addresses2 = new ArrayList<>();
		addresses2.add(addressEntity2);

		userEntity2.setAddress(addresses2);

		userReposit.save(userEntity2);

		// recordsCreated = true;

	}

}
