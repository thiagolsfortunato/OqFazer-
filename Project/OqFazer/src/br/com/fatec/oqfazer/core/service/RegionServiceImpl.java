package br.com.fatec.oqfazer.core.service;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.dao.CityDAO;
import br.com.fatec.oqfazer.api.dao.RegionDAO;
import br.com.fatec.oqfazer.api.dto.CityDTO;
import br.com.fatec.oqfazer.api.dto.RegionDTO;
import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.fatec.oqfazer.api.service.CityService;
import br.com.fatec.oqfazer.api.service.RegionService;
import br.com.fatec.oqfazer.core.converter.RegionDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class RegionServiceImpl implements RegionService, CityService {
	
	private RegionDAO regionDAO;
	private CityDAO cityDAO;
	private RegionDTOConverter regionDTOConverter;
	
	public RegionServiceImpl() {
		this.regionDAO = ImplFinder.getImpl(RegionDAO.class);
		this.cityDAO = ImplFinder.getImpl(CityDAO.class);
		this.regionDTOConverter = ImplFinder.getFinalImpl(RegionDTOConverter.class);
	}
	
	@Override
	public RegionDTO insert(RegionDTO regionDTO){
		Region region = this.regionDTOConverter.toEntity(regionDTO);
		Long id = this.regionDAO.insertRegion(region);
		this.cityDAO.insertCity(id, this.regionDTOConverter.toEntityCity(regionDTO.getCities()));
		regionDTO.setId(id);
		return regionDTO;
	}

	@Override
	public void update(RegionDTO regionDTO) {
		Region region = this.regionDTOConverter.toEntity(regionDTO);
		this.regionDAO.updateRegion(region);
		this.cityDAO.updateCity(region.getId(), this.regionDTOConverter.toEntityCity(regionDTO.getCities()));
	}

	@Override
	public Boolean delete(Long idRegionDTO) {
		this.cityDAO.deleteCity(idRegionDTO);
		boolean condition = this.regionDAO.deleteRegion(idRegionDTO);
		return condition;
	}
	
	@Override
	public void delete(RegionDTO regionDTO) {
		this.regionDAO.deleteRegion(regionDTO.getId());
		this.cityDAO.deleteCity(regionDTO.getId(), this.regionDTOConverter.toEntityCity(regionDTO.getCities()));
	}
	
	@Override
	public List<RegionDTO> searchAll() {
		return this.regionDTOConverter.toDTO(this.regionDAO.searchAllRegions());
	}

	@Override
	public RegionDTO searchById(Long idRegionDTO) {
		return this.regionDTOConverter.toDTO(this.regionDAO.searchRegionById(idRegionDTO));
	}

	@Override
	public List<CityDTO> searchCityByRegionId(Long regionId) {
		List<City> cities = this.cityDAO.searchCityByRegionId(regionId);
		return this.regionDTOConverter.toStringCity(cities);
	}

	@Override
	public List<CityDTO> searchAllCities(){
		List<City> cities = Lists.newArrayList(City.values());
		return this.regionDTOConverter.toStringCity(cities);
	}
}
