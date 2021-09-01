package com.restfullProject.restfullProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restfullProject.restfullProject.shared.dto.AddressDto;


public interface AddressService {
	public List<AddressDto> getAddress(String id);
	public AddressDto getByAddressId(String id);
}
