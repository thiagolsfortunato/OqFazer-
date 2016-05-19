package br.com.fatec.oqfazer.api.service;

import java.util.List;

import br.com.fatec.oqfazer.api.dto.CityDTO;
import br.com.fatec.oqfazer.api.entity.City;

public interface CityService {

	List<CityDTO> searchAllCities();
	
	List<CityDTO> searchCityByRegionId(Long regionId);
	
}
