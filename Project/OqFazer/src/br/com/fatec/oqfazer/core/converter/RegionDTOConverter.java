package br.com.fatec.oqfazer.core.converter;

import java.util.List;

import br.com.fatec.oqfazer.api.dto.RegionDTO;
import br.com.fatec.oqfazer.api.entity.Region;
import br.com.spektro.minispring.core.implfinder.ImplFinder;
import br.com.spektro.minispring.dto.DTOConverter;

public class RegionDTOConverter implements DTOConverter<Region, RegionDTO> {
	
	@Override
	public RegionDTO toDTO(Region region) {
		RegionDTO regionDTO = new RegionDTO();
		
		return null;
	}

	public RegionDTO toDTOSimple(Region region){
		return null;
	}
	
	@Override
	public List<RegionDTO> toDTO(List<Region> regions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Region toEntity(RegionDTO regionDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Region> toEntity(List<RegionDTO> regionsDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
