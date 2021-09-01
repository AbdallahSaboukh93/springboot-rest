package com.restfullProject.restfullProject.io.repositories;

import org.springframework.data.repository.CrudRepository;

import com.restfullProject.restfullProject.io.entities.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
 public RoleEntity findByName(String name) ;
}
