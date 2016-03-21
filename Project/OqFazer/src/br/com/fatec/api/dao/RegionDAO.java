package br.com.fatec.api.dao;

import java.util.List;
import br.com.fatec.api.entity.Region;

public interface RegionDAO {
	
	Long insertRegion(Region region);
	
	Long deleteRegion(Long id);
	
	void updateRegion(Region region);
	
	Region searchRegionById(Long id);
	
	List<Region> searchAllRegions();
}
