package br.com.fatec.oqfazer.core.converter;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.dto.CityDTO;
import br.com.fatec.oqfazer.api.entity.City;
import br.com.spektro.minispring.core.implfinder.ImplFinder;
import br.com.spektro.minispring.dto.DTOConverter;

public class CityDTOConverter implements DTOConverter<City, CityDTO>{

	private RegionDTOConverter regionDTOConverter;
	
	public CityDTOConverter() {
		this.regionDTOConverter = ImplFinder.getFinalImpl(RegionDTOConverter.class);
	}
	
	@Override
	public CityDTO toDTO(City city) {
		CityDTO cityDTO = new CityDTO();
		cityDTO.setNome(city.name());
		cityDTO.setRegionDTO(this.regionDTOConverter.toDTO(city.getRegion()));
		return cityDTO;
	}
		
	@Override
	public City toEntity(CityDTO cityDTO) {
		City city = null;
		city.valueOf(cityDTO.getNome());
		city.setRegion(this.regionDTOConverter.toEntity(cityDTO.getRegionDTO()));
		return city;
	}

	@Override
	public List<City> toEntity(List<CityDTO> citiesDTO) {
		List<City> cities = Lists.newArrayList();
		for(CityDTO cityDTO : citiesDTO){
			cities.add(this.toEntity(cityDTO));
		}
		return cities;
	}
	
	@Override
	public List<CityDTO> toDTO(List<City> cities) {
		List<CityDTO> citiesDTO = Lists.newArrayList();
		for(City city : cities){
			citiesDTO.add(this.toDTO(city));
		}
		return citiesDTO;
	}	
}
