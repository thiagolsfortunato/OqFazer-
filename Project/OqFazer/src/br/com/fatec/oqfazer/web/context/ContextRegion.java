package br.com.fatec.oqfazer.web.context;

import java.io.Serializable;
import java.util.List;

import br.com.fatec.oqfazer.api.dto.CityDTO;
import br.com.fatec.oqfazer.api.dto.RegionDTO;
import br.com.fatec.oqfazer.api.entity.City;

public class ContextRegion implements Serializable{

	/***/
	private static final long serialVersionUID = 6814441968746878032L;
	
	private RegionDTO region;
	private List<RegionDTO> regions;
	private List<CityDTO> cities;
	
	public RegionDTO getRegion() {
		return region;
	}
	
	public void setRegion(RegionDTO region) {
		this.region = region;
	}
	
	public List<RegionDTO> getRegions() {
		return regions;
	}
	
	public void setRegions(List<RegionDTO> regions) {
		this.regions = regions;
	}

	public List<CityDTO> getCities() {
		return cities;
	}

	public void setCities(List<CityDTO> cities) {
		this.cities = cities;
	}
}
