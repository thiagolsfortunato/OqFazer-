package br.com.fatec.oqfazer.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.oqfazer.api.dao.RegionDAO;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.fatec.oqfazer.test.common.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class RegionDAOTest extends TestBase{
	
	private RegionDAO regionDAO;
	
	@Before
	public void config(){
		this.regionDAO = ImplFinder.getImpl(RegionDAO.class);
	}
	
	@Test
	public void testSave(){		
		Region saveRegion = new Region();		
		saveRegion.setId((long) 1);
		saveRegion.setName("Vale do Paraiba");	
		
		Long id = this.regionDAO.insertRegion(saveRegion);
		Region savedRegion = this.regionDAO.searchRegionById(id);
		
		Assert.assertNotNull(savedRegion);
		Assert.assertEquals(new Long (1), savedRegion.getId());
		Assert.assertEquals("Vale do Paraiba", savedRegion.getName());
	}
	
	//@Test
	public void testUpdate() {
		Region region = new Region();
		region.setId((long) 1);
		region.setName("Sentido Rio");

		Long id = this.regionDAO.insertRegion(region);
		Region updateRegion = this.regionDAO.searchRegionById(id);
				
		updateRegion.setId((long) 1);
		updateRegion.setName("Sentido Sao Paulo");	

		this.regionDAO.updateRegion(updateRegion);;
		Region updatedRegion = this.regionDAO.searchRegionById(id);

		Assert.assertNotNull(updatedRegion);
		Assert.assertEquals(String.valueOf(1), updatedRegion.getId());
		Assert.assertEquals("Sentido Sao Paulo", updatedRegion.getName());
	}
	
	//@Test
	public void testDelete() {		
		Region region = new Region();
		region.setId((long)1);
		region.setName("Vale do Paraiba");
		
		Long id = this.regionDAO.insertRegion(region);
		this.regionDAO.deleteRegion(id);;

		Region deletedUser = this.regionDAO.searchRegionById(id);

		Assert.assertNull(deletedUser);
	}
	
	//@Test
	public void testFindAll() {	
		Region region1 = new Region();
		region1.setId((long) 1);
		region1.setName("Sentido Rio");
		
		Region region2 = new Region();
		region2.setId((long) 2);
		region2.setName("Sentido Sao Paulo");

		this.regionDAO.insertRegion(region1);
		this.regionDAO.insertRegion(region2);

		List<Region> listRegions = this.regionDAO.searchAllRegions();

		Assert.assertEquals(2, listRegions.size());
		Assert.assertEquals(String.valueOf(1), listRegions.get(0).getId());
		Assert.assertEquals("Sentido Rio", listRegions.get(0).getName());
				
		Assert.assertEquals(String.valueOf(2), listRegions.get(1).getId());
		Assert.assertEquals("Sentido Sao Paulo", listRegions.get(1).getName());
	}
	
}

