package br.com.fatec.oqfazer.web.action;

import br.com.fatec.oqfazer.api.dto.RegionDTO;
import br.com.fatec.oqfazer.api.service.CityService;
import br.com.fatec.oqfazer.api.service.RegionService;
import br.com.fatec.oqfazer.web.context.ContextRegion;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class RegionAction extends OqFazerWebAction{

	/***/
	private static final long serialVersionUID = -9049417808540514788L;
	private static final String IT_WORKED = "worked";
	
	private ContextRegion context = new ContextRegion();
	private RegionService service;
	private CityService serviceCity;
	
	public RegionAction() {
		this.service = ImplFinder.getImpl(RegionService.class);
		this.serviceCity = ImplFinder.getImpl(CityService.class);
	}

	public String searchAll(){
		this.context.setRegions(this.service.searchAll());
		return IT_WORKED;
	}
	
	public String searchAllCities(){
		//this.context.getRegion().setCities(this.serviceCity.searchAllCities());
		this.context.setCities(this.serviceCity.searchAllCities());
		return IT_WORKED;
	}
	
	public String insert(){
		if(this.context.getRegion().getId() != null){
			this.service.update(this.context.getRegion());
		}else{
			this.service.insert(this.context.getRegion());
		}
		return this.searchAll();
	}
	
	public String update(){
		RegionDTO regionDTO = this.service.searchById(this.context.getRegion().getId());
		this.context.setRegion(regionDTO);
		return this.searchAll();
	}
	
	public String delete(){
		this.service.delete(this.context.getRegion().getId());
		return this.searchAll();
	}
	
	public ContextRegion getContext() {
		return context;
	}

	public void setContext(ContextRegion context) {
		this.context = context;
	}
}
