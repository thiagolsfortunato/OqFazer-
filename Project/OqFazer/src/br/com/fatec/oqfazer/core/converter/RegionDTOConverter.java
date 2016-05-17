package br.com.fatec.oqfazer.core.converter;
import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.dao.CityDAO;
import br.com.fatec.oqfazer.api.dao.RegionDAO;
import br.com.fatec.oqfazer.api.dto.RegionDTO;
import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.spektro.minispring.core.implfinder.ImplFinder;
import br.com.spektro.minispring.dto.DTOConverter;

public class RegionDTOConverter implements DTOConverter<Region, RegionDTO> {
	
	private CityDAO cityDao;
	
	public RegionDTOConverter() {
		ImplFinder.getImpl(RegionDAO.class);
		cityDao = ImplFinder.getImpl(CityDAO.class);
	}
	
	@Override
	public RegionDTO toDTO(Region region) {
		RegionDTO regionDTO = this.toDTOSimple(region);
		List<City> cities = cityDao.searchCityByRegionId(region.getId());
		regionDTO.setCities(this.toStringCity(cities));
		return regionDTO;
	}

	public RegionDTO toDTOSimple(Region region){
		RegionDTO regionDTO = new RegionDTO();
		regionDTO.setId(region.getId());
		regionDTO.setName(region.getName());
		return regionDTO;
	}

	@Override
	public Region toEntity(RegionDTO regionDTO) {
		Region region = new Region();
		region.setId(regionDTO.getId());
		region.setName(regionDTO.getName());
		List<City> city = this.toEntityCity(regionDTO.getCities()); // ver o que vai fazer aqui !!! 
		return region;
	}

	@Override
	public List<Region> toEntity(List<RegionDTO> regionsDTO) {
		List<Region> regions = Lists.newArrayList();
		for(RegionDTO regionDTO : regionsDTO){
			regions.add(this.toEntity(regionDTO));
		}
		return regions;
	}
	
	@Override
	public List<RegionDTO> toDTO(List<Region> regions) {
		return this.toDTO(regions, false);
	}
	
	public List<RegionDTO> toDTOSimples(List<Region> regions) {
		return this.toDTO(regions, true);
	}
	
	private List<RegionDTO> toDTO(List<Region> regions, boolean isSimple){
		List<RegionDTO> regionsDTO = Lists.newArrayList();
		for(Region region : regions){
			regionsDTO.add(isSimple ? this.toDTOSimple(region) : this.toDTO(region));
		}
		return regionsDTO;
	}
	
	public List<City> toEntityCity (List<String> citiesString){
		List<City> cities = Lists.newArrayList();
		for (City city : City.values()) {
			for(String cityString : citiesString){
				if(city.getNome().equals(cityString)){
					cities.add(city);
				}		
			}
		}
		return cities;
	}

	public List<String> toStringCity (List<City> citiesEnum){
		List<String> cities = Lists.newArrayList();
		for (City city : citiesEnum) {
			cities.add(city.getNome());
		}
		return cities;
	}

}
