package br.com.fatec.oqfazer.api.dao;

import java.util.List;

import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.api.entity.Region;

public interface CityDAO {

	void insertCity(Region region, List<City> cities);
	
	public void deleteCity(Long idRegion, List<City> cities);
	
	List<String> searchCityByRegionId(Long regionId);
	
	List<String> searchCityByRegionName(String regionName);
	
	List<String> searchAllCity();
}
