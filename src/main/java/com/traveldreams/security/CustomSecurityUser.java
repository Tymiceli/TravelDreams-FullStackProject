package com.traveldreams.security;



import com.traveldreams.entity.UserEntity;

public class CustomSecurityUser extends UserEntity {

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

}
