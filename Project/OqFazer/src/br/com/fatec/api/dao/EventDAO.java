package br.com.fatec.api.dao;

import java.util.List;
import br.com.fatec.api.entity.Event;

public interface EventDAO {
	
	Long inserEvent(Event event);
	
	Long deleteEvent(Long id);
	
	void updateEvent(Event event);
	
	Event searchEventById(Long event);
	
	List<Event> searchAllEvents();
}
