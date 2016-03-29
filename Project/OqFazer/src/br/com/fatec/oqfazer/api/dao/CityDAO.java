package br.com.fatec.oqfazer.api.dao;

import java.util.List;
import br.com.fatec.oqfazer.api.entity.City;

public interface CityDAO {
	
	Long insertCity(City city);
	
	void deleteCity(Long id);
	
	void updateCity(City category);
	
	City searchCityById(Long id);
	
	List<City> searchAllCity();
}
