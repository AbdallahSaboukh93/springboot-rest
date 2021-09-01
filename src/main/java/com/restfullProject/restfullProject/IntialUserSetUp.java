package com.restfullProject.restfullProject;

import java.util.Arrays;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.restfullProject.restfullProject.io.entities.AuthorityEntity;
import com.restfullProject.restfullProject.io.entities.RoleEntity;
import com.restfullProject.restfullProject.io.entities.UserEntity;
import com.restfullProject.restfullProject.io.repositories.AuthorityRepository;
import com.restfullProject.restfullProject.io.repositories.RoleRepository;
import com.restfullProject.restfullProject.io.repositories.UserRepository;
import com.restfullProject.restfullProject.shared.Utilits;

@Component
public class IntialUserSetUp {
	@Autowired
	AuthorityRepository authorityRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utilits utilits;

	@EventListener
	@Transactional
	public void onApplicationEvent(ApplicationReadyEvent event) {

		System.out.println("From Application Ready Event ");

		AuthorityEntity readAuthority = createAuthority("READ_AUTHORITY");
		AuthorityEntity writeAuthority = createAuthority("WRITE_AUTHORITY");
		AuthorityEntity deleteAuthority = createAuthority("DELETE_AUTHORITY");

		RoleEntity roleUser = createRole("ROLE_USER", Arrays.asList(readAuthority, writeAuthority));
		RoleEntity roleAdmin = createRole("ROLE_ADMIN", Arrays.asList(readAuthority, writeAuthority, deleteAuthority));

		if (roleAdmin == null)return;

		UserEntity adminUser = new UserEntity();
		adminUser.setFirstName("abdallah");
		adminUser.setLastName("saboukh");
		adminUser.setEmail("abdallah@gmail.com");
		adminUser.setPassword("123");
		adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("123"));
		adminUser.setEmailVerficationStatus(true);
		adminUser.setUserId(utilits.generateUserId(30));
		adminUser.setRoles(Arrays.asList(roleAdmin));
		userRepository.save(adminUser);
	}

	@Transactional
	private AuthorityEntity createAuthority(String authorityName) {
		AuthorityEntity authorityEntity = authorityRepository.findByName(authorityName);
		if (authorityEntity == null) {
			authorityEntity = new AuthorityEntity(authorityName);
			authorityRepository.save(authorityEntity);
		}
		return authorityEntity;
	}

	@Transactional
	private RoleEntity createRole(String name, Collection<AuthorityEntity> authority) {
		RoleEntity roleEntity = roleRepository.findByName(name);
		if (roleEntity == null) {
			roleEntity = new RoleEntity(name);
			roleEntity.setAuthorities(authority);
			roleRepository.save(roleEntity);
		}
		return roleEntity;
	}
}
