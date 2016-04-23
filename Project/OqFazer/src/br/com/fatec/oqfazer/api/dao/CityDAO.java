package br.com.fatec.oqfazer.api.dao;

import java.util.List;

import br.com.fatec.oqfazer.api.entity.City;

public interface CityDAO {

	Long insertCity(Long regionId, List<City> cities);
	
	public void deleteCity(Long regionId, List<City> cities);
	
	public void deleteCity(Long regionId);
	
	public void updateCity(Long regionId, List<City> cities);
	
	List<City> searchCityByRegionId(Long regionId);
	
	List<City> searchCityByRegionName(String regionName);
	
	List<City> searchAllCity();
}
