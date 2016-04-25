package br.com.fatec.oqfazer.web.context;

import java.io.Serializable;
import java.util.List;

import br.com.fatec.oqfazer.api.dto.RegionDTO;

public class ContextRegion implements Serializable{

	/***/
	private static final long serialVersionUID = 6814441968746878032L;
	
	private RegionDTO regionDTO;
	private List<RegionDTO> regionsDTO;
	
	public RegionDTO getRegionDTO() {
		return regionDTO;
	}
	
	public void setRegionDTO(RegionDTO regionDTO) {
		this.regionDTO = regionDTO;
	}

	public List<RegionDTO> getRegionsDTO() {
		return regionsDTO;
	}

	public void setRegionsDTO(List<RegionDTO> regionsDTO) {
		this.regionsDTO = regionsDTO;
	}
}
