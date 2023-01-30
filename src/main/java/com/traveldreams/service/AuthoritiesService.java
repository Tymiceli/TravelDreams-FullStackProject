package com.traveldreams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traveldreams.entity.Authorities;
import com.traveldreams.repository.AuthoritiesRepository;

@Service
public class AuthoritiesService {
	
	@Autowired
	private AuthoritiesRepository authRepo;
	
	public Long countAllAuthorities() {
		return authRepo.count();
	}
	
	public void saveAuth (Authorities auth) {
		authRepo.save(auth);
	}

}
