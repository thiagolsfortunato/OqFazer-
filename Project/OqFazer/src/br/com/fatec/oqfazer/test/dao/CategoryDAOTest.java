package br.com.fatec.oqfazer.test.dao;

import org.junit.Before;

import br.com.fatec.oqfazer.api.dao.CategoryDAO;
import br.com.fatec.oqfazer.test.common.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class CategoryDAOTest extends TestBase{
	
	private CategoryDAO dao;
	
	@Before
	public void config() {
		this.dao = ImplFinder.getImpl(CategoryDAO.class);
	}
	
	public void testSave(){}
	
	public void testUpdate(){}
	
	public void testDelete(){}
	
	public void testFindAll(){}
}
