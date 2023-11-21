package com.traveldreams.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.traveldreams.entity.UserEntity;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(ModelMap model) {
		UserEntity user = new UserEntity();
		
		model.put("user", user);
		
		return "login";
	}
	@GetMapping("/login-error")
	public String getLoginError (Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
}
