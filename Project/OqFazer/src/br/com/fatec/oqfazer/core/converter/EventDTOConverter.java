package br.com.fatec.oqfazer.core.converter;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.dao.CategoryDAO;
import br.com.fatec.oqfazer.api.dao.EventCategory;
import br.com.fatec.oqfazer.api.dao.EventDAO;
import br.com.fatec.oqfazer.api.dao.ParticipationDAO;
import br.com.fatec.oqfazer.api.dao.UserDAO;
import br.com.fatec.oqfazer.api.dto.EventDTO;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.entity.User;
import br.com.spektro.minispring.core.implfinder.ImplFinder;
import br.com.spektro.minispring.dto.DTOConverter;

public class EventDTOConverter implements DTOConverter<Event, EventDTO>{

	private RegionDTOConverter regionDTOConverter;
	private UserDTOConverter userDTOConverter;
	private CategoryDTOConverter categoryDTOConverter;
	private EventCategory eventCategory;
	private ParticipationDAO participation;
	private CategoryDAO categoryDAO;
	private UserDAO userDAO;
	
	public EventDTOConverter() {
		ImplFinder.getImpl(EventDAO.class);
		this.regionDTOConverter = ImplFinder.getFinalImpl(RegionDTOConverter.class);
		this.userDTOConverter = ImplFinder.getFinalImpl(UserDTOConverter.class);
		this.categoryDTOConverter = ImplFinder.getFinalImpl(CategoryDTOConverter.class);
		this.eventCategory = ImplFinder.getImpl(EventCategory.class);
		this.participation = ImplFinder.getImpl(ParticipationDAO.class);
		this.categoryDAO = ImplFinder.getImpl(CategoryDAO.class);
		this.userDAO = ImplFinder.getImpl(UserDAO.class); 
	}
	
	
	@Override
	public EventDTO toDTO(Event event) {
		return this.toDTO(event, true);
	}
	
	public EventDTO toDTO(Event event, boolean convertDependence) {		
		EventDTO eventDTO = this.toDTOSimple(event);
		Long id = eventDTO.getId();
		
		if(id != null && convertDependence){					
			List<Long> idsCategory = this.eventCategory.searchCategories(id);
			List<Category> categories = this.categoryDAO.searchCategoriesByListIds(idsCategory);
			eventDTO.setCategories(this.categoryDTOConverter.toDTO(categories));	
			
			List<Long> idsParticipation = this.participation.searchUsers(id);
			List<User> participation = this.userDAO.searchUsersByIds(idsParticipation);
			eventDTO.setParticipation(this.userDTOConverter.toDTO(participation));					
		}		
		
		eventDTO.setDescription(event.getDescription());
		eventDTO.setRegistration_date(event.getRegistration_date());
		eventDTO.setRegion(this.regionDTOConverter.toDTO(event.getRegion()));
		eventDTO.setOwner(this.userDTOConverter.toDTO(event.getOwner()));
		
		return eventDTO;
	}
	
	public EventDTO toDTOSimple(Event event) {
		EventDTO eventDTO = new EventDTO();
		eventDTO.setId(event.getId());
		eventDTO.setName(event.getName());
		eventDTO.setEvent_date(event.getEvent_date());
		eventDTO.setLocal(event.getLocal());
		eventDTO.setImageURL(event.getImageURL());		
		return eventDTO;
	}
	
	@Override
	public Event toEntity(EventDTO eventDTO) {
		Event event = new Event();
		event.setId(eventDTO.getId());
		event.setName(eventDTO.getName());
		event.setDescription(eventDTO.getDescription());
		event.setEvent_date(eventDTO.getEvent_date());
		event.setRegistration_date(eventDTO.getRegistration_date());
		event.setLocal(eventDTO.getLocal());
		event.setImageURL(event.getImageURL());
		if (eventDTO.getRegion() != null) event.setRegion(this.regionDTOConverter.toEntity(eventDTO.getRegion()));
		else event.setRegion(null);
		if (eventDTO.getOwner() != null) event.setOwner(this.userDTOConverter.toEntity(eventDTO.getOwner()));
		else event.setOwner(null);
		return event;
	}

	@Override
	public List<Event> toEntity(List<EventDTO> eventsDTO) {
		List<Event> events = Lists.newArrayList();
		for(EventDTO eventDTO : eventsDTO){
			events.add(this.toEntity(eventDTO));
		}
		return events;
	}

	@Override
	public List<EventDTO> toDTO(List<Event> events) {
		return this.toDTO(events, true);
	}
	
	public List<EventDTO> toDTOSimple(List<Event> events){
		return this.toDTO(events,false);
	}

	public List<EventDTO> toDTO(List<Event> events, boolean isSimple){
		List<EventDTO> eventDTO = Lists.newArrayList();
		for(Event event : events){
			eventDTO.add(this.toDTO(event, isSimple));
		}
		return eventDTO;
	}
}
