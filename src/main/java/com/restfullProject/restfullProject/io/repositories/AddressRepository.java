package com.restfullProject.restfullProject.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.restfullProject.restfullProject.io.entities.AddressEntity;
import com.restfullProject.restfullProject.io.entities.UserEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
	List<AddressEntity> findAllByUserDetials(UserEntity userEntity);

	AddressEntity findByAddressId(String AddressId);
}
