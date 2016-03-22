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
	
	private RegionDAO dao;
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(RegionDAO.class);
	}
	
	@Test
	public void testSave(){
		Region saveRegion = new Region();		
		saveRegion.setId((long) 1);
		saveRegion.setName("Caçapava");
		
		Long id = this.dao.insertRegion(saveRegion);
		Region savedRegion = this.dao.searchRegionById(id);
		
		Assert.assertNotNull(savedRegion);
		Assert.assertEquals(String.valueOf(1), savedRegion.getId());
		Assert.assertEquals("Caçapava", savedRegion.getName());
	}
	
	@Test
	public void testUpdate() {
		Region region = new Region();
		region.setId((long) 1);
		region.setName("Caçapava");

		Long id = this.dao.insertRegion(region);
		Region updateRegion = this.dao.searchRegionById(id);

		updateRegion.setId((long)1);
		updateRegion.setName("Kansaspava");

		this.dao.updateRegion(updateRegion);;
		Region updatedRegion = this.dao.searchRegionById(id);

		Assert.assertNotNull(updatedRegion);
		Assert.assertEquals(String.valueOf(1), updatedRegion.getId());
		Assert.assertEquals("kansaspava", updatedRegion.getName());
	}
	
	@Test
	public void testDelete() {
		Region region = new Region();
		region.setId((long)1);
		region.setName("Caçapava");

		Long id = this.dao.insertRegion(region);
		this.dao.deleteRegion(id);;

		Region deletedUser = this.dao.searchRegionById(id);

		Assert.assertNull(deletedUser);
	}
	
	@Test
	public void testFindAll() {
		Region region1 = new Region();
		region1.setId((long) 1);
		region1.setName("Caçapava");
		Region region2 = new Region();
		region2.setId((long)2);
		region2.setName("São José dos Campos");

		this.dao.insertRegion(region1);
		this.dao.insertRegion(region2);

		List<Region> listRegions = this.dao.searchAllRegions();

		Assert.assertEquals(2, listRegions.size());
		Assert.assertEquals("user 1", listRegions.get(0).getId());
		Assert.assertEquals("password_1", listRegions.get(0).getName());
		Assert.assertEquals("user 2", listRegions.get(1).getId());
		Assert.assertEquals("password_2", listRegions.get(1).getName());
	}
	
}
