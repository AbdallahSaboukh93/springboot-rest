package com.restfullProject.restfullProject.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfullProject.restfullProject.io.entities.AddressEntity;
import com.restfullProject.restfullProject.io.entities.UserEntity;
import com.restfullProject.restfullProject.io.repositories.AddressRepository;
import com.restfullProject.restfullProject.io.repositories.UserRepository;
import com.restfullProject.restfullProject.service.AddressService;
import com.restfullProject.restfullProject.shared.dto.AddressDto;

@Service
public class AddressServiceOmpl implements AddressService {
	@Autowired
	UserRepository userRepository ;
	@Autowired
	AddressRepository addressRepository ;

	@Override
	public List<AddressDto> getAddress(String id) {
		ModelMapper modelMapper= new ModelMapper() ;
		List<AddressDto>  returnedAddressDto =new ArrayList<>() ;
		UserEntity userEntity= userRepository.findByUserId(id);
		if(userEntity==null) return returnedAddressDto ;
		List<AddressEntity> addressEntityList =addressRepository.findAllByUserDetials(userEntity);
		for(AddressEntity addressEntity :addressEntityList) {
			returnedAddressDto.add(modelMapper.map(addressEntity, AddressDto.class)) ;
		}
		return returnedAddressDto;
	}

	@Override
	public AddressDto getByAddressId(String addresId) {
		ModelMapper modelMapper= new ModelMapper() ;
		AddressDto returnedValue =new AddressDto();
		AddressEntity  addressEntity =addressRepository.findByAddressId(addresId);
		if(addressEntity!=null) {
			returnedValue=modelMapper.map(addressEntity, AddressDto.class);
		}
		return returnedValue;
	}

}
