package com.traveldreams.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.traveldreams.entity.CountryEntity;
import com.traveldreams.entity.UserEntity;
import com.traveldreams.service.CountryService;
import com.traveldreams.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/profile")
	public String getProfilePage (ModelMap model, @AuthenticationPrincipal UserEntity user) {
		
		UserEntity userFound = userService.findById(user.getId());
		
		model.put("user", userFound);
		
		return "profile";
	}
	@GetMapping("/user")
	public String getUserCountryList(ModelMap model, @AuthenticationPrincipal UserEntity user) {
		
		UserEntity authUser = userService.findById(user.getId());
		
		model.put("user", authUser);
		
		return "user";
	}
	
	@PostMapping ("/user/{userId}/country/{countryId}/delete")
	public String deleteCountryFromUserCountryList(@PathVariable Long userId, @PathVariable Long countryId) {
		
		userService.removeCountry(userId, countryId);
		
		return "redirect:/user";
	}
	
	@PostMapping("/update-user-account")
	public String updateUser (UserEntity user) {
		
		userService.save(user);
		
		return "redirect:/user";
	}
	
}
