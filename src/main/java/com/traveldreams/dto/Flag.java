package com.traveldreams.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Flag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("svg")
	private String svgURL;

	public Long getFlagId() {
		return id;
	}

	public void setFlagId(Long flagId) {
		this.id = flagId;
	}

	public String getSvg() {
		return svgURL;
	}

	public void setSvg(String svgURL) {
		this.svgURL = svgURL;
	}

	
	
}