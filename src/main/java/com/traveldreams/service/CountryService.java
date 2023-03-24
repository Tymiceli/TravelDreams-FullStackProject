package com.traveldreams.service;

import java.io.IOException;
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
//		for (CountryEntity c : countriesList) {
//			if (c.getCapital() == null) {
//				System.out.println(c.getName() + "'s capital is null!");
//			} else if (c.getCapital().length > 1) {
//				String[] capital = c.getCapital();
//				
//				String.join(", ", capital);
//				c.setCapital(capital);
//			} 
//		}
//		this was an attempted fix on feb 7 trying to stop the exception "java.sql.SQLException: Incorrect string value: '\xAC\xED\x00\x05ur...' for column 'capital' at row 1"
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

//	public void sortCountries () {
//		
//	}
}
