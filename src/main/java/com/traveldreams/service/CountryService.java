package com.traveldreams.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.traveldreams.entity.CountryEntity;
import com.traveldreams.entity.CurrencyEntity;
import com.traveldreams.entity.LanguageEntity;
import com.traveldreams.repository.CountryRepository;
import com.traveldreams.repository.CurrencyRepository;
import com.traveldreams.repository.LanguageRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private CurrencyRepository currencyRepo;
	@Autowired
	private LanguageRepository languageRepo;

	// the following code is not to be used
	public List<CountryEntity> getAllCountries() throws IOException {
		
		return countryRepo.findAll();

//		Set<CountryEntity> allCountries = new LinkedHashSet<>();
//		List<LanguageEntity> languages = new ArrayList<>();
//		List<CurrencyEntity> currencies = new ArrayList<>();
//		
////		allCountries = countryRepo.findAll();
//
//		
//		return allCountries;

	}

	public CountryEntity save(CountryEntity country) {

		return countryRepo.save(country);
	}
}
