package com.traveldreams.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.traveldreams.entity.UserEntity;
import com.traveldreams.service.AdminService;
import com.traveldreams.service.CountryService;
import com.traveldreams.service.UserService;

import java.util.Optional;

@Controller
public class HomeController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private UserService userService;

	@GetMapping({"/home", "/"})
    public String getHomePage() {

		return "home";
	}
}
