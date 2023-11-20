package com.traveldreams.entity;

import jakarta.persistence.*;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Authorities implements GrantedAuthority{
	
	private static final long serialVersionUID = 3454450083120715451L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String authority;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private UserEntity user;
	
	public Authorities() {}
	
	public Authorities(String authority, UserEntity user) {
		super();
		this.authority = authority;
		this.user = user;
	}

	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
