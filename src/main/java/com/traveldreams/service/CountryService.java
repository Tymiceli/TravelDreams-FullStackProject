package com.traveldreams.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traveldreams.dto.Flag;
import com.traveldreams.dto.Name;
import com.traveldreams.entity.CountryEntity;
import com.traveldreams.repository.CountryRepository;
import com.traveldreams.repository.FlagRepository;
import com.traveldreams.repository.NameRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private NameRepository nameRepo;
	@Autowired
	private FlagRepository flagRepo;
	
	// the following code is not to be used
	public List<CountryEntity> getAllCountries() throws IOException {
		 
		return countryRepo.findAll();
	}

	public CountryEntity save(CountryEntity country) {

		return countryRepo.save(country);
	}

	public List<CountryEntity> saveAll(List<CountryEntity> countriesList) {
		return countryRepo.saveAll(countriesList);
	}
	
	public void saveName(List<Name> namesList) {
		
		nameRepo.saveAll(namesList);
	}

	public CountryEntity findById(Long countryId) {
		return countryRepo.findById(countryId).get();
	}

	public void saveFlag(List<Flag> flagList) {
		
		flagRepo.saveAll(flagList);
		
	}
  
	public void storeCountries(CountryEntity[] countries) {

		List<CountryEntity> countriesList = new ArrayList<>();
		List<Name> namesList = new ArrayList<>();
		List<Flag> flagList = new ArrayList<>();
		
		for (CountryEntity c : countries) {
			countriesList.add(c);
			namesList.add(c.getName());
			flagList.add(c.getFlagImg());
		}
		
		for (CountryEntity c:countriesList) {
		System.out.println(c.toString());
		}
		
		saveFlag(flagList);
		saveName(namesList);
		saveAll(countriesList);
		
		System.out.println("All Countries Saved /n" + "Printing all saved Countries...");
		
		for (CountryEntity c : countriesList) {
		System.out.println(c.toString());
		}
		
	}

//	public void sortCountries () {
//		
//	}
}
