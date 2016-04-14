package br.com.fatec.oqfazer.core.converter;

import java.lang.reflect.Constructor;
import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.dao.CityDAO;
import br.com.fatec.oqfazer.api.dto.RegionDTO;
import br.com.fatec.oqfazer.api.entity.City;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.spektro.minispring.core.implfinder.ImplFinder;
import br.com.spektro.minispring.dto.DTOConverter;

public class RegionDTOConverter implements DTOConverter<Region, RegionDTO> {
	
	private CityDAO cityDao;
	
	public RegionDTOConverter() {
		cityDao = ImplFinder.getImpl(CityDAO.class);
	}
	
	@Override
	public RegionDTO toDTO(Region region) {
		RegionDTO regionDTO = this.toDTOSimple(region);
		List<City> cities = cityDao.searchCityByRegionId(region.getId());
		regionDTO.setCities(cities);
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
		return null;
	}

	@Override
	public List<Region> toEntity(List<RegionDTO> regionsDTO) {
		// TODO Auto-generated method stub
		return null;
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

}
