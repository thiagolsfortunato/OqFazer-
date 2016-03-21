package br.com.fatec.oqfazer.api.dao;

import java.util.List;
import br.com.fatec.api.entity.Category;

public interface CategoryDAO {
	
	Long insertCategory(Category category);
	
	Long deleteCategory(Long id);
	
	void updateCategory(Category category);
	
	Category searchCategoryById(Long id);
	
	List<Category> searchAllCategory();
}
