package br.com.fatec.oqfazer.api.dao;

import java.util.List;
import br.com.fatec.oqfazer.api.entity.Category;


public interface CategoryDAO {
	
	Long insertCategory(Category category);
	
	void deleteCategory(Long id);
	
	void updateCategory(Category category);
	
	Category searchCategoryById(Long id);
	
	List<Category> searchAllCategory();
}
