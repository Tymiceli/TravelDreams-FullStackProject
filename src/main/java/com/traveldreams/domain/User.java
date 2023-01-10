package com.traveldreams.domain;

import java.util.List;

public class User {

	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private List<Country> countries;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Country> getCountries() {
		return countries;
	}
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	
	public void addCountry(Country country) {
		countries.add(country);
	}
	
	public void removeCountry(Country country) {
		countries.remove(country);
	}
	
	public void updateCountry(Country oldCountry, Country newCountry) {
		int index = countries.indexOf(oldCountry);
		countries.set(index, newCountry);
	}

}
