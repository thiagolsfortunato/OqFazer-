package br.com.fatec.oqfazer.web.context;

import java.io.Serializable;
import java.util.List;

import br.com.fatec.oqfazer.api.dto.EventDTO;

public class ContextEvent implements Serializable{

	/***/
	private static final long serialVersionUID = 7611395930990198414L;

	private EventDTO eventDTO;
	private List<EventDTO> eventsDTO;
	
	public EventDTO getEventDTO() {
		return eventDTO;
	}
	
	public void setEventDTO(EventDTO eventDTO) {
		this.eventDTO = eventDTO;
	}
	
	public List<EventDTO> getEventsDTO() {
		return eventsDTO;
	}
	
	public void setEventsDTO(List<EventDTO> eventsDTO) {
		this.eventsDTO = eventsDTO;
	}
	
}
