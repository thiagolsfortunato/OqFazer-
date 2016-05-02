package br.com.fatec.oqfazer.api.dto;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class CategoryDTO {
	
	private Long id;
	private String name;
	private Long parent;
	
	private List<CategoryDTO> categories = Lists.newArrayList();
	private Set<CategoryDTO> categoriesChildren = Sets.newHashSet();
	
	public CategoryDTO(){};
	
	public CategoryDTO(Long id, String name, Long parent) {
		this.id = id;
		this.name = name;
		this.parent = parent;
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

	public Long getParentDTO() {
		return parent;
	}

	public void setParentDTO(Long parent) {
		this.parent = parent;
	}
	
	public String toString(){
		return "Category[" + this.id + " - " + this.name + " ]";
	}
	
	public List<CategoryDTO> getCategories(){
		return this.categories;
	}

	public void setCategories(List<CategoryDTO> categoriesDTO) {
		this.categories = categoriesDTO;
	}
	
	public Set<CategoryDTO> getCategoriesChildren(){
		return this.categoriesChildren;
	}
	
	public void setCategoriesChildren (Set<CategoryDTO> categoriesChildren) {
		this.categoriesChildren = categoriesChildren;
	}
	
}
