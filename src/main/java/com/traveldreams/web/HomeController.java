package com.traveldreams.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.traveldreams.entity.UserEntity;
import com.traveldreams.service.AdminService;
import com.traveldreams.service.CountryService;

@Controller
public class HomeController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CountryService countryService;

	@GetMapping("/home")
	public String getHomePage(@AuthenticationPrincipal UserEntity user, ModelMap model) {
		model.put("user", user);
		
//		List<UserEntity> allUserAccounts = adminService.getAllUserAccounts();
//		System.out.println(allUserAccounts);
		
		return "home";
	}
		
}
