package br.com.fatec.oqfazer.api.dao;

import java.util.List;

import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.api.entity.Region;

public interface CityDAO {

	Long insertCity(Region region, List<City> cities);
	
	void deleteCity(Long idRegion);
	
	void updateCity(Region region, List<City> cities);
	
	void updateCity(Region region, City city);
	
	City searchCityById(Long id);
	
	List<City> searchAllCity();
}
