package com.traveldreams.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.traveldreams.domain.Country;
import com.traveldreams.dto.Flag;
import com.traveldreams.dto.Name;
import com.traveldreams.entity.CountryEntity;
import com.traveldreams.entity.UserEntity;
import com.traveldreams.service.AdminService;
import com.traveldreams.service.CountryService;
import com.traveldreams.service.UserService;

@Controller
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/countries")
	public String getAllCountries(ModelMap model,@AuthenticationPrincipal UserEntity user) throws IOException {
		
		model.put("countries", countryService.getAllCountries());
		model.put("user", user);
		
		System.out.println("The controller for /countries GetMapping was hit");
		
		return "countries";
	}
	
	@GetMapping("/get-all-countries")
	public String allCountries(@AuthenticationPrincipal UserEntity user, ModelMap model) {
		
		CountryEntity[] countries = adminService.callApi();
		
		List<CountryEntity> countriesList = new ArrayList<>();
		List<Name> namesList = new ArrayList<>();
		List<Flag> flagList = new ArrayList<>();
		
		for (CountryEntity c : countries) {
			countriesList.add(c);
			namesList.add(c.getName());
			flagList.add(c.getFlagImg());
		}
		
//		for (CountryEntity c:countriesList) {
//		System.out.println(c.toString());
//		}
		
		countryService.saveFlag(flagList);
		countryService.saveName(namesList);
		countryService.saveAll(countriesList);
		
		System.out.println("All Countries Saved /n" + "Printing all saved Countries...");

		return "countries";
	}
	
	@GetMapping("/country/{countryId}")
	public String getCountryPage (@PathVariable Long countryId, ModelMap model, @AuthenticationPrincipal UserEntity user) {
		
		CountryEntity countryFoundById = countryService.findById(countryId);
		UserEntity userFound = userService.findById(user.getId());
		
		model.put("country", countryFoundById);
		model.put("user", userFound);
		
		return "country";
	}

	@PostMapping("/country/{countryId}")
	public String saveCountryToUser(@PathVariable Long countryId, @AuthenticationPrincipal UserEntity user) {
		
		UserEntity userFound = userService.findById(user.getId());
		
		CountryEntity country = countryService.findById(countryId);
		
		if (!userFound.getCountries().contains(country)) {			
			userFound.getCountries().add(country);
		}
		
		userService.save(userFound);
		
		return "redirect:/countries";
		
	}
	
	@GetMapping("/sort-countries-by-name")
	@ResponseBody
	public ResponseEntity<List<CountryEntity>> sortCountries() throws IOException {
		List<CountryEntity> countries = countryService.getAllCountries();
		
		return ResponseEntity.ok(countries);
	}
	
	@PostMapping("/display-sorted-countries")
	@ResponseBody
	public ResponseEntity<String> receiveSortedCountries(@RequestBody List<CountryEntity> sortedCountries) {
		
		List<Name> namesList = new ArrayList<>();
		List<Flag> flagList = new ArrayList<>();
		
		for (CountryEntity c: sortedCountries) {
			namesList.add(c.getName());
			flagList.add(c.getFlagImg());
		}
		countryService.saveName(namesList);
		countryService.saveFlag(flagList);
		countryService.saveAll(sortedCountries);
		
		System.out.println(sortedCountries);
		
		return ResponseEntity.status(HttpStatus.OK).body("Successful sorting");
	}

}

