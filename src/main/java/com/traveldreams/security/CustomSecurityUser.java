package com.traveldreams.security;

import org.springframework.security.core.userdetails.UserDetails;

import com.traveldreams.entity.UserEntity;

public class CustomSecurityUser extends UserEntity implements UserDetails{

	private static final long serialVersionUID = -1879148325125317719L;

	// No arg constructor
	public CustomSecurityUser() {}
	
	// CustomSecurityUser constructor
	public CustomSecurityUser(UserEntity user) {
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setEmail(user.getEmail());
		this.setCountries(user.getCountries());
		this.setAuthorities(user.getAuthorities());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	

}
