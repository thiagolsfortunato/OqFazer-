package br.com.fatec.oqfazer.test.service;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import br.com.fatec.oqfazer.api.dto.CategoryDTO;
import br.com.fatec.oqfazer.test.common.TestScenario;

public class CategoryServiceTest extends TestScenario {
	
	@Test
	public void insertCategoryWithoutChildren(){
		CategoryDTO dto = new CategoryDTO(null, "Sertanejo", null);
		CategoryDTO saved = this.categoryService.insert(dto);
		
		saved = this.categoryService.searchById(saved.getId());
		
		Assert.assertEquals(new Long(4), saved.getId());
		Assert.assertEquals("Sertanejo", saved.getName());
		Assert.assertNull(saved.getParentDTO());
		
		Assert.assertEquals(0, saved.getCategoriesChildren().size());
	}
	
	@Test
	public void insertCategoryWithChildren(){
		CategoryDTO sertanejoDto = new CategoryDTO(null, "Sertanejo", null);
		CategoryDTO sertanejoEntidade = this.categoryService.insert(sertanejoDto);
		
		CategoryDTO uversitarioDto = new CategoryDTO(null, "Universitario", sertanejoEntidade);
		CategoryDTO universitarioEntidade = this.categoryService.insert(uversitarioDto);
		
		universitarioEntidade = this.categoryService.searchById(universitarioEntidade.getId());
		sertanejoEntidade = this.categoryService.searchById(sertanejoEntidade.getId());
		
		Assert.assertEquals(1, sertanejoEntidade.getCategoriesChildren().size());
		Assert.assertEquals(new Long(5), universitarioEntidade.getId());
		Assert.assertEquals("Universitario", universitarioEntidade.getName());
		Assert.assertEquals(new Long(4), universitarioEntidade.getParentDTO().getId());
	}
	
	@Test
	public void deleteWithoutChildren(){
		CategoryDTO dto = new CategoryDTO(null, "Sertanejo", null);
		CategoryDTO saved = this.categoryService.insert(dto);
		saved = this.categoryService.searchById(saved.getId());
		//CategoryDTO dto = this.categoriesDTO.get(3l);
		//dto.setCategories(this.getCategories(3l));
		//CategoryDTO saved = this.categoryService.insert(dto);
		if(saved.getCategoriesChildren()==null){
			this.categoryService.delete(saved.getId());
		} else {
			System.out.println("Não é possível deletar, a categoria possui filhos!");
		}
		Assert.assertEquals(0, saved.getCategoriesChildren().size());
	}
	
	@Test
	public void deleteWithChildren(){
		CategoryDTO dto = this.categoriesDTO.get(2l);
		
		if(dto.getCategoriesChildren()!=null){
			System.out.println("Não é possível deletar, a categoria possui filhos!");
		} else {
			this.categoryService.delete(dto.getId());
		}
		Assert.assertEquals(3, this.categoriesDTO.size());
	}
	
	@Test
	public void update(){
		CategoryDTO dto = this.categoriesDTO.get(1l);
		
		dto.setId(new Long(1));
		dto.setName("Show");
		dto.setParentDTO(null);
		
		this.categoryService.update(dto);
		dto = this.categoryService.searchById(dto.getId());
		
		Assert.assertEquals(new Long(1), dto.getId());
		Assert.assertEquals("Show", dto.getName());
		Assert.assertNull(dto.getParentDTO());
	}
	
	@Test
	public void searchAll(){
		CategoryDTO dto = new CategoryDTO(null, "Sertanejo", null);
		CategoryDTO saved = this.categoryService.insert(dto);
		saved = this.categoryService.searchById(saved.getId());
		
		CategoryDTO dto1 = new CategoryDTO(null, "Universitario", dto);
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
