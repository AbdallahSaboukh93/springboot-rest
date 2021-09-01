package com.restfullProject.restfullProject.service.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.restfullProject.restfullProject.io.entities.AuthorityEntity;
import com.restfullProject.restfullProject.io.entities.RoleEntity;
import com.restfullProject.restfullProject.io.entities.UserEntity;

public class UserPrincipal implements UserDetails {

	private UserEntity userEntity;
	private String userId ;

	public UserPrincipal(UserEntity userEntity  ) {
		this.userEntity = userEntity;
		this.userId=userEntity.getUserId() ;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<AuthorityEntity> authorityEntity = new ArrayList<>();

		Collection<RoleEntity> roles = this.userEntity.getRoles();

		if (roles == null)	return authorities;
		
		
		roles.forEach((role) -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			authorityEntity.addAll(role.getAuthorities());
		});
		authorityEntity.forEach((authority) -> {
			authorities.add(new SimpleGrantedAuthority(authority.getName()));
		});
		
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.userEntity.getEncryptedPassword();
	}

	@Override
	public String getUsername() {
		return this.userEntity.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.userEntity.getEmailVerficationStatus();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
