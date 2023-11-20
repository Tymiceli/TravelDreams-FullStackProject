package com.traveldreams.service;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.traveldreams.entity.Authorities;
import com.traveldreams.entity.CountryEntity;
import com.traveldreams.entity.UserEntity;
import com.traveldreams.exception.ResourceNotFoundException;
import com.traveldreams.repository.CountryRepository;
import com.traveldreams.repository.UserRepository;

@Service
public class UserService extends UserDetailsServiceImpl {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public UserEntity save(UserEntity user) {
		return userRepository.save(user);
	}

	public Optional<UserEntity> findById(Long userId) {
		return userRepository.findById(userId);
	}

	public UserEntity findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}
}
