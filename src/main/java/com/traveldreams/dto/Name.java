package com.traveldreams.dto;


import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Name {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("common")
	private String common;
	
	@JsonProperty("official")
	private String official;
	
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
	public String getOfficial() {
		return official;
	}
	public void setOfficial(String official) {
		this.official = official;
	}
	@Override
	public String toString() {
		return "Name [id=" + id + ", common=" + common + ", official=" + official + "]";
	}
	
}
