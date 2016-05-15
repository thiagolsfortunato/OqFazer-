package br.com.fatec.oqfazer.api.service;

import java.util.List;

import br.com.fatec.oqfazer.api.entity.City;

public interface CityService {

	List<City> searchAllCities();
	
	List<City> searchCityByRegionId(Long regionId);
	
}
