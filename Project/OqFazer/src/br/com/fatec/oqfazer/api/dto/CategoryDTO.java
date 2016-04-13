package br.com.fatec.oqfazer.api.dto;

import java.util.List;
import java.util.Set;

import br.com.fatec.oqfazer.api.entity.Category;

public class CategoryDTO {
	
	private Long id;
	private String name;
	private Category category;
	
	public CategoryDTO(){};
	
	public CategoryDTO(Long id, String name, Category category) {
		this.id = id;
		this.name = name;
		this.category = category;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String toString(){
		return "Category[" + this.id + " - " + this.name + " ]";
	}

	public void setCategories(List<CategoryDTO> categoriesDTO) {
		// TODO Auto-generated method stub
		
	}

	public void setCategoryCategories(Set<CategoryDTO> categoryCategories) {
		// TODO Auto-generated method stub
		
	}
	
}
