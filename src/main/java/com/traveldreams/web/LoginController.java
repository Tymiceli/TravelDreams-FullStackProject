package com.traveldreams.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.traveldreams.entity.UserEntity;

@Controller
public class LoginController {
	
//	@Autowired
//	private UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@GetMapping("/login")
	public String login(ModelMap model) {
		UserEntity user = new UserEntity();
		
		model.put("user", user);
		
		return "login";
	}
	
	@PostMapping("/login")
	public ResponseEntity<HttpStatus> login(@RequestBody UserEntity userModel) throws Exception {
		System.out.println(userModel);
		Authentication authentication;

		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userModel.getUsername(), userModel.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid credentials");
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}
	
	
	
	
	

}
