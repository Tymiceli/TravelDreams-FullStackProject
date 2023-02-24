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

@Controller
public class HomeController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/home")
	public String getHomePage(@AuthenticationPrincipal UserEntity user, ModelMap model) {
		Long userId = user.getId();
		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		UserEntity authenticatedUser = (UserEntity)authentication.getPrincipal();
//		
//		System.out.println(authenticatedUser.getFirstName());
//		
//		if (authenticatedUser.getId() == null) {
////			model.put("user", authenticatedUser);
//			return "home";
//		}
		
		UserEntity userFound = userService.findById(userId);
		
		model.put("user", userFound);
		
		
//		List<UserEntity> allUserAccounts = adminService.getAllUserAccounts();
//		System.out.println(allUserAccounts);
		
		return "home";
	}
	
	@GetMapping("/index")
	public String getIndex(@AuthenticationPrincipal UserEntity user) {


		
		return "index";
	}
		
}
