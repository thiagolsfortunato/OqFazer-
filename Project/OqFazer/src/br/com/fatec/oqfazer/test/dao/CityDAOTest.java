package br.com.fatec.oqfazer.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.oqfazer.api.dao.CityDAO;
import br.com.fatec.oqfazer.api.dao.RegionDAO;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.test.common.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class CityDAOTest extends TestBase {
	
	private CityDAO cityDAO;
	private RegionDAO regionDAO;
	private Region region1;
	private Region region2;
	
	@Before
	public void config(){
		this.cityDAO = ImplFinder.getImpl(CityDAO.class);
		this.regionDAO = ImplFinder.getImpl(RegionDAO.class);
		
		Region region1 = new Region();
		region1.setId((long) 1);
		region1.setName("Vale do Paraiba");
		
		Region region2 = new Region();
		region2.setId((long) 1);
		region2.setName("Litoral Norte");
		
		Long idRegion1 = this.regionDAO.insertRegion(region1);
		Long idRegion2 = this.regionDAO.insertRegion(region2);
		
		region1 = this.regionDAO.searchRegionById(idRegion1);
		region2 = this.regionDAO.searchRegionById(idRegion2);
	}
	
	@Test
	public void testSave(){	
		List<City> cities = null;
		cities.add(City.SAO_JOSE_DOS_CAMPOS);
		cities.add(City.CACAPAVA);
		
		Long idRegion = this.cityDAO.insertCity(region1.getId(), cities);
		
		List<String> citiesSaved = this.cityDAO.searchCityByRegionId(idRegion);
		
		Assert.assertNotNull(citiesSaved);
		Assert.assertEquals("São José dos Campos", citiesSaved.get(0));
		Assert.assertEquals("Caçapava", citiesSaved.get(1));
	}
	
	@Test
	public void testDelete(){
		List<City> cities = null;
		cities.add(City.CARAGUATATUBA);
		cities.add(City.UBATUBA);
		
		Long idRegion = this.cityDAO.insertCity(region2.getId(), cities);
		this.cityDAO.deleteCity(idRegion, cities);
		
		List<String> citiesDelete = this.cityDAO.searchCityByRegionId(idRegion);
		
		Assert.assertNull(citiesDelete);
	}
	
	@Test
	public void testSearchByRegionName(){
		List<City> cities = null;
		cities.add(City.TAUBATE);
		cities.add(City.JACAREI);
		
		this.cityDAO.insertCity(region1.getId(), cities);
		
		List<String> citiesSearchByName = this.cityDAO.searchCityByRegionName(region1.getName());
		
		Assert.assertEquals("Taubaté", citiesSearchByName.get(0));
		Assert.assertEquals("Caçapava", citiesSearchByName.get(1));		
	}
	
	@Test
	public void testSearchAll(){
		List<City> cities = null;
		cities.add(City.CARAGUATATUBA);
		cities.add(City.UBATUBA);
		cities.add(City.ILHA_BELA);
		cities.add(City.SAO_SEBASTIAO);
		
		this.cityDAO.insertCity(region2.getId(), cities);
		
		List<String> citiesSearchAll = this.cityDAO.searchAllCity();
		
		Assert.assertEquals(4, cities.size());
		Assert.assertEquals("Caraguatatuba", citiesSearchAll.get(0));
		Assert.assertEquals("Ubatuba", citiesSearchAll.get(1));
		Assert.assertEquals("Ilha Bela", citiesSearchAll.get(2));
		Assert.assertEquals("São Sebastião", citiesSearchAll.get(3));
	}
	
}
