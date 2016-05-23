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
		Region region = this.regionDTOConverter.toEntity(regionDTO);
		Long id = this.regionDAO.insertRegion(region);
		try{
			this.cityDAO.insertCity(id, this.regionDTOConverter.toEntityCity(regionDTO.getCities()));
		}catch(Exception e){
			String[] a = e.getMessage().split(" ");
			if(a[2].equals("constraint")){
				regionDTO.setErro("N�o foi poss�vel salvar esta regi�o, pois contem cidades ja cadastradas");
			}
		}
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
	public void delete(Long idRegionDTO) {
		if(this.eventDAO.searchEvenstsByIdRegion(idRegionDTO).isEmpty()){
			this.cityDAO.deleteCity(idRegionDTO);
			try{
				this.regionDAO.deleteRegion(idRegionDTO);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
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
