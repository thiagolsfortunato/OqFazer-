package br.com.fatec.oqfazer.web.action;

import br.com.fatec.oqfazer.api.dto.RegionDTO;
import br.com.fatec.oqfazer.api.service.RegionService;
import br.com.fatec.oqfazer.web.context.ContextRegion;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class RegionAction extends OqFazerWebAction{

	/***/
	private static final long serialVersionUID = -9049417808540514788L;
	private static final String IT_WORKED = "worked";
	
	private ContextRegion context = new ContextRegion();
	private RegionService service;
	
	public RegionAction() {
		this.service = ImplFinder.getFinalImpl(RegionService.class);
	}

	public String searchAll(){
		this.context.setRegionsDTO(this.service.searchAll());
		return IT_WORKED;
	}
	
	public String insert(){
		if(this.context.getRegionDTO().getId() != null){
			this.service.update(this.context.getRegionDTO());
		}else{
			this.service.insert(this.context.getRegionDTO());
		}
		return this.searchAll();
	}
	
	public String update(){
		RegionDTO regionDTO = this.service.searchById(this.context.getRegionDTO().getId());
		this.context.setRegionDTO(regionDTO);
		return this.searchAll();
	}
	
	public String delete(){
		this.service.delete(this.context.getRegionDTO().getId());
		return this.searchAll();
	}
	
	public ContextRegion getContext() {
		return context;
	}

	public void setContext(ContextRegion context) {
		this.context = context;
	}

}
