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
		
		Category category1 = new Category();
		category1.setId((long) 2);
		category1.setName("Show de Rock");
		category1.setCategory(category);

		Long id = this.dao.insertCategory(category);
		Long id1 = this.dao.insertCategory(category1);
		
		Category savedCategory = this.dao.searchCategoryById(id);
		Category savedCategory1 = this.dao.searchCategoryById(id1);
		

		Assert.assertNotNull(savedCategory);
		Assert.assertEquals(new Long (1), savedCategory.getId());
		Assert.assertEquals("Show", savedCategory.getName());
		Assert.assertEquals(null, savedCategory.getCategory());
		
		Assert.assertNotNull(savedCategory);
		Assert.assertEquals(new Long (2), savedCategory1.getId());
		Assert.assertEquals("Show de Rock", savedCategory1.getName());
		Assert.assertEquals("Show", savedCategory1.getCategory().getName());
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
		Assert.assertEquals(new Long (1), updateCategory.getId());
		Assert.assertEquals("Teatro", updatedCatedory.getName());
		Assert.assertEquals(null, updatedCatedory.getCategory());
	}

	@Test
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

	@Test
	public void testFindAll() {
		Category category1 = new Category();
		Category category2 = new Category();
		
		category1.setId((long) 1);
		category1.setName("Show");
		category1.setCategory(null);
		
		category2.setId((long) 2);
		category2.setName("Show Rock");
		category2.setCategory(category1);
		
		Long id1 = this.dao.insertCategory(category1);
		Long id2 = this.dao.insertCategory(category2);
		
		List<Category> listCategories = this.dao.searchAllCategory();
		
		Assert.assertEquals(2, listCategories.size());
		Assert.assertEquals(new Long (1), listCategories.get(0).getId());
		Assert.assertEquals("Show", listCategories.get(0).getName());
		Assert.assertEquals(null, listCategories.get(0).getCategory());
		
		Assert.assertEquals(new Long (2), listCategories.get(1).getId());
		Assert.assertEquals("Show Rock", listCategories.get(1).getName());
		Assert.assertEquals("Show", listCategories.get(1).getCategory().getName());
	}
}
