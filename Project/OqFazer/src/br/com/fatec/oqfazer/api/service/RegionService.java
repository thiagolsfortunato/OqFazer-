package br.com.fatec.oqfazer.api.service;

import java.util.List;

import br.com.fatec.oqfazer.api.dto.RegionDTO;

public interface RegionService {
	RegionDTO insert(RegionDTO regionDTO);
	
	void update(RegionDTO regionDTO);
	
	void delete(Long idRegionDTO);
	
	void delete(RegionDTO regionDTO);
	
	List<RegionDTO> searchAll();
	
	RegionDTO searchById(Long idRegionDTO);
}
