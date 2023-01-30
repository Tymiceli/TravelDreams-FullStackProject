package com.traveldreams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

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

}
