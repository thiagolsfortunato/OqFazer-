package br.com.fatec.oqfazer.core.converter;

import java.util.List;
import br.com.fatec.oqfazer.api.dto.EventDTO;
import br.com.fatec.oqfazer.api.entity.Event;
import br.com.spektro.minispring.core.implfinder.ImplFinder;
import br.com.spektro.minispring.dto.DTOConverter;

public class EventDTOConverter implements DTOConverter<Event, EventDTO>{

	private CategoryDTOConverter categoryDTOConverter;
	private RegionDTOConverter regionDTOConverter;
	private UserDTOConverter userDTOConverter;
	
	public EventDTOConverter() {
		this.categoryDTOConverter = ImplFinder.getFinalImpl(CategoryDTOConverter.class);
		this.regionDTOConverter = ImplFinder.getFinalImpl(RegionDTOConverter.class);
		this.userDTOConverter = ImplFinder.getFinalImpl(UserDTOConverter.class);
	}
	
	@Override
	public EventDTO toDTO(Event event) {
		EventDTO eventDTO = this.toDTOSimple(event);
		eventDTO.setId(event.getId());
		eventDTO.setDescription(event.getDescription());
		eventDTO.setRegistration_date(event.getRegistration_date());
		eventDTO.setRegion(this.regionDTOConverter.toDTO(event.getRegion()));
		eventDTO.setOwner(this.userDTOConverter.toDTO(event.getOwner()));
		
		return null;
	}
	
	public EventDTO toDTOSimple(Event event) {
		EventDTO eventDTO = new EventDTO();
		eventDTO.setName(event.getName());
		eventDTO.setEvent_date(event.getEvent_date());
		eventDTO.setLocal(event.getLocal());
		eventDTO.setImageURL(event.getImageURL());		
		return eventDTO;
	}
	
	@Override
	public Event toEntity(EventDTO arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> toEntity(List<EventDTO> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventDTO> toDTO(List<Event> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
