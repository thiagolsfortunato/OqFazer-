package br.com.fatec.oqfazer.api.dao;

import java.util.List;
import br.com.fatec.oqfazer.api.entity.Event;

public interface EventDAO {
	
	Long inserEvent(Event event);
	
	void deleteEvent(Long id);
	
	void updateEvent(Event event);
	
	Event searchEventById(Long id);
	
	List<Event> searchAllEvents();
	
	List<Event> searchEventsByListIds(List<Long> idsEvent);
}
