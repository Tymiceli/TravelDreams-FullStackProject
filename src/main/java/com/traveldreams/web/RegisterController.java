package com.traveldreams.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
		
		userService.register(user);
		
		return "redirect:/login";
	}
	
}
