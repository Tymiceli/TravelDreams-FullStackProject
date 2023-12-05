package com.traveldreams.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.traveldreams.entity.UserEntity;
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
