package com.traveldreams.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traveldreams.entity.Authorities;
import com.traveldreams.entity.CountryEntity;
import com.traveldreams.entity.UserEntity;
import com.traveldreams.exception.ResourceNotFoundException;
import com.traveldreams.repository.CountryRepository;
import com.traveldreams.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CountryRepository countryRepository;

	
	public void addCountry(Long userId, CountryEntity country) {
		try {
			UserEntity user = userRepository.findById(userId).get();
			user.getCountries().add(country);
			userRepository.save(user);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("User not found");
		}
	}

	public void removeCountry(Long userId, Long countryId) {

		CountryEntity country = countryRepository.findById(countryId).get();
		
		try {
			UserEntity user = userRepository.findById(userId).get();
			user.getCountries().remove(country);
			userRepository.save(user);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("User not found");
		}
	}

	public void save(UserEntity user) {
		userRepository.save(user);
		
		
	}

	public UserEntity findById(Long userId) {
		return userRepository.findById(userId).get();
	}

}
