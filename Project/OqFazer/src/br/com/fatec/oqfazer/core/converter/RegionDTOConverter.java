package br.com.fatec.oqfazer.core.converter;
import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.dao.CityDAO;
import br.com.fatec.oqfazer.api.dao.RegionDAO;
import br.com.fatec.oqfazer.api.dto.CityDTO;
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

	public Region toEntity(RegionDTO regionDTO) {
		Region region = new Region();
		region.setId(regionDTO.getId());
		region.setName(regionDTO.getName());
		List<City> city = this.toEnumCity(regionDTO.getCities()); // ver o que vai fazer aqui !!! 
		return region;
	}

	public List<Region> toEntity(List<RegionDTO> regionsDTO) {
		List<Region> regions = Lists.newArrayList();
		for(RegionDTO regionDTO : regionsDTO){
			regions.add(this.toEntity(regionDTO));
		}
		return regions;
	}
	
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
	
	public List<City> toEnumCity (List<CityDTO> citiesString){
		List<City> cities = Lists.newArrayList();
		for(CityDTO cityString : citiesString){
			cities.add(City.valueOf(cityString.getName()));
		}
		return cities;
	}

	public List<CityDTO> toStringCity (List<City> citiesEnum){
		List<CityDTO> cities = Lists.newArrayList();
		for (City city : citiesEnum) {
			CityDTO cityDTO = new CityDTO();
			cityDTO.setName(city.name());
			cityDTO.setNome(city.getNome());
			cities.add(cityDTO);
		}
		return cities;
	}

}
