package com.restfullProject.restfullProject.ui.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restfullProject.restfullProject.exceptions.UserServiceException;
import com.restfullProject.restfullProject.io.entities.UserEntity;
import com.restfullProject.restfullProject.service.AddressService;
import com.restfullProject.restfullProject.service.UserService;
import com.restfullProject.restfullProject.shared.dto.AddressDto;
import com.restfullProject.restfullProject.shared.dto.UserDto;
import com.restfullProject.restfullProject.ui.model.request.PasswordResetModel;
import com.restfullProject.restfullProject.ui.model.request.RequestOperationName;
import com.restfullProject.restfullProject.ui.model.request.UserDetailsRequestModel;
import com.restfullProject.restfullProject.ui.model.response.AddressRest;
import com.restfullProject.restfullProject.ui.model.response.ErrorMessages;
import com.restfullProject.restfullProject.ui.model.response.OperationStatusModel;
import com.restfullProject.restfullProject.ui.model.response.RequestOperationStatus;
import com.restfullProject.restfullProject.ui.model.response.UserRest;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class userController {

	@Autowired
	UserService userService;
	@Autowired
	AddressService addressService;

	@PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.userId ==principal.userId")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authoriation JWT Token", value = "Bearer", paramType = "header") })
	@GetMapping(path = "{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser(@PathVariable String id) {
		UserRest returnedUserRest = new UserRest();
		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnedUserRest);
		return returnedUserRest;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
		// if (userDetails.getFirstName().isEmpty()) throw new
		// UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		if (userDetails.getFirstName().isEmpty())
			throw new NullPointerException("the object is null ");
		UserRest userRestResponse = new UserRest();
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto createdUser = userService.createUser(userDto);
		userRestResponse = modelMapper.map(createdUser, UserRest.class);
		return userRestResponse;
	}

	@PutMapping(path = "{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {
		UserRest userRestResponse = new UserRest();
		// UserDto userDto = new UserDto();
		// BeanUtils.copyProperties(userDetails, userDto);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto updatedUser = userService.updateUser(id, userDto);
		BeanUtils.copyProperties(updatedUser, userRestResponse);
		return userRestResponse;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or  #id ==principal.userId")
	@Secured("ROLE_ADMIN")
	@DeleteMapping(path = "{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel retrnedValue = new OperationStatusModel();
		retrnedValue.setOperationName(RequestOperationName.DELETE.name());
		userService.deleteUser(id);
		retrnedValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return retrnedValue;
	}

	@GetMapping(path = "{userId}/address", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public List<AddressRest> getAddresses(@PathVariable String userId) {
		List<AddressRest> returnedAddressRest = new ArrayList<>();
		List<AddressDto> addressDto = addressService.getAddress(userId);
		Type listType = new TypeToken<List<AddressRest>>() {
		}.getType();
		ModelMapper modelMapper = new ModelMapper();
		if (addressDto != null && !addressDto.isEmpty()) {
			returnedAddressRest = modelMapper.map(addressDto, listType);
		}
		for (AddressRest addressRest : returnedAddressRest) {
			Link userLink = linkTo(userController.class).slash(userId).withRel("user");

			Link addressesLink = linkTo(methodOn(userController.class).getAddresses(userId)).withRel("addresses");

			addressRest.add(addressesLink);
			addressRest.add(userLink);
		}
		return returnedAddressRest;
	}

	@GetMapping(path = "{userId}/address/{addressId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public AddressRest getAddress(@PathVariable String userId, @PathVariable String addressId) {
		AddressRest returnedAddressRest = new AddressRest();
		AddressDto addressDto = addressService.getByAddressId(addressId);
		ModelMapper modelMapper = new ModelMapper();
		Link addressLink = linkTo(methodOn((userController.class)).getAddress(userId, addressId)).withSelfRel();
		Link userLink = linkTo(userController.class).slash(userId).withRel("user");

		Link addressesLink = linkTo(methodOn(userController.class).getAddresses(userId)).withRel("addresses");

		returnedAddressRest = modelMapper.map(addressDto, AddressRest.class);

		returnedAddressRest.add(addressLink);
		returnedAddressRest.add(userLink);
		returnedAddressRest.add(addressesLink);
		return returnedAddressRest;
	}

	@PostMapping(path = "/password-reset", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OperationStatusModel resetPassword(@RequestBody PasswordResetModel passwordResetModel) {
		OperationStatusModel returnValue = new OperationStatusModel();

		boolean operationResult = userService.resetPassword(passwordResetModel.getToken(),
				passwordResetModel.getPassword());

		returnValue.setOperationName(RequestOperationName.PASSWORD_RESET.name());
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());

		if (operationResult) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		}

		return returnValue;
	}
}
