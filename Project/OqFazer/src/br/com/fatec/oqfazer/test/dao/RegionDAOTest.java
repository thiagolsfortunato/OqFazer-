package br.com.fatec.oqfazer.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.oqfazer.api.dao.RegionDAO;
import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.fatec.oqfazer.test.common.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class RegionDAOTest extends TestBase{
	
	private RegionDAO dao;
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(RegionDAO.class);
	}
	
	@Test
	public void testSave(){
		List<City> cities = null;
		for (City city : City.values()) {
			cities.add(city);
		}
		
		Region saveRegion = new Region();		
		saveRegion.setId((long) 1);
		saveRegion.setName("Vale do Paraiba");
		saveRegion.setCity(cities);		
		
		Long id = this.dao.insertRegion(saveRegion);
		Region savedRegion = this.dao.searchRegionById(id);
		
		Assert.assertNotNull(savedRegion);
		Assert.assertEquals(String.valueOf(1), savedRegion.getId());
		Assert.assertEquals("Vale do Paraiba", savedRegion.getName());
		Assert.assertEquals(City.SAO_JOSE_DOS_CAMPOS, savedRegion.getCity().get(0));
		Assert.assertEquals(City.TAUBATE.getNome(), savedRegion.getCity().get(1).getNome());
		
	}
	
	@Test
	public void testUpdate() {
		List<City> cities = null;
		cities.add(City.CACAPAVA);
		cities.add(City.TAUBATE);
		
		Region region = new Region();
		region.setId((long) 1);
		region.setName("Sentido Rio");
		region.setCity(cities);

		Long id = this.dao.insertRegion(region);
		Region updateRegion = this.dao.searchRegionById(id);

		List<City> cities2 = null;
		cities2.add(City.SAO_JOSE_DOS_CAMPOS);
		cities2.add(City.JACAREI);
				
		updateRegion.setId((long) 1);
		updateRegion.setName("Sentido Sao Paulo");
		updateRegion.setCity(cities2);		

		this.dao.updateRegion(updateRegion);;
		Region updatedRegion = this.dao.searchRegionById(id);

		Assert.assertNotNull(updatedRegion);
		Assert.assertEquals(String.valueOf(1), updatedRegion.getId());
		Assert.assertEquals("Sentido Sao Paulo", updatedRegion.getName());
		Assert.assertEquals(City.SAO_JOSE_DOS_CAMPOS, updateRegion.getCity().get(0));
		Assert.assertEquals(City.TAUBATE.getNome(), updateRegion.getCity().get(1).getNome());
	}
	
	@Test
	public void testDelete() {
		List<City> cities = null;
		cities.add(City.CACAPAVA);
		cities.add(City.TAUBATE);
		
		Region region = new Region();
		region.setId((long)1);
		region.setName("Vale do Paraiba");
		region.setCity(cities);
		
		Long id = this.dao.insertRegion(region);
		this.dao.deleteRegion(id);;

		Region deletedUser = this.dao.searchRegionById(id);

		Assert.assertNull(deletedUser);
	}
	
	@Test
	public void testFindAll() {
		List<City> cities = null;
		cities.add(City.CACAPAVA);
		cities.add(City.TAUBATE);
		
		Region region1 = new Region();
		region1.setId((long) 1);
		region1.setName("Sentido Rio");
		region1.setCity(cities);
		
		List<City> cities2 = null;
		cities2.add(City.SAO_JOSE_DOS_CAMPOS);
		cities2.add(City.JACAREI);
		
		Region region2 = new Region();
		region2.setId((long) 2);
		region2.setName("Sentido Sao Paulo");
		region2.setCity(cities2);

		this.dao.insertRegion(region1);
		this.dao.insertRegion(region2);

		List<Region> listRegions = this.dao.searchAllRegions();

		Assert.assertEquals(2, listRegions.size());
		Assert.assertEquals(String.valueOf(1), listRegions.get(0).getId());
		Assert.assertEquals("Sentido Rio", listRegions.get(0).getName());
		Assert.assertEquals(City.CACAPAVA, listRegions.get(0).getCity().get(0));
		Assert.assertEquals(City.TAUBATE.getNome(), listRegions.get(0).getCity().get(1).getNome());
		
		Assert.assertEquals(String.valueOf(2), listRegions.get(1).getId());
		Assert.assertEquals("Sentido Sao Paulo", listRegions.get(1).getName());
		Assert.assertEquals(City.SAO_JOSE_DOS_CAMPOS, listRegions.get(1).getCity().get(0));
		Assert.assertEquals(City.JACAREI.getNome(), listRegions.get(1).getCity().get(1).getNome());
	}
	
}

