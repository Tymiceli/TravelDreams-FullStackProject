package com.traveldreams.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.traveldreams.entity.UserEntity;
import com.traveldreams.repository.UserRepository;
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

	private CountryRepository countryRepository;
	private NameRepository nameRepository;
	private FlagRepository flagRepository;
	private UserRepository userRepository;

	public CountryService(CountryRepository countryRepository, NameRepository nameRepository, FlagRepository flagRepository, UserRepository userRepository) {
		this.countryRepository = countryRepository;
		this.nameRepository = nameRepository;
		this.flagRepository = flagRepository;
		this.userRepository = userRepository;
	}

	// the following code is not to be used
	public List<CountryEntity> getAllCountries() throws IOException {
		 
		return countryRepository.findAll();
	}

	public CountryEntity save(CountryEntity country) {

		return countryRepository.save(country);
	}

	public List<CountryEntity> saveAll(List<CountryEntity> countriesList) {
		return countryRepository.saveAll(countriesList);
	}
	
	public void saveName(List<Name> namesList) {
		nameRepository.saveAll(namesList);
	}

	public CountryEntity findById(Long countryId) {
		return countryRepository.findById(countryId).get();
	}

	public void saveFlag(List<Flag> flagList) {
		
		flagRepository.saveAll(flagList);
		
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

	public void removeCountry(Long userId, Long countryId) {
		Optional<UserEntity> userFound = userRepository.findById(userId);

		Optional<CountryEntity> countryFound = countryRepository.findById(countryId);

		userFound.get().getCountries().remove(countryFound.get());

		userRepository.save(userFound.get());

	}

//	public void sortCountries () {
//		
//	}
}
