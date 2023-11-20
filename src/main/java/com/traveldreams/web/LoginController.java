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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
