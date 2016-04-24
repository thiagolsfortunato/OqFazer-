package br.com.fatec.oqfazer.api.dto;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class CategoryDTO {
	
	private Long id;
	private String name;
	private CategoryDTO categoryDTO;
	
	private List<CategoryDTO> categories = Lists.newArrayList();
	private Set<CategoryDTO> categoriesOfCategory = Sets.newHashSet();
	
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
	
	public List<CategoryDTO> getCategories(){
		return this.categories;
	}

	public void setCategories(List<CategoryDTO> categoriesDTO) { //Verificar Implementação
		this.categories = categoriesDTO;
	}
	
	public Set<CategoryDTO> getCategoriesOfCategory(){
		return this.categoriesOfCategory;
	}
	
	public void setCategoriesOfCategory(Set<CategoryDTO> categoriesOfCategory) { //Verificar Implementação
		this.categoriesOfCategory = categoriesOfCategory;
	}
	
}
