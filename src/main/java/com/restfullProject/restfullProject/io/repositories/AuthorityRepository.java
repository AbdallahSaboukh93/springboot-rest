package com.restfullProject.restfullProject.io.repositories;

import org.springframework.data.repository.CrudRepository;

import com.restfullProject.restfullProject.io.entities.AuthorityEntity;
import com.restfullProject.restfullProject.io.entities.RoleEntity;

public interface AuthorityRepository extends CrudRepository<AuthorityEntity, Long> {
	public AuthorityEntity findByName(String name) ;
}
