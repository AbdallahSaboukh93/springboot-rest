package com.restfullProject.restfullProject.service.imp;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.restfullProject.restfullProject.io.entities.UserEntity;
import com.restfullProject.restfullProject.io.repositories.PasswordResetTokenRepositories;
import com.restfullProject.restfullProject.io.repositories.UserRepository;
import com.restfullProject.restfullProject.service.UserService;
import com.restfullProject.restfullProject.shared.Utilits;
import com.restfullProject.restfullProject.shared.dto.AddressDto;
import com.restfullProject.restfullProject.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utilits utilits;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	PasswordResetTokenRepositories passwordResetTokenRepositories ;

	@Override
	public UserDto createUser(UserDto user) {
		for(AddressDto addressDto:user.getAddress()) {
			addressDto.setUserDetials(user);
			addressDto.setAddressId(utilits.generateAddressId(30));
		}
		
		ModelMapper modelMapper = new ModelMapper();
		UserEntity newUser = modelMapper.map(user, UserEntity.class);
		newUser.setUserId(utilits.generateUserId(20));
		newUser.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		UserEntity savedUser = userRepository.save(newUser);
		UserDto returnedUser = new UserDto();
		returnedUser = modelMapper.map(savedUser, UserDto.class);
		return returnedUser;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		
		return new  UserPrincipal(userEntity);
		//return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto findUserByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		UserDto returnedUser = new UserDto();
		BeanUtils.copyProperties(userEntity, returnedUser);
		return returnedUser;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		UserDto returnedUser = new UserDto();
		BeanUtils.copyProperties(userEntity, returnedUser);
		return returnedUser;
	}

	@Override
	public UserDto updateUser(String userId, UserDto user) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		UserEntity savedUser = userRepository.save(userEntity);
		UserDto updatedUser = new UserDto();
		BeanUtils.copyProperties(savedUser, updatedUser);
		return updatedUser;
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		userRepository.delete(userEntity);
	}
	

	@Override
	public boolean resetPassword(String token, String password) {
        boolean returnValue = false;
        
        if( Utilits.hasTokenExpired(token) )
        {
            return returnValue;
        }
 
        PasswordResetTokenEntity passwordResetTokenEntity = passwordResetTokenRepositories.findByToken(token);

        if (passwordResetTokenEntity == null) {
            return returnValue;
        }

        // Prepare new password
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        
        // Update User password in database
        UserEntity userEntity = passwordResetTokenEntity.getUserDetails();
        userEntity.setEncryptedPassword(encodedPassword);
        UserEntity savedUserEntity = userRepository.save(userEntity);
 
        // Verify if password was saved successfully
        if (savedUserEntity != null && savedUserEntity.getEncryptedPassword().equalsIgnoreCase(encodedPassword)) {
            returnValue = true;
        }
   
        // Remove Password Reset token from database
        passwordResetTokenRepositories.delete(passwordResetTokenEntity);
        
        return returnValue;
	}

}
