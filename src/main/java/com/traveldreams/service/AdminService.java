package com.traveldreams.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.traveldreams.dto.RestCountriesResponse;
import com.traveldreams.entity.CountryEntity;
import com.traveldreams.entity.UserEntity;
import com.traveldreams.repository.UserRepository;

@Service
public class AdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Secured("ROLE_ADMIN")
	public List<UserEntity> getAllUserAccounts() {
		
		return userRepository.findAll();
	}
	
	@Secured("ROLE_ADMIN")
//	public ResponseEntity<RestCountriesResponse[]> callApi() {
	public CountryEntity[] callApi() {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = URI.create("https://restcountries.com/v3.1/all");
				
//				UriComponentsBuilder.fromHttpUrl("https://restcountries.com/v3.1/all")
//				.build()
//				.toUri();
		CountryEntity[] countries = restTemplate.getForObject(uri, CountryEntity[].class);
//		System.out.println(countries[0].getName());
//		ResponseEntity<RestCountriesResponse[]> countries = restTemplate.getForEntity(uri, RestCountriesResponse[].class);
//		System.out.println("Line 38 AdminService :" + countries.toString());
		
		return countries;
		
	}

}
