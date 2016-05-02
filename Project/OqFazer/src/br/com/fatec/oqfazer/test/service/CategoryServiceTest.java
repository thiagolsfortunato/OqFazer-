package br.com.fatec.oqfazer.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import br.com.fatec.oqfazer.api.dto.CategoryDTO;
import br.com.fatec.oqfazer.test.common.TestScenario;

public class CategoryServiceTest extends TestScenario {
	
	@Test
	public void insert(){
		CategoryDTO dto = new CategoryDTO(null, "Sertanejo", (long)1);
		CategoryDTO saved = this.categoryService.insert(dto);
		saved = this.categoryService.searchById(saved.getId());
		
		Assert.assertEquals(new Long(4), saved.getId());
		Assert.assertEquals("Sertanejo", saved.getName());
		Assert.assertEquals(new Long(1), saved.getParentDTO());

		Assert.assertEquals(1, saved.getCategories());
		Assert.assertEquals(new Long(1L), saved.getCategories().get(0).getId());

		Assert.assertEquals(2, saved.getCategoriesChildren().size());
		ArrayList<CategoryDTO> categoriesChildren = Lists.newArrayList(saved.getCategoriesChildren());
		Assert.assertEquals(new Long(1), categoriesChildren.get(0).getId());
		Assert.assertEquals(new Long(2), categoriesChildren.get(1).getId());
	}
	
	
	public void delete(){
		CategoryDTO dto = this.categoriesDTO.get(1l);
		dto.setCategories(this.getCategories(1l));
		
		CategoryDTO saved = this.categoryService.insert(dto);
		
		this.categoryService.delete(saved.getId());
	}
	
	
	public void update(){
		CategoryDTO dto = this.categoriesDTO.get(1l);
		
		dto.setName("Show");
		dto.setCategories(null);
		dto.setCategories(this.getCategories(2l,3l));
		
		this.categoryService.update(dto);
		dto = this.categoryService.searchById(dto.getId());
		
		Assert.assertEquals(new Long(1), dto.getId());
		Assert.assertEquals("show", dto.getName());
		Assert.assertEquals(null, dto.getParentDTO());
	}
	
	
	public void searchAll(){
		List<CategoryDTO> categories = this.categoryService.searchAll();
		
		Assert.assertEquals(2, categories.size());
		Assert.assertEquals("Show", categories.get(0).getName());
		Assert.assertEquals("Rock", categories.get(1).getName());
	}
}
