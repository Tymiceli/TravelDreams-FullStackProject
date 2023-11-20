package com.traveldreams.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	public CountryEntity[] callApi() {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = URI.create("https://restcountries.com/v3.1/all");
				
		CountryEntity[] countries = restTemplate.getForObject(uri, CountryEntity[].class);
		
		return countries;
		
	}

}
