package br.com.fatec.oqfazer.web.context;

import java.io.Serializable;
import java.util.List;

import br.com.fatec.oqfazer.api.dto.CategoryDTO;


public class ContextCategory implements Serializable {
	
	/** */
	private static final long serialVersionUID = -4946584307036887149L;
	
	private CategoryDTO categoryDTO;
	private List<CategoryDTO> categoriesDTO;
	
	
	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}
	
	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}
	
	public List<CategoryDTO> getCategoriesDTO() {
		return categoriesDTO;
	}
	
	public void setCategoriesDTO(List<CategoryDTO> categoriesDTO) {
		this.categoriesDTO = categoriesDTO;
	}
}
