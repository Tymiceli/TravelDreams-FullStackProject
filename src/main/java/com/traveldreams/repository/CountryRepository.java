package com.traveldreams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.traveldreams.entity.CountryEntity;

@Repository
public interface CountryRepository extends JpaRepository <CountryEntity, Long> {

	@Query (value = "select from Countries c where c.name = :name", nativeQuery = true)
	public CountryEntity getCountryByName (String name);
	
	
}
