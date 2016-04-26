package br.com.fatec.oqfazer.test.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.fatec.oqfazer.api.dto.RegionDTO;
import br.com.fatec.oqfazer.test.common.TestScenario;

public class RegionServiceTest extends TestScenario {

	@Test
	public void insert(){
		RegionDTO dto = new RegionDTO(null, "Litoral Norte");
		dto.setCities(this.getCities(1l));
		
		RegionDTO saved = this.regionService.insert(dto);
		saved = this.regionService.searchById(saved.getId());
		Assert.assertEquals(new Long(3), saved.getId());
		Assert.assertEquals("Litoral Norte", saved.getName());
		
		Assert.assertEquals(1, saved.getCities().size());
		Assert.assertEquals(new Long(1L), saved.getCities().get(0));
	}
	
	@Test
	public void delete(){
		RegionDTO dto = this.regionsDTO.get(1L);
		dto.setCities(this.getCities(1L));
		
		RegionDTO saved = this.regionService.insert(dto);
		
		this.regionService.delete(saved.getId());
	}
	
	@Test
	public void update(){
		RegionDTO dto = this.regionsDTO.get(1L);
		
		dto.setName("Litoral Norte2");
		dto.setCities(this.getCities(1L));
		
		this.regionService.update(dto);
		dto = this.regionService.searchById(dto.getId());
		
		Assert.assertEquals(new Long(1), dto.getId());
		Assert.assertEquals("Litoral Norte2", dto.getName());
	}
	
	@Test
	public void searchAll(){
		List<RegionDTO> regions = this.regionService.searchAll();

		Assert.assertEquals(3, regions.size());
		Assert.assertEquals("Region1", regions.get(0).getName());
		Assert.assertEquals("Region2", regions.get(1).getName());
		Assert.assertEquals("Litoral Norte", regions.get(2).getName());
	}
	
}
