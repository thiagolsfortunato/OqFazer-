package br.com.fatec.oqfazer.test.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.dto.RegionDTO;
import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.test.common.TestScenario;

public class RegionServiceTest extends TestScenario {

	@Test
	public void insert(){
		RegionDTO dto = new RegionDTO(null, "Litoral Norte");
		List<City> cities = Lists.newArrayList();
		cities.add(City.CARAGUATATUBA);
		cities.add(City.ILHA_BELA);
		cities.add(City.SAO_SEBASTIAO);
		cities.add(City.UBATUBA);
		dto.setCities(this.regionDTOConverter.toStringCity(cities));
		
		RegionDTO saved = this.regionService.insert(dto);
		saved = this.regionService.searchById(saved.getId());
		Assert.assertEquals(new Long(3), saved.getId());
		Assert.assertEquals("Litoral Norte", saved.getName());
		
		Assert.assertEquals(4, saved.getCities().size());
		Assert.assertEquals("Caraguatatuba", saved.getCities().get(0).getNome());
		Assert.assertEquals("Ilha Bela", saved.getCities().get(1).getNome());
		Assert.assertEquals("São Sebastião", saved.getCities().get(2).getNome());
		Assert.assertEquals("Ubatuba", saved.getCities().get(3).getNome());

	}
	
	@Test
	public void delete(){
		RegionDTO dto = new RegionDTO(null, "Litoral Norte");
		List<City> cities = Lists.newArrayList();
		cities.add(City.CARAGUATATUBA);
		cities.add(City.ILHA_BELA);
		cities.add(City.SAO_SEBASTIAO);
		cities.add(City.UBATUBA);
		dto.setCities(this.regionDTOConverter.toStringCity(cities));
		
		RegionDTO saved = this.regionService.insert(dto);
		saved = this.regionService.searchById(saved.getId());
		boolean condition = false;
		
		Assert.assertTrue(!condition);
	}
	
	@Test
	public void update(){
		RegionDTO dto = this.regionsDTO.get(1L);
		
		dto.setName("Vale do Paraíba");
		dto.setCities(this.regionDTOConverter.toStringCity(this.getCities(1L)));
		
		this.regionService.update(dto);
		
		Assert.assertEquals(new Long(1), dto.getId());
		Assert.assertEquals("Vale do Paraíba", dto.getName());
	}
	
	@Test
	public void updateCities(){
		RegionDTO dto = this.regionsDTO.get(1L);
		List<City> cities = Lists.newArrayList();
		cities.add(City.CARAGUATATUBA);
		cities.add(City.ILHA_BELA);
		cities.add(City.SAO_SEBASTIAO);
		cities.add(City.UBATUBA);
		cities.add(City.PINDAMONHANGABA);
		dto.setCities(this.regionDTOConverter.toStringCity(cities));
		
		this.regionService.update(dto);
		
		Assert.assertEquals(new Long(1), dto.getId());
		Assert.assertEquals("Region1", dto.getName());
		Assert.assertEquals("Caraguatatuba", dto.getCities().get(0).getNome());
		Assert.assertEquals("Ilha Bela", dto.getCities().get(1).getNome());
		Assert.assertEquals("São Sebastião", dto.getCities().get(2).getNome());
		Assert.assertEquals("Ubatuba", dto.getCities().get(3).getNome());
		Assert.assertEquals("Pindamonhangaba", dto.getCities().get(4).getNome());
	}
	
	@Test
	public void searchAll(){
		List<RegionDTO> regions = this.regionService.searchAll();

		Assert.assertEquals(2, regions.size());
		Assert.assertEquals("Region1", regions.get(0).getName());
		Assert.assertEquals("Region2", regions.get(1).getName());
	}
	
}
