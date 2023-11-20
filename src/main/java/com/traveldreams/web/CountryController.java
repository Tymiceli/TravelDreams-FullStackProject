package com.traveldreams.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.traveldreams.dto.Flag;
import com.traveldreams.dto.Name;
import com.traveldreams.entity.CountryEntity;
import com.traveldreams.entity.UserEntity;
import com.traveldreams.service.AdminService;
import com.traveldreams.service.CountryService;
import com.traveldreams.service.UserService;

@Controller
public class CountryController {

	private CountryService countryService;
	private UserService userService;
	private AdminService adminService;

	public CountryController(CountryService countryService, UserService userService, AdminService adminService) {
		this.countryService = countryService;
		this.userService = userService;
		this.adminService = adminService;
	}

	@GetMapping("/countries")
	public String getAllCountries(ModelMap model,@AuthenticationPrincipal UserEntity user) throws IOException {
  List<CountryEntity> allCountries = countryService.getAllCountries();

		System.out.println(user.getUsername());
		
		model.put("countries", allCountries);
		model.put("user", user);
		
		System.out.println("The controller for /countries GetMapping was hit");
		
		return "countries";
	}
	
	@GetMapping("/get-all-countries")
	public String allCountries(@AuthenticationPrincipal UserEntity user, ModelMap model) {
		
		CountryEntity[] countries = adminService.callApi();
		
		countryService.storeCountries(countries);
		
		return "countries";
	}
	
	@GetMapping("/country/{countryId}")
	public String getCountryPage (@PathVariable Long countryId, ModelMap model, @AuthenticationPrincipal UserEntity user) {
		
		CountryEntity countryFoundById = countryService.findById(countryId);
		Optional<UserEntity> userFound = userService.findById(user.getId());

		model.put("country", countryFoundById);
		model.put("user", userFound);
		
		return "country";
	}

	@PostMapping("/country/{countryId}")
	public String saveCountryToUser(@PathVariable Long countryId, @AuthenticationPrincipal UserEntity user) {
		
		Optional<UserEntity> userFound = userService.findById(user.getId());

		CountryEntity country = countryService.findById(countryId);

		if (userFound.isPresent()) {
			if (!userFound.get().getCountries().contains(country)) {
				userFound.get().getCountries().add(country);
			}

			userService.save(userFound.get());
		}

		return "redirect:/countries";
		
	}
}

