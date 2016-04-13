package br.com.fatec.oqfazer.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.entity.City;

public class RegionDTO {
	
	private Long id;
	private String name;
	private List<City> cities = Lists.newArrayList();
	
	public RegionDTO(){};
	
	public RegionDTO(Long id, String name) {
		this.id = id;
		this.name = name;
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

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	
	public String toString(){
		return "Region[" + this.id + " - " + this.name + " ]"; 
	}
}
