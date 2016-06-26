package br.com.fatec.oqfazer.core.service;

import java.util.ArrayList;
import java.util.List;

import br.com.fatec.oqfazer.api.dao.EventCategory;
import br.com.fatec.oqfazer.api.dao.EventDAO;
import br.com.fatec.oqfazer.api.dao.ParticipationDAO;
import br.com.fatec.oqfazer.api.dto.CategoryDTO;
import br.com.fatec.oqfazer.api.dto.EventDTO;
import br.com.fatec.oqfazer.api.dto.UserDTO;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.entity.User;
import br.com.fatec.oqfazer.api.service.EventService;
import br.com.fatec.oqfazer.core.converter.CategoryDTOConverter;
import br.com.fatec.oqfazer.core.converter.EventDTOConverter;
import br.com.fatec.oqfazer.core.converter.UserDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class EventServiceImpl implements EventService{
	
	private EventDAO eventDao;
	private EventCategory eventCategory;
	private ParticipationDAO participation;	
	
	private EventDTOConverter eventConverter;
	private CategoryDTOConverter categoryConverter;
	private UserDTOConverter userConverter;
	
	public EventServiceImpl(){
		this.eventDao = ImplFinder.getImpl(EventDAO.class);
		this.eventCategory = ImplFinder.getImpl(EventCategory.class);
		this.participation = ImplFinder.getImpl(ParticipationDAO.class);
		
		this.eventConverter = ImplFinder.getFinalImpl(EventDTOConverter.class);
		this.categoryConverter = ImplFinder.getFinalImpl(CategoryDTOConverter.class);
		this.userConverter = ImplFinder.getFinalImpl(UserDTOConverter.class);
	}

	@Override
	public EventDTO insert(EventDTO eventDTO) {
		Event event = this.eventConverter.toEntity(eventDTO);
		Long id = this.eventDao.inserEvent(event);
		
		List<CategoryDTO> categoriesList = eventDTO.getCategories();
		this.updateCategories(id, categoriesList);
		
		List<UserDTO> participationDTO = eventDTO.getParticipation();
		this.updateParticipation(id, participationDTO);
		
		eventDTO.setId(id);
		return eventDTO;
	}

	private void updateCategories(Long id, List<CategoryDTO> categoriesList) {
		List<Category> categories = this.categoryConverter.toEntity(categoriesList);
		this.eventCategory.updateEventCategory(id, categories);
	}
	
	private void updateParticipation(Long idEvent, List<UserDTO> participationDTO){
		List<User> participation = this.userConverter.toEntity(participationDTO);
		this.participation.updateUserParticipations(idEvent, participation);
	}
	
	public void insertParticipation(Long idEvent, Long idUser){
		this.participation.insertParticipation(idEvent, idUser);		
	}
	
	public void removeParticipation(Long idEvent, Long idUser){
		this.participation.deleteParticipation(idEvent, idUser);
	}	

	@Override
	public void update(EventDTO eventDTO) {
		Event event = this.eventConverter.toEntity(eventDTO);
		this.eventDao.updateEvent(event);
		
		List<CategoryDTO> categoriesEvent = eventDTO.getCategories();
		this.updateCategories(eventDTO.getId(), categoriesEvent);
		
		List<UserDTO> participationDTO = eventDTO.getParticipation();
		this.updateParticipation(eventDTO.getId(), participationDTO);
	}

	@Override
	public void delete(Long idEvent) {
		this.updateCategories(idEvent, new ArrayList<CategoryDTO>());
		this.updateParticipation(idEvent, new ArrayList<UserDTO>() );
		this.eventDao.deleteEvent(idEvent);
	}

	@Override
	public List<EventDTO> searchAll() {
		List<Event> events = this.eventDao.searchAllEvents();
		return this.eventConverter.toDTO(events);
	}

	@Override
	public EventDTO searchById(Long idEvent) {
		Event event = this.eventDao.searchEventById(idEvent);
		return this.eventConverter.toDTO(event);
	}
}
