package br.com.fatec.oqfazer.core.service;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.dao.CityDAO;
import br.com.fatec.oqfazer.api.dao.EventDAO;
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
	private EventDAO eventDAO;
	private RegionDTOConverter regionDTOConverter;
	
	public RegionServiceImpl() {
		this.regionDAO = ImplFinder.getImpl(RegionDAO.class);
		this.cityDAO = ImplFinder.getImpl(CityDAO.class);
		this.eventDAO = ImplFinder.getImpl(EventDAO.class);
		this.regionDTOConverter = ImplFinder.getFinalImpl(RegionDTOConverter.class);
	}
	
	@Override
	public RegionDTO insert(RegionDTO regionDTO){
		if(this.cityNotRegistered(regionDTO.getCities())){
			Region region = this.regionDTOConverter.toEntity(regionDTO);
			Long id = this.regionDAO.insertRegion(region);
			this.cityDAO.insertCity(id, this.regionDTOConverter.toEnumCity(regionDTO.getCities()));
			regionDTO.setId(id);
		}else{
			regionDTO.setErro("Cidade já cadastrada em outra Região");
		}
		return regionDTO;
	}

	@Override
	public void update(RegionDTO regionDTO) {
		List<CityDTO> newCities = Lists.newArrayList();
		for (CityDTO cityDTO : regionDTO.getCities()) {
			if(!cityDTO.getCreateSystem()) newCities.add(cityDTO);
		}
		
		if(this.cityNotRegistered(newCities)){
			Region region = this.regionDTOConverter.toEntity(regionDTO);
			this.regionDAO.updateRegion(region);
			this.cityDAO.updateCity(region.getId(), this.regionDTOConverter.toEnumCity(regionDTO.getCities()));
		}else{
			regionDTO.setErro("Cidade já cadastrada em outra Região");
		}
	}

	@Override
	public void delete(Long idRegionDTO) {
		if(this.eventDAO.searchEvenstsByIdRegion(idRegionDTO).isEmpty()){
			this.cityDAO.deleteCity(idRegionDTO);
			this.regionDAO.deleteRegion(idRegionDTO);
		}
	}
	
	@Override
	public void delete(RegionDTO regionDTO) {
		this.regionDAO.deleteRegion(regionDTO.getId());
		this.cityDAO.deleteCity(regionDTO.getId(), this.regionDTOConverter.toEnumCity(regionDTO.getCities()));
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
		List<CityDTO> citiesDTO = this.regionDTOConverter.toStringCity(cities);
		for (CityDTO cityDTO : citiesDTO) {
			cityDTO.setCreateSystem(true);
		}
		return citiesDTO;
	}

	@Override
	public List<CityDTO> searchAllCities(){
		List<City> cities = Lists.newArrayList(City.values());
		return this.regionDTOConverter.toStringCity(cities);
	}
	
	private boolean cityNotRegistered(List<CityDTO> cityDTO) {
		List<City> cities = this.regionDTOConverter.toEnumCity(cityDTO);
		for (City city : cities) {
			if(this.cityDAO.searchCityByName(city.name()) != null){
				return false;
			}
		}
		return true;
	}
	
}
