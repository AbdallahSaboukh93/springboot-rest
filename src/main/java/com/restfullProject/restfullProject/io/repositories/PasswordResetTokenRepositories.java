package com.restfullProject.restfullProject.io.repositories;

import org.springframework.data.repository.CrudRepository;

import com.restfullProject.restfullProject.service.imp.PasswordResetTokenEntity;

public interface PasswordResetTokenRepositories extends CrudRepository<PasswordResetTokenEntity, Long> {
	PasswordResetTokenEntity findByToken(String token) ;
}
