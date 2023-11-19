package com.traveldreams.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.traveldreams.entity.UserEntity;
import com.traveldreams.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String getRegisterPage(ModelMap model) {
		
		model.put("user", new UserEntity());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser (UserEntity user) {
		
		// if username already exists in the database
		// return an error message to the user on the register page
		
		userService.register(user);
		
		return "redirect:/login";
	}
	
	@PostMapping("/exists")
	@ResponseBody
	public Boolean postExists (@RequestBody UserEntity user) {
		
		System.out.println("Endpoint hit");
		user = userService.findByUsername(user.getUsername());
		
		Boolean userFoundCheck = user !=null;
		
		if (userFoundCheck) {
			System.out.println("User found in database");
		} else {
			System.out.println("User not found in database");
		}
		
		return userFoundCheck;
	}
}
