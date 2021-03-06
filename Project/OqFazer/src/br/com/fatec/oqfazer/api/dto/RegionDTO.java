package br.com.fatec.oqfazer.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class RegionDTO {
	
	private Long id;
	private String name;
	private List<CityDTO> cities = Lists.newArrayList();
	private List<RegionDTO> regions = Lists.newArrayList();
	
	//atribute screen
	private String erro = null;
	
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

	public List<CityDTO> getCities() {
		return cities;
	}

	public void setCities(List<CityDTO> cities) {
		this.cities = cities;
	}
	
	public List<RegionDTO> getRegions(){
		return regions;
	}
	
	public void setRegions(List<RegionDTO> regions){
		this.regions = regions;
	}
	
	public String toString(){
		return "Region[" + this.id + " - " + this.name + " ]"; 
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}
}
