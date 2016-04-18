package br.com.fatec.oqfazer.api.dto;

import java.util.List;
import java.util.Set;

import br.com.fatec.oqfazer.api.entity.Category;

public class CategoryDTO {
	
	private Long id;
	private String name;
	private CategoryDTO categoryDTO;
	
	public CategoryDTO(){};
	
	public CategoryDTO(Long id, String name, CategoryDTO category) {
		this.id = id;
		this.name = name;
		this.categoryDTO = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryDTO getCategory() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}
	
	public String toString(){
		return "Category[" + this.id + " - " + this.name + " ]";
	}

	public void setCategories(List<CategoryDTO> categoriesDTO) { //Verificar implementação
		// TODO Auto-generated method stub
		
	}

	public void setCategoryCategories(Set<CategoryDTO> categoryCategories) { //Verificar Implementação
		// TODO Auto-generated method stub
		
	}
	
}
