package com.traveldreams.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "languages")
public class LanguageEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String nameLanguage;
	
	@ManyToMany(mappedBy = "languages")
	private List<CountryEntity> countries;

	public LanguageEntity(String code, String nameLanguage) {
		super();
		this.code = code;
		this.nameLanguage = nameLanguage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNameLanguage() {
		return nameLanguage;
	}

	public void setNameLanguage(String nameLanguage) {
		this.nameLanguage = nameLanguage;
	}

	public List<CountryEntity> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryEntity> countries) {
		this.countries = countries;
	}

	
	

}
