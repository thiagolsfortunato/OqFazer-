package br.com.fatec.oqfazer.core.converter;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import br.com.fatec.oqfazer.api.dao.CategoryDAO;
import br.com.fatec.oqfazer.api.dto.CategoryDTO;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.spektro.minispring.core.implfinder.ImplFinder;
import br.com.spektro.minispring.dto.DTOConverter;

public class CategoryDTOConverter implements DTOConverter<Category, CategoryDTO>{
	
	private CategoryDAO categoryDAO;
	private CategoryDTOConverter categoryConverter;
	
	public CategoryDTOConverter (){
		ImplFinder.getImpl(CategoryDAO.class);
	}

	@Override
	public CategoryDTO toDTO(Category entityCategory) {
		return this.toDTO(entityCategory, true);
	}
	
	public CategoryDTO toDTO(Category entityCategory, boolean convertDependences){
		CategoryDTO dtoCategory = this.DTOSimple(entityCategory);
		Long id = entityCategory.getId();
		if(id!=null && convertDependences){
			List<Long> idsCategories = this.categoryDAO.searchCategories(id);
			List<Category> entityCategories = this.categoryDAO.searchCategoriesByListIds(idsCategories);
			List<CategoryDTO> categoriesDTO = this.categoryConverter.toDTO(entityCategories);
			
			Set<CategoryDTO> categoryCategories = Sets.newLinkedHashSet();
			categoryCategories.addAll(categoriesDTO);

			dtoCategory.setCategories(categoriesDTO);
			dtoCategory.setCategoryCategories(categoryCategories);
		}
		return dtoCategory;
	}

	private CategoryDTO DTOSimple(Category entityCategory) {
		CategoryDTO dtoCategory = new CategoryDTO();
		dtoCategory.setId(entityCategory.getId());
		dtoCategory.setName(entityCategory.getName());
		dtoCategory.setCategoryDTO(this.DTOSimple(entityCategory.getCategory()));
		return dtoCategory;
	}

	@Override
	public List<CategoryDTO> toDTO(List<Category> categories) {
		return this.toDTO(categories, false);
	}
	
	public List<CategoryDTO> toDTOSimple(List<Category> categories){
		return this.toDTO(categories, true);
	}

	private List<CategoryDTO> toDTO(List<Category> entities, boolean isSimple) {
		List<CategoryDTO> dtos = Lists.newArrayList();
		for (Category entity: entities){
			dtos.add(this.toDTO(entity, isSimple));
		}
		return dtos;
	}

	@Override
	public Category toEntity(CategoryDTO dto) {
		Category entity = new Category();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCategory(this.toEntity(dto.getCategory()));
		return entity;
	}

	@Override
	public List<Category> toEntity(List<CategoryDTO> dtos) {
		List<Category> entities = Lists.newArrayList();
		for (CategoryDTO dto: dtos){
			entities.add(this.toEntity(dto));
		}
		return entities;
	}

}