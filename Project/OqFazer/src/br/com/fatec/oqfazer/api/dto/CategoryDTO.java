package br.com.fatec.oqfazer.api.dto;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class CategoryDTO {
	
	private Long id;
	private String name;
	private CategoryDTO parentDTO;
	
	private List<CategoryDTO> categories = Lists.newArrayList();
	private Set<Long> categoriesChildren = Sets.newHashSet();
	
	public CategoryDTO(){};
	
	public CategoryDTO(Long id, String name, CategoryDTO parentDTO) {
		this.id = id;
		this.name = name;
		this.parentDTO = parentDTO;
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

	public CategoryDTO getParentDTO() {
		return parentDTO;
	}

	public void setParentDTO(CategoryDTO parent) {
		this.parentDTO = parent;
	}
	
	public String toString(){
		return "Category[" + this.id + " - " + this.name + " ]";
	}
	
	public List<CategoryDTO> getCategories(){
		return this.categories;
	}

	public void setCategories(CategoryDTO categoryDTO) {
		this.categories.add(categoryDTO);
	}
	
	public Set<Long> getCategoriesChildren(){
		return this.categoriesChildren;
	}
	
	public void setCategoriesChildren (Long categoryChild) {
		this.categoriesChildren.add(categoryChild);
	}	
}
