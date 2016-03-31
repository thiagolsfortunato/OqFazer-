package br.com.fatec.oqfazer.api.dao;

import java.util.List;

import br.com.fatec.oqfazer.api.entity.Category;


public interface EventCategory {
	
	void updateCategory(Long eventId, List<Category> categories);
	
	void updateCategory(Long eventId, Category category);
	
	List<Category> searchCategory(Long eventId);
}
