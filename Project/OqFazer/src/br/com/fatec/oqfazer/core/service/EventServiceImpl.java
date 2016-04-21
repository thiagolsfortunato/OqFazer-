package br.com.fatec.oqfazer.core.service;

import java.util.ArrayList;
import java.util.List;

import br.com.fatec.oqfazer.api.dao.EventCategory;
import br.com.fatec.oqfazer.api.dao.EventDAO;
import br.com.fatec.oqfazer.api.dto.CategoryDTO;
import br.com.fatec.oqfazer.api.dto.EventDTO;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.service.EventService;
import br.com.fatec.oqfazer.core.converter.CategoryDTOConverter;
import br.com.fatec.oqfazer.core.converter.EventDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class EventServiceImpl implements EventService{
	
	private EventDAO eventDao;
	private EventCategory eventCategory;
	
	private EventDTOConverter eventConverter;
	private CategoryDTOConverter categoryConverter;
	
	public EventServiceImpl(){
		this.eventDao = ImplFinder.getImpl(EventDAO.class);
		this.eventCategory = ImplFinder.getImpl(EventCategory.class);
		
		this.eventConverter = ImplFinder.getFinalImpl(EventDTOConverter.class);
		this.categoryConverter = ImplFinder.getFinalImpl(CategoryDTOConverter.class);
	}

	@Override
	public EventDTO insert(EventDTO eventDTO) {
		Event event = this.eventConverter.toEntity(eventDTO);
		Long id = this.eventDao.inserEvent(event);
		List<CategoryDTO> categoriesList = eventDTO.getCategories();
		this.atualizeCategories(id, categoriesList);
		eventDTO.setId(id);
		return eventDTO;
	}

	private void atualizeCategories(Long id, List<CategoryDTO> categoriesList) {
		List<Category> categories = this.categoryConverter.toEntity(categoriesList);
		this.eventCategory.updateEventCategory(id, categories);
	}

	@Override
	public void update(EventDTO eventDTO) {
		Event event = this.eventConverter.toEntity(eventDTO);
		this.eventDao.updateEvent(event);
		List<CategoryDTO> categoriesEvent = eventDTO.getCategories();
		this.atualizeCategories(eventDTO.getId(), categoriesEvent);
	}

	@Override
	public void delete(Long idEvent) {
		this.atualizeCategories(idEvent, new ArrayList<CategoryDTO>());
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
