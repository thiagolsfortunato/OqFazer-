package br.com.fatec.oqfazer.web.action;
import br.com.fatec.oqfazer.api.dto.EventDTO;
import br.com.fatec.oqfazer.api.service.EventService;
import br.com.fatec.oqfazer.web.context.ContextEvent;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class EventAction extends OqFazerWebAction{

	private static final long serialVersionUID = 1071989853380980252L;
	private static final String IT_WORKED = "worked";
	
	private ContextEvent context = new ContextEvent();
	private EventService service;
	
	public EventAction() {
		this.service = ImplFinder.getFinalImpl(EventService.class);
	}
	
	public String searchAll(){
		this.context.setEventsDTO(this.service.searchAll());
		return IT_WORKED;
	}
	
	public String insert(){
		if(this.context.getEventDTO().getId() != null){
			this.service.update(this.context.getEventDTO());
		}else{
			this.service.insert(this.context.getEventDTO());
		}
		return this.searchAll();
	}
	
	public String update(){
		EventDTO eventDTO = this.service.searchById(this.context.getEventDTO().getId());
		this.context.setEventDTO(eventDTO);
		return this.searchAll();
	}
	
	public String delete(){
		this.service.delete(this.context.getEventDTO().getId());
		return this.searchAll();
	}

	public ContextEvent getContext() {
		return context;
	}

	public void setContext(ContextEvent context) {
		this.context = context;
	}
}
