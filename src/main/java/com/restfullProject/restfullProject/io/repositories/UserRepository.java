package com.restfullProject.restfullProject.io.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.restfullProject.restfullProject.io.entities.UserEntity;
import com.restfullProject.restfullProject.shared.dto.UserDto;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

	UserEntity findByUserId(String userId);
	
	@Query(value="select * from users u where u.EMAIL_VERIFICATION_STATUS = 'true'", 
			countQuery="select count(*) from users u where u.EMAIL_VERIFICATION_STATUS = 'true'", 
			nativeQuery = true)
	Page<UserEntity> findAllUsersWithConfirmedEmailAddress( Pageable pageableRequest );
	
//    @Query(name = "select * from users ", countQuery = "select count(*) from users ", nativeQuery = true)
//	Page<UserEntity> findAllUsers(Pageable pageable);

	@Query(value = "select * from users  where users.firsName=?1", nativeQuery = true)
	List<UserEntity> findUserByFristName(String firstName);

    @Query(value = "select * from users  where users.firsName=:firstName", nativeQuery = true)
    List<UserEntity> findUserByLastName(@Param("firstName") String lastName);
    @Modifying
    @Transactional
    @Query(value = "update UserEntity users set  users.email=:email where users.userId=:userId")
    List<UserEntity> updateEmail(@Param("email") String email ,@Param("userId") String userId);
    
}
