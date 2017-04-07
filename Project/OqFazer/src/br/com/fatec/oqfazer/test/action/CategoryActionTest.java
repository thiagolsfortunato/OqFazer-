package br.com.fatec.oqfazer.test.action;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.fatec.oqfazer.api.dto.CategoryDTO;
import br.com.fatec.oqfazer.test.common.TestScenario;
import br.com.fatec.oqfazer.web.action.CategoryAction;
import br.com.fatec.oqfazer.web.context.ContextCategory;

public class CategoryActionTest<Category> extends TestScenario {

	private CategoryAction action = new CategoryAction();
	private CategoryDTO dto = new CategoryDTO();
	private ContextCategory context = new ContextCategory(); 
	
	@Before
	public void loadContext(){
		buildCategory();
		context.setCategory(dto);
		action.setContext(context);
	}
	
	@Test
	public void seachAllTest(){		
		String searchAll = action.searchAll();
		assertEquals(false,searchAll.isEmpty());
	}
	
	@Test
	public void insertTest(){
		action.insert();	
		CategoryDTO result = context.getCategory();	
		assertEquals(dto, result);
	}
	
	@Test
	public void updateTest(){
		action.insert();	
		CategoryDTO result = context.getCategory();
		
		result.setName("categoria2");
		context.setCategory(result);
		action.setContext(context);
		
		action.insert();
		
		assertEquals("categoria2", result.getName());
	}
	
	@Test 
	public void deleteTest(){
		action.delete();
		assertEquals(2, context.getCategories().size());
	}
	
	
	private void buildCategory(){
		dto.setId((long)1);
		dto.setName("categoria1");
		dto.setParentDTO(null);
	}
}
