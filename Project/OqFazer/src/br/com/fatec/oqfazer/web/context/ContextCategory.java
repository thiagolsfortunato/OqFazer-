package br.com.fatec.oqfazer.web.context;

import java.io.Serializable;
import java.util.List;

import br.com.fatec.oqfazer.api.dto.CategoryDTO;


public class ContextCategory implements Serializable {
	
	/** */
	private static final long serialVersionUID = -4946584307036887149L;
	
	private CategoryDTO category;
	private List<CategoryDTO> categories;
	
	public CategoryDTO getCategory() {
		return category;
	}
	
	public void setCategory(CategoryDTO category) {
		this.category = category;
	}
	
	public List<CategoryDTO> getCategories() {
		return categories;
	}
	
	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}	
}
