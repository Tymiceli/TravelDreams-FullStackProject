package com.traveldreams.dto;

import jakarta.persistence.*;
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