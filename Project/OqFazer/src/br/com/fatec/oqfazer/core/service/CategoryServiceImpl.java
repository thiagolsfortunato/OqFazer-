package br.com.fatec.oqfazer.core.service;

import java.util.List;

import br.com.fatec.oqfazer.api.dao.CategoryDAO;
import br.com.fatec.oqfazer.api.dao.EventCategory;
import br.com.fatec.oqfazer.api.dao.EventDAO;
import br.com.fatec.oqfazer.api.dto.CategoryDTO;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.fatec.oqfazer.api.service.CategoryService;
import br.com.fatec.oqfazer.core.converter.CategoryDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDAO categoryDAO;
	private EventCategory eventCategory;
	private EventDAO eventDAO;
	private CategoryDTOConverter categoryDTOConveter;

	public CategoryServiceImpl() {
		this.categoryDAO = ImplFinder.getImpl(CategoryDAO.class);
		this.eventDAO = ImplFinder.getImpl(EventDAO.class);
		this.eventCategory = ImplFinder.getImpl(EventCategory.class);
		this.categoryDTOConveter = ImplFinder.getFinalImpl(CategoryDTOConverter.class);
	}

	@Override
	public CategoryDTO insert(CategoryDTO categoryDTO) {
		Category category = this.categoryDTOConveter.toEntity(categoryDTO);
		Long id = categoryDAO.insertCategory(category);
		categoryDTO.setId(id);
		return categoryDTO;
	}

	@Override
	public void update(CategoryDTO categoryDTO) {
		Category category = this.categoryDTOConveter.toEntity(categoryDTO);
		this.categoryDAO.updateCategory(category);		
	}

	@Override 
	public void delete(Long idCategoryDTO) {
		CategoryDTO categoryDTO = this.categoryDTOConveter.toDTO(this.categoryDAO.searchCategoryById(idCategoryDTO));
		if (this.eventCategory.searchEvents(categoryDTO.getId()).isEmpty()) {
			if (categoryDTO.getCategoriesChildren().isEmpty()) {
				this.categoryDAO.deleteCategory(idCategoryDTO);
			} 
		}else{
			
		}
	}
	
	@Override
	public List<CategoryDTO> searchAll() {
		return this.categoryDTOConveter.toDTO(this.categoryDAO.searchAllCategory());
	}

	@Override
	public CategoryDTO searchById(Long idCategoryDTO) {
		return this.categoryDTOConveter.toDTO(this.categoryDAO.searchCategoryById(idCategoryDTO));
	}
}
