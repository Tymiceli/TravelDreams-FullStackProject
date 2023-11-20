package com.traveldreams.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.traveldreams.dto.Flag;
import com.traveldreams.dto.Name;
import jakarta.persistence.*;

@Entity
@Table(name = "countries")
public class CountryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("name")
	@OneToOne (optional = false)
	private Name name;
	
	@JsonProperty("flags")
	@OneToOne(optional = true)
	private Flag flagImg;
	
	@Column(name = "region")
	@JsonProperty("region")
	private String region;
	
	@Column(name = "population")
	@JsonProperty("population")
	private Integer population;

	@Column(name = "landlocked")
	@JsonProperty("landlocked")
	private boolean landlocked;

	@Column(name = "flag")
	@JsonProperty("flag")
	private String flag;
	
	@ManyToMany(mappedBy = "countries", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
	private List<UserEntity> user = new ArrayList<>();
	
	public CountryEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}


	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public boolean isLandlocked() {
		return landlocked;
	}

	public void setLandlocked(boolean landlocked) {
		this.landlocked = landlocked;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<UserEntity> getUser() {
		return user;
	}
	
	public void setUser(List<UserEntity> user) {
		this.user = user;
	}

	public Flag getFlagImg() {
		return flagImg;
	}
	
	public void setFlagImg(Flag flagImg) {
		this.flagImg = flagImg;
	}
	
	@Override
	public String toString() {
		return "CountryEntity [id=" + id + ", name=" + name + ", region=" + region + ", population=" + population
				+ ", landlocked=" + landlocked + ", flag=" + flag + ", user=" + user + ", flagImg=" + flagImg + "]";
	}

}