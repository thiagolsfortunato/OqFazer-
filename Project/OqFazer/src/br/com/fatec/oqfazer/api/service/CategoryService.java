package br.com.fatec.oqfazer.api.service;

import java.util.List;

import br.com.fatec.oqfazer.api.dto.CategoryDTO;

public interface CategoryService {
	
	CategoryDTO insert(CategoryDTO categoryDTO);
	
	void update(CategoryDTO categoryDTO);
	
	void delete(Long idCategoryDTO);
	
	List<CategoryDTO> searchAll();
	
	CategoryDTO searchById(Long idCategoryDTO);
}
