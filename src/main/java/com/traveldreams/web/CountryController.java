package com.traveldreams.web;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.traveldreams.entity.CountryEntity;
import com.traveldreams.service.CountryService;

@Controller
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping("/country/{country}")
	public String getCountryByName () {
		
		return "country";
	}
	
	@GetMapping("/countries")
	public String getAllCountries(ModelMap model) throws IOException {
		List<CountryEntity> allCountries = countryService.getAllCountries();
		Set<CountryEntity> allUniqueCountries = allCountries.stream().collect(Collectors.toSet());
		
		model.put("countries", allUniqueCountries);	
		
		System.out.println("The controller for /countries GetMapping was hit");
		
		return "countries";
	}
	
	@PostMapping("/save-all-countries")
	public CountryEntity country (@RequestBody CountryEntity country) {
		
		System.out.println(country);
		
		return countryService.save(country);
		
	}


}
