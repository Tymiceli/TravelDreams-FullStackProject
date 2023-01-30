package com.traveldreams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.traveldreams.entity.UserEntity;
import com.traveldreams.repository.UserRepository;
import com.traveldreams.security.CustomSecurityUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = userRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Username and/or password was incorrect");
		}
		return new CustomSecurityUser(user);
	}

}
