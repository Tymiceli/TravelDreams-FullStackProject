package com.traveldreams.web;

//import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.traveldreams.entity.Authorities;
import com.traveldreams.entity.UserEntity;
import com.traveldreams.repository.AuthoritiesRepository;
import com.traveldreams.service.AuthoritiesService;
import com.traveldreams.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthoritiesService authService;

	@GetMapping("/register")
	public String getRegisterPage(ModelMap model) {
		
		model.put("user", new UserEntity());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser (UserEntity user) {
		System.out.println(user);
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		Long id = authService.countAllAuthorities();
		Authorities auth = new Authorities("ROLE_USER", user);
		user.getAuthorities().add(auth);
		
//		user.setAuthorities(user.getAuthorities());
		System.out.println(user.getAuthorities());
		userService.save(user);
		authService.saveAuth(auth);
		return "redirect:/login";
	}
	
}
