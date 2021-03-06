package br.com.fatec.oqfazer.core.converter;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.dao.CategoryDAO;
import br.com.fatec.oqfazer.api.dto.CategoryDTO;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.spektro.minispring.core.implfinder.ImplFinder;
import br.com.spektro.minispring.dto.DTOConverter;

public class CategoryDTOConverter implements DTOConverter<Category, CategoryDTO>{
	
	private CategoryDAO categoryDAO;
	
	public CategoryDTOConverter (){
		this.categoryDAO = ImplFinder.getImpl(CategoryDAO.class);
	}

	@Override
	public CategoryDTO toDTO(Category entityCategory) {
		return this.toDTO(entityCategory, true);
	}
	
	public CategoryDTO toDTO(Category entityCategory, boolean convertDependences){
		CategoryDTO dtoCategory = this.toDTOSimple(entityCategory);
		Long id = entityCategory.getId();
		if(id != null && convertDependences){
			List<Long> idsCategories = this.categoryDAO.searchCategoriesChildren(id);
				
			if(!idsCategories.isEmpty()){
				for (Long idCategory : idsCategories) {
					dtoCategory.setCategoriesChildren(idCategory);
				}				
			}
			
//			CategoryDTO dtoParent = new CategoryDTO();
//			dtoParent.setId(entityCategory.getParent());
//			dtoParent.setCategoriesChildren(entityCategory.getId());
		}
		return dtoCategory;
	}

	public CategoryDTO toDTOSimple(Category entityCategory) {
		CategoryDTO dtoCategory = new CategoryDTO();
		dtoCategory.setId(entityCategory.getId());
		dtoCategory.setName(entityCategory.getName());
		if(entityCategory.getParent() != 0){
			dtoCategory.setParentDTO(this.toDTOSimple(this.categoryDAO.searchCategoryById(entityCategory.getParent())));
		}
		
		return dtoCategory;
	}

	public List<Long> CategoryChild(Long id){
		List<Long> children = Lists.newArrayList();
		Long father = id;
		Long child = this.categoryDAO.searchChildCategory(id);
		if(child == null){
			children.add(father);
		}else{
			children.addAll(this.CategoryChild(child));
		}
		return children;
	}
	
	@Override
	public List<CategoryDTO> toDTO(List<Category> categories) {
		return this.toDTO(categories, true);
	}
	
	public List<CategoryDTO> toDTOSimple(List<Category> categories){
		return this.toDTO(categories, false);
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
		if (dto.getParentDTO()!= null) entity.setParent(dto.getParentDTO().getId());
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