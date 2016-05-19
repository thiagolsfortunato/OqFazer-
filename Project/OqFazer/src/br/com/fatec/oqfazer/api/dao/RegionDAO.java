package br.com.fatec.oqfazer.api.dao;

import java.util.List;
import br.com.fatec.oqfazer.api.entity.Region;

public interface RegionDAO {
	
	Long insertRegion(Region region);
	
	Boolean deleteRegion(Long id);
	
	void updateRegion(Region region);
	
	Region searchRegionById(Long id);
	
	List<Region> searchAllRegions();
}
