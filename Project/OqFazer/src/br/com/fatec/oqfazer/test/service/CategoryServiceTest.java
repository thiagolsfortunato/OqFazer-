package br.com.fatec.oqfazer.test.service;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import br.com.fatec.oqfazer.api.dto.CategoryDTO;
import br.com.fatec.oqfazer.test.common.TestScenario;

public class CategoryServiceTest extends TestScenario {
	
	@Test
	public void insertCategoryWithoutChildren(){
		CategoryDTO dto = new CategoryDTO(null, "Sertanejo", (long)1);
		CategoryDTO saved = this.categoryService.insert(dto);
		saved = this.categoryService.searchById(saved.getId());
		
		Assert.assertEquals(new Long(4), saved.getId());
		Assert.assertEquals("Sertanejo", saved.getName());
		Assert.assertEquals(new Long(1), saved.getParentDTO());
		
		Assert.assertEquals(0, saved.getCategoriesChildren().size());
	}
	
	@Test
	public void insertCategoryWithChildren(){
		CategoryDTO dto = new CategoryDTO(null, "Sertanejo", (long)1);
		CategoryDTO saved = this.categoryService.insert(dto);
		saved = this.categoryService.searchById(saved.getId());
		
		CategoryDTO dto1 = new CategoryDTO(null, "Universitario", (long)4);
		CategoryDTO saved1 = this.categoryService.insert(dto1);
		saved1 = this.categoryService.searchById(saved1.getId());
		
		Assert.assertEquals(new Long(5), saved1.getId());
		Assert.assertEquals("Universitario", saved1.getName());
		Assert.assertEquals(new Long(4), saved1.getParentDTO());
		
		Assert.assertEquals(1, saved.getCategoriesChildren().size());
	}
	
	@Test
	public void delete(){
		CategoryDTO dto = this.categoriesDTO.get(1l);
		dto.setCategories(this.getCategories(1l));
		
		CategoryDTO saved = this.categoryService.insert(dto);
		
		this.categoryService.delete(saved.getId());
	}
	
	@Test
	public void update(){
		CategoryDTO dto = this.categoriesDTO.get(1l);
		
		dto.setName("Show");
		
		this.categoryService.update(dto);
		dto = this.categoryService.searchById(dto.getId());
		
		Assert.assertEquals(new Long(1), dto.getId());
		Assert.assertEquals("Show", dto.getName());
		Assert.assertEquals(null, dto.getParentDTO());
	}
	
	@Test
	public void searchAll(){
		CategoryDTO dto = new CategoryDTO(null, "Sertanejo", (long)1);
		CategoryDTO saved = this.categoryService.insert(dto);
		saved = this.categoryService.searchById(saved.getId());
		
		CategoryDTO dto1 = new CategoryDTO(null, "Universitario", (long)4);
		CategoryDTO saved1 = this.categoryService.insert(dto1);
		saved1 = this.categoryService.searchById(saved1.getId());
		
		List<CategoryDTO> categories = this.categoryService.searchAll();
		
		Assert.assertEquals(5, categories.size());
		Assert.assertEquals("Category 1", categories.get(0).getName());
		Assert.assertEquals("Category 2", categories.get(1).getName());
		Assert.assertEquals("Category 3", categories.get(2).getName());
		Assert.assertEquals("Sertanejo", categories.get(3).getName());
		Assert.assertEquals("Universitario", categories.get(4).getName());
	}
}
