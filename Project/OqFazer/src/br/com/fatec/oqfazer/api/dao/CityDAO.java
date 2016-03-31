package br.com.fatec.oqfazer.api.dao;

import java.util.List;

import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.api.entity.Region;

public interface CityDAO {

	Long insertCity(Long idRegion, List<City> cities);
	
	public void deleteCity(Long idRegion, List<City> cities);
	
	public void updateCity(Long idRegion, List<City> cities);
	
	List<String> searchCityByRegionId(Long regionId);
	
	List<String> searchCityByRegionName(String regionName);
	
	List<String> searchAllCity();
}
