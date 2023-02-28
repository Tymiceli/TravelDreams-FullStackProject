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
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/profile")
	public String getProfilePage (ModelMap model, @AuthenticationPrincipal UserEntity user) {
		
		UserEntity userFound = userService.findById(user.getId());
		
//		System.out.println(passwordEncoder.encode(userFound.getPassword()));
		
		model.put("user", userFound);
		
		return "profile";
	}
	@GetMapping("/user")
	public String getUserCountryList(ModelMap model, @AuthenticationPrincipal UserEntity user) {
		
//		UserEntity userFoundByAuthPrincipal = userService.findById(user.getId());
		
		UserEntity userFoundByPathVariable = userService.findById(user.getId());
		
//		userFoundByPathVariable.getCountries().forEach(t -> System.out.println(t));
				
		model.put("user", userFoundByPathVariable);
		
		return "user";
	}
	
	@PostMapping ("/user/{userId}/country/{countryId}/delete")
	public String deleteCountryFromUserCountryList(@PathVariable Long userId, @PathVariable Long countryId) {
		
		UserEntity user = userService.findById(userId);
		
		CountryEntity countryToDelete = countryService.findById(countryId);
		
		user.getCountries().remove(countryToDelete);
		
		userService.save(user);
		
		
		return "redirect:/user";
	}
	
	@PostMapping("/update-user-account")
	public String updateUser (UserEntity user) {
		
//		List<CountryEntity> countries = user.getCountries();
//		
//		countries.forEach(c-> System.out.println("Saved User's Countries: " + c));
		
//		passwordEncoder.encode(user.getPassword());
		
		userService.save(user);
		
		
		
		return "redirect:/user";
	}
	
}
