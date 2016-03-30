package br.com.fatec.oqfazer.core.dao;

import java.util.List;

import br.com.fatec.oqfazer.api.dao.CityDAO;
import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.api.entity.Region;

public class CityDAOImpl implements CityDAO{

	@Override
	public Long insertCity(Region region, List<City> cities) {
		
		return null;
	}

	@Override
	public void deleteCity(Long idRegion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCity(Region region, List<City> cities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCity(Region region, City city) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public City searchCityById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> searchAllCity() {
		// TODO Auto-generated method stub
		return null;
	}

}
