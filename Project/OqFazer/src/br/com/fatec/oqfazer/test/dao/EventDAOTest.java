package br.com.fatec.oqfazer.test.dao;

import org.junit.Before;
import org.junit.Test;

import br.com.fatec.oqfazer.api.dao.CategoryDAO;
import br.com.fatec.oqfazer.api.dao.EventDAO;
import br.com.fatec.oqfazer.api.dao.UserDAO;
import br.com.fatec.oqfazer.api.entity.Category;
import br.com.fatec.oqfazer.api.entity.Event;
import br.com.fatec.oqfazer.api.entity.User;
import br.com.fatec.oqfazer.test.common.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class EventDAOTest extends TestBase {
	
	private EventDAO eventDAO;
	private UserDAO userDAO;
	private CategoryDAO categoryDAO;
	
	@Before
	public void config() {
		this.eventDAO = ImplFinder.getImpl(EventDAO.class);
		this.userDAO = ImplFinder.getImpl(UserDAO.class);
		this.categoryDAO = ImplFinder.getImpl(CategoryDAO.class);
		
		User user = new User();
		user.setName("User 1");
		user.setEmail("user1@test");
		user.setPassword("user1");
		user.setPhone(11111);
		
		User user2 = new User();
		user2.setName("User 2");
		user2.setEmail("user2@test");
		user2.setPassword("user2");
		user2.setPhone(22222);
		
		User user3 = new User();
		user3.setName("User 3");
		user3.setEmail("user3@test");
		user3.setPassword("user3");
		user3.setPhone(3333);
		
		Category category1 = new Category();
		category1.setName("Show");
		category1.setCategory(null);
		
		Category category2 = new Category();
		category2.setName("Show Rock");
		category2.setCategory(category1);
	}
	
	@Test
	public void testSave() {
		Event event = new Event();
		event.setName("");
	}
	
	@Test
	public void testUpdate() {
		
	}
	
	@Test
	public void testDelete() {
		
	}
	
	@Test
	public void testsearchAll() {
		
	}
	
}
