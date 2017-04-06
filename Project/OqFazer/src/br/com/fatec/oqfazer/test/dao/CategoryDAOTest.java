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
		category.setParent(null);
		
		Category category1 = new Category();
		category1.setId((long) 2);
		category1.setName("Show de Rock");
		category1.setParent(category.getId());

		Long id = this.dao.insertCategory(category);
		Long id1 = this.dao.insertCategory(category1);
		
		Category savedCategory = this.dao.searchCategoryById(id);
		Category savedCategory1 = this.dao.searchCategoryById(id1);
		

		Assert.assertNotNull(savedCategory);
		Assert.assertEquals(new Long (1), savedCategory.getId());
		Assert.assertEquals("Show", savedCategory.getName());
		Assert.assertEquals(0, savedCategory.getParent().longValue());
		
		Assert.assertNotNull(savedCategory1);
		Assert.assertEquals(new Long (2), savedCategory1.getId());
		Assert.assertEquals("Show de Rock", savedCategory1.getName());
		Assert.assertEquals(new Long (1), savedCategory1.getParent());
	}

	@Test
	public void testUpdate() {
		Category category = new Category();
		category.setId((long) 1);
		category.setName("Show");
		category.setParent(null);

		Long id = this.dao.insertCategory(category);
		Category updateCategory = this.dao.searchCategoryById(id);
		
		updateCategory.setName("Teatro");
		
		this.dao.updateCategory(updateCategory);
		
		Category updatedCatedory = this.dao.searchCategoryById(id);
		
		Assert.assertNotNull(category);
		Assert.assertEquals(new Long (1), updateCategory.getId());
		Assert.assertEquals("Teatro", updatedCatedory.getName());
		Assert.assertEquals(0, updatedCatedory.getParent().longValue());
	}

	@Test
	public void testDelete() {
		Category category = new Category();
		category.setId((long) 1);
		category.setName("Show");
		category.setParent(null);

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
		category1.setParent(null);
		
		category2.setId((long) 2);
		category2.setName("Show Rock");
		category2.setParent(category1.getId());
		
		this.dao.insertCategory(category1);
		this.dao.insertCategory(category2);
		
		List<Category> listCategories = this.dao.searchAllCategory();
		
		Assert.assertEquals(2, listCategories.size());
		Assert.assertEquals(new Long (1), listCategories.get(0).getId());
		Assert.assertEquals("Show", listCategories.get(0).getName());
		Assert.assertEquals(0, listCategories.get(0).getParent().longValue());
		
		Assert.assertEquals(new Long (2), listCategories.get(1).getId());
		Assert.assertEquals("Show Rock", listCategories.get(1).getName());
		Assert.assertEquals(new Long (1), listCategories.get(1).getParent());
	}
}
