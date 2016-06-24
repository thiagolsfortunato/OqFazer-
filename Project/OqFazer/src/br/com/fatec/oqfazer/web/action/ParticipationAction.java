package br.com.fatec.oqfazer.web.action;

import br.com.fatec.oqfazer.api.service.EventService;
import br.com.fatec.oqfazer.web.context.ContextParticipation;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class ParticipationAction extends OqFazerWebAction{
	
	private static final long serialVersionUID = 8594238764831932338L;
	private static final String IT_WORKED = "worked";
	
	private ContextParticipation context = new ContextParticipation();
	private EventService service;
	
	
	public ParticipationAction(EventService service) {
		this.service = ImplFinder.getImpl(EventService.class);
	}
	
	public String insert(){
		this.service.insertParticipation(context.getIdEvent(), context.getIdUser());
		return IT_WORKED;
	}
	
	public String remove(){
		this.service.removeParticipation(context.getIdEvent(), context.getIdUser());
		return IT_WORKED;		
	}
}
