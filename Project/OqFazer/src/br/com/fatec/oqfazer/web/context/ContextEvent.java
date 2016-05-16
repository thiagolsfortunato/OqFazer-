package br.com.fatec.oqfazer.web.context;

import java.io.Serializable;
import java.util.List;

import br.com.fatec.oqfazer.api.dto.EventDTO;

public class ContextEvent implements Serializable{

	/***/
	private static final long serialVersionUID = 7611395930990198414L;

	private EventDTO event;
	private List<EventDTO> events;
	
	public EventDTO getEvent() {
		return event;
	}
	
	public void setEvent(EventDTO event) {
		this.event = event;
	}
	
	public List<EventDTO> getEvents() {
		return events;
	}
	
	public void setEvents(List<EventDTO> events) {
		this.events = events;
	}
	
	
}
