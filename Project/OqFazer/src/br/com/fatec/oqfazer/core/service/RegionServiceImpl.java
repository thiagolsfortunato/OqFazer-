package br.com.fatec.oqfazer.core.service;

import java.util.List;

import br.com.fatec.oqfazer.api.dao.CityDAO;
import br.com.fatec.oqfazer.api.dao.RegionDAO;
import br.com.fatec.oqfazer.api.dto.RegionDTO;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.fatec.oqfazer.api.service.RegionService;
import br.com.fatec.oqfazer.core.converter.RegionDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class RegionServiceImpl implements RegionService{
	
	private RegionDAO regionDAO;
	private RegionDTOConverter regionDTOConverter;
	private CityDAO cityDAO;
	
	public RegionServiceImpl() {
		this.regionDAO = ImplFinder.getImpl(RegionDAO.class);
		this.cityDAO = ImplFinder.getImpl(CityDAO.class);
		this.regionDTOConverter = ImplFinder.getFinalImpl(RegionDTOConverter.class);
	}
	
	@Override
	public RegionDTO insert(RegionDTO regionDTO){
		Region region = this.regionDTOConverter.toEntity(regionDTO);
		Long id = this.regionDAO.insertRegion(region);
		this.cityDAO.insertCity(id, regionDTO.getCities());
		regionDTO.setId(id);
		return regionDTO;
	}

	@Override
	public void update(RegionDTO regionDTO) {
		Region region = this.regionDTOConverter.toEntity(regionDTO);
		this.regionDAO.updateRegion(region);
		this.cityDAO.updateCity(region.getId(), regionDTO.getCities());
	}

	@Override
	public void delete(Long idRegionDTO) {
		this.regionDAO.deleteRegion(idRegionDTO);
		this.cityDAO.deleteCity(idRegionDTO);
	}
	
	@Override
	public void delete(RegionDTO regionDTO) {
		this.regionDAO.deleteRegion(regionDTO.getId());
		this.cityDAO.deleteCity(regionDTO.getId(), regionDTO.getCities());
	}

	@Override
	public List<RegionDTO> searchAll() {
		return this.regionDTOConverter.toDTO(this.regionDAO.searchAllRegions());
	}

	@Override
	public RegionDTO searchById(Long idRegionDTO) {
		return this.regionDTOConverter.toDTO(this.regionDAO.searchRegionById(idRegionDTO));
	}

}
