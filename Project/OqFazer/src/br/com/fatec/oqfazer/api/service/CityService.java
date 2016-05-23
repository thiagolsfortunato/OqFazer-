package br.com.fatec.oqfazer.api.service;

import java.util.List;

import br.com.fatec.oqfazer.api.entity.City;

public interface CityService {

	List<String> searchAllCities();
	
	List<String> searchCityByRegionId(Long regionId);
	
}
