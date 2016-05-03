package br.com.fatec.oqfazer.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

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
		region2.setId((long) 2);
		region2.setName("Litoral Norte");
		
		Long idRegion1 = this.regionDAO.insertRegion(region1);
		Long idRegion2 = this.regionDAO.insertRegion(region2);
		
		this.region1 = this.regionDAO.searchRegionById(idRegion1);
		this.region2 = this.regionDAO.searchRegionById(idRegion2);
	}
	
	@Test
	public void testSave(){	
		List<City> cities = Lists.newArrayList();
		cities.add(City.SAO_JOSE_DOS_CAMPOS);
		cities.add(City.CACAPAVA);
		
		Long idRegion = this.cityDAO.insertCity(region1.getId(), cities);
		
		List<City> citiesSaved = this.cityDAO.searchCityByRegionId(idRegion);
		
		//ArrayList<City> citiesA = Lists.newArrayList(City.values());
		//System.out.println(cities.get(0));   -- EXEMPLO 
		
		Assert.assertNotNull(citiesSaved);
		Assert.assertEquals("São José dos Campos", citiesSaved.get(0).getNome());
		Assert.assertEquals("Caçapava", citiesSaved.get(1).getNome());
	}
	
	@Test
	public void testDelete(){
		List<City> cities = Lists.newArrayList();
		cities.add(City.CARAGUATATUBA);
		cities.add(City.UBATUBA);
		
		Long idRegion = this.cityDAO.insertCity(region2.getId(), cities);
		this.cityDAO.deleteCity(idRegion, cities);
		
		List<City> citiesDelete = this.cityDAO.searchCityByRegionId(idRegion);
		
		Assert.assertEquals(true, citiesDelete.isEmpty());
	}
	
	@Test
	public void testSearchByRegionName(){
		List<City> cities = Lists.newArrayList();
		cities.add(City.JACAREI);
		cities.add(City.TAUBATE);
		
		
		this.cityDAO.insertCity(region1.getId(), cities);
		
		List<City> citiesSearchByName = this.cityDAO.searchCityByRegionName(region1.getName());
		
		Assert.assertEquals("Jacareí", citiesSearchByName.get(0).getNome());
		Assert.assertEquals("Taubaté", citiesSearchByName.get(1).getNome());		
	}
	
	@Test
	public void testSearchAll(){
		List<City> cities = Lists.newArrayList();
		cities.add(City.CARAGUATATUBA);
		cities.add(City.UBATUBA);
		cities.add(City.ILHA_BELA);
		cities.add(City.SAO_SEBASTIAO);
		
		this.cityDAO.insertCity(region2.getId(), cities);
		
		List<City> citiesSearchAll = this.cityDAO.searchAllCity();
		
		Assert.assertEquals(4, cities.size());
		Assert.assertEquals("Caraguatatuba", citiesSearchAll.get(0).getNome());
		Assert.assertEquals("Ubatuba", citiesSearchAll.get(1).getNome());
		Assert.assertEquals("Ilha Bela", citiesSearchAll.get(2).getNome());
		Assert.assertEquals("São Sebastião", citiesSearchAll.get(3).getNome());
	}
}
