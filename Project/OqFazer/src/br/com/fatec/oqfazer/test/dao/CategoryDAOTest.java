package br.com.fatec.oqfazer.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.oqfazer.api.dao.CategoryDAO;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.fatec.oqfazer.test.common.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class CategoryDAOTest extends TestBase {

	private CategoryDAO dao;

	@Before
	public void config() {
		this.dao = ImplFinder.getImpl(CategoryDAO.class);
	}

	@Test
	public void testSave() {
		Category category = new Category();
		category.setId((long) 1);
		category.setName("Show");
		category.setCategory(null);

		Long id = this.dao.insertCategory(category);
		Category savedCategory = this.dao.searchCategoryById(id);

		Assert.assertNotNull(savedCategory);
		Assert.assertEquals(String.valueOf(1), savedCategory.getId());
		Assert.assertEquals("Show", savedCategory.getName());
		Assert.assertEquals(null, savedCategory.getCategory());
	}

	@Test
	public void testUpdate() {
		Category category = new Category();
		category.setId((long) 1);
		category.setName("Show");
		category.setCategory(null);

		Long id = this.dao.insertCategory(category);
		Category updateCategory = this.dao.searchCategoryById(id);
		
		updateCategory.setName("Teatro");
		
		this.dao.updateCategory(updateCategory);
		
		Category updatedCatedory = this.dao.searchCategoryById(id);
		
		Assert.assertNotNull(category);
		Assert.assertEquals(String.valueOf(1), updateCategory.getId());
		Assert.assertEquals("Teatro", updatedCatedory.getName());
		Assert.assertEquals(null, updatedCatedory.getCategory());
	}

	public void testDelete() {
		Category category = new Category();
		category.setId((long) 1);
		category.setName("Show");
		category.setCategory(null);

		Long id = this.dao.insertCategory(category);
		this.dao.deleteCategory(id);
		
		Category deletedCategory = this.dao.searchCategoryById(id);
		
		Assert.assertNull(deletedCategory);
	}

	public void testFindAll() {
		Category category1 = new Category();
		Category category2 = new Category();
		
		category1.setId((long) 1);
		category1.setName("Show");
		category1.setCategory(null);
		category1.setId((long) 2);
		category2.setName("Show Rock");
		category2.setCategory(category1);
		
		this.dao.insertCategory(category1);
		this.dao.insertCategory(category2);
		
		List<Category> listCategories = this.dao.searchAllCategory();
		
		Assert.assertEquals(2, listCategories.size());
		Assert.assertEquals(String.valueOf(1), listCategories.get(0).getId());
		Assert.assertEquals("Show", listCategories.get(0).getName());
		Assert.assertEquals(null, listCategories.get(0).getCategory());
		Assert.assertEquals(String.valueOf(2), listCategories.get(1).getId());
		Assert.assertEquals("Show Rock", listCategories.get(1).getName());
		Assert.assertEquals(category1, listCategories.get(1).getCategory());
	}
}
