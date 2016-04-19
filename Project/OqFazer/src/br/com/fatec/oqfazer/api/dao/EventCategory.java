package br.com.fatec.oqfazer.api.dao;

import java.util.List;

import br.com.fatec.oqfazer.api.entity.Category;

public interface EventCategory {
	
	Long insertEventCategory(Long idEvent, Category category);
	
	Long insertEventCategory(Long idEvent, List<Category> categories);
	
	void updateEventCategory(Long idEvent, List<Category> categories);
	
	void deleteEventCategory(Long idEvent, Long idCategory);
	
	List<Long> searchCategories(Long idEvent);
	
	List<Long> searchEvents(Long idCategory);
}
