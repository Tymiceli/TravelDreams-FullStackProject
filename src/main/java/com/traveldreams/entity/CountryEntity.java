package com.traveldreams.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "countries")
public class CountryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	@JsonProperty("name")
	private String name;

	@Column(name = "capital")
	@JsonProperty("capital")
	private String capital;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "country_currency",
            joinColumns = @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "currency_id"))
    private List<CurrencyEntity> currencies;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "country_language",
            joinColumns = @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<LanguageEntity> languages;

	@Column(name = "flag")
	@JsonProperty("flag")
	private String flag;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserEntity user;

	
	
	public CountryEntity(String name, String capital, List<CurrencyEntity> currencies,
			List<LanguageEntity> languages, String flag) {
		
		this.name = name;
		this.capital = capital;
		this.currencies = currencies;
		this.languages = languages;
		this.flag = flag;
//		this.user = user;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCapital() {
		return capital;
	}



	public void setCapital(String capital) {
		this.capital = capital;
	}



	public List<CurrencyEntity> getCurrencies() {
		return currencies;
	}



	public void setCurrencies(List<CurrencyEntity> currencies) {
		this.currencies = currencies;
	}



	public List<LanguageEntity> getLanguages() {
		return languages;
	}



	public void setLanguages(List<LanguageEntity> languages) {
		this.languages = languages;
	}



	public String getFlag() {
		return flag;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}



	public UserEntity getUser() {
		return user;
	}



	public void setUser(UserEntity user) {
		this.user = user;
	}
	
}